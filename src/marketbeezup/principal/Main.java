/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.principal;

import com.formdev.flatlaf.IntelliJTheme;
import com.controladores.PedidosControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Pedido;
import com.vistas.PedidosVista;

/**
 *
 * @author Manolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntelliJTheme.setup(Main.class.getResourceAsStream("/marketbeezup/themes/GitHub.theme.json"));

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
