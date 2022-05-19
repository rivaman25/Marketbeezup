/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelos.Articulo;
import modelos.Pedido;
import vistas.ImprimirVista;

/**
 *
 * @author Manolo
 */
public class ImprimirControlador implements ActionListener {

    private ImprimirVista imprimirVista;
    private List<Articulo> articulosImpr;

    public ImprimirControlador(ImprimirVista imprimirVista) {
        this.imprimirVista = imprimirVista;
        articulosImpr = new ArrayList<>();
        /* Se obtienen los pedidos que no están impresos, tienen fecha de salida, no están cancelados y 
            la agencia no contiene la cadena 'drop' */
        for (Pedido pedido : PedidosControlador.getPedidos()) {
            for (Articulo articulo : pedido.getArticulos()) {
                // Se comprueba que haya un envío registrado
                if (articulo.getEnvio() != null) {
                    /* Se comprueba que el pedido no esté impreso, no esté cancelado y la agencia no contenga
                        la cadena 'drop' */
                    if (articulo.getFechaHoraImpr() == null & !articulo.getEstado().equalsIgnoreCase("CANCELADO")
                            & !articulo.getEnvio().getIdAgencia().toUpperCase().contains("DROP")) {  
                        articulosImpr.add(articulo);
                    }
                }
            }
        }
    }

    public void actualizarVista() {
        imprimirVista.actualizarTabla(articulosImpr);
        imprimirVista.setVisible(true);
    }

    public ImprimirVista getImprimirVista() {
        return imprimirVista;
    }

    public void setImprimirVista(ImprimirVista imprimirVista) {
        this.imprimirVista = imprimirVista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
