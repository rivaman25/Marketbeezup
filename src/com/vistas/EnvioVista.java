/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.EnvioControlador;
import com.controladores.PedidosControlador;
import com.modelos.Almacen;
import com.modelos.Articulo;
import com.modelos.Envio;
import com.principal.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class EnvioVista extends javax.swing.JDialog {

    private Articulo articulo;
    private final Timer TIMER;
    private final List<String> almacenes;

    /**
     * Creates new form Envio
     */
    public EnvioVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        almacenes = new ArrayList<>();
        for (Almacen almacen : PedidosControlador.getAlmacenes()) {
            almacenes.add(almacen.getIdAlmacen() + " - " + almacen.getAlmacen());
        }
        // Repite una tarea cada cierto tiempo, en este caso oculta una etiqueta cada n milisegundos
        TIMER = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setVisible(false);
            }
        });
    }

    /**
     * Actuliza el formulario con los valores del artículo y muestra la lista de
     * agencia y almacenes
     *
     * @param articulo Artículo que registrará el nuevo envío
     */
    public void actualizarVista(Articulo articulo) {
        this.articulo = articulo;
        textoMarketplace.setText(articulo.getMarketplace());
        textoIdPedido.setText(articulo.getIdPedido());
        textoCodigoArticulo.setText(articulo.getCodigoArticulo());
        // Muestra la lista de agencias para registrar la seleccionada en el envío
        DefaultComboBoxModel<String> comboAgenciasModel = new DefaultComboBoxModel<>();
        comboAgenciasModel.addAll(PedidosControlador.getAgencias());
        comboAgenciaSalida.setModel(comboAgenciasModel);
        // Muestra la lista de almacenes para registrar el seleccionado en el envío
        DefaultComboBoxModel<String> comboAlmacenesModel = new DefaultComboBoxModel<>();
        comboAlmacenesModel.addAll(almacenes);
        comboAlmacenSalida.setModel(comboAlmacenesModel);
        if (articulo.getEnvio() == null) {
            selectorFechaSalida.setDate(new java.util.Date(Main.fechaActual().getTime()));
        } else {
            selectorFechaSalida.setDate(new java.util.Date(articulo.getEnvio().getFechaSalida().getTime()));
            comboAgenciaSalida.setSelectedItem(articulo.getEnvio().getIdAgencia());
            comboAlmacenSalida.setSelectedItem(articulo.getEnvio().getIdAlmacen());
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Añade el listener implementado en envioControlador a los botones del
     * formulario
     *
     * @param envioControlador
     */
    public void setControlador(EnvioControlador envioControlador) {
        botonGuardar.addActionListener(envioControlador);
        botonGuardar.setActionCommand("GuardarNuevoEnvio");
        botonCancelar.addActionListener(envioControlador);
        botonCancelar.setActionCommand("CancelarNuevoEnvio");
    }

    /**
     * Devuelve un envio con los valores introducidos en el formulario
     *
     * @return Envio con los valores introducidos en el formulario
     */
    public Envio obtenerEnvio() {
        Envio envio = new Envio();
        envio.setCodigoArticulo(articulo.getCodigoArticulo());
        envio.setMarketplace(articulo.getMarketplace());
        envio.setIdPedido(articulo.getIdPedido());
        if (selectorFechaSalida.getDate() != null) {
            envio.setFechaSalida(new java.sql.Date(selectorFechaSalida.getDate().getTime()));
        }
        if (comboAgenciaSalida.getSelectedItem() != null) {
            envio.setIdAgencia((String) comboAgenciaSalida.getSelectedItem());
        }
        if (comboAlmacenSalida.getSelectedItem() != null) {
            envio.setIdAlmacen(PedidosControlador.getAlmacenes().get(comboAlmacenSalida.getSelectedIndex()).getIdAlmacen());
        }
        return envio;
    }

    /**
     * Muestra un mensaje durante un intervalo de tiempo controlado por el timer
     *
     * @param mensaje Mensaje que se muestra en la etiqueta
     */
    public void mostrarMensaje(String mensaje) {
        TIMER.stop(); // Detiene el timer si está en ejecución
        etiquetaMensaje.setText(mensaje);
        etiquetaMensaje.setVisible(true);
        TIMER.setRepeats(false); // Indica al timer que solo se ejecute una vez
        TIMER.start(); // Inicia el timer para que la etiqueta se oculte pasados n milisegundos
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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

        etiquetaMensaje = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textoMarketplace = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        selectorFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        textoIdPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboAgenciaSalida = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        textoCodigoArticulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboAlmacenSalida = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Envío");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaMensaje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(255, 51, 51));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaMensaje.setPreferredSize(new java.awt.Dimension(10, 10));
        getContentPane().add(etiquetaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 630, 18));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envío", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Marketplace:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        textoMarketplace.setEditable(false);
        textoMarketplace.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoMarketplace.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(textoMarketplace, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Fecha salida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        selectorFechaSalida.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel1.add(selectorFechaSalida, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("ID Pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        textoIdPedido.setEditable(false);
        textoIdPedido.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoIdPedido.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(textoIdPedido, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Agencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        comboAgenciaSalida.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel1.add(comboAgenciaSalida, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Código Artículo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        textoCodigoArticulo.setEditable(false);
        textoCodigoArticulo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoCodigoArticulo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(textoCodigoArticulo, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Almacén salida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel6, gridBagConstraints);

        comboAlmacenSalida.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel1.add(comboAlmacenSalida, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 731, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jTable1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 117, 731, 167));

        botonGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonGuardar.setText("Guardar");
        jPanel2.add(botonGuardar);

        botonCancelar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonCancelar.setText("Cancelar");
        jPanel2.add(botonCancelar);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 731, -1));

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
            java.util.logging.Logger.getLogger(EnvioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EnvioVista dialog = new EnvioVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonGuardar;
    private javax.swing.JComboBox<String> comboAgenciaSalida;
    private javax.swing.JComboBox<String> comboAlmacenSalida;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser selectorFechaSalida;
    private javax.swing.JTextField textoCodigoArticulo;
    private javax.swing.JTextField textoIdPedido;
    private javax.swing.JTextField textoMarketplace;
    // End of variables declaration//GEN-END:variables
}
