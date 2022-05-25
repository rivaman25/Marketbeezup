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

/**
 *
 * @author Manolo
 */
public class TablaPedidosCellRenderer extends DefaultTableCellRenderer {

    List<Articulo> articulos;
    List<Pedido> pedidos;

    public TablaPedidosCellRenderer(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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
        if (articulos.get(row).getEstado().equals("CANCELADO")) {
            if (isSelected) {
                this.setBackground(Color.GRAY);
            } else {
                this.setBackground(Color.LIGHT_GRAY);
            }
        } else {
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
                    this.setBackground(Color.YELLOW);
                } else {
                    this.setBackground(Color.ORANGE);
                }
            } else {
                if (articulos.get(row).getIdPedido().equals(idPedidoSiguiente)
                        & articulos.get(row).getMarketplace().equals(marketplaceSiguiente)) {
                    if (isSelected) {
                        this.setBackground(Color.YELLOW);
                    } else {
                        this.setBackground(Color.ORANGE);
                    }
                } else {
                    if (!isSelected) {
                        this.setBackground(alternate);
                    }
                }
            }
        }
        /*String idPedido;
        String marketplace;
        if ((row + 1) < articulos.size()) {
            
                if (row > 0) {
                    if (articulos.get(row).getIdPedido().equals(articulos.get(row - 1).getIdPedido()) & 
                            articulos.get(row).getMarketplace().equals(articulos.get(row - 1).getMarketplace())) {
                        this.setForeground(Color.orange);
                    } else {
                        this.setForeground();
                    }
                }
                this.setForeground(Color.white);
            }
        } else {
            this.setForeground(Color.white);
        }
        /*
        switch (String.valueOf(table.getValueAt(row, 15))) {
            case "Novedad":
                this.setBackground(Color.white);
                this.setForeground(Color.green);
                break;
            case "Actual":
                this.setBackground(Color.white);
                this.setForeground(Color.black);
                break;
            case "Preobsoleto":
                this.setBackground(Color.white);
                this.setForeground(Color.red);
                break;
            case "Obsoleto":
                this.setBackground(Color.red);
                this.setForeground(Color.white);
                break;
            default:
                this.setBackground(Color.black);
                this.setForeground(Color.white);
        }
        this.setToolTipText(String.valueOf(table.getValueAt(row, column)));
         */
        return this;
    }
}
