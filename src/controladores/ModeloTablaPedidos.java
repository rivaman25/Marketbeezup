/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Articulo;
import modelos.Pedido;

/**
 *
 * @author Manolo
 */
public class ModeloTablaPedidos extends AbstractTableModel {

    private List<Pedido> pedidos;
    private final List<Articulo> articulos;

    //                                          0           1                              
    private final Class[] columnClass = {String.class, String.class,
        //      2               3               4             5        
        String.class, Timestamp.class, String.class, String.class,
        //      6           7           8               9           10
        String.class, String.class, String.class, String.class, String.class,
        //      11          12          13          14              15
        String.class, String.class, String.class, Float.class, Integer.class,
        //      16          17          18          19              20
        Float.class, Float.class, Float.class, String.class, Boolean.class,
        //      21                  22          23              24
        Timestamp.class, String.class, Date.class, String.class,
        //      25          26          27                  28              29
        String.class, String.class, String.class, Date.class, Date.class,
        //      30          31              32            33
        String.class, Date.class, String.class, Date.class};

    private final String[] columnNames = {"Tienda", "Marketplace", "Pedido",
        "Fecha Pedido", "DNI", "Nombre", "Dirección", "C.P.", "Población",
        "Provincia", "Teléfono", "Email", "Id Artículo", "Descripción", "Precio",
        "Unidades", "Importe", "Comisión", "Porte", "Existe", "Observaciones", "Albarán",
        "Estado", "Fecha Salida", "Almacén", "Agencia", "Id Compra", "Proveedor",
        "Fecha Compra", "Fecha Entrega", "Ticket", "Fecha ticket", "Albarán",
        "Fecha Albarán"};

    public ModeloTablaPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        articulos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            articulos.addAll(pedido.getArticulos());
        }
    }

    @Override
    public int getRowCount() {
        return articulos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /*
     * familia, subfamilia, marca puc, beneficio;
     */
    @Override
    public Object getValueAt(int row, int col) {
        if (pedidos.isEmpty()) {
            return null;
        } else {
            Pedido pedidoFila = new Pedido();
            // Obtengo el pedido correspondiente al artículo a mostrar en la fila
            for (Pedido pedido : pedidos) {
                if (articulos.get(row).getMarketplace().equals(pedido.getMarketplace())
                        & articulos.get(row).getIdPedido().equals(pedido.getIdPedido())) {
                    pedidoFila = pedido;
                    break;
                }
            }
            switch (col) {
                case 0:
                    return pedidoFila.getTienda();
                case 1:
                    return pedidoFila.getMarketplace();
                case 2:
                    return pedidoFila.getIdPedido();
                case 3:
                    return pedidoFila.getFechaPedido();
                case 4:
                    return pedidoFila.getDni();
                case 5:
                    return pedidoFila.getNombreApellidos();
                case 6:
                    return pedidoFila.getDireccion();
                case 7:
                    return pedidoFila.getCp();
                case 8:
                    return pedidoFila.getPoblacion();
                case 9:
                    return pedidoFila.getProvincia();
                case 10:
                    return pedidoFila.getTelefono();
                case 11:
                    return pedidoFila.getEmail();
                case 12:
                    return articulos.get(row).getCodigoArticulo();
                case 13:
                    return articulos.get(row).getDescripcion();
                case 14:
                    return articulos.get(row).getPrecio();
                case 15:
                    return articulos.get(row).getCantidad();
                case 16:
                    return pedidoFila.getImporte();
                case 17:
                    return pedidoFila.getComision();
                case 18:
                    return pedidoFila.getCostePorte();
                case 19:
                    return articulos.get(row).getTipoArticulo();
                case 20:
                    return !pedidoFila.getObservaciones().isEmpty();
                case 21:
                    return articulos.get(row).getFechaHoraImpr();
                case 22:
                    return articulos.get(row).getEstado();
                case 23:
                    if (articulos.get(row).getEnvio() != null) {
                        return articulos.get(row).getEnvio().getFechaSalida();
                    } else {
                        return null;
                    }
                case 24:
                    if (articulos.get(row).getEnvio() != null) {
                        return articulos.get(row).getEnvio().getIdAlmacen();
                    } else {
                        return null;
                    }
                case 25:
                    if (articulos.get(row).getEnvio() != null) {
                        return articulos.get(row).getEnvio().getIdAgencia();
                    } else {
                        return null;
                    }
                case 26:
                    if (articulos.get(row).getCompra() != null) {
                        return articulos.get(row).getCompra().getIdCompra();
                    } else {
                        return null;
                    }
                case 27:
                    if (articulos.get(row).getCompra() != null) {
                        return articulos.get(row).getCompra().getProveedor();
                    } else {
                        return null;
                    }
                case 28:
                    if (articulos.get(row).getCompra() != null) {
                        return articulos.get(row).getCompra().getFechaCompra();
                    } else {
                        return null;
                    }
                case 29:
                    if (articulos.get(row).getCompra() != null) {
                        return articulos.get(row).getCompra().getFechaEntrada();
                    } else {
                        return null;
                    }
                case 30:
                    if (articulos.get(row).getDocumentoVenta() != null) {
                        return articulos.get(row).getDocumentoVenta().getNumeroVenta();
                    } else {
                        return null;
                    }
                case 31:
                    if (articulos.get(row).getDocumentoVenta() != null) {
                        return articulos.get(row).getDocumentoVenta().getFechaVenta();
                    } else {
                        return null;
                    }
                case 32:
                    if (articulos.get(row).getAlbaranVenta() != null) {
                        return articulos.get(row).getAlbaranVenta().getNumeroAlbaran();
                    } else {
                        return null;
                    }
                case 33:
                    if (articulos.get(row).getAlbaranVenta() != null) {
                        return articulos.get(row).getAlbaranVenta().getFechaAlbaran();
                    } else {
                        return null;
                    }
                default:
                    return null;
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     *
     * @param columnIndex
     * @return
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
