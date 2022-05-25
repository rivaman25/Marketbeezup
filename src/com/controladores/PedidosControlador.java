/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.DAOAgenciaImpl;
import com.dao.DAOAlmacenImpl;
import com.dao.DAOArticuloImpl;
import com.dao.DAOPedidoImpl;
import java.util.List;
import com.modelos.Pedido;
import com.vistas.PedidosVista;
import com.daoInterfaces.DAOPedido;
import com.daoInterfaces.DAOPedidoNuevos;
import com.dao.DAOPedidoNuevosImpl;
import com.daoInterfaces.DAOAgencia;
import com.daoInterfaces.DAOAlmacen;
import com.daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;
import com.vistas.FiltroVista;
import com.vistas.ImprimirVista;
import com.vistas.PedidoVista;

/**
 *
 * @author Manolo
 */
public class PedidosControlador implements ActionListener, KeyListener {

    private static List<Pedido> pedidos;
    private static Filtro filtro;
    private static List<String> agencias;
    private static List<String> almacenes;
    private static List<String> markets;
    private static List<String> tiendas;
    private static List<String> estados;
    private PedidosVista pedidosVista;
    DAOPedido daoPedido;
    DAOArticulo daoArticulo;
    DAOPedidoNuevos daoPedidoNuevos;
    DAOAgencia daoAgencias;
    DAOAlmacen daoAlmacenes;

    public PedidosControlador(List<Pedido> pedidos, PedidosVista pedidosVista) throws Exception {
        PedidosControlador.pedidos = pedidos;
        this.pedidosVista = pedidosVista;
        PedidosControlador.filtro = new Filtro();
        this.daoPedidoNuevos = new DAOPedidoNuevosImpl("jdbc:mysql://", "localhost", 3306, "online", "root", "Mrbmysql2536");
        this.daoPedido = new DAOPedidoImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        this.daoArticulo = new DAOArticuloImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        this.daoAgencias = new DAOAgenciaImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        this.daoAlmacenes = new DAOAlmacenImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        List<Pedido> pedidosNuevos = daoPedidoNuevos.obtenerPedidosNuevos();
        this.daoPedido.registrar(pedidosNuevos);
        PedidosControlador.tiendas = daoPedido.listarTiendas();
        PedidosControlador.markets = daoPedido.listarMarket();
        PedidosControlador.agencias = daoAgencias.obtener();
        PedidosControlador.almacenes = daoAlmacenes.obtener();
        PedidosControlador.estados = daoArticulo.listarEstados();
    }

    public int obtenerPedidos() throws Exception {
        PedidosControlador.pedidos.clear();
        PedidosControlador.pedidos.addAll(this.daoPedido.listar(filtro));
        return pedidos.size();
    }

    public int actualizarPedidos() throws Exception {
        List<Pedido> pedidosNuevos = daoPedidoNuevos.obtenerPedidosNuevos();
        this.daoPedido.registrar(pedidosNuevos);
        return pedidosNuevos.size();
    }

    public void actualizarVista() {
        pedidosVista.actualizaTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "Filtrar":
                    // Se inicia el formulario de selección de filtros para la lista de pedidos
                    FiltroVista filtroVista = new FiltroVista(pedidosVista, true);
                    filtroVista.actualizarVista(filtro);
                    filtroVista.setLocationRelativeTo(null);
                    filtroVista.setVisible(true);
                    // Si se selecciona aplicar en el formulario de filtros se muestra la lista de pedidos filtrada
                    if (filtroVista.getBotonSeleccionado().equals("APLICAR")) {
                        // Se obtiene el nuevo filtro
                        filtro = filtroVista.getFiltroNuevo();
                        // Se obtienen los pedidos filtrados
                        this.obtenerPedidos();
                        this.actualizarVista();
                    }
                    break;
                case "AplicarFiltro":
                    // Se actualiza la tabla con los pedidos filtrados
                    this.obtenerPedidos();
                    this.actualizarVista();
                    break;
                case "ImprimirAlbaran":
                    ImprimirVista imprimirVista = new ImprimirVista(pedidosVista, true);
                    ImprimirControlador imprimirControlador = new ImprimirControlador(imprimirVista);
                    imprimirVista.setControlador(imprimirControlador);
                    imprimirControlador.actualizarAgenciasVista();
                    imprimirControlador.actualizarVista();
                    break;
                case "NuevoPedido":
                    Pedido pedido = new Pedido();
                    PedidoVista pedidoVista = new PedidoVista(pedidosVista, true);
                    PedidoControlador pedidoControlador = new PedidoControlador(pedidoVista, pedido);
                    
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            List<Pedido> pedidosBuscar;
            if (!pedidosVista.getValorBuscar().isBlank()) {
                try {
                    pedidosBuscar = daoPedido.listar(pedidosVista.getAtributoBuscar(), pedidosVista.getValorBuscar());
                    if (!pedidosBuscar.isEmpty()) {
                        PedidosControlador.pedidos.clear();
                        PedidosControlador.pedidos.addAll(pedidosBuscar);
                        actualizarVista();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(List<Pedido> pedidos) {
        PedidosControlador.pedidos = pedidos;
    }

    public PedidosVista getPedidosVista() {
        return pedidosVista;
    }

    public void setPedidosVista(PedidosVista pedidosVista) {
        this.pedidosVista = pedidosVista;
    }

    public static Filtro getFiltro() {
        return filtro;
    }

    public static void setFiltro(Filtro filtro) {
        PedidosControlador.filtro = filtro;
    }

    public static List<String> getAgencias() {
        return agencias;
    }

    public static void setAgencias(List<String> agencias) {
        PedidosControlador.agencias = agencias;
    }

    public static List<String> getAlmacenes() {
        return almacenes;
    }

    public static void setAlmacenes(List<String> almacenes) {
        PedidosControlador.almacenes = almacenes;
    }

    public static List<String> getMarkets() {
        return markets;
    }

    public static void setMarkets(List<String> markets) {
        PedidosControlador.markets = markets;
    }

    public static List<String> getTiendas() {
        return tiendas;
    }

    public static void setTiendas(List<String> tiendas) {
        PedidosControlador.tiendas = tiendas;
    }

    public static List<String> getEstados() {
        return estados;
    }

    public static void setEstados(List<String> estados) {
        PedidosControlador.estados = estados;
    }
}
