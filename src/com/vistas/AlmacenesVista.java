/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.ModeloTablaAlmacenes;
import com.controladores.AlmacenesControlador;
import com.modelos.Almacen;
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
public class AlmacenesVista extends javax.swing.JDialog {

    private ModeloTablaAlmacenes modeloTablaAlmacenes;
    private List<Almacen> almacenes;
    private final Timer TIMER;

    /**
     * Creates new form Almacenes
     */
    public AlmacenesVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        TIMER = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setVisible(false);
            }
        });
    }

    public void actualizarVista(List<Almacen> almacenes) {
        this.almacenes = almacenes;
        modeloTablaAlmacenes = new ModeloTablaAlmacenes(almacenes);
        tablaAlmacenes.setModel(modeloTablaAlmacenes);
    }

    public void setControlador(AlmacenesControlador almacenesControlador) {
        botonRegistrar.addActionListener(almacenesControlador);
        botonRegistrar.setActionCommand("Registrar");
        botonEliminar.addActionListener(almacenesControlador);
        botonEliminar.setActionCommand("Eliminar");
        botonSalir.addActionListener(almacenesControlador);
        botonSalir.setActionCommand("Salir");
        textoBuscar.addKeyListener(almacenesControlador);
    }

    public Almacen obtenerAlmacen() {
        Almacen almacen = new Almacen();
        almacen.setIdAlmacen(textoIdAlmacen.getText());
        almacen.setAlmacen(textoAlmacen.getText());
        return almacen;
    }
    
    public String obtenerTextoBuscar() {
        return textoBuscar.getText();
    }

    public Almacen obtenerAlmacenSeleccionado() {
        if (tablaAlmacenes.getSelectedRow() != -1) {
            return almacenes.get(tablaAlmacenes.getSelectedRow());
        } else {
            return null;
        }
    }

    public int obtenerFilaSeleccionada() {
        return tablaAlmacenes.getSelectedRow();
    }

    public void mostrarMensaje(String mensaje) {
        TIMER.stop();
        etiquetaMensaje.setText(mensaje);
        etiquetaMensaje.setVisible(true);
        TIMER.setRepeats(false);
        TIMER.start();
    }
    
    public void limpiarTexto() {
        textoIdAlmacen.setText(null);
        textoAlmacen.setText(null);
        textoIdAlmacen.requestFocus();
    }
    
    public void limpiarTextoBuscar() {
        textoBuscar.setText(null);
    }

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public ModeloTablaAlmacenes getModeloTablaAlmacenes() {
        return modeloTablaAlmacenes;
    }

    public void setModeloTablaAlmacenes(ModeloTablaAlmacenes modeloTablaAlmacenes) {
        this.modeloTablaAlmacenes = modeloTablaAlmacenes;
    }

    public JTable getTablaAlmacenes() {
        return tablaAlmacenes;
    }

    public void setTablaAlmacenes(JTable tablaAlmacenes) {
        this.tablaAlmacenes = tablaAlmacenes;
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
        textoIdAlmacen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textoAlmacen = new javax.swing.JTextField();
        botonRegistrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textoBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlmacenes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Almacenes");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaMensaje.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(255, 51, 51));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(etiquetaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 460, 20));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Almacen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Id Almac??n:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        textoIdAlmacen.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textoIdAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoIdAlmacenKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(textoIdAlmacen, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Almac??n:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        textoAlmacen.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoAlmacenKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(textoAlmacen, gridBagConstraints);

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
                .addComponent(textoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
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

        tablaAlmacenes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tablaAlmacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlmacenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaAlmacenes.setShowGrid(true);
        jScrollPane1.setViewportView(tablaAlmacenes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 460, 152));

        botonSalir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        botonSalir.setText("Salir");
        jPanel2.add(botonSalir);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 460, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoIdAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIdAlmacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            textoAlmacen.requestFocus();
        }
    }//GEN-LAST:event_textoIdAlmacenKeyPressed

    private void textoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoAlmacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botonRegistrar.requestFocus();
        }
    }//GEN-LAST:event_textoAlmacenKeyPressed

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
            java.util.logging.Logger.getLogger(AlmacenesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlmacenesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlmacenesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlmacenesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                AlmacenesVista dialog = new AlmacenesVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable tablaAlmacenes;
    private javax.swing.JTextField textoAlmacen;
    private javax.swing.JTextField textoBuscar;
    private javax.swing.JTextField textoIdAlmacen;
    // End of variables declaration//GEN-END:variables
}
