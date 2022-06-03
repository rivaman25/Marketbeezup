/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Provincia;
import com.vistas.ProvinciasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Manolo
 */
public class ProvinciasControlador implements ActionListener {

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
                if (provincia.getNombreProvincia().isBlank()) {
                    provinciasVista.mostrarMensaje("Introduzca un valor para el nombre de la provincia");
                }
                try {
                    if (PedidosControlador.getDaoProvincia().obtener(provincia.getCodigoProvincia()) == null) {
                        PedidosControlador.getDaoProvincia().registrar(provincia);
                        PedidosControlador.getProvincias().add(provincia);
                        String[] arrayProvincia = new String[2];
                        arrayProvincia[0] = provincia.getCodigoProvincia();
                        arrayProvincia[1] = provincia.getNombreProvincia();
                        provinciasVista.getMODELO_TABLA_PROVINCIAS().addRow(arrayProvincia);
                    } else {
                        provinciasVista.mostrarMensaje("El código de provincia ya existe en la Base de Datos");
                    }
                } catch (SQLException ex) {
                    provinciasVista.mostrarMensaje("No hay conexión con la Base de Datos");
                }
                break;
            case "Editar":
                if (provinciasVista.obtenerProvinciaSeleccionada() == null) {
                    provinciasVista.mostrarMensaje("No se ha seleccionado ninguna provincia");
                } else {
                    
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
                            provinciasVista.getMODELO_TABLA_PROVINCIAS().removeRow(provinciasVista.obtenerFilaSeleccionada());
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
