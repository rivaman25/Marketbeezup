/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Articulo;
import modelos.Pedido;

/**
 *
 * @author Manolo
 */
public class ModeloTablaAlbaranesImpr extends AbstractTableModel {

    private List<Articulo> articulos;

    private final Class[] tipoColumna = new Class[]{String.class, String.class, String.class, java.sql.Date.class,
        java.sql.Date.class, String.class, String.class, String.class, String.class};
    private final String[] columnNames = {"Tienda", "Market", "Pedido", "Fecha Salida", "FechaImpr", "Código",
        "Descripción", "Agencia", "Nombre Cliente"};

    public ModeloTablaAlbaranesImpr(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public int getRowCount() {
        return articulos.size();
    }

    @Override
    public int getColumnCount() {
        return tipoColumna.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pedido pedidoFila = new Pedido();
        // Obtengo el pedido correspondiente al artículo a mostrar en la fila
        for (Pedido pedido : PedidosControlador.getPedidos()) {
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
                if (articulos.get(row).getEnvio() != null) {
                    return articulos.get(row).getEnvio().getFechaSalida();
                } else {
                    return null;
                }
            case 4:
                return articulos.get(row).getFechaHoraImpr();
            case 5:
                return articulos.get(row).getCodigoArticulo();
            case 6:
                return articulos.get(row).getDescripcion();
            case 7:
                if (articulos.get(row).getEnvio() != null) {
                    return articulos.get(row).getEnvio().getIdAgencia();
                } else {
                    return null;
                }
            case 8:
                return pedidoFila.getNombreApellidos();
            default:
                return null;
        }
    }
}
