/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.vistas;

import com.controladores.ModeloTablaArticulos;
import com.controladores.PedidoControlador;
import com.modelos.Articulo;
import com.modelos.Pedido;

/**
 *
 * @author Manolo
 */
public class PedidoVista extends javax.swing.JDialog {

    private Pedido pedido;
    private ModeloTablaArticulos modeloTablaArticulos;

    /**
     * Creates new form Pedido
     */
    public PedidoVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void actualizarVista(Pedido pedido) {
        this.pedido = pedido;
        textoTienda.setText(pedido.getTienda());
        textoMarketplace.setText(pedido.getMarketplace());
        textoIdPedido.setText(pedido.getIdPedido());
        if (pedido.getFechaPedido() != null) {
            selectorFechaPedido.setDate(new java.util.Date(pedido.getFechaPedido().getTime()));
        }
        textoNIF.setText(pedido.getDni());
        textoNombre.setText(pedido.getNombreApellidos());
        textoDireccion.setText(pedido.getDireccion());
        textoCP.setText(pedido.getCp());
        textoPoblacion.setText(pedido.getPoblacion());
        textoProvincia.setText(pedido.getProvincia());
        textoTelefono.setText(pedido.getTelefono());
        textoEmail.setText(pedido.getEmail());
        textoImporte.setText(String.valueOf(pedido.getImporte()));
        textoComision.setText(String.valueOf(pedido.getComision()));
        textoPorte.setText(String.valueOf(pedido.getCostePorte()));
        modeloTablaArticulos = new ModeloTablaArticulos(pedido.getArticulos());
        tablaArticulos.setModel(modeloTablaArticulos);
    }

    public void actualizarVistaArticulo(Articulo articulo) {
        textoIdArticulo.setText(articulo.getCodigoArticulo());
        textoDescripcion.setText(articulo.getDescripcion());
        textoUnidades.setText(String.valueOf(articulo.getCantidad()));
        textoPrecio.setText(String.valueOf(articulo.getPrecio()));
    }

    public void setControlador(PedidoControlador pedidoControlador) {
        botonGuardar.addActionListener(pedidoControlador);
        botonGuardar.setActionCommand("Guardar");
        botonRegistrarArticulo.addActionListener(pedidoControlador);
        botonRegistrarArticulo.setActionCommand("Registrar");
        botonEditar.addActionListener(pedidoControlador);
        botonEditar.setActionCommand("Editar");
        botonBorrar.addActionListener(pedidoControlador);
        botonBorrar.setActionCommand("Borrar");
    }

    public void muestraMensaje(String mensaje) {
        etiquetaMensaje.setText(mensaje);
    }

    public Articulo obtenerArticulo() throws NumberFormatException {
        Articulo articulo = new Articulo();
        if (!textoIdArticulo.getText().isBlank()) {
            articulo.setCodigoArticulo(textoIdArticulo.getText());
        }
        if (!textoDescripcion.getText().isBlank()) {
            articulo.setDescripcion(textoDescripcion.getText());
        }
        try {
            if (textoUnidades.getText().isBlank()) {
                articulo.setCantidad(0);
            } else {
                articulo.setCantidad(Integer.valueOf(textoUnidades.getText()));
            }
            if (textoPrecio.getText().isBlank()) {
                articulo.setPrecio(0);
            } else {
                articulo.setPrecio(Float.valueOf(textoPrecio.getText()));
            }
        } catch (NumberFormatException ex) {
            throw ex;
        }
        return articulo;
    }

    public void actualizarTabla() {
        modeloTablaArticulos.setArticulos(pedido.getArticulos());
        modeloTablaArticulos.fireTableDataChanged();
        textoIdArticulo.setText(null);
        textoDescripcion.setText(null);
        textoUnidades.setText(null);
        textoPrecio.setText(null);
    }

    public int getFilaSeleccionada() {
        return tablaArticulos.getSelectedRow();
    }
    
    public Pedido obtenerPedido() throws NumberFormatException {
        if (!textoTienda.getText().isBlank()) {
            pedido.setTienda(textoTienda.getText());
        }
        if (!textoMarketplace.getText().isBlank()) {
            pedido.setMarketplace(textoMarketplace.getText());
        }
        if (!textoIdPedido.getText().isBlank()) {
            pedido.setIdPedido(textoIdPedido.getText());
        }
        if (selectorFechaPedido.getDate() != null) {
            pedido.setFechaPedido(new java.sql.Timestamp(selectorFechaPedido.getDate().getTime()));
        }
        if (!textoNIF.getText().isBlank()) {
            pedido.setDni(textoNIF.getText());
        }
        if (!textoNombre.getText().isBlank()) {
            pedido.setNombreApellidos(textoNombre.getText());
        }
        if (!textoDireccion.getText().isBlank()) {
            pedido.setDireccion(textoDireccion.getText());
        }
        if (!textoCP.getText().isBlank()) {
            pedido.setCp(textoCP.getText());
        }
        if (!textoPoblacion.getText().isBlank()) {
            pedido.setPoblacion(textoPoblacion.getText());
        }
        if (!textoProvincia.getText().isBlank()) {
            pedido.setProvincia(textoProvincia.getText());
        }
        if (!textoTelefono.getText().isBlank()) {
            pedido.setTelefono(textoTelefono.getText());
        }
        if (!textoEmail.getText().isBlank()) {
            pedido.setEmail(textoEmail.getText());
        }
        try {
            if (textoComision.getText().isBlank()) {
                pedido.setComision(0);
            } else {
                pedido.setComision(Float.valueOf(textoComision.getText()));
            }
            if (textoPorte.getText().isBlank()) {
                pedido.setCostePorte(0);
            } else {
                pedido.setCostePorte(Float.valueOf(textoPorte.getText()));
            }
            if (textoImporte.getText().isBlank()) {
                pedido.setImporte(0);
            } else {
                pedido.setImporte(Float.valueOf(textoImporte.getText()));
            }
        } catch (NumberFormatException ex) {
            throw ex;
        }
        return pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ModeloTablaArticulos getModeloTablaArticulos() {
        return modeloTablaArticulos;
    }

    public void setModeloTablaArticulos(ModeloTablaArticulos modeloTablaArticulos) {
        this.modeloTablaArticulos = modeloTablaArticulos;
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

        panelPedido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textoTienda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textoMarketplace = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textoIdPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        selectorFechaPedido = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        textoNIF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textoDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textoCP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textoPoblacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textoProvincia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textoTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textoEmail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textoComision = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        textoPorte = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        textoImporte = new javax.swing.JTextField();
        panelArticulo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        textoIdArticulo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        textoDescripcion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        textoPrecio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        textoUnidades = new javax.swing.JTextField();
        botonRegistrarArticulo = new javax.swing.JButton();
        panelListaPedidos = new javax.swing.JPanel();
        botonEditar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        etiquetaMensaje = new javax.swing.JLabel();
        panelScrollArticulos = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        panelBotones = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Pedido");
        setResizable(false);

        panelPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        panelPedido.setMaximumSize(new java.awt.Dimension(740, 218));
        panelPedido.setMinimumSize(new java.awt.Dimension(740, 218));
        panelPedido.setPreferredSize(new java.awt.Dimension(740, 218));
        panelPedido.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setText("Tienda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelPedido.add(jLabel1, gridBagConstraints);

        textoTienda.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTiendaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        panelPedido.add(textoTienda, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("Marketplace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 5);
        panelPedido.add(jLabel2, gridBagConstraints);

        textoMarketplace.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        panelPedido.add(textoMarketplace, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setText("IdPedido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel3, gridBagConstraints);

        textoIdPedido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoIdPedido, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel4, gridBagConstraints);

        selectorFechaPedido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panelPedido.add(selectorFechaPedido, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("NIF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel5, gridBagConstraints);

        textoNIF.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoNIF, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel6, gridBagConstraints);

        textoNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoNombre, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel7, gridBagConstraints);

        textoDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDireccionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoDireccion, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setText("Código Postal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel8, gridBagConstraints);

        textoCP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoCP, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel9.setText("Población");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel9, gridBagConstraints);

        textoPoblacion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoPoblacion, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setText("Provincia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel10, gridBagConstraints);

        textoProvincia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoProvincia, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel11, gridBagConstraints);

        textoTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoTelefono, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel12, gridBagConstraints);

        textoEmail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoEmail, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel17.setText("Comisión");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelPedido.add(jLabel17, gridBagConstraints);

        textoComision.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoComision, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel18.setText("Porte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel18, gridBagConstraints);

        textoPorte.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoPorte, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel19.setText("Importe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelPedido.add(jLabel19, gridBagConstraints);

        textoImporte.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelPedido.add(textoImporte, gridBagConstraints);

        panelArticulo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 18))); // NOI18N
        panelArticulo.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        panelArticulo.setMaximumSize(new java.awt.Dimension(840, 2147483647));
        panelArticulo.setMinimumSize(new java.awt.Dimension(840, 118));
        panelArticulo.setPreferredSize(new java.awt.Dimension(840, 118));
        panelArticulo.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setText("Id Articulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelArticulo.add(jLabel13, gridBagConstraints);

        textoIdArticulo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelArticulo.add(textoIdArticulo, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setText("Descripción");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 5);
        panelArticulo.add(jLabel14, gridBagConstraints);

        textoDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelArticulo.add(textoDescripcion, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel15.setText("Precio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelArticulo.add(jLabel15, gridBagConstraints);

        textoPrecio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelArticulo.add(textoPrecio, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel16.setText("Unidades");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 5);
        panelArticulo.add(jLabel16, gridBagConstraints);

        textoUnidades.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelArticulo.add(textoUnidades, gridBagConstraints);

        botonRegistrarArticulo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        botonRegistrarArticulo.setText("Registrar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        panelArticulo.add(botonRegistrarArticulo, gridBagConstraints);

        panelListaPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Artículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 18))); // NOI18N
        panelListaPedidos.setMaximumSize(new java.awt.Dimension(840, 2147483647));
        panelListaPedidos.setMinimumSize(new java.awt.Dimension(840, 93));
        panelListaPedidos.setPreferredSize(new java.awt.Dimension(840, 221));
        java.awt.GridBagLayout panelListaPedidosLayout = new java.awt.GridBagLayout();
        panelListaPedidosLayout.columnWidths = new int[] {0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0};
        panelListaPedidosLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelListaPedidos.setLayout(panelListaPedidosLayout);

        botonEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        botonEditar.setText("Editar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelListaPedidos.add(botonEditar, gridBagConstraints);

        botonBorrar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        botonBorrar.setText("Borrar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelListaPedidos.add(botonBorrar, gridBagConstraints);

        etiquetaMensaje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        etiquetaMensaje.setForeground(java.awt.Color.red);
        panelListaPedidos.add(etiquetaMensaje, new java.awt.GridBagConstraints());

        tablaArticulos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tablaArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaArticulos.setShowGrid(true);
        panelScrollArticulos.setViewportView(tablaArticulos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelListaPedidos.add(panelScrollArticulos, gridBagConstraints);

        panelBotones.setMaximumSize(new java.awt.Dimension(840, 37));
        panelBotones.setMinimumSize(new java.awt.Dimension(840, 37));
        panelBotones.setPreferredSize(new java.awt.Dimension(840, 37));

        botonGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        botonGuardar.setText("Guardar");
        panelBotones.add(botonGuardar);

        botonCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        botonCancelar.setText("Cancelar");
        panelBotones.add(botonCancelar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(panelPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(panelListaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelListaPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTiendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTiendaActionPerformed

    private void textoDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDireccionActionPerformed

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
            java.util.logging.Logger.getLogger(PedidoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoVista dialog = new PedidoVista(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonRegistrarArticulo;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panelArticulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelListaPedidos;
    private javax.swing.JPanel panelPedido;
    private javax.swing.JScrollPane panelScrollArticulos;
    private com.toedter.calendar.JDateChooser selectorFechaPedido;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JTextField textoCP;
    private javax.swing.JTextField textoComision;
    private javax.swing.JTextField textoDescripcion;
    private javax.swing.JTextField textoDireccion;
    private javax.swing.JTextField textoEmail;
    private javax.swing.JTextField textoIdArticulo;
    private javax.swing.JTextField textoIdPedido;
    private javax.swing.JTextField textoImporte;
    private javax.swing.JTextField textoMarketplace;
    private javax.swing.JTextField textoNIF;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoPoblacion;
    private javax.swing.JTextField textoPorte;
    private javax.swing.JTextField textoPrecio;
    private javax.swing.JTextField textoProvincia;
    private javax.swing.JTextField textoTelefono;
    private javax.swing.JTextField textoTienda;
    private javax.swing.JTextField textoUnidades;
    // End of variables declaration//GEN-END:variables
}
