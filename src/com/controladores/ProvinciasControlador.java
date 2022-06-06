/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Provincia;
import com.vistas.ProvinciasVista;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Manolo
 */
public class ProvinciasControlador implements ActionListener, KeyListener {

    private ProvinciasVista provinciasVista;
    private List<Provincia> provincias;

    public ProvinciasControlador(ProvinciasVista provinciasVista, List<Provincia> provincias) {
        this.provinciasVista = provinciasVista;
        this.provincias = provincias;
    }

    public void actualizarVista() {
        provinciasVista.actualizarVista(provincias);
        provinciasVista.setLocationRelativeTo(null);
        provinciasVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Provincia provincia;
        switch (e.getActionCommand()) {
            case "Registrar":
                provincia = provinciasVista.obtenerProvincia();
                if (provincia.getCodigoProvincia().isBlank()) {
                    provinciasVista.mostrarMensaje("Introduzca un valor para el código de provincia");
                    return;
                }
                if (provincia.getCodigoProvincia().length() > 2) {
                    provinciasVista.mostrarMensaje("El código de provincia solo puede tener 2 caracteres");
                    return;
                }
                if (provincia.getNombreProvincia().isBlank()) {
                    provinciasVista.mostrarMensaje("Introduzca un valor para el nombre de la provincia");
                    return;
                }
                try {
                    if (PedidosControlador.getDaoProvincia().obtener(provincia.getCodigoProvincia()) == null) {
                        PedidosControlador.getDaoProvincia().registrar(provincia);
                        PedidosControlador.getProvincias().add(0, provincia);
                        provinciasVista.getModeloTablaProvincias().fireTableDataChanged();
                        provinciasVista.limpiarTexto();
                    } else {
                        provinciasVista.mostrarMensaje("El código de provincia ya existe en la Base de Datos");
                    }
                } catch (SQLException ex) {
                    provinciasVista.mostrarMensaje("No hay conexión con la Base de Datos");
                }
                break;
            case "Eliminar":
                if (provinciasVista.obtenerProvinciaSeleccionada() == null) {
                    provinciasVista.mostrarMensaje("No se ha seleccionado ninguna provincia");
                } else {
                    if (JOptionPane.showConfirmDialog(provinciasVista, "¿Desea anular la provincia seleccionada?",
                            "Eliminar Provincia", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        try {
                            PedidosControlador.getDaoProvincia().eliminar(provinciasVista.obtenerProvinciaSeleccionada());
                            PedidosControlador.getProvincias().remove(provinciasVista.obtenerFilaSeleccionada());
                            provinciasVista.getModeloTablaProvincias().fireTableDataChanged();
                        } catch (SQLException ex) {
                            provinciasVista.mostrarMensaje("Error de conexión, no se ha podido eliminar");
                        }
                    }
                }
                break;
            case "Salir":
                provinciasVista.dispose();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String textoBuscar = provinciasVista.obtenerTextoBuscar();
            if (!textoBuscar.isBlank()) {
                for (Provincia provincia : provincias) {
                    if (provincia.getCodigoProvincia().toUpperCase().contains(textoBuscar.toUpperCase()) 
                            || provincia.getNombreProvincia().toUpperCase().contains(textoBuscar.toUpperCase())) {
                        provinciasVista.getTablaProvincias().getSelectionModel().
                                setSelectionInterval(provincias.indexOf(provincia), provincias.indexOf(provincia));
                        provinciasVista.getTablaProvincias().scrollRectToVisible(
                                new Rectangle(provinciasVista.getTablaProvincias().getCellRect(provincias.indexOf(provincia), 0, true)));
                        provinciasVista.limpiarTextoBuscar();
                        return;
                    }
                }
                provinciasVista.mostrarMensaje("Registro no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public ProvinciasVista getProvinciasVista() {
        return provinciasVista;
    }

    public void setProvinciasVista(ProvinciasVista provinciasVista) {
        this.provinciasVista = provinciasVista;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
