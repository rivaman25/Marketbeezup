/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOInterfaz;
import com.modelos.Articulo;
import com.modelos.FacturaVenta;
import com.vistas.FacturaVentaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class FacturaVentaControlador implements ActionListener {

    private Articulo articulo;
    private FacturaVentaVista facturaVista;
    private FacturaVenta factura;
    private boolean guardado;
    private boolean editar;
    private DAOInterfaz<FacturaVenta> daoFacturaVenta;
    private DAOArticulo daoArticulo;

    public FacturaVentaControlador(Articulo articulo, FacturaVentaVista facturaVista) {
        this.articulo = articulo;
        this.facturaVista = facturaVista;
        guardado = false;
        editar = false;
        daoFacturaVenta = PedidosControlador.getDaoFacturaVenta();
        daoArticulo = PedidosControlador.getDaoArticulo();
    }

    public void actualizarVista() {
        facturaVista.actualizarVista(articulo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GuardarNuevaFacturaVenta":
                factura = facturaVista.obtenerFacturaVenta();
                if (factura.getNumeroFactura().isBlank()) {
                    facturaVista.mostrarMensaje("Debe introducir un número de Factura");
                    return;
                }
                if (factura.getFechaFactura() == null) {
                    facturaVista.mostrarMensaje("Debe introducir una fecha de Factura");
                    return;
                }
                try {
                    if (editar) {
                        daoFacturaVenta.modificar(factura);
                    } else {
                        articulo.setEstado("FACTURADO");
                        daoArticulo.modificar(articulo);
                        daoFacturaVenta.registrar(factura, null);
                    }
                    guardado = true;
                    facturaVista.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(facturaVista, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CancelarNuevaFacturaVenta":
                facturaVista.dispose();
                break;
        }
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public FacturaVentaVista getFacturaVentaVista() {
        return facturaVista;
    }

    public void setFacturaVentaVista(FacturaVentaVista envioVista) {
        this.facturaVista = envioVista;
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

    public FacturaVenta getFacturaVenta() {
        return factura;
    }

    public void setFacturaVenta(FacturaVenta envio) {
        this.factura = envio;
    }

    public DAOInterfaz<FacturaVenta> getDaoFacturaVenta() {
        return daoFacturaVenta;
    }

    public void setDaoFacturaVenta(DAOInterfaz<FacturaVenta> daoFacturaVenta) {
        this.daoFacturaVenta = daoFacturaVenta;
    }

    public DAOArticulo getDaoArticulo() {
        return daoArticulo;
    }

    public void setDaoArticulo(DAOArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }
}
