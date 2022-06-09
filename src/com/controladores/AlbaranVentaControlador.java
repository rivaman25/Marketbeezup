/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOInterfaz;
import com.modelos.Articulo;
import com.modelos.AlbaranVenta;
import com.vistas.AlbaranVentaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class AlbaranVentaControlador implements ActionListener {

    private Articulo articulo;
    private AlbaranVentaVista albaranVista;
    private AlbaranVenta albaran;
    private boolean guardado;
    private boolean editar;
    private DAOInterfaz<AlbaranVenta> daoAlbaranVenta;
    private DAOArticulo daoArticulo;

    public AlbaranVentaControlador(Articulo articulo, AlbaranVentaVista albaranVista) {
        this.articulo = articulo;
        this.albaranVista = albaranVista;
        guardado = false;
        editar = false;
        daoAlbaranVenta = PedidosControlador.getDaoAlbaranVenta();
        daoArticulo = PedidosControlador.getDaoArticulo();
    }

    public void actualizarVista() {
        albaranVista.actualizarVista(articulo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GuardarNuevaAlbaranVenta":
                albaran = albaranVista.obtenerAlbaranVenta();
                if (albaran.getNumeroAlbaran().isBlank()) {
                    albaranVista.mostrarMensaje("Debe introducir un número de Albaran");
                    return;
                }
                if (albaran.getFechaAlbaran() == null) {
                    albaranVista.mostrarMensaje("Debe introducir una fecha de Albaran");
                    return;
                }
                try {
                    if (editar) {
                        daoAlbaranVenta.modificar(albaran);
                    } else {
                        articulo.setEstado("FACTURADO");
                        daoArticulo.modificar(articulo);
                        daoAlbaranVenta.registrar(albaran, null);
                    }
                    guardado = true;
                    albaranVista.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(albaranVista, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CancelarNuevaAlbaranVenta":
                albaranVista.dispose();
                break;
        }
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public AlbaranVentaVista getAlbaranVentaVista() {
        return albaranVista;
    }

    public void setAlbaranVentaVista(AlbaranVentaVista envioVista) {
        this.albaranVista = envioVista;
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

    public AlbaranVenta getAlbaranVenta() {
        return albaran;
    }

    public void setAlbaranVenta(AlbaranVenta envio) {
        this.albaran = envio;
    }

    public DAOInterfaz<AlbaranVenta> getDaoAlbaranVenta() {
        return daoAlbaranVenta;
    }

    public void setDaoAlbaranVenta(DAOInterfaz<AlbaranVenta> daoAlbaranVenta) {
        this.daoAlbaranVenta = daoAlbaranVenta;
    }

    public DAOArticulo getDaoArticulo() {
        return daoArticulo;
    }

    public void setDaoArticulo(DAOArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }
}
