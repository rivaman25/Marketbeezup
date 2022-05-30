/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import com.modelos.Articulo;
import com.modelos.Pedido;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Manolo
 */
public class TablaPedidosCellRenderer extends DefaultTableCellRenderer {

    private final List<Articulo> articulos;
    private DateFormat formatter;

    public TablaPedidosCellRenderer(List<Pedido> pedidos) {
        articulos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            articulos.addAll(pedido.getArticulos());
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String idPedidoAnterior = null;
        String marketplaceAnterior = null;
        String idPedidoSiguiente = null;
        String marketplaceSiguiente = null;
        Color alternate = UIManager.getColor("Table.alternateRowColor");
        switch (articulos.get(row).getEstado()) {
            case "ANULADO":
                if (isSelected) {
                    //this.setBackground(Color.GRAY);
                    this.setForeground(Color.RED);
                } else {
                    //this.setBackground(Color.LIGHT_GRAY);
                    this.setForeground(Color.RED);
                }
                break;
            case "ENTREGADO":
                if (isSelected) {
                    //this.setBackground(Color.GRAY);
                    this.setForeground(Color.GREEN);
                } else {
                    //this.setBackground(Color.LIGHT_GRAY);
                    this.setForeground(Color.GREEN);
                }
                break;
            default:
                if (row < articulos.size() - 1) {
                    idPedidoSiguiente = articulos.get(row + 1).getIdPedido();
                    marketplaceSiguiente = articulos.get(row + 1).getMarketplace();
                }
                if (row > 0) {
                    idPedidoAnterior = articulos.get(row - 1).getIdPedido();
                    marketplaceAnterior = articulos.get(row - 1).getMarketplace();
                }
                if (articulos.get(row).getIdPedido().equals(idPedidoAnterior)
                        & articulos.get(row).getMarketplace().equals(marketplaceAnterior)) {
                    if (isSelected) {
                        // this.setBackground(Color.YELLOW);
                        this.setForeground(Color.BLUE);
                    } else {
                        // this.setBackground(Color.ORANGE);
                        this.setForeground(Color.BLUE);
                    }
                } else {
                    if (articulos.get(row).getIdPedido().equals(idPedidoSiguiente)
                            & articulos.get(row).getMarketplace().equals(marketplaceSiguiente)) {
                        if (isSelected) {
                            // this.setBackground(Color.YELLOW);
                            this.setForeground(Color.BLUE);
                        } else {
                            // this.setBackground(Color.ORANGE);
                            this.setForeground(Color.BLUE);
                        }
                    } else {
                        if (!isSelected) {
                            // this.setBackground(alternate);
                            this.setForeground(alternate);
                        }
                    }
                }
        }
        if (value instanceof java.sql.Timestamp) {
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.setText(formatter.format(value));
        }
        if (value instanceof java.sql.Date) {
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.setText(formatter.format(value));
        }
        return this;
    }
}
