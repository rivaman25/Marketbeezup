/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import dao.DAOAgenciaImpl;
import dao.DAOAlmacenImpl;
import dao.DAOArticuloImpl;
import dao.DAOPedidoImpl;
import java.util.List;
import modelos.Pedido;
import vistas.PedidosVista;
import daoInterfaces.DAOPedido;
import daoInterfaces.DAOPedidoNuevos;
import dao.DAOPedidoNuevosImpl;
import daoInterfaces.DAOAgencia;
import daoInterfaces.DAOAlmacen;
import daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Filtro;
import vistas.FiltroVista;

/**
 *
 * @author Manolo
 */
public class PedidosControlador implements ActionListener {

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
        if (e.getActionCommand().equals("Filtrar")) {
            // Se inicia el formulario de selección de filtros para la lista de pedidos
            FiltroVista filtroVista = new FiltroVista(pedidosVista, true);
            filtroVista.actualizarVista(filtro);
            filtroVista.setLocationRelativeTo(null);
            filtroVista.setVisible(true);
            // Si se selecciona aplicar en el formulario de filtros se muestra la lista de pedidos filtrada
            if (filtroVista.getBotonSeleccionado().equals("APLICAR")) {
                // Se obtiene el nuevo filtro
                filtro = filtroVista.getFiltroNuevo();
                try {
                    // Se obtienen los pedidos filtrados
                    this.obtenerPedidos();
                    this.actualizarVista();
                } catch (Exception ex) {
                    Logger.getLogger(PedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getActionCommand().equals("Buscar")) {
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
        if (e.getActionCommand().equals("AplicarFiltro")) {
            try {
                // Se actualiza la tabla con los pedidos filtrados
                this.obtenerPedidos();
                this.actualizarVista();
            } catch (Exception ex) {
                Logger.getLogger(PedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
