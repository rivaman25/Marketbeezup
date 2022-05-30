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

/**
 *
 * @author Manolo
 */
public class PedidoControlador implements ActionListener {

    private PedidoVista pedidoVista;
    private Pedido pedido;
    private final DAOPedido daoPedido;
    private boolean guardar;

    public PedidoControlador(PedidoVista pedidoVista, Pedido pedido) {
        this.pedidoVista = pedidoVista;
        this.pedido = new Pedido(pedido);
        daoPedido = new DAOPedidoImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        guardar = false;
    }

    public void actualizarVista() {
        pedidoVista.actualizarVista(pedido);
        pedidoVista.setLocationRelativeTo(null);
        pedidoVista.setVisible(true);
    }

    public boolean isGuardar() {
        return guardar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Articulo articulo;
        int fila;
        switch (e.getActionCommand()) {
            case "Guardar":
                try {
                pedidoVista.obtenerPedido();
            } catch (NumberFormatException ex) {
                pedidoVista.muestraMensaje("Introduzca un valor numérico válido");
                return;
            }
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
            // Registro para cada artículo del pedido el marketplace y el idPedido
            for (Articulo art : pedido.getArticulos()) {
                art.setIdPedido(pedido.getIdPedido());
                art.setMarketplace(pedido.getMarketplace());
            }
            try {
                if (pedidoVista.isEditar()) {
                    daoPedido.modificar(pedido);
                    guardar = true;
                    pedidoVista.dispose();
                } else if (daoPedido.obtener(pedido.getMarketplace(), pedido.getIdPedido()).getIdPedido() == null
                        || daoPedido.obtener(pedido.getMarketplace(), pedido.getIdPedido()).getMarketplace() == null) {
                    daoPedido.registrar(pedido);
                    guardar = true;
                    pedidoVista.dispose();

                } else {
                    pedidoVista.muestraMensaje("El pedido existe, no se puede guardar");
                }
            } catch (Exception ex) {
                Logger.getLogger(PedidoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case "Registrar":
                try {
                articulo = pedidoVista.obtenerArticulo();
                articulo.setEstado("NUEVO");
            } catch (NumberFormatException ex) {
                pedidoVista.muestraMensaje("Introduzca un valor numérico válido en la cantidad o el precio");
                return;
            }

            if (articulo.getCodigoArticulo() == null) {
                pedidoVista.muestraMensaje("Introduzca un valor para el campo Código de Artículo");
                return;
            }
            if (articulo.getDescripcion() == null) {
                pedidoVista.muestraMensaje("Introduzca un valor para el campo Descripción");
                return;
            }
            fila = Articulo.existeArticulo(articulo.getCodigoArticulo(), pedido.getArticulos());
            if (fila == -1) {
                pedido.NuevoArticulo(articulo);
            } else {
                pedido.getArticulos().get(fila).setDescripcion(articulo.getDescripcion());
                pedido.getArticulos().get(fila).setCantidad(articulo.getCantidad());
                pedido.getArticulos().get(fila).setPrecio(articulo.getPrecio());
            }
            pedidoVista.actualizarTabla();
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
