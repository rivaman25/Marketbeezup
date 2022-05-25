/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.DAOPedidoImpl;
import com.daoInterfaces.DAOPedido;
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
    DAOPedido daoPedido;

    public PedidoControlador(PedidoVista pedidoVista, Pedido pedido) {
        this.pedidoVista = pedidoVista;
        this.pedido = pedido;
        daoPedido = new DAOPedidoImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        pedidoVista.setVisible(true);
    }

    public void actualizarVista(Pedido pedido) {
        // pedidoVista.actualizarVista(pedido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
                pedido = pedidoVista.getPedido();
                if (Pedido.existePedido(pedido.getMarketplace(), pedido.getIdPedido(), PedidosControlador.getPedidos()) == -1) {
                    try {
                        daoPedido.registrar(pedido);
                    } catch (Exception ex) {
                        Logger.getLogger(PedidoControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "Registrar":
                pedido.NuevoArticulo(pedidoVista.obtenerArticulo());
                break;
            case "Editar":
                break;
            case "Borrar":
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
