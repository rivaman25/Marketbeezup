/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.ModeloTablaObservaciones;
import com.controladores.ObservacionesControlador;
import com.modelos.Observacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.Timer;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class ObservacionesVista extends javax.swing.JDialog {

    private ModeloTablaObservaciones modeloTablaObservaciones;
    private List<Observacion> observaciones;
    private final Timer TIMER;

    /**
     * Creates new form Observaciones
     */
    public ObservacionesVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        TIMER = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setVisible(false);
            }
        });
    }

    public void actualizarVista(List<Observacion> observaciones) {
        this.observaciones = observaciones;
        modeloTablaObservaciones = new ModeloTablaObservaciones(observaciones);
        tablaObservaciones.setModel(modeloTablaObservaciones);
    }

    public void setControlador(ObservacionesControlador observacionesControlador) {
        botonRegistrar.addActionListener(observacionesControlador);
        botonRegistrar.setActionCommand("Registrar");
        botonEliminar.addActionListener(observacionesControlador);
        botonEliminar.setActionCommand("Eliminar");
        botonSalir.addActionListener(observacionesControlador);
        botonSalir.setActionCommand("Salir");
        textoBuscar.addKeyListener(observacionesControlador);
    }

    public Observacion obtenerObservacion() {
        Observacion provincia = new Observacion();
        provincia.setTitulo(textoTitulo.getText());
        provincia.setDescripcion(textoDescripcion.getText());
        return provincia;
    }
    
    public String obtenerTextoBuscar() {
        return textoBuscar.getText();
    }

    public Observacion obtenerObservacionSeleccionada() {
        if (tablaObservaciones.getSelectedRow() != -1) {
            return observaciones.get(tablaObservaciones.getSelectedRow());
        } else {
            return null;
        }
    }

    public int obtenerFilaSeleccionada() {
        return tablaObservaciones.getSelectedRow();
    }

    public void mostrarMensaje(String mensaje) {
        TIMER.stop();
        etiquetaMensaje.setText(mensaje);
        etiquetaMensaje.setVisible(true);
        TIMER.setRepeats(false);
        TIMER.start();
    }
    
    public void limpiarTexto() {
        textoTitulo.setText(null);
        textoDescripcion.setText(null);
        textoTitulo.requestFocus();
    }
    
    public void limpiarTextoBuscar() {
        textoBuscar.setText(null);
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public ModeloTablaObservaciones getModeloTablaObservaciones() {
        return modeloTablaObservaciones;
    }

    public void setModeloTablaObservaciones(ModeloTablaObservaciones modeloTablaObservaciones) {
        this.modeloTablaObservaciones = modeloTablaObservaciones;
    }

    public JTable getTablaObservaciones() {
        return tablaObservaciones;
    }

    public void setTablaObservaciones(JTable tablaObservaciones) {
        this.tablaObservaciones = tablaObservaciones;
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
        textoTitulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textoDescripcion = new javax.swing.JTextField();
        botonRegistrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textoBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObservaciones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Observaciones");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaMensaje.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(255, 51, 51));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(etiquetaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 460, 20));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Título:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        textoTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textoTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTituloKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(textoTitulo, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Descripción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        textoDescripcion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textoDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDescripcionKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(textoDescripcion, gridBagConstraints);

        botonRegistrar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonRegistrar.setText("Registrar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(botonRegistrar, gridBagConstraints);

        botonEliminar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonEliminar.setText("Elminar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(botonEliminar, gridBagConstraints);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Search.png"))); // NOI18N

        textoBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        textoBuscar.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(textoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textoBuscar)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, -1));

        tablaObservaciones.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tablaObservaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaObservaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaObservaciones.setShowGrid(true);
        jScrollPane1.setViewportView(tablaObservaciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 460, 152));

        botonSalir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonSalir.setText("Salir");
        jPanel2.add(botonSalir);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 460, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTituloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            textoDescripcion.requestFocus();
        }
    }//GEN-LAST:event_textoTituloKeyPressed

    private void textoDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botonRegistrar.requestFocus();
        }
    }//GEN-LAST:event_textoDescripcionKeyPressed

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
            java.util.logging.Logger.getLogger(ObservacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ObservacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ObservacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ObservacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                ObservacionesVista dialog = new ObservacionesVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaObservaciones;
    private javax.swing.JTextField textoBuscar;
    private javax.swing.JTextField textoDescripcion;
    private javax.swing.JTextField textoTitulo;
    // End of variables declaration//GEN-END:variables
}
