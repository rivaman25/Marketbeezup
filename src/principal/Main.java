/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import controladores.PedidosControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import modelos.Pedido;
import vistas.PedidosVista;

/**
 *
 * @author Manolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {

        }
        try {
            List<Pedido> pedidos = new ArrayList<>();
            PedidosVista pedidosVista = new PedidosVista();
            PedidosControlador pedidosControlador = new PedidosControlador(pedidos, pedidosVista);
            pedidosVista.setControlador(pedidosControlador);
            pedidosVista.setVisible(true);
            pedidosControlador.obtenerPedidos();
            pedidosControlador.actualizarVista();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve la fecha actual con tipo java.sql.Date
     */
    private static java.sql.Date fechaActual() {
        LocalDate fecha = LocalDate.now();
        return java.sql.Date.valueOf(fecha);
    }
}
