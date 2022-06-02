/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.controladores.PedidosControlador;
import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOEnvio;
import com.daoInterfaces.DAOInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.AlbaranVenta;
import com.modelos.Articulo;
import com.modelos.Compra;
import com.modelos.DocumentoVenta;
import com.modelos.Filtro;

/**
 *
 * @author Manolo
 */
public class DAOArticuloImpl extends ConexionBD implements DAOArticulo {

    DAOEnvio daoEnvio;
    DAOInterfaz<Compra> daoCompra;
    DAOInterfaz<DocumentoVenta> daoDocumentoVenta;
    DAOInterfaz<AlbaranVenta> daoAlbaranVenta;

    public DAOArticuloImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
        daoEnvio = PedidosControlador.getDaoEnvio();
        daoCompra = PedidosControlador.getDaoCompra();
        daoDocumentoVenta = PedidosControlador.getDaoDocumentoVenta();
        daoAlbaranVenta = PedidosControlador.getDaoAlbaranVenta();
    }

    @Override
    public List<Articulo> listar(String idPedido, java.sql.Date fechaSalida,
            List<String> agencias, boolean reimprimir) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        Articulo articulo;
        int indice = 1;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(Consultas.obtenerConsultaImprimirAlbaranes(idPedido, fechaSalida, agencias, reimprimir));
            if (idPedido != null) {
                pstm.setString(indice, idPedido);
                indice++;
            }
            if (fechaSalida != null) {
                pstm.setDate(indice, fechaSalida);
                indice++;
            }
            for (String agencia : agencias) {
                pstm.setString(indice, agencia);
                indice++;
            }
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                articulo = new Articulo();
                articulo.setCodigoArticulo(result.getString("codigoArticulo"));
                articulo.setDescripcion(result.getString("descripcion"));
                articulo.setPrecio(result.getFloat("precio"));
                articulo.setCantidad(result.getInt("cantidad"));
                articulo.setEstado(result.getString("estado"));
                articulo.setPuc(result.getFloat("puc"));
                articulo.setTipoArticulo(result.getString("tipoArticulo"));
                articulo.setFechaHoraImpr(result.getTimestamp("fechaHoraImpr"));
                articulo.setFamilia(result.getString("idFamilia"));
                articulo.setSubfamilia(result.getString("idSubfamilia"));
                articulo.setMarca(result.getString("marca"));
                articulo.setMarketplace(result.getString("marketplace"));
                articulo.setIdPedido(result.getString("idPedido"));
                if (result.getDate("fechaSalida") != null) {
                    articulo.NuevoEnvio(result.getDate("fechaSalida"), result.getString("idAlmacen"),
                            result.getString("idAgencia"), result.getString("codigoArticulo"),
                            result.getString("idPedido"), result.getString("marketplace"));
                }
                articulos.add(articulo);
            }
            pstm.close();
            result.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return articulos;
    }

    @Override
    public List<Articulo> listar(String atributo, String valor) throws SQLException {
        Articulo articulo;
        List<Articulo> articulos = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    Consultas.obtenerConsultaArticulos(atributo, valor));
            pstm.setString(1, valor + "%");
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                articulo = new Articulo();
                articulo.setCodigoArticulo(result.getString("codigoArticulo"));
                articulo.setDescripcion(result.getString("descripcion"));
                articulo.setPrecio(result.getFloat("precio"));
                articulo.setCantidad(result.getInt("cantidad"));
                articulo.setEstado(result.getString("estado"));
                articulo.setPuc(result.getFloat("puc"));
                articulo.setTipoArticulo(result.getString("tipoArticulo"));
                articulo.setFechaHoraImpr(result.getTimestamp("fechaHoraImpr"));
                articulo.setFamilia(result.getString("idFamilia"));
                articulo.setSubfamilia(result.getString("idSubfamilia"));
                articulo.setMarca(result.getString("marca"));
                articulo.setMarketplace(result.getString("marketplace"));
                articulo.setIdPedido(result.getString("idPedido"));
                if (result.getDate("fechaSalida") != null) {
                    articulo.NuevoEnvio(result.getDate("fechaSalida"), result.getString("idAlmacen"),
                            result.getString("idAgencia"), result.getString("codigoArticulo"),
                            result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("idCompra") != null) {
                    articulo.NuevaCompra(result.getString("idCompra"), result.getString("proveedor"),
                            result.getDate("fechaCompra"), result.getDate("fechaEntrada"), result.getString("codigoArticulo"),
                            result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("numeroVenta") != null) {
                    articulo.NuevoDocumentoVenta(result.getString("numeroVenta"), result.getDate("fechaVenta"),
                            result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("numeroAlbaran") != null) {
                    articulo.NuevoAlbaranVenta(result.getString("numeroAlbaran"), result.getDate("fechaAlbaran"),
                            result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                }
                articulos.add(articulo);
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return articulos;
    }

    @Override
    public List<Articulo> listar(Filtro filtro) throws SQLException {
        Articulo articulo;
        List<Articulo> articulos = new ArrayList<>();
        int indice = 1;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(Consultas.obtenerConsultaArticulos(filtro));
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
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                articulo = new Articulo();
                articulo.setCodigoArticulo(result.getString("codigoArticulo"));
                articulo.setDescripcion(result.getString("descripcion"));
                articulo.setPrecio(result.getFloat("precio"));
                articulo.setCantidad(result.getInt("cantidad"));
                articulo.setEstado(result.getString("estado"));
                articulo.setPuc(result.getFloat("puc"));
                articulo.setTipoArticulo(result.getString("tipoArticulo"));
                articulo.setFechaHoraImpr(result.getTimestamp("fechaHoraImpr"));
                articulo.setFamilia(result.getString("idFamilia"));
                articulo.setSubfamilia(result.getString("idSubfamilia"));
                articulo.setMarca(result.getString("marca"));
                articulo.setMarketplace(result.getString("marketplace"));
                articulo.setIdPedido(result.getString("idPedido"));
                if (result.getDate("fechaSalida") != null) {
                    articulo.NuevoEnvio(result.getDate("fechaSalida"), result.getString("idAlmacen"),
                            result.getString("idAgencia"), result.getString("codigoArticulo"),
                            result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("idCompra") != null) {
                    articulo.NuevaCompra(result.getString("idCompra"), result.getString("proveedor"),
                            result.getDate("fechaCompra"), result.getDate("fechaEntrada"), result.getString("codigoArticulo"),
                            result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("numeroVenta") != null) {
                    articulo.NuevoDocumentoVenta(result.getString("numeroVenta"), result.getDate("fechaVenta"),
                            result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                }
                if (result.getString("numeroAlbaran") != null) {
                    articulo.NuevoAlbaranVenta(result.getString("numeroAlbaran"), result.getDate("fechaAlbaran"),
                            result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                }
                articulos.add(articulo);
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return articulos;
    }

    @Override
    public void registrar(Articulo articulo, Connection conexion) throws SQLException {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Articulos (codigoArticulo, descripcion, precio, "
                    + "cantidad, estado, puc, tipoArticulo, fechaHoraImpr, "
                    + "idFamilia, idSubfamilia, marca, idPedido, marketplace)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, articulo.getCodigoArticulo());
            pstm.setString(2, articulo.getDescripcion());
            pstm.setFloat(3, articulo.getPrecio());
            pstm.setInt(4, articulo.getCantidad());
            pstm.setString(5, articulo.getEstado());
            pstm.setFloat(6, articulo.getPuc());
            pstm.setString(7, articulo.getTipoArticulo());
            pstm.setTimestamp(8, articulo.getFechaHoraImpr());
            pstm.setString(9, articulo.getFamilia());
            pstm.setString(10, articulo.getSubfamilia());
            pstm.setString(11, articulo.getMarca());
            pstm.setString(12, articulo.getIdPedido());
            pstm.setString(13, articulo.getMarketplace());
            pstm.executeUpdate();
            if (articulo.getEnvio() != null) {
                daoEnvio.registrar(articulo.getEnvio(), this.getConnection());
            }
            if (articulo.getCompra() != null) {
                daoCompra.registrar(articulo.getCompra(), this.getConnection());
            }
            if (articulo.getDocumentoVenta() != null) {
                daoDocumentoVenta.registrar(articulo.getDocumentoVenta(), this.getConnection());
            }
            if (articulo.getAlbaranVenta() != null) {
                daoAlbaranVenta.registrar(articulo.getAlbaranVenta(), this.getConnection());
            }
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
    }

    @Override
    public void registrar(List<Articulo> articulos, Connection conexion) throws SQLException {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Articulos (codigoArticulo, descripcion, precio, "
                    + "cantidad, estado, puc, tipoArticulo, fechaHoraImpr, "
                    + "idFamilia, idSubfamilia, marca, idPedido, marketplace)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (Articulo articulo : articulos) {
                pstm.setString(1, articulo.getCodigoArticulo());
                pstm.setString(2, articulo.getDescripcion());
                pstm.setFloat(3, articulo.getPrecio());
                pstm.setInt(4, articulo.getCantidad());
                pstm.setString(5, articulo.getEstado());
                pstm.setFloat(6, articulo.getPuc());
                pstm.setString(7, articulo.getTipoArticulo());
                pstm.setTimestamp(8, articulo.getFechaHoraImpr());
                pstm.setString(9, articulo.getFamilia());
                pstm.setString(10, articulo.getSubfamilia());
                pstm.setString(11, articulo.getMarca());
                pstm.setString(12, articulo.getIdPedido());
                pstm.setString(13, articulo.getMarketplace());
                pstm.executeUpdate();
                if (articulo.getEnvio() != null) {
                    daoEnvio.registrar(articulo.getEnvio(), this.getConnection());
                }
                if (articulo.getCompra() != null) {
                    daoCompra.registrar(articulo.getCompra(), this.getConnection());
                }
                if (articulo.getDocumentoVenta() != null) {
                    daoDocumentoVenta.registrar(articulo.getDocumentoVenta(), this.getConnection());
                }
                if (articulo.getAlbaranVenta() != null) {
                    daoAlbaranVenta.registrar(articulo.getAlbaranVenta(), this.getConnection());
                }
                pstm.clearParameters();
            }
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
    }

    @Override
    public void registrar(Articulo articulo) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Articulos (codigoArticulo, descripcion, precio, "
                    + "cantidad, estado, puc, tipoArticulo, fechaHoraImpr, "
                    + "idFamilia, idSubfamilia, marca, idPedido, marketplace)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, articulo.getCodigoArticulo());
            pstm.setString(2, articulo.getDescripcion());
            pstm.setFloat(3, articulo.getPrecio());
            pstm.setInt(4, articulo.getCantidad());
            pstm.setString(5, articulo.getEstado());
            pstm.setFloat(6, articulo.getPuc());
            pstm.setString(7, articulo.getTipoArticulo());
            pstm.setTimestamp(8, articulo.getFechaHoraImpr());
            pstm.setString(9, articulo.getFamilia());
            pstm.setString(10, articulo.getSubfamilia());
            pstm.setString(11, articulo.getMarca());
            pstm.setString(12, articulo.getIdPedido());
            pstm.setString(13, articulo.getMarketplace());
            pstm.executeUpdate();
            if (articulo.getEnvio() != null) {
                daoEnvio.registrar(articulo.getEnvio(), this.getConnection());
            }
            if (articulo.getCompra() != null) {
                daoCompra.registrar(articulo.getCompra(), this.getConnection());
            }
            if (articulo.getDocumentoVenta() != null) {
                daoDocumentoVenta.registrar(articulo.getDocumentoVenta(), this.getConnection());
            }
            if (articulo.getAlbaranVenta() != null) {
                daoAlbaranVenta.registrar(articulo.getAlbaranVenta(), this.getConnection());
            }
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void modificar(Articulo articulo) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                UPDATE
                    articulos
                SET
                    articulos.descripcion = ?,
                    articulos.precio = ?,
                    articulos.cantidad = ?,
                    articulos.estado = ?,
                    articulos.puc = ?,
                    articulos.tipoArticulo = ?,
                    articulos.fechaHoraImpr = ?,
                    articulos.idFamilia = ?,
                    articulos.idSubfamilia = ?,
                    articulos.marca = ?
                WHERE
                    articulos.codigoArticulo = ? AND
                    articulos.idPedido = ? AND
                    articulos.marketplace = ?
                """);
            pstm.setString(1, articulo.getDescripcion());
            pstm.setFloat(2, articulo.getPrecio());
            pstm.setInt(3, articulo.getCantidad());
            pstm.setString(4, articulo.getEstado());
            pstm.setFloat(5, articulo.getPuc());
            pstm.setString(6, articulo.getTipoArticulo());
            pstm.setTimestamp(7, articulo.getFechaHoraImpr());
            pstm.setString(8, articulo.getFamilia());
            pstm.setString(9, articulo.getSubfamilia());
            pstm.setString(10, articulo.getMarca());
            pstm.setString(11, articulo.getCodigoArticulo());
            pstm.setString(12, articulo.getIdPedido());
            pstm.setString(13, articulo.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Articulo articulo) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM Articulos WHERE codigoArticulo = ? AND idPedido = ? "
                    + "AND marketplace = ?");
            pstm.setString(1, articulo.getCodigoArticulo());
            pstm.setString(2, articulo.getIdPedido());
            pstm.setString(3, articulo.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(String marketplace, String idPedido) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                DELETE FROM 
                    Articulos
                WHERE
                    marketplace = ? AND
                    idPedido = ?
                """);
            pstm.setString(1, marketplace);
            pstm.setString(2, idPedido);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<String> listarEstados() throws SQLException {
        List<String> lista = new ArrayList<>();
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT DISTINCT estado FROM Articulos ORDER BY estado");
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
