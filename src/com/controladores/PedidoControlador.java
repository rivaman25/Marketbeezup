/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.DAOPedidoImpl;
import com.daoInterfaces.DAOPedido;
import com.modelos.Articulo;
import com.modelos.Pedido;
import com.vistas.PedidoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Manolo
 */
public class PedidoControlador implements ActionListener {

    private PedidoVista pedidoVista;
    private Pedido pedido;
    DAOPedido daoPedido;

    public PedidoControlador(PedidoVista pedidoVista, Pedido pedido) {
        this.pedidoVista = pedidoVista;
        this.pedido = pedido;
        daoPedido = new DAOPedidoImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
    }

    public void actualizarVista() {
        pedidoVista.actualizarVista(pedido);
        pedidoVista.setLocationRelativeTo(null);
        pedidoVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Articulo articulo;
        int fila;
        switch (e.getActionCommand()) {
            case "Guardar":
                try {
                pedido = pedidoVista.getPedido();
                if (pedido.getTienda() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo Tienda");
                    return;
                }
                if (pedido.getMarketplace() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo Marketplace");
                    return;
                }
                if (pedido.getIdPedido() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo IdPedido");
                    return;
                }
                if (pedido.getFechaPedido() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo Fecha Pedido");
                    return;
                }
                if (pedido.getNombreApellidos() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo Nombre");
                    return;
                }
                if (pedido.getDireccion() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo Dirección");
                    return;
                }
                if (pedido.getCp() == null) {
                    pedidoVista.muestraMensaje("Introduzca un valor para el campo CP");
                    return;
                }
                if (pedido.getArticulos().isEmpty()) {
                    pedidoVista.muestraMensaje("Introduzca al menos un articulo para este pedido");
                    return;
                }
                if (Pedido.existePedido(pedido.getMarketplace(), pedido.getIdPedido(), PedidosControlador.getPedidos()) == -1) {
                    try {
                        daoPedido.registrar(pedido);
                    } catch (Exception ex) {
                        Logger.getLogger(PedidoControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (JOptionPane.showConfirmDialog(pedidoVista, "El pedido existe, ¿Desea modificarlo?", "Modificar Pedido", JOptionPane.OK_CANCEL_OPTION) == 0) {
                            daoPedido.modificar(pedido);
                        }
                    } catch (Exception ex) {

                    }
                }
            } catch (NumberFormatException ex) {
                pedidoVista.muestraMensaje("Introduzca un valor numérico válido");
            }
            break;
            case "Registrar":
                try {
                articulo = pedidoVista.obtenerArticulo();
                if (articulo.getCodigoArticulo() == null || articulo.getDescripcion() == null) {
                    JOptionPane.showMessageDialog(pedidoVista, "Introduzca un valor para IdPedido o Descripción", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!articulo.getCodigoArticulo().isBlank() & !articulo.getDescripcion().isBlank()) {
                        fila = Articulo.existeArticulo(articulo.getCodigoArticulo(), pedidoVista.getPedido().getArticulos());
                        if (fila == -1) {
                            pedidoVista.getPedido().NuevoArticulo(articulo);
                        } else {
                            pedidoVista.getPedido().getArticulos().get(fila).setDescripcion(articulo.getDescripcion());
                            pedidoVista.getPedido().getArticulos().get(fila).setCantidad(articulo.getCantidad());
                            pedidoVista.getPedido().getArticulos().get(fila).setPrecio(articulo.getPrecio());
                        }
                        pedidoVista.actualizarTabla();
                    } else {
                        JOptionPane.showMessageDialog(pedidoVista, "Introduzca un valor para IdPedido o Descripción", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(pedidoVista, "Introduzca un valor numérico válido en la cantidad o el precio", "Error", JOptionPane.ERROR_MESSAGE);
            }
            break;
            case "Editar":
                fila = pedidoVista.getFilaSeleccionada();
                if (fila != -1) {
                    pedidoVista.actualizarVistaArticulo(pedidoVista.getPedido().getArticulos().get(fila));
                }
                break;
            case "Borrar":
                fila = pedidoVista.getFilaSeleccionada();
                if (fila != -1) {
                    pedidoVista.getPedido().getArticulos().remove(fila);
                    pedidoVista.actualizarTabla();
                }
        }
    }

    public PedidoVista getPedidoVista() {
        return pedidoVista;
    }

    public void setPedidoVista(PedidoVista pedidoVista) {
        this.pedidoVista = pedidoVista;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
