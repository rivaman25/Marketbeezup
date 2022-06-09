/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOInterfaz;
import com.modelos.Articulo;
import com.modelos.Compra;
import com.vistas.CompraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class CompraControlador implements ActionListener {

    private Articulo articulo;
    private CompraVista compraVista;
    private Compra compra;
    private boolean guardado;
    private boolean editar;
    private DAOInterfaz<Compra> daoCompra;
    private DAOArticulo daoArticulo;

    public CompraControlador(Articulo articulo, CompraVista compraVista) {
        this.articulo = articulo;
        this.compraVista = compraVista;
        guardado = false;
        editar = false;
        daoCompra = PedidosControlador.getDaoCompra();
        daoArticulo = PedidosControlador.getDaoArticulo();
    }

    public void actualizarVista() {
        compraVista.actualizarVista(articulo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GuardarNuevaCompra":
                compra = compraVista.obtenerCompra();
                if (compra.getIdCompra().isBlank()) {
                    compraVista.mostrarMensaje("Debe introducir un Id Compra");
                    return;
                }
                if (compra.getProveedor().isBlank()) {
                    compraVista.mostrarMensaje("Debe introducir un Proveedor");
                    return;
                }
                if (compra.getFechaCompra() == null) {
                    compraVista.mostrarMensaje("Debe introducir una fecha de compra");
                    return;
                }
                if (compra.getFechaEntrada() == null) {
                    compraVista.mostrarMensaje("Debe introducir una fecha de entrada");
                    return;
                }
                try {
                    if (editar) {
                        daoCompra.modificar(compra);
                    } else {
                        articulo.setEstado("COMPRADO");
                        daoArticulo.modificar(articulo);
                        daoCompra.registrar(compra, null);
                    }
                    guardado = true;
                    compraVista.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(compraVista, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CancelarNuevaCompra":
                compraVista.dispose();
                break;
        }
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public CompraVista getCompraVista() {
        return compraVista;
    }

    public void setCompraVista(CompraVista envioVista) {
        this.compraVista = envioVista;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra envio) {
        this.compra = envio;
    }

    public DAOInterfaz<Compra> getDaoCompra() {
        return daoCompra;
    }

    public void setDaoCompra(DAOInterfaz<Compra> daoCompra) {
        this.daoCompra = daoCompra;
    }

    public DAOArticulo getDaoArticulo() {
        return daoArticulo;
    }

    public void setDaoArticulo(DAOArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }
}
