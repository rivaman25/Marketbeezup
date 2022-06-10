/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.CompraControlador;
import com.modelos.Articulo;
import com.modelos.Compra;
import com.principal.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class CompraVista extends javax.swing.JDialog {

    private Articulo articulo;
    private final Timer TIMER;

    /**
     * Creates new form Compra
     */
    public CompraVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Repite una tarea cada cierto tiempo, en este caso oculta una etiqueta cada n milisegundos
        TIMER = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setVisible(false);
            }
        });
        selectorFechaCompra.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selectorFechaCompraKeyPressed(evt);
            }
        });
        selectorFechaEntrada.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selectorFechaEntradaKeyPressed(evt);
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
        if (articulo.getCompra() == null) {
            selectorFechaCompra.setDate(new java.util.Date(Main.fechaActual().getTime()));
        } else {
            textoIdCompra.setText(articulo.getCompra().getIdCompra());
            textoProveedor.setText(articulo.getCompra().getProveedor());
            selectorFechaCompra.setDate(new java.util.Date(articulo.getCompra().getFechaCompra().getTime()));
            if (articulo.getCompra().getFechaEntrada() != null) {
                selectorFechaEntrada.setDate(new java.util.Date(articulo.getCompra().getFechaEntrada().getTime()));
            }
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Añade el listener implementado en compraControlador a los botones del
     * formulario
     *
     * @param compraControlador
     */
    public void setControlador(CompraControlador compraControlador) {
        botonGuardar.addActionListener(compraControlador);
        botonGuardar.setActionCommand("GuardarNuevaCompra");
        botonCancelar.addActionListener(compraControlador);
        botonCancelar.setActionCommand("CancelarNuevaCompra");
    }

    /**
     * Devuelve un compra con los valores introducidos en el formulario
     *
     * @return Compra con los valores introducidos en el formulario
     */
    public Compra obtenerCompra() {
        Compra compra = new Compra();
        compra.setCodigoArticulo(articulo.getCodigoArticulo());
        compra.setMarketplace(articulo.getMarketplace());
        compra.setIdPedido(articulo.getIdPedido());
        compra.setIdCompra(textoIdCompra.getText());
        compra.setProveedor(textoProveedor.getText());
        if (selectorFechaCompra.getDate() != null) {
            compra.setFechaCompra(new java.sql.Date(selectorFechaCompra.getDate().getTime()));
        }
        if (selectorFechaEntrada.getDate() != null) {
            compra.setFechaEntrada(new java.sql.Date(selectorFechaEntrada.getDate().getTime()));
        }
        return compra;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textoMarketplace = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        selectorFechaEntrada = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        textoIdPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoCodigoArticulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        selectorFechaCompra = new com.toedter.calendar.JDateChooser();
        textoIdCompra = new javax.swing.JTextField();
        textoProveedor = new javax.swing.JTextField();
        etiquetaMensaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Envío");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
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
        jLabel2.setText("Proveedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        selectorFechaEntrada.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(selectorFechaEntrada, gridBagConstraints);

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
        jLabel4.setText("Fecha compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

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
        jLabel6.setText("Fecha entrada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Id Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        jPanel1.add(jLabel7, gridBagConstraints);

        selectorFechaCompra.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(selectorFechaCompra, gridBagConstraints);

        textoIdCompra.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoIdCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoIdCompraKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(textoIdCompra, gridBagConstraints);

        textoProveedor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoProveedorKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(textoProveedor, gridBagConstraints);

        etiquetaMensaje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(255, 51, 51));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaMensaje.setMaximumSize(new java.awt.Dimension(1920, 20));
        etiquetaMensaje.setMinimumSize(new java.awt.Dimension(500, 20));
        etiquetaMensaje.setPreferredSize(new java.awt.Dimension(50, 20));

        botonGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonGuardar.setText("Guardar");
        jPanel2.add(botonGuardar);

        botonCancelar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonCancelar.setText("Cancelar");
        jPanel2.add(botonCancelar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoIdCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIdCompraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            textoProveedor.requestFocus();
        }
    }//GEN-LAST:event_textoIdCompraKeyPressed

    private void textoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            selectorFechaCompra.getDateEditor().getUiComponent().requestFocus();
        }
    }//GEN-LAST:event_textoProveedorKeyPressed

    private void selectorFechaCompraKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            selectorFechaEntrada.getDateEditor().getUiComponent().requestFocus();
        }
    }

    private void selectorFechaEntradaKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botonGuardar.requestFocus();
        }
    }

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
            java.util.logging.Logger.getLogger(CompraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CompraVista dialog = new CompraVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser selectorFechaCompra;
    private com.toedter.calendar.JDateChooser selectorFechaEntrada;
    private javax.swing.JTextField textoCodigoArticulo;
    private javax.swing.JTextField textoIdCompra;
    private javax.swing.JTextField textoIdPedido;
    private javax.swing.JTextField textoMarketplace;
    private javax.swing.JTextField textoProveedor;
    // End of variables declaration//GEN-END:variables
}
