/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.controladores.PedidosControlador;
import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOPedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.modelos.Observacion;
import com.modelos.Pedido;
import com.daoInterfaces.DAOObservacion;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Articulo;
import com.modelos.Filtro;
import com.modelos.PedidoPK;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class DAOPedidoImpl extends ConexionBD implements DAOPedido {

    DAOArticulo daoArticulo;
    DAOObservacion daoObservacion;

    public DAOPedidoImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
        daoArticulo = PedidosControlador.getDaoArticulo();
        daoObservacion = PedidosControlador.getDaoObservacion();
    }

    @Override
    public List<Pedido> listar(List<Articulo> articulos) throws SQLException {
        return listarPedidos(articulos);
    }

    @Override
    public List<Pedido> listar(String atributo, String valor) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        if (daoArticulo != null) {
            articulos = daoArticulo.listar(atributo, valor);
        }
        return listarPedidos(articulos);
    }

    /**
     * Devuele una lista de pedidos de la base de datos en función de los
     * filtros seleccionados
     *
     * @param filtro
     * @return Pedidos
     * @throws java.sql.SQLException
     */
    @Override
    public List<Pedido> listar(Filtro filtro) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        if (daoArticulo != null) {
            articulos = daoArticulo.listar(filtro);
        }
        return listarPedidos(articulos);
    }

    private List<Pedido> listarPedidos(List<Articulo> articulos) throws SQLException {
        List<String> idPedidos = new ArrayList<>();
        List<String> marketplace = new ArrayList<>();
        Pedido pedido;
        List<Observacion> observaciones;
        List<Pedido> pedidos = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet result;
        int indice = 1;
        if (!articulos.isEmpty()) {
            try {
                this.openConnection();
                observaciones = daoObservacion.listar(articulos);
                pstm = this.getConnection().prepareStatement(Consultas.obtenerConsultaPedidos(articulos));
                for (Articulo articulo : articulos) {
                    if (!marketplace.contains(articulo.getMarketplace())) {
                        marketplace.add(articulo.getMarketplace());
                    }
                    if (!idPedidos.contains(articulo.getIdPedido())) {
                        idPedidos.add(articulo.getIdPedido());
                    }
                }
                for (String market : marketplace) {
                    pstm.setString(indice, market);
                    indice++;
                }
                for (String idPedido : idPedidos) {
                    pstm.setString(indice, idPedido);
                    indice++;
                }
                result = pstm.executeQuery();
                while (result.next()) {
                    pedido = new Pedido();
                    pedido.setTienda(result.getString("tienda"));
                    pedido.setMarketplace(result.getString("marketplace"));
                    pedido.setIdPedido(result.getString("idPedido"));
                    pedido.setFechaPedido(result.getTimestamp("fechaPedido"));
                    pedido.setDni(result.getString("dni"));
                    pedido.setNombreApellidos(result.getString("nombreApellidos"));
                    pedido.setDireccion(result.getString("direccion"));
                    pedido.setCp(result.getString("cp"));
                    pedido.setPoblacion(result.getString("poblacion"));
                    pedido.setProvincia(result.getString("provincia"));
                    pedido.setTelefono(result.getString("telefono"));
                    pedido.setEmail(result.getString("email"));
                    pedido.setImporte(result.getFloat("importe"));
                    pedido.setComision(result.getFloat("comision"));
                    pedido.setCostePorte(result.getFloat("costePorte"));
                    for (Articulo articulo : articulos) {
                        if (articulo.getMarketplace().equalsIgnoreCase(pedido.getMarketplace())
                                & articulo.getIdPedido().equalsIgnoreCase(pedido.getIdPedido())) {
                            pedido.NuevoArticulo(articulo);
                        }
                    }
                    for (Observacion observacion : observaciones) {
                        if (observacion.getMarketplace().equalsIgnoreCase(pedido.getMarketplace())
                                & observacion.getIdPedido().equalsIgnoreCase(pedido.getIdPedido())) {
                            pedido.NuevaObservacion(observacion);
                        }
                    }
                    pedidos.add(pedido);
                }
                result.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                this.closeConnection();
            }
        }
        return pedidos;
    }

    @Override
    public Pedido obtener(String marketplace, String idPedido) throws SQLException {
        Pedido pedido = new Pedido();
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    Consultas.obtenerConsultaObtenerPedidos(marketplace, idPedido));
            pstm.setString(1, marketplace);
            pstm.setString(2, idPedido);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                pedido.setTienda(result.getString("tienda"));
                pedido.setMarketplace(result.getString("marketplace"));
                pedido.setIdPedido(result.getString("idPedido"));
                pedido.setFechaPedido(result.getTimestamp("fechaPedido"));
                pedido.setDni(result.getString("dni"));
                pedido.setNombreApellidos(result.getString("nombreApellidos"));
                pedido.setDireccion(result.getString("direccion"));
                pedido.setCp(result.getString("cp"));
                pedido.setPoblacion(result.getString("poblacion"));
                pedido.setProvincia(result.getString("provincia"));
                pedido.setTelefono(result.getString("telefono"));
                pedido.setEmail(result.getString("email"));
                pedido.setImporte(result.getFloat("importe"));
                pedido.setComision(result.getFloat("comision"));
                pedido.setCostePorte(result.getFloat("costePorte"));
                // pedido.setArticulos(daoArticulo.obtener(marketplace, idPedido));
                // pedido.setObservaciones(daoObservacion.obtener(marketplace, idPedido));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return pedido;
    }

    /**
     * Registra un pedido en la base de datos
     *
     * @param pedido Pedido a registrar
     * @throws java.sql.SQLException
     */
    @Override
    public void registrar(Pedido pedido) throws SQLException {
        try {
            this.openConnection();
            this.getConnection().setAutoCommit(false);
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Pedidos (tienda, marketplace, idPedido, "
                    + "fechaPedido, dni, nombreApellidos, direccion, cp, "
                    + "poblacion, provincia, telefono, email, importe, comision, "
                    + "costePorte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?)");
            pstm.setString(1, pedido.getTienda());
            pstm.setString(2, pedido.getMarketplace());
            pstm.setString(3, pedido.getIdPedido());
            pstm.setTimestamp(4, pedido.getFechaPedido());
            pstm.setString(5, pedido.getDni());
            pstm.setString(6, pedido.getNombreApellidos());
            pstm.setString(7, pedido.getDireccion());
            pstm.setString(8, pedido.getCp());
            pstm.setString(9, pedido.getPoblacion());
            pstm.setString(10, pedido.getProvincia());
            pstm.setString(11, pedido.getTelefono());
            pstm.setString(12, pedido.getEmail());
            pstm.setFloat(13, pedido.getImporte());
            pstm.setFloat(14, pedido.getComision());
            pstm.setFloat(15, pedido.getCostePorte());
            pstm.executeUpdate();
            daoArticulo.registrar(pedido.getArticulos(), this.getConnection());
            if (!pedido.getObservaciones().isEmpty()) {
                daoObservacion.registrar(pedido.getObservaciones(), this.getConnection());
            }
            this.getConnection().commit();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Registra una lista de pedidos en la base de datos
     *
     * @param pedidos
     * @throws SQLException
     */
    @Override
    public void registrar(List<Pedido> pedidos) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        List<Observacion> observaciones = new ArrayList<>();
        try {
            this.openConnection();
            this.getConnection().setAutoCommit(false);
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Pedidos (tienda, marketplace, idPedido, "
                    + "fechaPedido, dni, nombreApellidos, direccion, cp, "
                    + "poblacion, provincia, telefono, email, importe, comision, "
                    + "costePorte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?)");
            for (Pedido pedido : pedidos) {
                pstm.setString(1, pedido.getTienda());
                pstm.setString(2, pedido.getMarketplace());
                pstm.setString(3, pedido.getIdPedido());
                pstm.setTimestamp(4, pedido.getFechaPedido());
                pstm.setString(5, pedido.getDni());
                pstm.setString(6, pedido.getNombreApellidos());
                pstm.setString(7, pedido.getDireccion());
                pstm.setString(8, pedido.getCp());
                pstm.setString(9, pedido.getPoblacion());
                pstm.setString(10, pedido.getProvincia());
                pstm.setString(11, pedido.getTelefono());
                pstm.setString(12, pedido.getEmail());
                pstm.setFloat(13, pedido.getImporte());
                pstm.setFloat(14, pedido.getComision());
                pstm.setFloat(15, pedido.getCostePorte());
                pstm.executeUpdate();
                articulos.addAll(pedido.getArticulos());
                if (!pedido.getObservaciones().isEmpty()) {
                    observaciones.addAll(pedido.getObservaciones());
                }
            }
            daoArticulo.registrar(articulos, this.getConnection());
            daoObservacion.registrar(observaciones, this.getConnection());
            this.getConnection().commit();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Modifica el pedido que se pasa por parámetro
     *
     * @param pedido
     * @throws SQLException
     */
    @Override
    public void modificar(Pedido pedido) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                UPDATE 
                    Pedidos
                SET 
                    pedidos.tienda  = ?, 
                    pedidos.fechaPedido = ?, 
                    pedidos.dni = ?, 
                    pedidos.nombreApellidos = ?, 
                    pedidos.direccion = ?, 
                    pedidos.cp = ?, 
                    pedidos.poblacion = ?, 
                    pedidos.provincia = ?, 
                    pedidos.telefono = ?, 
                    pedidos.email = ?, 
                    pedidos.importe = ?, 
                    pedidos.comision = ?, 
                    pedidos.costePorte = ? 
                WHERE 
                    pedidos.marketplace = ? AND 
                    pedidos.idPedido = ?
                """);
            pstm.setString(1, pedido.getTienda());
            pstm.setTimestamp(2, pedido.getFechaPedido());
            pstm.setString(3, pedido.getDni());
            pstm.setString(4, pedido.getNombreApellidos());
            pstm.setString(5, pedido.getDireccion());
            pstm.setString(6, pedido.getCp());
            pstm.setString(7, pedido.getPoblacion());
            pstm.setString(8, pedido.getProvincia());
            pstm.setString(9, pedido.getTelefono());
            pstm.setString(10, pedido.getEmail());
            pstm.setFloat(11, pedido.getImporte());
            pstm.setFloat(12, pedido.getComision());
            pstm.setFloat(13, pedido.getCostePorte());
            pstm.setString(14, pedido.getMarketplace());
            pstm.setString(15, pedido.getIdPedido());
            pstm.executeUpdate();
            // Elimina todos los artículos del pedido.
            // Esto se hace parar poder registrar a posterior los artículos modificados o nuevos, y eliminar los que se han borrado en la edición
            daoArticulo.eliminar(pedido.getMarketplace(), pedido.getIdPedido());
            // Registra de nuevo los artículos modificados y nuevos
            daoArticulo.registrar(pedido.getArticulos(), this.getConnection());
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Elimina un pedido de la base de datos
     *
     * @param pedido Pedido a eliminar
     * @throws java.sql.SQLException
     */
    @Override
    public void eliminar(Pedido pedido) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("DELETE "
                    + "FROM pedidos WHERE marketplace = ? AND idPedido = ?");
            pstm.setString(1, pedido.getMarketplace());
            pstm.setString(2, pedido.getIdPedido());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Devuelve los valores de la clave primaria de la tabla pedidos. Cuando se
     * vayan a añadir nuevos pedidos, se comprobará que no estén registrados.
     *
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public List<PedidoPK> listarPK() throws SQLException {
        List<PedidoPK> pedidosPK = new ArrayList<>();
        PedidoPK pedidoPK;
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT marketplace, idPedido FROM Pedidos");
            while (result.next()) {
                pedidoPK = new PedidoPK();
                pedidoPK.setIdPedido(result.getString("idPedido"));
                pedidoPK.setMarketplace(result.getString("marketplace"));
                pedidosPK.add(pedidoPK);
            }
            result.close();
            st.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return pedidosPK;
    }

    @Override
    public List<String> listarTiendas() throws SQLException {
        List<String> lista = new ArrayList<>();
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT DISTINCT tienda FROM Pedidos ORDER BY marketplace");
            while (result.next()) {
                lista.add(result.getString(1));
            }
            result.close();
            st.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public List<String> listarMarket() throws SQLException {
        List<String> lista = new ArrayList<>();
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT DISTINCT marketplace FROM Pedidos ORDER BY marketplace");
            while (result.next()) {
                lista.add(result.getString(1));
            }
            result.close();
            st.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return lista;
    }
}
