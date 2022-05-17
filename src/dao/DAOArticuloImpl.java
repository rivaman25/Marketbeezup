/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controladores.PedidosControlador;
import daoInterfaces.DAOArticulo;
import daoInterfaces.DAOInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.AlbaranVenta;
import modelos.Articulo;
import modelos.Compra;
import modelos.DocumentoVenta;
import modelos.Envio;
import modelos.Filtro;

/**
 *
 * @author Manolo
 */
public class DAOArticuloImpl extends ConexionBD implements DAOArticulo {

    public DAOArticuloImpl() {
    }

    public DAOArticuloImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    private List<String> obtenerAgenciasFiltradas() {
        List<String> lista = new ArrayList<>();
        if (PedidosControlador.getFiltro().getAgencias() != null) {
            for (int i : PedidosControlador.getFiltro().getAgencias()) {
                lista.add(PedidosControlador.getAgencias().get(i));
            }
        }
        return lista;
    }

    private List<String> obtenerAlmacenesFiltrados() {
        List<String> lista = new ArrayList<>();
        if (PedidosControlador.getFiltro().getAlmacenes() != null) {
            for (int i : PedidosControlador.getFiltro().getAlmacenes()) {
                lista.add(PedidosControlador.getAlmacenes().get(i));
            }
        }
        return lista;
    }

    @Override
    public List<Articulo> listar(String atributo, String valor) throws Exception {
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
                articulo.NuevoEnvio(result.getDate("fechaSalida"), result.getString("idAlmacen"),
                        result.getString("idAgencia"), result.getString("codigoArticulo"),
                        result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevaCompra(result.getString("idCompra"), result.getString("proveedor"),
                        result.getDate("fechaCompra"), result.getDate("fechaEntrada"), result.getString("codigoArticulo"),
                        result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevoDocumentoVenta(result.getString("numeroVenta"), result.getDate("fechaVenta"),
                        result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevoAlbaranVenta(result.getString("numeroAlbaran"), result.getDate("fechaAlbaran"),
                        result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                articulos.add(articulo);
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
        return articulos;
    }

    @Override
    public List<Articulo> listar(Filtro filtro) throws Exception {
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
                articulo.NuevoEnvio(result.getDate("fechaSalida"), result.getString("idAlmacen"),
                        result.getString("idAgencia"), result.getString("codigoArticulo"),
                        result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevaCompra(result.getString("idCompra"), result.getString("proveedor"),
                        result.getDate("fechaCompra"), result.getDate("fechaEntrada"), result.getString("codigoArticulo"),
                        result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevoDocumentoVenta(result.getString("numeroVenta"), result.getDate("fechaVenta"),
                        result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                articulo.NuevoAlbaranVenta(result.getString("numeroAlbaran"), result.getDate("fechaAlbaran"),
                        result.getString("codigoArticulo"), result.getString("idPedido"), result.getString("marketplace"));
                articulos.add(articulo);
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
        return articulos;
    }

    @Override
    public void registrar(Articulo articulo, Connection conexion) throws Exception {
        DAOInterfaz<Envio> daoEnvio;
        DAOInterfaz<Compra> daoCompra;
        DAOInterfaz<DocumentoVenta> daoDocumentoVenta;
        DAOInterfaz<AlbaranVenta> daoAlbaranVenta;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            daoEnvio = new DAOEnvioImpl();
            daoCompra = new DAOCompraImpl();
            daoDocumentoVenta = new DAODocumentoVentaImpl();
            daoAlbaranVenta = new DAOAlbaranVentaImpl();
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
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void registrar(List<Articulo> articulos, Connection conexion) throws Exception {
        DAOInterfaz<Envio> daoEnvio;
        DAOInterfaz<Compra> daoCompra;
        DAOInterfaz<DocumentoVenta> daoDocumentoVenta;
        DAOInterfaz<AlbaranVenta> daoAlbaranVenta;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            daoEnvio = new DAOEnvioImpl();
            daoCompra = new DAOCompraImpl();
            daoDocumentoVenta = new DAODocumentoVentaImpl();
            daoAlbaranVenta = new DAOAlbaranVentaImpl();
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
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void registrar(Articulo articulo) throws Exception {
        DAOInterfaz<Envio> daoEnvio;
        DAOInterfaz<Compra> daoCompra;
        DAOInterfaz<DocumentoVenta> daoDocumentoVenta;
        DAOInterfaz<AlbaranVenta> daoAlbaranVenta;
        try {
            this.openConnection();
            daoEnvio = new DAOEnvioImpl();
            daoCompra = new DAOCompraImpl();
            daoDocumentoVenta = new DAODocumentoVentaImpl();
            daoAlbaranVenta = new DAOAlbaranVentaImpl();
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
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void modificar(Articulo articulo) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Articulos SET descripcion = ? AND precio = ? AND "
                    + "cantidad = ? AND estado = ? AND puc = ? AND tipoArticulo = "
                    + "? AND fechaHoraImpr = ? AND idFamilia = ? AND idSubfamilia = "
                    + "? AND marca = ? WHERE codigoArticulo = ? AND idPedido = ? "
                    + "AND marketplace = ?");
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
            Logger.getLogger(DAOArticuloImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Articulo articulo) throws Exception {
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
    public List<String> listarEstados() {
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
                            articulos.codigoArticulo,
                            articulos.descripcion,
                            articulos.precio,
                            articulos.cantidad,
                            articulos.estado,
                            articulos.puc,
                            articulos.tipoArticulo,
                            articulos.fechaHoraImpr,
                            articulos.idFamilia,
                            articulos.idSubfamilia,
                            articulos.marca,
                            articulos.marketplace,
                            articulos.idPedido,
                            envios.fechaSalida,
                            envios.idAgencia,
                            envios.idAlmacen,
                            compras.idCompra,
                            compras.proveedor,
                            compras.fechaCompra,
                            compras.fechaEntrada,
                            albaranesVenta.fechaAlbaran,
                            albaranesVenta.numeroAlbaran,
                            documentosVenta.numeroVenta,
                            documentosVenta.fechaVenta
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
                            articulos.codigoArticulo,
                            articulos.descripcion,
                            articulos.precio,
                            articulos.cantidad,
                            articulos.estado,
                            articulos.puc,
                            articulos.tipoArticulo,
                            articulos.fechaHoraImpr,
                            articulos.idFamilia,
                            articulos.idSubfamilia,
                            articulos.marca,
                            articulos.marketplace,
                            articulos.idPedido,
                            envios.fechaSalida,
                            envios.idAgencia,
                            envios.idAlmacen,
                            compras.idCompra,
                            compras.proveedor,
                            compras.fechaCompra,
                            compras.fechaEntrada,
                            albaranesVenta.fechaAlbaran,
                            albaranesVenta.numeroAlbaran,
                            documentosVenta.numeroVenta,
                            documentosVenta.fechaVenta
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
        consulta.append(" GROUP BY articulos.idPedido, articulos.marketplace, articulos.codigoArticulo");
        return consulta.toString();
    }
}
