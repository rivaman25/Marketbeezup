/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import daoInterfaces.DAOArticulo;
import daoInterfaces.DAOPedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Observacion;
import modelos.Pedido;
import daoInterfaces.DAOInterfazLista;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Articulo;
import modelos.Filtro;
import modelos.PedidoPK;

/**
 *
 * @author Manolo
 */
public class DAOPedidoImpl extends ConexionBD implements DAOPedido {

    public DAOPedidoImpl() {
    }

    public DAOPedidoImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    /**
     * Devuelve una cadena con los símbolos de los parámetros para la consulta
     * preparada en función del número de elementos de la lista
     *
     * @param lista
     * @return
     */
    private String obtenerParametros(List<String> lista) {
        StringBuilder parametros = new StringBuilder();
        if (!lista.isEmpty()) {
            parametros.append("?");
            for (int i = 1; i < lista.size(); i++) {
                parametros.append(", ?");
            }
        }
        return parametros.toString();
    }

    /**
     * Devuele una lista de pedidos de la base de datos en función de los
     * filtros seleccionados
     *
     * @param filtro
     * @return Pedidos
     * @throws Exception
     */
    @Override
    public List<Pedido> listar(Filtro filtro) throws Exception {
        Pedido pedido;
        List<Articulo> articulos;
        List<Observacion> observaciones;
        DAOArticulo daoArticulo;
        DAOInterfazLista<Observacion> daoObservacion;
        List<Pedido> pedidos = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet result;
        int indice = 1;
        try {
            this.openConnection();
            this.getConnection().setAutoCommit(false);
            daoObservacion = new DAOObservacionImpl();
            daoArticulo = new DAOArticuloImpl();
            articulos = daoArticulo.listar(new Pedido(), filtro, this.getConnection());
            // observaciones = daoObservaciones.listar(filtro);
            pstm = this.getConnection().prepareStatement(obtenerConsulta(filtro));
            // Se añaden los parámetros en función de los filtros almacenados
            if (filtro.getMarketplace() != null) {
                for (String market : Filtro.obtenerMarketsFiltrados(filtro.getMarketplace())) {
                    pstm.setString(indice, market);
                    indice++;
                }
            }
            if (filtro.getTiendas() != null) {
                for (String tienda : Filtro.obtenerTiendasFiltradas(filtro.getTiendas())) {
                    pstm.setString(indice, tienda);
                    indice++;
                }
            }
            if (filtro.getFechaPedidoDesde() != null) {
                pstm.setDate(indice, filtro.getFechaPedidoDesde());
                indice++;
            }
            if (filtro.getFechaPedidoHasta() != null) {
                pstm.setDate(indice, filtro.getFechaPedidoHasta());
                indice++;
            }
            if (filtro.getEstados() != null) {
                for (String estado : Filtro.obtenerEstadosFiltrados(filtro.getEstados())) {
                    pstm.setString(indice, estado);
                    indice++;
                }
            }
            if (filtro.getFechaSalidaDesde() != null) {
                pstm.setDate(indice, filtro.getFechaSalidaDesde());
                indice++;
            }
            if (filtro.getFechaSalidaHasta() != null) {
                pstm.setDate(indice, filtro.getFechaSalidaHasta());
                indice++;
            }
            if (filtro.getAlmacenes() != null) {
                for (String almacen : Filtro.obtenerAlmacenesFiltrados(filtro.getAlmacenes())) {
                    pstm.setString(indice, almacen);
                    indice++;
                }
            }
            if (filtro.getAgencias() != null) {
                for (String agencia : Filtro.obtenerAgenciasFiltradas(filtro.getAgencias())) {
                    pstm.setString(indice, agencia);
                    indice++;
                }
            }
            if (filtro.getFechaCompraDesde() != null) {
                pstm.setDate(indice, filtro.getFechaCompraDesde());
                indice++;
            }
            if (filtro.getFechaCompraHasta() != null) {
                pstm.setDate(indice, filtro.getFechaCompraHasta());
                indice++;
            }
            if (filtro.getFechaVentaDesde() != null) {
                pstm.setDate(indice, filtro.getFechaVentaDesde());
                indice++;
            }
            if (filtro.getFechaVentaHasta() != null) {
                pstm.setDate(indice, filtro.getFechaVentaHasta());
                indice++;
            }
            if (filtro.getFechaAlbaranDesde() != null) {
                pstm.setDate(indice, filtro.getFechaAlbaranDesde());
                indice++;
            }
            if (filtro.getFechaAlbaranHasta() != null) {
                pstm.setDate(indice, filtro.getFechaAlbaranHasta());
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
                    if (articulo.getMarketplace().equalsIgnoreCase(pedido.getMarketplace()) &
                            articulo.getIdPedido().equalsIgnoreCase(pedido.getIdPedido())) {
                        pedido.NuevoArticulo(articulo);
                    }
                }
                /*for (Observacion observacion : observaciones) {
                    if (observacion.getMarketplace().equalsIgnoreCase(pedido.getMarketplace()) &
                            observacion.getIdPedido().equalsIgnoreCase(pedido.getIdPedido())) {
                        pedido.NuevaObservacion(observacion);
                    }
                }*/
                pedidos.add(pedido);
            }
            this.getConnection().commit();
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
        return pedidos;
    }

    /**
     * Registra un pedido en la base de datos
     *
     * @param pedido Pedido a registrar
     * @throws Exception
     */
    @Override
    public void registrar(Pedido pedido) throws Exception {
        DAOArticulo daoArticulo;
        DAOInterfazLista<Observacion> daoObservacion;
        try {
            this.openConnection();
            this.getConnection().setAutoCommit(false);
            daoArticulo = new DAOArticuloImpl();
            daoObservacion = new DAOObservacionImpl();
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
            Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void registrar(List<Pedido> pedidos) throws Exception {
        DAOArticulo daoArticulo;
        DAOInterfazLista<Observacion> daoObservacion;
        try {
            this.openConnection();
            this.getConnection().setAutoCommit(false);
            daoArticulo = new DAOArticuloImpl();
            daoObservacion = new DAOObservacionImpl();
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
                daoArticulo.registrar(pedido.getArticulos(), this.getConnection());
                if (!pedido.getObservaciones().isEmpty()) {
                    daoObservacion.registrar(pedido.getObservaciones(), this.getConnection());
                }
            }
            this.getConnection().commit();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void modificar(Pedido pedido) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Pedidos SET tienda = ?, fechaPedido = ? WHERE "
                    + "marketplace = ? AND idPedido = ?");
            pstm.setString(1, pedido.getTienda());
            pstm.setTimestamp(2, pedido.getFechaPedido());
            pstm.setString(3, pedido.getMarketplace());
            pstm.setString(4, pedido.getIdPedido());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Elimina un pedido de la base de datos
     *
     * @param pedido Pedido a eliminar
     * @throws Exception
     */
    @Override
    public void eliminar(Pedido pedido) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("DELETE "
                    + "FROM pedido WHERE marketplace = ? AND idPedido = ?");
            pstm.setString(1, pedido.getMarketplace());
            pstm.setString(2, pedido.getIdPedido());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedidoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Devuelve los valores de la clave primaria de la tabla pedidos. Se usará
     * para cuando se vayan a añadir nuevos pedidos, comprobar que no estén
     * registrados
     *
     * @return
     */
    @Override
    public List<PedidoPK> listarPK() {
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
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
        return pedidosPK;
    }

    @Override
    public List<String> listarTiendas() throws Exception {
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

        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public List<String> listarMarket() throws Exception {
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

        } finally {
            this.closeConnection();
        }
        return lista;
    }

    /**
     * Obtiene la consulta SQL en función del filtro seleccionado
     *
     * @param filtro
     * @return
     */
    private String obtenerConsulta(Filtro filtro) {
        StringBuilder consulta = new StringBuilder();
        StringBuilder predicadoAux;
        List<String> predicados = new ArrayList<>();
        if (filtro.isObservaciones()) { // Registros con observaciones almacenadas
            consulta.append("""
                        SELECT 
                            pedidos.tienda,
                            pedidos.marketplace,
                            pedidos.idPedido,
                            pedidos.fechaPedido,
                            pedidos.dni,
                            pedidos.nombreApellidos,
                            pedidos.direccion,
                            pedidos.cp,
                            pedidos.poblacion,
                            pedidos.provincia,
                            pedidos.telefono,
                            pedidos.email,
                            pedidos.importe,
                            pedidos.comision,
                            pedidos.costePorte
                        FROM
                            pedidos
                                NATURAL JOIN
                            articulos
                                INNER JOIN
                            observaciones ON (pedidos.idPedido = observaciones.idPedido
                                AND pedidos.marketplace = observaciones.marketplace)
                                LEFT OUTER JOIN
                            envios ON (articulos.idPedido = envios.idPedido
                                AND articulos.marketplace = envios.marketplace
                                AND articulos.codigoArticulo = envios.codigoArticulo)
                                LEFT OUTER JOIN
                            compras ON (articulos.idPedido = compras.idPedido
                                AND articulos.marketplace = compras.marketplace
                                AND articulos.codigoArticulo = compras.codigoArticulo)
                                LEFT OUTER JOIN
                            documentosVenta ON (articulos.idPedido = documentosVenta.idPedido
                                AND articulos.marketplace = documentosVenta.marketplace
                                AND articulos.codigoArticulo = documentosVenta.codigoArticulo)
                                LEFT OUTER JOIN
                            albaranesVenta ON (articulos.idPedido = albaranesVenta.idPedido
                                AND articulos.marketplace = albaranesVenta.marketplace
                                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)""");
        } else { // Registros con y sin observaciones registradas
            consulta.append("""
                        SELECT 
                            pedidos.tienda,
                            pedidos.marketplace,
                            pedidos.idPedido,
                            pedidos.fechaPedido,
                            pedidos.dni,
                            pedidos.nombreApellidos,
                            pedidos.direccion,
                            pedidos.cp,
                            pedidos.poblacion,
                            pedidos.provincia,
                            pedidos.telefono,
                            pedidos.email,
                            pedidos.importe,
                            pedidos.comision,
                            pedidos.costePorte
                        FROM
                            pedidos
                                NATURAL JOIN
                            articulos
                                LEFT OUTER JOIN
                            observaciones ON (pedidos.idPedido = observaciones.idPedido
                                AND pedidos.marketplace = observaciones.marketplace)
                                LEFT OUTER JOIN
                            envios ON (articulos.idPedido = envios.idPedido
                                AND articulos.marketplace = envios.marketplace
                                AND articulos.codigoArticulo = envios.codigoArticulo)
                                LEFT OUTER JOIN
                            compras ON (articulos.idPedido = compras.idPedido
                                AND articulos.marketplace = compras.marketplace
                                AND articulos.codigoArticulo = compras.codigoArticulo)
                                LEFT OUTER JOIN
                            documentosVenta ON (articulos.idPedido = documentosVenta.idPedido
                                AND articulos.marketplace = documentosVenta.marketplace
                                AND articulos.codigoArticulo = documentosVenta.codigoArticulo)
                                LEFT OUTER JOIN
                            albaranesVenta ON (articulos.idPedido = albaranesVenta.idPedido
                                AND articulos.marketplace = albaranesVenta.marketplace
                                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)""");
        }
        if (filtro.getMarketplace() != null) { // Si hay market seleccionado se añade el predicado correspondiente
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.marketplace IN (?");
            for (int i = 1; i < filtro.getMarketplace().length; i++) {
                predicadoAux.append(", ?"); // Se añaden tantos parámetros como nº de market
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getTiendas() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.tienda IN (?");
            for (int i = 1; i < filtro.getTiendas().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getFechaPedidoDesde() != null) {
            predicados.add(" pedidos.fechaPedido >= ?");
        }
        if (filtro.getFechaPedidoHasta() != null) {
            predicados.add(" pedidos.fechaPedido <= ?");
        }
        if (filtro.getEstados() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" articulos.estado IN (?");
            for (int i = 1; i < filtro.getEstados().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.isExiste()) {
            predicados.add((" (articulos.tipoArticulo <> 'EXISTE' OR articulos.tipoArticulo IS NULL)"));
        }
        if (filtro.getFechaSalidaDesde() != null) {
            predicados.add(" envios.fechaSalida >= ?");
        }
        if (filtro.getFechaSalidaHasta() != null) {
            predicados.add(" envios.fechaSalida <= ?");
        }
        if (filtro.getAlmacenes() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAlmacen IN (?");
            for (int i = 1; i < filtro.getAlmacenes().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getAgencias() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAgencia IN (?");
            for (int i = 1; i < filtro.getAgencias().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getFechaCompraDesde() != null) {
            predicados.add(" compras.fechaCompra >= ?");
        }
        if (filtro.getFechaCompraHasta() != null) {
            predicados.add(" compras.fechaCompra <= ?");
        }
        if (filtro.getFechaVentaDesde() != null) {
            predicados.add(" documentosVenta.fechaVenta >= ?");
        }
        if (filtro.getFechaVentaHasta() != null) {
            predicados.add(" documentosVenta.fechaVenta <= ?");
        }
        if (filtro.getFechaAlbaranDesde() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran >= ?");
        }
        if (filtro.getFechaAlbaranHasta() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran <= ?");
        }
        if (!predicados.isEmpty()) {
            consulta.append(" WHERE");
            for (String predicado : predicados) {
                consulta.append(predicado);
                consulta.append(" AND");
            }
            consulta.delete(consulta.length() - 4, consulta.length());
        }
        consulta.append(" ORDER BY pedidos.fechaPedido DESC");
        return consulta.toString();
    }
}
