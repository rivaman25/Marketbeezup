/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.ImprimirControlador;
import com.controladores.ModeloTablaAlbaranesImpr;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import com.modelos.Articulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class ImprimirVista extends javax.swing.JDialog {

    private ModeloTablaAlbaranesImpr modeloTablaAlbaranesImpr;
    private DefaultListModel<String> modeloListaAgencias;
    private boolean reimprimir;
    private final List<Articulo> articulos;
    private final Timer TIMER;

    /**
     * Creates new form Imprimir
     *
     * @param parent
     * @param modal
     */
    public ImprimirVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Obtenemos el límite de pantalla
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        // Obtenemos la altura del borde inferior, que es la altura de la barra de tareas
        int taskHeight = screenInsets.bottom;
        // Fijamos el tamaño de la ventana al tamaño del escritorio menos la altura de la barra de tareas
        this.setSize(this.getToolkit().getScreenSize().width, this.getToolkit().getScreenSize().height - taskHeight);
        this.setTitle("Imprimir Albarán");
        articulos = new ArrayList<>();
        selectorFecha.getJCalendar().setTodayButtonVisible(true);
        selectorFecha.getJCalendar().setNullDateButtonVisible(true);
        botonMarcar.setEnabled(false);
        TIMER = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setVisible(false);
            }
        });
    }

    public void actualizarTabla(List<Articulo> articulos) {
        // Se muestran los pedidos en la tabla
        this.articulos.addAll(articulos);
        modeloTablaAlbaranesImpr = new ModeloTablaAlbaranesImpr(articulos);
        tablaAlbaranesImpr.setModel(modeloTablaAlbaranesImpr);
        // Si se quieren reimprimir albaranen se habilita la selección de filas
        if (reimprimir) {
            this.setTitle("Reimprimir Albaranes");
            tablaAlbaranesImpr.setRowSelectionAllowed(true);
            tablaAlbaranesImpr.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
    }

    public List<Articulo> obtenerArticulosSeleccionados() {
        List<Articulo> articulosSeleccionados = new ArrayList<>();
        for (int i : tablaAlbaranesImpr.getSelectedRows()) {
            articulosSeleccionados.add(articulos.get(i));
        }
        return articulosSeleccionados;
    }

    public void mostrarMensaje(String mensaje) {
        TIMER.stop();
        etiquetaMensaje.setText(mensaje);
        etiquetaMensaje.setVisible(true);
        TIMER.start();
    }

    public void actualizarAgencias(List<Articulo> articulos) {
        List<String> agencias = new ArrayList<>();
        // Se obtiene la lista de agencias incluidas en los pedidos que se muestran en la tabla
        for (Articulo articulo : articulos) {
            if (articulo.getEnvio() != null) {
                if (!agencias.contains(articulo.getEnvio().getIdAgencia())) {
                    agencias.add(articulo.getEnvio().getIdAgencia());
                }
            }
        }
        // Se muestra el listado de agencias
        modeloListaAgencias = new DefaultListModel<>();
        modeloListaAgencias.addAll(agencias);
        listaAgencias.setModel(modeloListaAgencias);
    }

    public void deshabilitarControles() {
        botonMarcar.setEnabled(false);
        botonImprimir.setEnabled(false);
        botonFiltrar.setEnabled(false);
        botonLimpiar.setEnabled(false);
    }

    public void habilitarMarcar(boolean habilitar) {
        botonMarcar.setEnabled(habilitar);
    }

    public void setControlador(ImprimirControlador imprimirControlador) {
        reimprimir = imprimirControlador.isReimprimir();
        botonImprimir.addActionListener(imprimirControlador);
        botonImprimir.setActionCommand("ImprimirAlbaranes");
        botonFiltrar.addActionListener(imprimirControlador);
        botonFiltrar.setActionCommand("FiltrarAlbaranes");
        botonLimpiar.addActionListener(imprimirControlador);
        botonLimpiar.setActionCommand("LimpiarFiltroAlbaranes");
        botonMarcar.addActionListener(imprimirControlador);
        botonMarcar.setActionCommand("MarcarImpresos");
        botonCancelar.addActionListener(imprimirControlador);
        botonCancelar.setActionCommand("Salir");
        // selectorFecha.getDateEditor().adaddActionListener("FiltrarAlbaranes");
    }

    public List<String> getAgenciasSeleccionadas() {
        return listaAgencias.getSelectedValuesList();
    }

    public void setAgenciasSeleccionadas(String agenciaSeleccionada) {
        listaAgencias.setSelectedValue(agenciaSeleccionada, true);
    }

    public java.sql.Date getFechaSeleccionada() {
        if (selectorFecha.getDate() == null) {
            return null;
        } else {
            return new java.sql.Date(selectorFecha.getDate().getTime());
        }
    }

    public void setFechaSeleccionada(java.sql.Date fecha) {
        if (fecha != null) {
            selectorFecha.setDate(new java.util.Date(fecha.getTime()));
        } else {
            selectorFecha.setDate(null);
        }
    }

    public String getIdPedido() {
        if (textoIdPedido.getText().isBlank()) {
            return null;
        } else {
            return textoIdPedido.getText();
        }
    }

    public void setIdPedido(String idPedido) {
        textoIdPedido.setText(idPedido);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAgencias = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        selectorFecha = new com.toedter.calendar.JDateChooser();
        etiquetaMensaje = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoIdPedido = new javax.swing.JTextField();
        botonImprimir = new javax.swing.JButton();
        botonMarcar = new javax.swing.JButton();
        botonFiltrar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAlbaranesImpr = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Imprimir Albarán");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 132));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 132));
        jPanel1.setPreferredSize(new java.awt.Dimension(640, 132));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agencias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(266, 150));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(266, 150));

        listaAgencias.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listaAgencias);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Fecha:");

        selectorFecha.setDateFormatString("dd-MM-yy");
        selectorFecha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        selectorFecha.setMaximumSize(new java.awt.Dimension(150, 23));
        selectorFecha.setPreferredSize(new java.awt.Dimension(150, 23));

        etiquetaMensaje.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(255, 51, 51));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Id Pedido:");

        textoIdPedido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textoIdPedido.setMaximumSize(new java.awt.Dimension(219, 23));
        textoIdPedido.setMinimumSize(new java.awt.Dimension(219, 23));
        textoIdPedido.setPreferredSize(new java.awt.Dimension(219, 23));

        botonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Imprimir32(1).png"))); // NOI18N
        botonImprimir.setText("Imprimir");
        botonImprimir.setToolTipText("Imprimir Albaranes");
        botonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        botonMarcar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Check.png"))); // NOI18N
        botonMarcar.setText("Marcar");
        botonMarcar.setToolTipText("Marcar como Impreso");
        botonMarcar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonMarcar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        botonFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Filtrar32.png"))); // NOI18N
        botonFiltrar.setText("Filtrar");
        botonFiltrar.setToolTipText("Filtrar");
        botonFiltrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonFiltrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        botonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Limipiar32.png"))); // NOI18N
        botonLimpiar.setText("Limpiar");
        botonLimpiar.setToolTipText("Limpiar Filtro");
        botonLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Cancelar32.png"))); // NOI18N
        botonCancelar.setText("Salir");
        botonCancelar.setToolTipText("Salir");
        botonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(botonImprimir))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textoIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonMarcar)
                        .addGap(5, 5, 5)
                        .addComponent(botonFiltrar))
                    .addComponent(selectorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(botonLimpiar)
                        .addGap(5, 5, 5)
                        .addComponent(botonCancelar)
                        .addGap(105, 105, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(botonImprimir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(selectorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonMarcar)
                            .addComponent(botonFiltrar)
                            .addComponent(botonLimpiar)
                            .addComponent(botonCancelar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        getContentPane().add(jPanel1);

        tablaAlbaranesImpr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlbaranesImpr.setRowSelectionAllowed(false);
        tablaAlbaranesImpr.setShowGrid(true);
        jScrollPane2.setViewportView(tablaAlbaranesImpr);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ImprimirVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImprimirVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImprimirVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImprimirVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImprimirVista dialog = new ImprimirVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonFiltrar;
    private javax.swing.JButton botonImprimir;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonMarcar;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaAgencias;
    private com.toedter.calendar.JDateChooser selectorFecha;
    private javax.swing.JTable tablaAlbaranesImpr;
    private javax.swing.JTextField textoIdPedido;
    // End of variables declaration//GEN-END:variables
}
