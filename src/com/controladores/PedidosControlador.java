/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.DAOAgenciaImpl;
import com.dao.DAOAlmacenImpl;
import com.dao.DAOArticuloImpl;
import com.dao.DAOEnvioImpl;
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
import com.daoInterfaces.DAOEnvio;
import com.modelos.Articulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;
import com.modelos.Preferencias;
import com.principal.Main;
import com.vistas.EnvioVista;
import com.vistas.FiltroVista;
import com.vistas.ImprimirVista;
import com.vistas.PedidoVista;
import com.vistas.PreferenciasVista;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Gestiona el flujo de información de los pedidos entre los diferentes
 * formularios de la aplicación
 *
 * @author Manuel Rivallo Bejarano
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
    private DAOPedido daoPedido;
    private DAOArticulo daoArticulo;
    private DAOPedidoNuevos daoPedidoNuevos;
    private DAOAgencia daoAgencias;
    private DAOAlmacen daoAlmacenes;
    private Preferencias preferencias;

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
        this.preferencias = new Preferencias();
        filtro.setFechaPedidoDesde(java.sql.Date.valueOf(Main.fechaActual().toLocalDate().minusDays(preferencias.getDiasMarket())));
    }

    /**
     * Obtiene una lista de pedidos de la base de datos
     *
     * @return Número de pedidos obtenidos
     * @throws Exception
     */
    public int obtenerPedidos() throws Exception {
        PedidosControlador.pedidos.clear();
        PedidosControlador.pedidos.addAll(this.daoPedido.listar(filtro));
        return PedidosControlador.pedidos.size();
    }

    /**
     * Actualiza los pedidos accediendo a la base de datos online
     *
     * @return Número de pedidos nuevos
     * @throws Exception
     */
    public int actualizarPedidos() throws Exception {
        List<Pedido> pedidosNuevos = daoPedidoNuevos.obtenerPedidosNuevos();
        this.daoPedido.registrar(pedidosNuevos);
        return pedidosNuevos.size();
    }

    /**
     * Actualiza la tabla de pedidos en el formulario principal
     */
    public void actualizarVista() {
        pedidosVista.actualizaTabla();
    }

    /**
     * Gestiona los eventos que se producen en el formulario principal
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PedidoVista pedidoVista;
        Pedido pedido;
        Articulo articulo;
        PedidoControlador pedidoControlador;
        DAOEnvio daoEnvio = new DAOEnvioImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        try {
            switch (e.getActionCommand()) {
                case "Configuracion":
                    PreferenciasVista preferenciasVista = new PreferenciasVista(pedidosVista, true);
                    PreferenciasControlador preferenciasControlador
                            = new PreferenciasControlador(preferencias, preferenciasVista);
                    preferenciasVista.setControlador(preferenciasControlador);
                    preferenciasControlador.actualizarVista();
                    if (preferenciasControlador.isGuardar()) {
                        filtro.setFechaPedidoDesde(java.sql.Date.valueOf(Main.fechaActual().toLocalDate().minusDays(preferencias.getDiasMarket())));
                        this.obtenerPedidos();
                        this.actualizarVista();
                    }
                    break;
                case "Filtrar":
                    // Se inicia el formulario de selección de filtros para la lista de pedidos
                    FiltroVista filtroVista = new FiltroVista(pedidosVista, true);
                    filtroVista.actualizarVista(filtro, preferencias);
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
                    // Inicia el formulario de impresión de pedidos con envío
                    ImprimirVista imprimirVista = new ImprimirVista(pedidosVista, true);
                    ImprimirControlador imprimirControlador = new ImprimirControlador(imprimirVista);
                    imprimirVista.setControlador(imprimirControlador);
                    imprimirControlador.actualizarAgenciasVista();
                    imprimirControlador.actualizarVista();
                    if (imprimirControlador.isImpreso()) {
                        this.obtenerPedidos();
                        this.actualizarVista();
                    }
                    break;
                case "NuevoPedido":
                    // Introduce un nuevo pedido en la base de datos
                    pedido = new Pedido();
                    pedidoVista = new PedidoVista(pedidosVista, true);
                    pedidoVista.setTitle("Nuevo Pedido");
                    pedidoControlador = new PedidoControlador(pedidoVista, pedido);
                    pedidoVista.setControlador(pedidoControlador);
                    pedidoControlador.actualizarVista();
                    // Si se ha registrado un nuevo pedido, actualiza la tabla de pedidos
                    if (pedidoControlador.isGuardar()) {
                        obtenerPedidos();
                        actualizarVista();
                    }
                    break;
                case "EditarPedido":
                    // Edita el pedido seleccionado
                    pedido = pedidosVista.obtenerPedidoSeleccionado();
                    if (pedido != null) {
                        pedidoVista = new PedidoVista(pedidosVista, true);
                        pedidoVista.setTitle("Editar Pedido");
                        pedidoControlador = new PedidoControlador(pedidoVista, pedido);
                        pedidoVista.setControlador(pedidoControlador);
                        pedidoVista.setEditar(true);
                        pedidoControlador.actualizarVista();
                        if (pedidoControlador.isGuardar()) {
                            obtenerPedidos();
                            actualizarVista();
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido");
                    }
                    break;
                case "EliminarPedido":
                    pedido = pedidosVista.obtenerPedidoSeleccionado();
                    if (pedido != null) {
                        if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea eliminar el pedido seleccionado?",
                                "Borrar Pedido", JOptionPane.OK_CANCEL_OPTION) == 0) {
                            daoPedido.eliminar(pedido);
                            obtenerPedidos();
                            actualizarVista();
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido");
                    }
                    break;
                case "AnularPedido":
                    pedido = pedidosVista.obtenerPedidoSeleccionado();
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    List<Articulo> articulosSinAnular = new ArrayList<>();
                    if (pedido != null) { // Se ha seleccionado un artículo del pedido
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("Esta línea de pedido está anulada.");
                        } else {
                            // Obtengo una lista de artículos del pedido que están sin anular
                            for (Articulo art : pedido.getArticulos()) {
                                if (!art.getEstado().equals("ANULADO")) {
                                    articulosSinAnular.add(art);
                                }
                            }
                            // Si hay más de un artículo sin anular, pregunto si se quieren anular todos o solo el seleccionado
                            if (articulosSinAnular.size() > 1) {
                                Object[] options = {"TODO", "LINEA", "CANCELAR"};
                                int opcion = JOptionPane.showOptionDialog(pedidosVista, "¿Desea anular todo el pedido o sólo la línea seleccionada?",
                                        "Eliminar Pedido", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                                switch (opcion) {
                                    case 0: // Se anulan todos los artículos del pedido que no estaban anulados
                                        for (Articulo art : articulosSinAnular) {
                                            art.setEstado("ANULADO");
                                            daoArticulo.modificar(art);
                                            if (art.getEnvio() != null) {
                                                daoEnvio.eliminar(art.getEnvio());
                                            }
                                        }
                                        obtenerPedidos();
                                        actualizarVista();
                                        break;
                                    case 1: // Se anula solo el artículo seleccionado
                                        articulo.setEstado("ANULADO");
                                        daoArticulo.modificar(articulo);
                                        if (articulo.getEnvio() != null) {
                                            daoEnvio.eliminar(articulo.getEnvio());
                                        }
                                        obtenerPedidos();
                                        actualizarVista();
                                        break;
                                }
                            } else { // Solo hay un artículo sin anular en el pedido, se pide confirmación de anulación
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea anular el pedido seleccionado?",
                                        "Eliminar Pedido", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    articulo.setEstado("ANULADO");
                                    daoArticulo.modificar(articulo);
                                    if (articulo.getEnvio() != null) {
                                        daoEnvio.eliminar(pedido.getArticulos().get(0).getEnvio());
                                    }
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione una línea de pedido para anular.");
                    }
                    break;
                case "NuevoEnvio":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getEnvio() == null) {
                                EnvioVista envioVista = new EnvioVista(pedidosVista, true);
                                envioVista.setTitle("Nuevo Envío");
                                EnvioControlador envioControlador = new EnvioControlador(articulo, envioVista);
                                envioVista.setControlador(envioControlador);
                                envioControlador.actualizarVista();
                                if (envioControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido ya tiene registrado un envío, puede seleccionar el menú Envíos y luego Editar.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EditarEnvio":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getEnvio() != null) {
                                EnvioVista envioVista = new EnvioVista(pedidosVista, true);
                                envioVista.setTitle("Editar Envío");
                                EnvioControlador envioControlador = new EnvioControlador(articulo, envioVista);
                                envioControlador.setEditar(true);
                                envioVista.setControlador(envioControlador);
                                envioControlador.actualizarVista();
                                if (envioControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado un envío, puede seleccionar el menú Envíos y luego Nuevo.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EliminarEnvio":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getEnvio() != null) {
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea anular el envío del pedido seleccionado?",
                                        "Eliminar Envío", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    daoEnvio.eliminar(articulo.getEnvio());
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado un envío.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
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
                    Logger.getLogger(PedidosControlador.class
                            .getName()).log(Level.SEVERE, null, ex);
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
