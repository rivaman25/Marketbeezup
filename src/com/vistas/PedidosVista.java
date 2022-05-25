/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vistas;

import com.controladores.ModeloTablaPedidos;
import com.controladores.PedidosControlador;
import java.awt.Image;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumnModel;
import com.controladores.TablaPedidosCellRenderer;

/**
 *
 * @author Manolo
 */
public class PedidosVista extends javax.swing.JFrame {

    private ModeloTablaPedidos modeloTablaPedidos;
    private TablaPedidosCellRenderer tablaPedidosCellRender;
    private final DefaultComboBoxModel<String> modeloComboBuscar = new DefaultComboBoxModel<>();;

    /**
     * Creates new form Principal
     */
    public PedidosVista() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("MarketBeezUp");
        Image icon = new ImageIcon(getClass().getResource("/com/imagenes/AlbarenesWEB-ico.png")).getImage();
        setIconImage(icon);
        modeloComboBuscar.addElement("Pedido");
        modeloComboBuscar.addElement("Nombre");
        modeloComboBuscar.addElement("Dirección");
        modeloComboBuscar.addElement("Teléfono");
        modeloComboBuscar.addElement("Artículo");
        modeloComboBuscar.addElement("Compra");
        modeloComboBuscar.addElement("Ticket");
        modeloComboBuscar.addElement("Albarán");
        comboBuscar.setModel(modeloComboBuscar);
    }

    public void actualizaTabla() {
        modeloTablaPedidos = new ModeloTablaPedidos(PedidosControlador.getPedidos());
        tablaPedidosCellRender = new TablaPedidosCellRenderer(PedidosControlador.getPedidos());
        tablaPedidos.setModel(modeloTablaPedidos);
        tablaPedidos.setDefaultRenderer(Double.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(String.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(Boolean.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(java.sql.Timestamp.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(Float.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(Integer.class, tablaPedidosCellRender);
        tablaPedidos.setDefaultRenderer(java.sql.Date.class, tablaPedidosCellRender);
        TableColumnModel colModel = tablaPedidos.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(100);
        colModel.getColumn(1).setPreferredWidth(40);
        colModel.getColumn(2).setPreferredWidth(140);
        colModel.getColumn(3).setPreferredWidth(140);
        colModel.getColumn(4).setPreferredWidth(80);
        colModel.getColumn(5).setPreferredWidth(200);
        colModel.getColumn(6).setPreferredWidth(400);
        colModel.getColumn(7).setPreferredWidth(45);
        colModel.getColumn(8).setPreferredWidth(180);
        colModel.getColumn(9).setPreferredWidth(180);
        colModel.getColumn(10).setPreferredWidth(93);
        colModel.getColumn(11).setPreferredWidth(73);
        colModel.getColumn(12).setPreferredWidth(30);
        colModel.getColumn(13).setPreferredWidth(76);
        colModel.getColumn(14).setPreferredWidth(312);
        colModel.getColumn(15).setPreferredWidth(73);
        colModel.getColumn(16).setPreferredWidth(76);
        colModel.getColumn(17).setPreferredWidth(33);
        colModel.getColumn(18).setPreferredWidth(93);
        colModel.getColumn(19).setPreferredWidth(73);
        colModel.getColumn(20).setPreferredWidth(30);
        colModel.getColumn(21).setPreferredWidth(76);
        colModel.getColumn(22).setPreferredWidth(312);
        colModel.getColumn(23).setPreferredWidth(73);
        colModel.getColumn(24).setPreferredWidth(76);
        colModel.getColumn(25).setPreferredWidth(33);
        colModel.getColumn(26).setPreferredWidth(93);
        colModel.getColumn(27).setPreferredWidth(73);
        colModel.getColumn(28).setPreferredWidth(30);
        colModel.getColumn(29).setPreferredWidth(76);
        colModel.getColumn(30).setPreferredWidth(312);
        colModel.getColumn(31).setPreferredWidth(73);
        colModel.getColumn(32).setPreferredWidth(312);
        colModel.getColumn(33).setPreferredWidth(73);
        textoBuscar.setText(null);
    }
    
    public void setControlador(PedidosControlador pedidosControlador) {
        botonFiltrar.addActionListener(pedidosControlador);
        botonFiltrar.setActionCommand("Filtrar");
        menuFiltrar.addActionListener(pedidosControlador);
        menuFiltrar.setActionCommand("Filtrar");
        botonAplicarFiltro.addActionListener(pedidosControlador);
        botonAplicarFiltro.setActionCommand("AplicarFiltro");
        botonImprimirAlbaran.addActionListener(pedidosControlador);
        botonImprimirAlbaran.setActionCommand("ImprimirAlbaran");
        menuImprimirAlbaran.addActionListener(pedidosControlador);
        menuImprimirAlbaran.setActionCommand("ImprimirAlbaran");
        textoBuscar.addKeyListener(pedidosControlador);
    }

    public String getAtributoBuscar() {
        return (String) comboBuscar.getSelectedItem();
    }
    
    public String getValorBuscar() {
        return textoBuscar.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        botonFiltrar = new javax.swing.JButton();
        botonAplicarFiltro = new javax.swing.JButton();
        botonNuevoEnvio = new javax.swing.JButton();
        botonNuevaCompra = new javax.swing.JButton();
        botonImprimirAlbaran = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        comboBuscar = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textoBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpciones = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuFiltrar = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuProvincias = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuNuevoPedido = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuEditarPedido = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuNuevoEnvio = new javax.swing.JMenuItem();
        menuEditarEnvio = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuNuevaCompra = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuImprimirAlbaran = new javax.swing.JMenuItem();
        menuReimprimirAlbaran = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MARKETBEEZUP");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(3840, 60));
        jToolBar1.setMinimumSize(new java.awt.Dimension(800, 60));
        jToolBar1.setPreferredSize(new java.awt.Dimension(1366, 60));

        botonFiltrar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        botonFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Filtrar32(1).png"))); // NOI18N
        botonFiltrar.setText("Filtro");
        botonFiltrar.setToolTipText("Modificar Filtro");
        botonFiltrar.setFocusable(false);
        botonFiltrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonFiltrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(botonFiltrar);

        botonAplicarFiltro.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        botonAplicarFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Filtrar32(2).png"))); // NOI18N
        botonAplicarFiltro.setText("Filtrar");
        botonAplicarFiltro.setToolTipText("Aplicar Filtro");
        botonAplicarFiltro.setFocusable(false);
        botonAplicarFiltro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAplicarFiltro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(botonAplicarFiltro);

        botonNuevoEnvio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        botonNuevoEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Envio32.png"))); // NOI18N
        botonNuevoEnvio.setText("Envío");
        botonNuevoEnvio.setToolTipText("Añadir Envío");
        botonNuevoEnvio.setFocusable(false);
        botonNuevoEnvio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevoEnvio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNuevoEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoEnvioActionPerformed(evt);
            }
        });
        jToolBar1.add(botonNuevoEnvio);

        botonNuevaCompra.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        botonNuevaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Compra32(1).png"))); // NOI18N
        botonNuevaCompra.setText("Compra");
        botonNuevaCompra.setToolTipText("Añadir Compra");
        botonNuevaCompra.setFocusable(false);
        botonNuevaCompra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevaCompra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNuevaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaCompraActionPerformed(evt);
            }
        });
        jToolBar1.add(botonNuevaCompra);

        botonImprimirAlbaran.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        botonImprimirAlbaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Imprimir32(1).png"))); // NOI18N
        botonImprimirAlbaran.setText("Albaranes");
        botonImprimirAlbaran.setToolTipText("Imprimir Albaranes");
        botonImprimirAlbaran.setFocusable(false);
        botonImprimirAlbaran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonImprimirAlbaran.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(botonImprimirAlbaran);

        jButton4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Ticket32.png"))); // NOI18N
        jButton4.setText("Tickets");
        jButton4.setToolTipText("Obtener tickets");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Listado32.png"))); // NOI18N
        jButton5.setText("Listados");
        jButton5.setToolTipText("Obtener Listados");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Cancelar32.png"))); // NOI18N
        jButton7.setText("Cancelar ");
        jButton7.setToolTipText("Cancelar Pedido");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        jButton6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Observaciones32.png"))); // NOI18N
        jButton6.setText("Observaciones");
        jButton6.setToolTipText("Obtener Observaciones");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        comboBuscar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pedido", "Nombre", "Dirección", "Teléfono", "Artículo", "Compra", "Ticket", "Albaran", " " }));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Search.png"))); // NOI18N

        textoBuscar.setBackground(new java.awt.Color(255, 255, 255));
        textoBuscar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoBuscar.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(textoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textoBuscar)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel2);

        getContentPane().add(jToolBar1);

        jPanel1.setMaximumSize(new java.awt.Dimension(3840, 19));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 19));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Listado con filtros");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(3840, 19));
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        tablaPedidos.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPedidos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaPedidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.setShowGrid(false);
        jScrollPane1.setViewportView(tablaPedidos);

        getContentPane().add(jScrollPane1);

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuOpciones.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        menuOpciones.setText("Opciones");
        jMenu1.add(menuOpciones);
        jMenu1.add(jSeparator1);

        menuSalir.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenu2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuFiltrar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuFiltrar.setText("Filtrar Pedidos");
        menuFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFiltrarActionPerformed(evt);
            }
        });
        jMenu2.add(menuFiltrar);

        jMenuItem5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem5.setText("Actualizar");
        jMenu2.add(jMenuItem5);
        jMenu2.add(jSeparator2);

        jMenuItem1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem1.setText("Almacenes");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem2.setText("Agencias de transporte");
        jMenu2.add(jMenuItem2);

        menuProvincias.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuProvincias.setText("Provincias");
        menuProvincias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProvinciasActionPerformed(evt);
            }
        });
        jMenu2.add(menuProvincias);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pedidos");
        jMenu3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuNuevoPedido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuNuevoPedido.setText("Nuevo");
        menuNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoPedidoActionPerformed(evt);
            }
        });
        jMenu3.add(menuNuevoPedido);

        jMenuItem4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem4.setText("Importar");
        jMenu3.add(jMenuItem4);

        menuEditarPedido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuEditarPedido.setText("Editar");
        menuEditarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarPedidoActionPerformed(evt);
            }
        });
        jMenu3.add(menuEditarPedido);

        jMenuItem3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem3.setText("Borrar");
        jMenu3.add(jMenuItem3);

        jMenuItem10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem10.setText("Cancelar");
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Envíos");
        jMenu4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuNuevoEnvio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuNuevoEnvio.setText("Nuevo");
        menuNuevoEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoEnvioActionPerformed(evt);
            }
        });
        jMenu4.add(menuNuevoEnvio);

        menuEditarEnvio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuEditarEnvio.setText("Editar");
        jMenu4.add(menuEditarEnvio);

        jMenuItem7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem7.setText("Borrar");
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Compras");
        jMenu5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuNuevaCompra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuNuevaCompra.setText("Nueva");
        menuNuevaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevaCompraActionPerformed(evt);
            }
        });
        jMenu5.add(menuNuevaCompra);

        jMenuItem12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem12.setText("Editar");
        jMenu5.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem13.setText("Borrar");
        jMenu5.add(jMenuItem13);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Albaranes");
        jMenu6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        menuImprimirAlbaran.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuImprimirAlbaran.setText("Imprimir");
        jMenu6.add(menuImprimirAlbaran);

        menuReimprimirAlbaran.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuReimprimirAlbaran.setText("Reimprimir");
        jMenu6.add(menuReimprimirAlbaran);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Tickets");
        jMenu7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jMenuItem16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem16.setText("Obtener");
        jMenu7.add(jMenuItem16);

        jMenuBar1.add(jMenu7);

        jMenu8.setText("Listados");
        jMenu8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jMenuItem17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem17.setText("Obtener");
        jMenu8.add(jMenuItem17);

        jMenuBar1.add(jMenu8);

        jMenu9.setText("Observaciones");
        jMenu9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jMenuItem19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem19.setText("Nueva");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenuItem9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem9.setText("Obtener");
        jMenu9.add(jMenuItem9);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoPedidoActionPerformed
        PedidoVista pedido = new PedidoVista(this, true);
        pedido.setLocationRelativeTo(null);
        pedido.setVisible(true);
    }//GEN-LAST:event_menuNuevoPedidoActionPerformed

    private void menuEditarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarPedidoActionPerformed
        PedidoVista pedido = new PedidoVista(this, true);
        pedido.setLocationRelativeTo(null);
        pedido.setVisible(true);
    }//GEN-LAST:event_menuEditarPedidoActionPerformed

    private void menuNuevoEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoEnvioActionPerformed
        EnvioVista envio = new EnvioVista(this, true);
        envio.setLocationRelativeTo(null);
        envio.setVisible(true);
    }//GEN-LAST:event_menuNuevoEnvioActionPerformed

    private void menuNuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevaCompraActionPerformed
        CompraVista compra = new CompraVista(this, true);
        compra.setLocationRelativeTo(null);
        compra.setVisible(true);
    }//GEN-LAST:event_menuNuevaCompraActionPerformed

    private void menuProvinciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProvinciasActionPerformed
        ProvinciasVista provincias = new ProvinciasVista(this, true);
        provincias.setLocationRelativeTo(null);
        provincias.setVisible(true);
    }//GEN-LAST:event_menuProvinciasActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void menuFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFiltrarActionPerformed

    }//GEN-LAST:event_menuFiltrarActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void botonNuevoEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoEnvioActionPerformed
        EnvioVista envio = new EnvioVista(this, true);
        envio.setLocationRelativeTo(null);
        envio.setVisible(true);
    }//GEN-LAST:event_botonNuevoEnvioActionPerformed

    private void botonNuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaCompraActionPerformed
        CompraVista compra = new CompraVista(this, true);
        compra.setLocationRelativeTo(null);
        compra.setVisible(true);
    }//GEN-LAST:event_botonNuevaCompraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAplicarFiltro;
    private javax.swing.JButton botonFiltrar;
    private javax.swing.JButton botonImprimirAlbaran;
    private javax.swing.JButton botonNuevaCompra;
    private javax.swing.JButton botonNuevoEnvio;
    private javax.swing.JComboBox<String> comboBuscar;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem menuEditarEnvio;
    private javax.swing.JMenuItem menuEditarPedido;
    private javax.swing.JMenuItem menuFiltrar;
    private javax.swing.JMenuItem menuImprimirAlbaran;
    private javax.swing.JMenuItem menuNuevaCompra;
    private javax.swing.JMenuItem menuNuevoEnvio;
    private javax.swing.JMenuItem menuNuevoPedido;
    private javax.swing.JMenuItem menuOpciones;
    private javax.swing.JMenuItem menuProvincias;
    private javax.swing.JMenuItem menuReimprimirAlbaran;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTextField textoBuscar;
    // End of variables declaration//GEN-END:variables
}