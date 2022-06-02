/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.principal;

import com.controladores.PedidosControlador;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.modelos.Pedido;
import com.vistas.PedidosVista;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatArcIJTheme.setup();
        // IntelliJTheme.setup(Main.class.getResourceAsStream("/com/themes/GitHub.theme.json"));
        List<Pedido> pedidos = new ArrayList<>();
        PedidosVista pedidosVista = new PedidosVista();
        pedidosVista.setVisible(true);
        PedidosControlador pedidosControlador = new PedidosControlador(pedidos, pedidosVista);
        pedidosVista.setControlador(pedidosControlador);
        pedidosControlador.obtenerPedidos();
        pedidosControlador.actualizarVista();
    }

    /**
     * Devuelve la fecha actual con tipo java.sql.Date
     *
     * @return
     */
    public static java.sql.Date fechaActual() {
        LocalDate fecha = LocalDate.now();
        return java.sql.Date.valueOf(fecha);
    }
}
