/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.DAOAgenciaImpl;
import com.dao.DAOAlbaranVentaImpl;
import com.dao.DAOAlmacenImpl;
import com.dao.DAOArticuloImpl;
import com.dao.DAOCompraImpl;
import com.dao.DAOFacturaVentaImpl;
import com.dao.DAOEnvioImpl;
import com.dao.DAOObservacionImpl;
import com.dao.DAOPedidoImpl;
import java.util.List;
import com.modelos.Pedido;
import com.vistas.PedidosVista;
import com.daoInterfaces.DAOPedido;
import com.daoInterfaces.DAOPedidoNuevos;
import com.dao.DAOPedidoNuevosImpl;
import com.dao.DAOProvinciaImpl;
import com.daoInterfaces.DAOAgencia;
import com.daoInterfaces.DAOAlmacen;
import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOEnvio;
import com.daoInterfaces.DAOInterfaz;
import com.daoInterfaces.DAOObservacion;
import com.daoInterfaces.DAOProvincia;
import com.modelos.Agencia;
import com.modelos.AlbaranVenta;
import com.modelos.Almacen;
import com.modelos.Articulo;
import com.modelos.Compra;
import com.modelos.FacturaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.modelos.Filtro;
import com.modelos.Preferencias;
import com.modelos.Provincia;
import com.principal.Main;
import com.vistas.AgenciasVista;
import com.vistas.AlbaranVentaVista;
import com.vistas.AlmacenesVista;
import com.vistas.CompraVista;
import com.vistas.EnvioVista;
import com.vistas.FacturaVentaVista;
import com.vistas.FiltroVista;
import com.vistas.ImprimirVista;
import com.vistas.ObservacionesVista;
import com.vistas.PedidoVista;
import com.vistas.PreferenciasVista;
import com.vistas.ProvinciasVista;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Gestiona el flujo de información de los pedidos entre los diferentes
 * formularios de la aplicación
 *
 * @author Manuel Rivallo Bejarano
 */
public class PedidosControlador implements ActionListener, KeyListener {

    private static List<Pedido> pedidos;
    private List<Pedido> pedidosNuevos;
    private static Filtro filtro;
    private static List<Agencia> agencias;
    private static List<Almacen> almacenes;
    private static List<String> markets;
    private static List<String> tiendas;
    private static List<String> estados;
    private static List<Provincia> provincias;
    private PedidosVista pedidosVista;
    private static DAOPedidoNuevos daoPedidoNuevos;
    private static DAOPedido daoPedido;
    private static DAOArticulo daoArticulo;
    private static DAOAgencia daoAgencia;
    private static DAOAlmacen daoAlmacen;
    private static DAOEnvio daoEnvio;
    private static DAOInterfaz<Compra> daoCompra;
    private static DAOInterfaz<FacturaVenta> daoFacturaVenta;
    private static DAOInterfaz<AlbaranVenta> daoAlbaranVenta;
    private static DAOObservacion daoObservacion;
    private static DAOProvincia daoProvincia;
    private final Preferencias PREFERENCIAS;

    public PedidosControlador(List<Pedido> pedidos, PedidosVista pedidosVista) {
        PedidosControlador.pedidos = pedidos;
        pedidosNuevos = new ArrayList<>();
        PedidosControlador.tiendas = new ArrayList<>();
        PedidosControlador.markets = new ArrayList<>();
        PedidosControlador.agencias = new ArrayList<>();
        PedidosControlador.almacenes = new ArrayList<>();
        PedidosControlador.estados = new ArrayList<>();
        PedidosControlador.provincias = new ArrayList<>();
        this.PREFERENCIAS = new Preferencias();
        this.pedidosVista = pedidosVista;
        PedidosControlador.filtro = new Filtro();
        PedidosControlador.daoProvincia = new DAOProvinciaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoAgencia = new DAOAgenciaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoAlmacen = new DAOAlmacenImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoEnvio = new DAOEnvioImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoCompra = new DAOCompraImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoFacturaVenta = new DAOFacturaVentaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoAlbaranVenta = new DAOAlbaranVentaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoArticulo = new DAOArticuloImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoObservacion = new DAOObservacionImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoPedido = new DAOPedidoImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                PREFERENCIAS.getPassMarket());
        PedidosControlador.daoPedidoNuevos = new DAOPedidoNuevosImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPOnline(),
                PREFERENCIAS.getPuertoOnline(), PREFERENCIAS.getBdOnline(), PREFERENCIAS.getUsuarioOnline(),
                PREFERENCIAS.getPassOnline());
        try {
            pedidosNuevos.addAll(daoPedidoNuevos.obtenerPedidosNuevos());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(pedidosVista, "No hay conexión con la Base de Datos, no se actualizarán los pedidos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
        }
        try {
            PedidosControlador.daoPedido.registrar(pedidosNuevos);
            PedidosControlador.tiendas.addAll(daoPedido.listarTiendas());
            PedidosControlador.markets.addAll(daoPedido.listarMarket());
            PedidosControlador.agencias.addAll(daoAgencia.listar());
            PedidosControlador.almacenes.addAll(daoAlmacen.listar());
            PedidosControlador.estados.addAll(daoArticulo.listarEstados());
            PedidosControlador.provincias.addAll(daoProvincia.listar());
        } catch (NullPointerException | SQLException ex) {
            pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos");
        }
        filtro.setFechaPedidoDesde(java.sql.Date.valueOf(Main.fechaActual().toLocalDate().minusDays(PREFERENCIAS.getDiasMarket())));
    }

    /**
     * Obtiene una lista de pedidos de la base de datos
     *
     * @return Número de pedidos obtenidos
     */
    public int obtenerPedidos() {
        PedidosControlador.pedidos.clear();
        try {
            PedidosControlador.pedidos.addAll(PedidosControlador.daoPedido.listar(filtro));
        } catch (SQLException ex) {
            pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos");
        }
        return PedidosControlador.pedidos.size();
    }

    /**
     * Actualiza los pedidos accediendo a la base de datos online
     *
     * @return Número de pedidos nuevos
     */
    public int actualizarPedidos() {
        pedidosNuevos.clear();
        try {
            pedidosNuevos = daoPedidoNuevos.obtenerPedidosNuevos();
            PedidosControlador.daoPedido.registrar(pedidosNuevos);

        } catch (SQLException ex) {
            pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos");
        }
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
        ImprimirVista imprimirVista;
        ImprimirControlador imprimirControlador;
        try {
            switch (e.getActionCommand()) {
                case "Configuracion":
                    PreferenciasVista preferenciasVista = new PreferenciasVista(pedidosVista, true);
                    PreferenciasControlador preferenciasControlador
                            = new PreferenciasControlador(PREFERENCIAS, preferenciasVista);
                    preferenciasVista.setControlador(preferenciasControlador);
                    preferenciasControlador.actualizarVista();
                    if (preferenciasControlador.isGuardar()) {
                        filtro.setFechaPedidoDesde(java.sql.Date.valueOf(Main.fechaActual().toLocalDate().minusDays(PREFERENCIAS.getDiasMarket())));
                        PedidosControlador.daoProvincia = new DAOProvinciaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoAgencia = new DAOAgenciaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoAlmacen = new DAOAlmacenImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoEnvio = new DAOEnvioImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoCompra = new DAOCompraImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoFacturaVenta = new DAOFacturaVentaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoAlbaranVenta = new DAOAlbaranVentaImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoArticulo = new DAOArticuloImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoObservacion = new DAOObservacionImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoPedido = new DAOPedidoImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPMarket(),
                                PREFERENCIAS.getPuertoMarket(), PREFERENCIAS.getBdMarket(), PREFERENCIAS.getUsuarioMarket(),
                                PREFERENCIAS.getPassMarket());
                        PedidosControlador.daoPedidoNuevos = new DAOPedidoNuevosImpl("jdbc:mysql://", PREFERENCIAS.getDireccionIPOnline(),
                                PREFERENCIAS.getPuertoOnline(), PREFERENCIAS.getBdOnline(), PREFERENCIAS.getUsuarioOnline(),
                                PREFERENCIAS.getPassOnline());
                        pedidosNuevos.clear();
                        PedidosControlador.pedidos.clear();
                        pedidosNuevos = new ArrayList<>();
                        PedidosControlador.tiendas.clear();
                        PedidosControlador.markets.clear();
                        PedidosControlador.agencias.clear();
                        PedidosControlador.almacenes.clear();
                        PedidosControlador.estados.clear();
                        PedidosControlador.provincias.clear();
                        try {
                            pedidosNuevos.addAll(daoPedidoNuevos.obtenerPedidosNuevos());
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(pedidosVista, "No hay conexión con la Base de Datos, no se actualizará la lista de pedidos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
                        }
                        try {
                            PedidosControlador.daoPedido.registrar(pedidosNuevos);
                            PedidosControlador.tiendas.addAll(daoPedido.listarTiendas());
                            PedidosControlador.markets.addAll(daoPedido.listarMarket());
                            PedidosControlador.agencias.addAll(daoAgencia.listar());
                            PedidosControlador.almacenes.addAll(daoAlmacen.listar());
                            PedidosControlador.estados.addAll(daoArticulo.listarEstados());
                            PedidosControlador.pedidos.addAll(PedidosControlador.daoPedido.listar(filtro));
                            PedidosControlador.provincias.addAll(daoProvincia.listar());
                        } catch (NullPointerException | SQLException ex) {
                            pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos");
                        }
                        this.actualizarVista();
                    }
                    break;
                case "Manual":
                    URL url = Thread.currentThread().getContextClassLoader().getResource("com/informes/manual.pdf");
                    try {
                        File path = new File(url.toURI());
                        Desktop.getDesktop().open(path);
                    } catch (IOException | URISyntaxException ex) {
                        Logger.getLogger(PedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "Provincias":
                    ProvinciasVista provinciasVista = new ProvinciasVista(pedidosVista, true);
                    ProvinciasControlador provinciasControlador
                            = new ProvinciasControlador(provinciasVista, PedidosControlador.provincias);
                    provinciasVista.setControlador(provinciasControlador);
                    provinciasControlador.actualizarVista();
                    break;
                case "Almacenes":
                    AlmacenesVista almacenesVista = new AlmacenesVista(pedidosVista, true);
                    AlmacenesControlador almacenesControlador
                            = new AlmacenesControlador(almacenesVista, PedidosControlador.almacenes);
                    almacenesVista.setControlador(almacenesControlador);
                    almacenesControlador.actualizarVista();
                    break;
                case "Agencias":
                    AgenciasVista agenciasVista = new AgenciasVista(pedidosVista, true);
                    AgenciasControlador agenciasControlador
                            = new AgenciasControlador(agenciasVista, PedidosControlador.agencias);
                    agenciasVista.setControlador(agenciasControlador);
                    agenciasControlador.actualizarVista();
                    break;
                case "Filtrar":
                    // Se inicia el formulario de selección de filtros para la lista de pedidos
                    FiltroVista filtroVista = new FiltroVista(pedidosVista, true);
                    filtroVista.actualizarVista(filtro, PREFERENCIAS);
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
                    imprimirVista = new ImprimirVista(pedidosVista, true);
                    imprimirControlador = new ImprimirControlador(false, imprimirVista, PREFERENCIAS);
                    imprimirVista.setControlador(imprimirControlador);
                    imprimirControlador.actualizarAgenciasVista();
                    imprimirControlador.actualizarVista();
                    if (imprimirControlador.isImpreso()) {
                        this.obtenerPedidos();
                        this.actualizarVista();
                    }
                    break;
                case "ReimprimirAlbaran":
                    // Inicia el formulario de impresión de pedidos con envío
                    imprimirVista = new ImprimirVista(pedidosVista, true);
                    imprimirControlador = new ImprimirControlador(true, imprimirVista, PREFERENCIAS);
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
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea eliminar el envío del pedido seleccionado?",
                                        "Eliminar Envío", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    daoEnvio.eliminar(articulo.getEnvio());
                                    articulo.setFechaHoraImpr(null);
                                    daoArticulo.modificar(articulo);
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
                case "NuevaCompra":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getCompra() == null) {
                                CompraVista compraVista = new CompraVista(pedidosVista, true);
                                compraVista.setTitle("Nueva Compra");
                                CompraControlador compraControlador = new CompraControlador(articulo, compraVista);
                                compraVista.setControlador(compraControlador);
                                compraControlador.actualizarVista();
                                if (compraControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido ya tiene registrado una compra, "
                                        + "puede seleccionar el menú Compras y luego Editar.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EditarCompra":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getCompra() != null) {
                                CompraVista compraVista = new CompraVista(pedidosVista, true);
                                compraVista.setTitle("Editar Compra");
                                CompraControlador compraControlador = new CompraControlador(articulo, compraVista);
                                compraControlador.setEditar(true);
                                compraVista.setControlador(compraControlador);
                                compraControlador.actualizarVista();
                                if (compraControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado una compra, puede seleccionar el menú Compras y luego Nuevo.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EliminarCompra":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getCompra() != null) {
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea eliminar la compra del pedido seleccionado?",
                                        "Eliminar Compra", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    daoCompra.eliminar(articulo.getCompra());
                                    articulo.setEstado("NUEVO");
                                    daoArticulo.modificar(articulo);
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado una compra.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "NuevoAlbaran":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getAlbaranVenta() == null) {
                                AlbaranVentaVista albaranVentaVista = new AlbaranVentaVista(pedidosVista, true);
                                albaranVentaVista.setTitle("Nuevo Albarán de Venta");
                                AlbaranVentaControlador albaranVentaControlador = new AlbaranVentaControlador(articulo, albaranVentaVista);
                                albaranVentaVista.setControlador(albaranVentaControlador);
                                albaranVentaControlador.actualizarVista();
                                if (albaranVentaControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado un Albarán de Venta, "
                                        + "puede seleccionar el menú Ventas y luego Nuevo Albarán.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EditarAlbaran":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getAlbaranVenta() != null) {
                                AlbaranVentaVista albaranVentaVista = new AlbaranVentaVista(pedidosVista, true);
                                albaranVentaVista.setTitle("Editar Albarán de Venta");
                                AlbaranVentaControlador albaranVentaControlador = new AlbaranVentaControlador(articulo, albaranVentaVista);
                                albaranVentaControlador.setEditar(true);
                                albaranVentaVista.setControlador(albaranVentaControlador);
                                albaranVentaControlador.actualizarVista();
                                if (albaranVentaControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido ya tiene registrado un Albarán de Venta, "
                                        + "puede seleccionar el menú Ventas y luego Editar Albarán.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EliminarAlbaran":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getAlbaranVenta() != null) {
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea eliminar el Albarán de Venta del pedido seleccionado?",
                                        "Eliminar Albarán de Venta", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    daoAlbaranVenta.eliminar(articulo.getAlbaranVenta());
                                    // articulo.setEstado("NUEVO");
                                    // daoArticulo.modificar(articulo);
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado un Albarán de Venta");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "NuevaFactura":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getFacturaVenta() == null) {
                                FacturaVentaVista facturaVentaVista = new FacturaVentaVista(pedidosVista, true);
                                facturaVentaVista.setTitle("Nueva Factura de Venta");
                                FacturaVentaControlador facturaVentaControlador = new FacturaVentaControlador(articulo, facturaVentaVista);
                                facturaVentaVista.setControlador(facturaVentaControlador);
                                facturaVentaControlador.actualizarVista();
                                if (facturaVentaControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado una Factura de Venta, "
                                        + "puede seleccionar el menú Ventas y luego Nueva Factura.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EditarFactura":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getFacturaVenta() != null) {
                                FacturaVentaVista facturaVentaVista = new FacturaVentaVista(pedidosVista, true);
                                facturaVentaVista.setTitle("Editar Factura de Venta");
                                FacturaVentaControlador facturaVentaControlador = new FacturaVentaControlador(articulo, facturaVentaVista);
                                facturaVentaControlador.setEditar(true);
                                facturaVentaVista.setControlador(facturaVentaControlador);
                                facturaVentaControlador.actualizarVista();
                                if (facturaVentaControlador.isGuardado()) {
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido ya tiene registrado un Albarán de Venta, "
                                        + "puede seleccionar el menú Ventas y luego Editar Albarán.");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "EliminarFactura":
                    articulo = pedidosVista.obtenerArticuloSeleccionado();
                    if (articulo != null) {
                        if (articulo.getEstado().equals("ANULADO")) {
                            pedidosVista.mostrarMensaje("El pedido está anulado.");
                        } else {
                            if (articulo.getFacturaVenta() != null) {
                                if (JOptionPane.showConfirmDialog(pedidosVista, "¿Desea eliminar la Factura de Venta del pedido seleccionado?",
                                        "Eliminar Factura", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                    daoFacturaVenta.eliminar(articulo.getFacturaVenta());
                                    // articulo.setEstado("NUEVO");
                                    // daoArticulo.modificar(articulo);
                                    obtenerPedidos();
                                    actualizarVista();
                                }
                            } else {
                                pedidosVista.mostrarMensaje("El pedido no tiene registrado una Factura de Venta");
                            }
                        }
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
                case "Observaciones":
                    ObservacionesVista observacionesVista;
                    ObservacionesControlador observacionesControlador;
                    pedido = pedidosVista.obtenerPedidoSeleccionado();
                    if (pedido != null) {
                        observacionesVista = new ObservacionesVista(pedidosVista, true);
                        observacionesControlador = new ObservacionesControlador(observacionesVista, pedido);
                        observacionesVista.setControlador(observacionesControlador);
                        observacionesControlador.actualizarVista();
                    } else {
                        pedidosVista.mostrarMensaje("Seleccione un pedido.");
                    }
                    break;
            }
        } catch (HeadlessException | SQLException ex) {
            pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            List<Pedido> pedidosBuscar;
            if (!pedidosVista.getValorBuscar().isBlank()) {
                try {
                    pedidosBuscar = daoPedido.listar(pedidosVista.getAtributoBuscar(), pedidosVista.getValorBuscar());
                    if (!pedidosBuscar.isEmpty()) {
                        PedidosControlador.pedidos.clear();
                        PedidosControlador.pedidos.addAll(pedidosBuscar);
                        actualizarVista();

                    }
                } catch (SQLException ex) {
                    pedidosVista.mostrarMensaje("No hay conexión con la Base de Datos.");
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

    public static List<Agencia> getAgencias() {
        return agencias;
    }

    public static void setAgencias(List<Agencia> agencias) {
        PedidosControlador.agencias = agencias;
    }

    public static List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public static void setAlmacenes(List<Almacen> almacenes) {
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

    public static DAOPedidoNuevos getDaoPedidoNuevos() {
        return daoPedidoNuevos;
    }

    public static void setDaoPedidoNuevos(DAOPedidoNuevos daoPedidoNuevos) {
        PedidosControlador.daoPedidoNuevos = daoPedidoNuevos;
    }

    public static DAOPedido getDaoPedido() {
        return daoPedido;
    }

    public static void setDaoPedido(DAOPedido daoPedido) {
        PedidosControlador.daoPedido = daoPedido;
    }

    public static DAOArticulo getDaoArticulo() {
        return daoArticulo;
    }

    public static void setDaoArticulo(DAOArticulo daoArticulo) {
        PedidosControlador.daoArticulo = daoArticulo;
    }

    public static DAOAgencia getDaoAgencia() {
        return daoAgencia;
    }

    public static void setDaoAgencia(DAOAgencia daoAgencia) {
        PedidosControlador.daoAgencia = daoAgencia;
    }

    public static DAOAlmacen getDaoAlmacen() {
        return daoAlmacen;
    }

    public static void setDaoAlmacen(DAOAlmacen daoAlmacen) {
        PedidosControlador.daoAlmacen = daoAlmacen;
    }

    public static DAOEnvio getDaoEnvio() {
        return daoEnvio;
    }

    public static void setDaoEnvio(DAOEnvio daoEnvio) {
        PedidosControlador.daoEnvio = daoEnvio;
    }

    public static DAOInterfaz<Compra> getDaoCompra() {
        return daoCompra;
    }

    public static void setDaoCompra(DAOInterfaz<Compra> daoCompra) {
        PedidosControlador.daoCompra = daoCompra;
    }

    public static DAOInterfaz<FacturaVenta> getDaoFacturaVenta() {
        return daoFacturaVenta;
    }

    public static void setDaoFacturaVenta(DAOInterfaz<FacturaVenta> daoFacturaVenta) {
        PedidosControlador.daoFacturaVenta = daoFacturaVenta;
    }

    public static DAOInterfaz<AlbaranVenta> getDaoAlbaranVenta() {
        return daoAlbaranVenta;
    }

    public static void setDaoAlbaranVenta(DAOInterfaz<AlbaranVenta> daoAlbaranVenta) {
        PedidosControlador.daoAlbaranVenta = daoAlbaranVenta;
    }

    public static DAOObservacion getDaoObservacion() {
        return daoObservacion;
    }

    public static void setDaoObservacion(DAOObservacion daoObservacion) {
        PedidosControlador.daoObservacion = daoObservacion;
    }

    public static DAOProvincia getDaoProvincia() {
        return daoProvincia;
    }

    public static void setDaoProvincia(DAOProvincia daoProvincia) {
        PedidosControlador.daoProvincia = daoProvincia;
    }

    public static List<Provincia> getProvincias() {
        return provincias;
    }

    public static void setProvincias(List<Provincia> provincias) {
        PedidosControlador.provincias = provincias;
    }
}
