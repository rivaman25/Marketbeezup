/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.controladores.PedidosControlador;
import com.daoInterfaces.DAOPedido;
import com.daoInterfaces.DAOPedidoNuevos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.modelos.Articulo;
import com.modelos.Observacion;
import com.modelos.Pedido;
import com.modelos.PedidoPK;

/**
 *
 * @author Manolo
 */
public class DAOPedidoNuevosImpl extends ConexionBD implements DAOPedidoNuevos {

    /**
     * Inicializa los parámetros de conexión de la base de datos
     *
     * @param url
     * @param serverName
     * @param portNumber
     * @param databaseName
     * @param userName
     * @param password
     */
    public DAOPedidoNuevosImpl(String url, String serverName, int portNumber,
            String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    /**
     * Accede a la base de datos online y obtiene los pedidos que no están en la
     * base de datos Marketbeezup
     *
     * @return Se obtiene una lista con los pedidos nuevos
     */
    @Override
    public List<Pedido> obtenerPedidosNuevos() throws SQLException {
        List<Pedido> pedidosNuevos = new ArrayList<>();
        int indicePedido;
        Pedido pedidoNuevo;
        Articulo articulo;
        Observacion observacion;
        DAOPedido daoPedido = PedidosControlador.getDaoPedido();
        // Se obtiene la clave de los pedidos que ya están registrados
        List<PedidoPK> pedidosPK = daoPedido.listarPK();
        try {
            this.openConnection();
            // Se obtienen los pedidos que no están registrados
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM pedidos WHERE marketplace NOT IN ("
                    + obtenerParametros(pedidosPK) + ") AND idPedido NOT IN("
                    + obtenerParametros(pedidosPK) + ")");
            for (int i = 0; i < pedidosPK.size(); i++) {
                pstm.setString(i + 1, pedidosPK.get(i).getMarketplace());
            }
            for (int i = 0; i < pedidosPK.size(); i++) {
                pstm.setString(i + pedidosPK.size() + 1, pedidosPK.get(i).getIdPedido());
            }
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                // Compruebo si ya se ha almacenado el pedido, para luego añadir, artículos, envío, compra, ticket y albarán
                indicePedido = Pedido.existePedido(result.getString("Marketplace"),
                        result.getString("idPedido"), pedidosNuevos);
                if (indicePedido >= 0) {
                    /* 
                     * Si existe el pedido lo recupero para añadirle
                     * artículos, envío, compra, ticket y albaran
                     */
                    pedidoNuevo = pedidosNuevos.get(indicePedido);
                } else {
                    // Si no existe el pedido creo uno nuevo
                    pedidoNuevo = new Pedido();
                    pedidoNuevo.setMarketplace(result.getString("Marketplace"));
                    pedidoNuevo.setTienda(result.getString("Tienda"));
                    pedidoNuevo.setIdPedido(result.getString("idPedido"));
                    pedidoNuevo.setFechaPedido(result.getTimestamp("Fecha"));
                    pedidoNuevo.setDni(result.getString("DNI"));
                    pedidoNuevo.setNombreApellidos(result.getString("Nombre_Cliente"));
                    pedidoNuevo.setDireccion(result.getString("direccion"));
                    pedidoNuevo.setCp(result.getString("CP"));
                    pedidoNuevo.setPoblacion(result.getString("Poblacion"));
                    pedidoNuevo.setProvincia(result.getString("Provincia"));
                    pedidoNuevo.setTelefono(result.getString("Telefono"));
                    pedidoNuevo.setEmail(result.getString("email"));
                    pedidoNuevo.setCostePorte(result.getFloat("Impporte"));
                    pedidoNuevo.setComision(result.getFloat("Comision"));
                    pedidoNuevo.setImporte(result.getFloat("TotalPedido"));
                    pedidosNuevos.add(pedidoNuevo);
                }
                articulo = new Articulo();
                if (existeArticuloPedido(result.getString("idarticulo"), pedidoNuevo)) {
                    System.out.println("Este artículo no se graba: " + result.getString("idarticulo"));
                } else {
                    articulo.setCodigoArticulo(result.getString("idarticulo"));
                    articulo.setDescripcion(result.getString("Descripcion"));
                    articulo.setCantidad(result.getInt("Bultos"));
                    articulo.setPrecio(result.getFloat("Importe"));
                    // Según los valores de diferentes atributos registro el estado del pedido
                    if (result.getBoolean("Cancelado")) {
                        articulo.setEstado("ANULADO");
                    } else {
                        if (result.getString("Estado") != null) {
                            articulo.setEstado(result.getString("Estado"));
                        } else {
                            if (result.getBoolean("Albaran")) {
                                articulo.setEstado("PREPARAR");
                            } else {
                                if (result.getDate("fechaSalida") != null) {
                                    articulo.setEstado("IMPRIMIR");
                                } else {
                                    articulo.setEstado("NUEVO");
                                }
                            }
                        }

                    }
                    // Si se ha marcado el albarán como impreso registra la fecha del pedido.
                    // En la nueva BD se va a registrar la hora de impresión de albaranes
                    if (result.getBoolean("Albaran")) {
                        articulo.setFechaHoraImpr(pedidoNuevo.getFechaPedido());
                    }
                    articulo.setTipoArticulo(result.getString("Tipo_Articulo"));
                    articulo.setIdPedido(result.getString("idPedido"));
                    articulo.setMarketplace(result.getString("Marketplace"));
                    if (result.getDate("FechaSalida") != null
                            & result.getString("AlmacenSalida") != null & !result.getString("AlmacenSalida").isBlank()
                            & result.getString("Agencia") != null & !result.getString("Agencia").isBlank()) {
                        articulo.NuevoEnvio(result.getDate("FechaSalida"),
                                result.getString("AlmacenSalida"), result.getString("Agencia"),
                                result.getString("idarticulo"), result.getString("idPedido"),
                                result.getString("Marketplace"));
                    }
                    if (result.getString("NumeroSeguimiento") != null & result.getDate("FPedido") != null) {
                        articulo.NuevaCompra(result.getString("NumeroSeguimiento"),
                                result.getString("Proveedor"), result.getDate("FPedido"),
                                result.getDate("FEntrega"), result.getString("idarticulo"),
                                result.getString("idPedido"), result.getString("Marketplace"));
                    }
                    if (result.getString("Tiquet") != null & result.getDate("FechaTiquet") != null) {
                        articulo.NuevoDocumentoVenta(result.getString("Tiquet"),
                                result.getDate("FechaTiquet"), result.getString("idarticulo"),
                                result.getString("idPedido"), result.getString("Marketplace"));
                    }
                    if ((result.getString("numeroAlbaran") != null & !result.getString("numeroAlbaran").isBlank())) {
                        articulo.NuevoAlbaranVenta(result.getString("numeroAlbaran"),
                                result.getDate("Fecha"), result.getString("idarticulo"),
                                result.getString("idPedido"), result.getString("Marketplace"));
                    }
                    pedidoNuevo.NuevoArticulo(articulo);
                    if (result.getString("Observaciones") != null) {
                        observacion = new Observacion();
                        observacion.setTitulo(articulo.getMarketplace() + "-"
                                + articulo.getIdPedido() + " - " + articulo.getCodigoArticulo());
                        observacion.setDescripcion(result.getString("Observaciones"));
                        observacion.setFechaHora(pedidoNuevo.getFechaPedido());
                        observacion.setMarketplace(result.getString("marketplace"));
                        observacion.setIdPedido(result.getString("idPedido"));
                        pedidoNuevo.NuevaObservacion(observacion);
                    }
                }
                /*			
                    idArticuloA varchar(1) YES
                    AceptaCambio tinyint(1) NO
                    RecogidaUsado tinyint(1) NO
                    NumSerie bigint (20)	YES
                    HomeLogistics varchar(15) YES 
                    IdServicio varchar(10) YES 
                    Cancelado tinyint(1)	NO
                    RT varchar(5) YES
                    Sistema date YES			
                    PedidoIndot varchar(10) YES
                    CostePortes	float YES			
                    CosteArticulo float YES	
                    Factura varchar(10)	YES
                    FechaFactura date YES			
                    Validaciones tinyint(1) NO
                    Finalizado varchar(10) YES
                    Cod_Pedido varchar(10) YES
                    Agencia_Proveedor varchar(10) YES
                    URL_tracking varchar(10) YES
                    Tipo_Articulo varchar(6) YES
                    GOi	varchar(16) YES 
                    ServicioGOi	varchar(40) YES
                    TIMESTAMP_Auto timestamp YES CURRENT_TIMESTAMP
                    expedicion_dachser varchar(40) YES
                    volumen double NO 0.1	
                 */
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return pedidosNuevos;
    }

    private boolean existeArticuloPedido(String codigoArticulo, Pedido pedido) {
        for (Articulo articulo : pedido.getArticulos()) {
            if (articulo.getCodigoArticulo().equalsIgnoreCase(codigoArticulo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que devuelve un string con los parámetros a introducir en la
     * consulta preparada
     *
     * @param pedidos Pedidos almacenados en la base de datos marketbeezup
     * @return Parámetros para la consulta preparada a la base de datos online
     */
    private String obtenerParametros(List<PedidoPK> pedidosPK) {
        StringBuilder parametros = new StringBuilder();
        if (pedidosPK.isEmpty()) {
            parametros.append("\"\"");
        } else {
            parametros.append("?");
            for (int i = 1; i < pedidosPK.size(); i++) {
                parametros.append(", ?");
            }
        }
        return parametros.toString();
    }
}
