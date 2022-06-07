/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.PedidosControlador;
import com.modelos.Agencia;
import com.modelos.Almacen;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import com.modelos.Filtro;
import com.modelos.Preferencias;
import com.principal.Main;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class FiltroVista extends javax.swing.JDialog {

    private Filtro filtroNuevo;
    private String botonSeleccionado;
    private Preferencias preferencias;
    private List<String> almacenes;
    private List<String> agencias;

    /**
     * Creates new form Filtro
     *
     * @param parent
     * @param modal
     */
    public FiltroVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            filtroNuevo = new Filtro();
            almacenes = new ArrayList<>();
            agencias = new ArrayList<>();
            DefaultListModel<String> listaTiendasModel = new DefaultListModel<>();
            listaTiendasModel.addAll(PedidosControlador.getTiendas());
            listaTiendas.setModel(listaTiendasModel);
            DefaultListModel<String> listaMarketModel = new DefaultListModel<>();
            listaMarketModel.addAll(PedidosControlador.getMarkets());
            listaMarket.setModel(listaMarketModel);
            for (Agencia agencia : PedidosControlador.getAgencias()) {
                agencias.add(agencia.getIdAgencia());
            }
            DefaultListModel<String> listaAgenciasModel = new DefaultListModel<>();
            listaAgenciasModel.addAll(agencias);
            listaAgencias.setModel(listaAgenciasModel);
            DefaultListModel<String> listaAlmacenesModel = new DefaultListModel<>();
            for (Almacen almacen : PedidosControlador.getAlmacenes()) {
                almacenes.add(almacen.getIdAlmacen() + " - " + almacen.getAlmacen());
            }
            listaAlmacenesModel.addAll(almacenes);
            listaAlmacenes.setModel(listaAlmacenesModel);
            DefaultListModel<String> listaEstadosModel = new DefaultListModel<>();
            listaEstadosModel.addAll(PedidosControlador.getEstados());
            listaEstados.setModel(listaEstadosModel);
            botonSeleccionado = "CERRAR";
        } catch (Exception ex) {
            Logger.getLogger(FiltroVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBotonSeleccionado() {
        return botonSeleccionado;
    }

    public void setBotonSeleccionado(String botonSeleccionado) {
        this.botonSeleccionado = botonSeleccionado;
    }

    public Filtro getFiltroNuevo() {
        return filtroNuevo;
    }

    public void setFiltroNuevo(Filtro filtro) {
        this.filtroNuevo = filtro;
    }

    public void actualizarVista(Filtro filtro, Preferencias preferencias) {
        this.filtroNuevo = filtro;
        this.preferencias = preferencias;
        if (PedidosControlador.getFiltro().getAgencias() != null) {
            listaAgencias.setSelectedIndices(PedidosControlador.getFiltro().getAgencias());
        }
        if (PedidosControlador.getFiltro().getAlmacenes() != null) {
            listaAlmacenes.setSelectedIndices(PedidosControlador.getFiltro().getAlmacenes());
        }
        if (PedidosControlador.getFiltro().getMarketplace() != null) {
            listaMarket.setSelectedIndices(PedidosControlador.getFiltro().getMarketplace());
        }
        if (PedidosControlador.getFiltro().getTiendas() != null) {
            listaTiendas.setSelectedIndices(PedidosControlador.getFiltro().getTiendas());
        }
        if (PedidosControlador.getFiltro().getEstados() != null) {
            listaEstados.setSelectedIndices(PedidosControlador.getFiltro().getEstados());
        }
        selectorFechaCompraDesde.setDate(filtro.getFechaCompraDesde());
        selectorFechaCompraHasta.setDate(filtro.getFechaCompraHasta());
        selectorFechaDocDesde.setDate(filtro.getFechaVentaDesde());
        selectorFechaDocHasta.setDate(filtro.getFechaVentaHasta());
        selectorFechaPedidoDesde.setDate(filtro.getFechaPedidoDesde());
        selectorFechaPedidoHasta.setDate(filtro.getFechaPedidoHasta());
        selectorFechaSalidaDesde.setDate(filtro.getFechaSalidaDesde());
        selectorFechaSalidaHasta.setDate(filtro.getFechaSalidaHasta());
        selectorFechaAlbaranDesde.setDate(filtro.getFechaAlbaranDesde());
        selectorFechaAlbaranHasta.setDate(filtro.getFechaAlbaranHasta());
        checkObservaciones.setSelected(filtro.isObservaciones());
        checkExiste.setSelected(filtro.isExiste());
        checkSinEnvio.setSelected(filtro.isFechaSalida());
        checkSinCompra.setSelected(filtro.isFechaCompra());
        checkSinTicket.setSelected(filtro.isFechaTicket());
        checkSinAlbaran.setSelected(filtro.isFechaAlbaran());
        if (filtro.isFechaSalida()) {
            selectorFechaSalidaDesde.setEnabled(false);
            selectorFechaSalidaHasta.setEnabled(false);
        }
        if (filtro.isFechaCompra()) {
            selectorFechaCompraDesde.setEnabled(false);
            selectorFechaCompraHasta.setEnabled(false);
        }
        if (filtro.isFechaTicket()) {
            selectorFechaDocDesde.setEnabled(false);
            selectorFechaDocHasta.setEnabled(false);
        }
        if (filtro.isFechaAlbaran()) {
            selectorFechaAlbaranDesde.setEnabled(false);
            selectorFechaAlbaranHasta.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelTienda = new javax.swing.JLabel();
        labelMarket = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaTiendas = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaMarket = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaAgencias = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaEstados = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaAlmacenes = new javax.swing.JList<>();
        labelAlmacen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelFechaPedido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        selectorFechaPedidoDesde = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        selectorFechaPedidoHasta = new com.toedter.calendar.JDateChooser();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelFechaSalida = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectorFechaSalidaDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        selectorFechaSalidaHasta = new com.toedter.calendar.JDateChooser();
        checkSinEnvio = new javax.swing.JCheckBox();
        labelFechaCompra = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectorFechaCompraDesde = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        selectorFechaCompraHasta = new com.toedter.calendar.JDateChooser();
        checkSinCompra = new javax.swing.JCheckBox();
        labelFechaDoc = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        selectorFechaDocDesde = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        selectorFechaDocHasta = new com.toedter.calendar.JDateChooser();
        checkSinTicket = new javax.swing.JCheckBox();
        labelFechaAlbaran = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        selectorFechaAlbaranDesde = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        selectorFechaAlbaranHasta = new com.toedter.calendar.JDateChooser();
        checkSinAlbaran = new javax.swing.JCheckBox();
        checkObservaciones = new javax.swing.JCheckBox();
        checkExiste = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" Seleccionar Filtro");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        labelTienda.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelTienda.setText("Tienda");

        labelMarket.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelMarket.setText("Marketplace");

        labelAgencia.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelAgencia.setText("Agencia");

        labelEstado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelEstado.setText("Estado");

        listaTiendas.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(listaTiendas);

        listaMarket.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(listaMarket);

        listaAgencias.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(listaAgencias);

        listaEstados.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(listaEstados);

        listaAlmacenes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane5.setViewportView(listaAlmacenes);

        labelAlmacen.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelAlmacen.setText("Almacén");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTienda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgencia)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEstado)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMarket)
                    .addComponent(labelTienda)
                    .addComponent(labelAgencia)
                    .addComponent(labelEstado)
                    .addComponent(labelAlmacen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 800));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelFechaPedido.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labelFechaPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaPedido.setText("Fecha Pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel3.add(labelFechaPedido, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel1, gridBagConstraints);

        selectorFechaPedidoDesde.setDateFormatString("dd-MM-yy");
        selectorFechaPedidoDesde.setFocusTraversalPolicyProvider(true);
        selectorFechaPedidoDesde.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaPedidoDesde.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaPedidoDesde.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaPedidoDesde.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaPedidoDesde, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        selectorFechaPedidoHasta.setDateFormatString("dd-MM-yy");
        selectorFechaPedidoHasta.setFocusTraversalPolicyProvider(true);
        selectorFechaPedidoHasta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaPedidoHasta.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaPedidoHasta.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaPedidoHasta.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaPedidoHasta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel3.add(filler1, gridBagConstraints);

        labelFechaSalida.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labelFechaSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaSalida.setText("Fecha Salida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel3.add(labelFechaSalida, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        selectorFechaSalidaDesde.setDateFormatString("dd-MM-yy");
        selectorFechaSalidaDesde.setFocusTraversalPolicyProvider(true);
        selectorFechaSalidaDesde.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaSalidaDesde.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaSalidaDesde.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaSalidaDesde.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaSalidaDesde, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        selectorFechaSalidaHasta.setDateFormatString("dd-MM-yy");
        selectorFechaSalidaHasta.setFocusTraversalPolicyProvider(true);
        selectorFechaSalidaHasta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaSalidaHasta.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaSalidaHasta.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaSalidaHasta.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaSalidaHasta, gridBagConstraints);

        checkSinEnvio.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        checkSinEnvio.setText("Sin Envio");
        checkSinEnvio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkSinEnvio.setMargin(new java.awt.Insets(2, 20, 2, 2));
        checkSinEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSinEnvioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(checkSinEnvio, gridBagConstraints);

        labelFechaCompra.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labelFechaCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaCompra.setText("Fecha Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel3.add(labelFechaCompra, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        selectorFechaCompraDesde.setDateFormatString("dd-MM-yy");
        selectorFechaCompraDesde.setFocusTraversalPolicyProvider(true);
        selectorFechaCompraDesde.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaCompraDesde.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaCompraDesde.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaCompraDesde.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaCompraDesde, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        selectorFechaCompraHasta.setDateFormatString("dd-MM-yy");
        selectorFechaCompraHasta.setFocusTraversalPolicyProvider(true);
        selectorFechaCompraHasta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaCompraHasta.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaCompraHasta.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaCompraHasta.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaCompraHasta, gridBagConstraints);

        checkSinCompra.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        checkSinCompra.setText("Sin Compra");
        checkSinCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkSinCompra.setMargin(new java.awt.Insets(2, 20, 2, 2));
        checkSinCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSinCompraActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(checkSinCompra, gridBagConstraints);

        labelFechaDoc.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labelFechaDoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaDoc.setText("Fecha Ticket:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel3.add(labelFechaDoc, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        selectorFechaDocDesde.setDateFormatString("dd-MM-yy");
        selectorFechaDocDesde.setFocusTraversalPolicyProvider(true);
        selectorFechaDocDesde.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaDocDesde.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaDocDesde.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaDocDesde.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaDocDesde, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        selectorFechaDocHasta.setDateFormatString("dd-MM-yy");
        selectorFechaDocHasta.setFocusTraversalPolicyProvider(true);
        selectorFechaDocHasta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaDocHasta.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaDocHasta.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaDocHasta.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaDocHasta, gridBagConstraints);

        checkSinTicket.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        checkSinTicket.setText("Sin Ticket");
        checkSinTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkSinTicket.setMargin(new java.awt.Insets(2, 20, 2, 2));
        checkSinTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSinTicketActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(checkSinTicket, gridBagConstraints);

        labelFechaAlbaran.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labelFechaAlbaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaAlbaran.setText("Fecha Albaran:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel3.add(labelFechaAlbaran, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        selectorFechaAlbaranDesde.setDateFormatString("dd-MM-yy");
        selectorFechaAlbaranDesde.setFocusTraversalPolicyProvider(true);
        selectorFechaAlbaranDesde.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaAlbaranDesde.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaAlbaranDesde.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaAlbaranDesde.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaAlbaranDesde, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        jPanel3.add(jLabel10, gridBagConstraints);

        selectorFechaAlbaranHasta.setDateFormatString("dd-MM-yy");
        selectorFechaAlbaranHasta.setFocusTraversalPolicyProvider(true);
        selectorFechaAlbaranHasta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorFechaAlbaranHasta.setMaximumSize(new java.awt.Dimension(75, 23));
        selectorFechaAlbaranHasta.setMinimumSize(new java.awt.Dimension(75, 23));
        selectorFechaAlbaranHasta.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(selectorFechaAlbaranHasta, gridBagConstraints);

        checkSinAlbaran.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        checkSinAlbaran.setText("Sin Albarán");
        checkSinAlbaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkSinAlbaran.setMargin(new java.awt.Insets(2, 20, 2, 2));
        checkSinAlbaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSinAlbaranActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(checkSinAlbaran, gridBagConstraints);

        checkObservaciones.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        checkObservaciones.setText("Con observaciones");
        checkObservaciones.setToolTipText("Solo con observaciones");

        checkExiste.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        checkExiste.setText("Ocultar Existen");
        checkExiste.setToolTipText("Ocultar los artículos que existen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkExiste))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkObservaciones)
                    .addComponent(checkExiste))
                .addContainerGap())
        );

        botonAceptar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        botonAceptar.setText("Aplicar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });
        jPanel4.add(botonAceptar);

        botonCancelar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonCancelar.setText("Cerrar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(botonCancelar);

        botonLimpiar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(botonLimpiar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        botonSeleccionado = "CANCELAR";
        this.dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        botonSeleccionado = "APLICAR";
        filtroNuevo = new Filtro();
        if (listaAgencias.getSelectedIndices().length > 0) {
            filtroNuevo.setAgencias(listaAgencias.getSelectedIndices());
        }
        if (listaAlmacenes.getSelectedIndices().length > 0) {
            filtroNuevo.setAlmacenes(listaAlmacenes.getSelectedIndices());
        }
        if (listaMarket.getSelectedIndices().length > 0) {
            filtroNuevo.setMarketplace(listaMarket.getSelectedIndices());
        }
        if (listaTiendas.getSelectedIndices().length > 0) {
            filtroNuevo.setTiendas(listaTiendas.getSelectedIndices());
        }
        if (listaEstados.getSelectedIndices().length > 0) {
            filtroNuevo.setEstados(listaEstados.getSelectedIndices());
        }
        if (selectorFechaCompraDesde.getDate() != null) {
            filtroNuevo.setFechaCompraDesde(new java.sql.Date(selectorFechaCompraDesde.getDate().getTime()));
        }
        if (selectorFechaCompraHasta.getDate() != null) {
            filtroNuevo.setFechaCompraHasta(new java.sql.Date(selectorFechaCompraHasta.getDate().getTime()));
        }
        if (selectorFechaDocDesde.getDate() != null) {
            filtroNuevo.setFechaVentaDesde(new java.sql.Date(selectorFechaDocDesde.getDate().getTime()));
        }
        if (selectorFechaDocHasta.getDate() != null) {
            filtroNuevo.setFechaVentaHasta(new java.sql.Date(selectorFechaDocHasta.getDate().getTime()));
        }
        if (selectorFechaPedidoDesde.getDate() != null) {
            filtroNuevo.setFechaPedidoDesde(new java.sql.Date(selectorFechaPedidoDesde.getDate().getTime()));
        }
        if (selectorFechaPedidoHasta.getDate() != null) {
            filtroNuevo.setFechaPedidoHasta(new java.sql.Date(selectorFechaPedidoHasta.getDate().getTime()));
        }
        if (selectorFechaSalidaDesde.getDate() != null) {
            filtroNuevo.setFechaSalidaDesde(new java.sql.Date(selectorFechaSalidaDesde.getDate().getTime()));
        }
        if (selectorFechaSalidaHasta.getDate() != null) {
            filtroNuevo.setFechaSalidaHasta(new java.sql.Date(selectorFechaSalidaHasta.getDate().getTime()));
        }
        if (selectorFechaAlbaranDesde.getDate() != null) {
            filtroNuevo.setFechaAlbaranDesde(new java.sql.Date(selectorFechaAlbaranDesde.getDate().getTime()));
        }
        if (selectorFechaAlbaranHasta.getDate() != null) {
            filtroNuevo.setFechaAlbaranHasta(new java.sql.Date(selectorFechaAlbaranHasta.getDate().getTime()));
        }
        filtroNuevo.setObservaciones(checkObservaciones.isSelected());
        filtroNuevo.setExiste(checkExiste.isSelected());
        filtroNuevo.setFechaSalida(checkSinEnvio.isSelected());
        filtroNuevo.setFechaCompra(checkSinCompra.isSelected());
        filtroNuevo.setFechaTicket(checkSinTicket.isSelected());
        filtroNuevo.setFechaAlbaran(checkSinAlbaran.isSelected());
        this.dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        listaAgencias.clearSelection();
        listaAlmacenes.clearSelection();
        listaMarket.clearSelection();
        listaTiendas.clearSelection();
        listaEstados.clearSelection();
        checkObservaciones.setSelected(false);
        checkExiste.setSelected(false);
        checkSinEnvio.setSelected(false);
        checkSinCompra.setSelected(false);
        checkSinTicket.setSelected(false);
        checkSinAlbaran.setSelected(false);
        selectorFechaCompraDesde.setDate(null);
        selectorFechaCompraHasta.setDate(null);
        selectorFechaDocDesde.setDate(null);
        selectorFechaDocHasta.setDate(null);
        selectorFechaPedidoDesde.setDate(java.sql.Date.valueOf(
                Main.fechaActual().toLocalDate().minusDays(preferencias.getDiasMarket())));
        selectorFechaPedidoHasta.setDate(null);
        selectorFechaSalidaDesde.setDate(null);
        selectorFechaSalidaHasta.setDate(null);
        selectorFechaAlbaranDesde.setDate(null);
        selectorFechaAlbaranHasta.setDate(null);
        selectorFechaSalidaDesde.setEnabled(true);
        selectorFechaSalidaHasta.setEnabled(true);
        selectorFechaCompraDesde.setEnabled(true);
        selectorFechaCompraHasta.setEnabled(true);
        selectorFechaDocDesde.setEnabled(true);
        selectorFechaDocHasta.setEnabled(true);
        selectorFechaAlbaranDesde.setEnabled(true);
        selectorFechaAlbaranHasta.setEnabled(true);
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void checkSinEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSinEnvioActionPerformed
        selectorFechaSalidaDesde.setDate(null);
        selectorFechaSalidaHasta.setDate(null);
        selectorFechaSalidaDesde.setEnabled(!checkSinEnvio.isSelected());
        selectorFechaSalidaHasta.setEnabled(!checkSinEnvio.isSelected());
    }//GEN-LAST:event_checkSinEnvioActionPerformed

    private void checkSinCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSinCompraActionPerformed
        selectorFechaCompraDesde.setDate(null);
        selectorFechaCompraHasta.setDate(null);
        selectorFechaCompraDesde.setEnabled(!checkSinCompra.isSelected());
        selectorFechaCompraHasta.setEnabled(!checkSinCompra.isSelected());
    }//GEN-LAST:event_checkSinCompraActionPerformed

    private void checkSinTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSinTicketActionPerformed
        selectorFechaDocDesde.setDate(null);
        selectorFechaDocHasta.setDate(null);
        selectorFechaDocDesde.setEnabled(!checkSinTicket.isSelected());
        selectorFechaDocHasta.setEnabled(!checkSinTicket.isSelected());
    }//GEN-LAST:event_checkSinTicketActionPerformed

    private void checkSinAlbaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSinAlbaranActionPerformed
        selectorFechaAlbaranDesde.setDate(null);
        selectorFechaAlbaranHasta.setDate(null);
        selectorFechaAlbaranDesde.setEnabled(!checkSinAlbaran.isSelected());
        selectorFechaAlbaranHasta.setEnabled(!checkSinAlbaran.isSelected());
    }//GEN-LAST:event_checkSinAlbaranActionPerformed

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
            java.util.logging.Logger.getLogger(FiltroVista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltroVista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltroVista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltroVista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FiltroVista dialog = new FiltroVista(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JCheckBox checkExiste;
    private javax.swing.JCheckBox checkObservaciones;
    private javax.swing.JCheckBox checkSinAlbaran;
    private javax.swing.JCheckBox checkSinCompra;
    private javax.swing.JCheckBox checkSinEnvio;
    private javax.swing.JCheckBox checkSinTicket;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelAlmacen;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelFechaAlbaran;
    private javax.swing.JLabel labelFechaCompra;
    private javax.swing.JLabel labelFechaDoc;
    private javax.swing.JLabel labelFechaPedido;
    private javax.swing.JLabel labelFechaSalida;
    private javax.swing.JLabel labelMarket;
    private javax.swing.JLabel labelTienda;
    private javax.swing.JList<String> listaAgencias;
    private javax.swing.JList<String> listaAlmacenes;
    private javax.swing.JList<String> listaEstados;
    private javax.swing.JList<String> listaMarket;
    private javax.swing.JList<String> listaTiendas;
    private com.toedter.calendar.JDateChooser selectorFechaAlbaranDesde;
    private com.toedter.calendar.JDateChooser selectorFechaAlbaranHasta;
    private com.toedter.calendar.JDateChooser selectorFechaCompraDesde;
    private com.toedter.calendar.JDateChooser selectorFechaCompraHasta;
    private com.toedter.calendar.JDateChooser selectorFechaDocDesde;
    private com.toedter.calendar.JDateChooser selectorFechaDocHasta;
    private com.toedter.calendar.JDateChooser selectorFechaPedidoDesde;
    private com.toedter.calendar.JDateChooser selectorFechaPedidoHasta;
    private com.toedter.calendar.JDateChooser selectorFechaSalidaDesde;
    private com.toedter.calendar.JDateChooser selectorFechaSalidaHasta;
    // End of variables declaration//GEN-END:variables
}
