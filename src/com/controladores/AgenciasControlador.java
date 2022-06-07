/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Agencia;
import com.vistas.AgenciasVista;
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
public class AgenciasControlador implements ActionListener, KeyListener {

    private AgenciasVista agenciasVista;
    private List<Agencia> agencias;

    public AgenciasControlador(AgenciasVista agenciasVista, List<Agencia> agencias) {
        this.agenciasVista = agenciasVista;
        this.agencias = agencias;
    }

    public void actualizarVista() {
        agenciasVista.actualizarVista(agencias);
        agenciasVista.setLocationRelativeTo(null);
        agenciasVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Agencia agencia;
        switch (e.getActionCommand()) {
            case "Registrar":
                agencia = agenciasVista.obtenerAgencia();
                if (agencia.getIdAgencia().isBlank()) {
                    agenciasVista.mostrarMensaje("Introduzca un valor para la agencia");
                    return;
                }
                if (agencia.getIdAgencia().length() > 16) {
                    agenciasVista.mostrarMensaje("El código de agencia solo puede tener 16 caracteres");
                    return;
                }
                try {
                    if (PedidosControlador.getDaoAgencia().obtener(agencia.getIdAgencia()) == null) {
                        PedidosControlador.getDaoAgencia().registrar(agencia);
                        PedidosControlador.getAgencias().add(0, agencia);
                        agenciasVista.getModeloTablaAgencias().fireTableDataChanged();
                        agenciasVista.limpiarTexto();
                    } else {
                        agenciasVista.mostrarMensaje("La agencia ya existe en la Base de Datos");
                    }
                } catch (SQLException ex) {
                    agenciasVista.mostrarMensaje("No hay conexión con la Base de Datos");
                }
                break;
            case "Eliminar":
                if (agenciasVista.obtenerAgenciaSeleccionada() == null) {
                    agenciasVista.mostrarMensaje("No se ha seleccionado ninguna agencia");
                } else {
                    if (JOptionPane.showConfirmDialog(agenciasVista, "¿Desea anular la agencia seleccionada?",
                            "Eliminar Agencia", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        try {
                            PedidosControlador.getDaoAgencia().eliminar(agenciasVista.obtenerAgenciaSeleccionada());
                            PedidosControlador.getAgencias().remove(agenciasVista.obtenerFilaSeleccionada());
                            agenciasVista.getModeloTablaAgencias().fireTableDataChanged();
                        } catch (SQLException ex) {
                            agenciasVista.mostrarMensaje("Error de conexión, no se ha podido eliminar");
                        }
                    }
                }
                break;
            case "Salir":
                agenciasVista.dispose();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String textoBuscar = agenciasVista.obtenerTextoBuscar();
            if (!textoBuscar.isBlank()) {
                for (Agencia agencia : agencias) {
                    if (agencia.getIdAgencia().toUpperCase().contains(textoBuscar.toUpperCase())) {
                        agenciasVista.getTablaAgencias().getSelectionModel().
                                setSelectionInterval(agencias.indexOf(agencia), agencias.indexOf(agencia));
                        agenciasVista.getTablaAgencias().scrollRectToVisible(
                                new Rectangle(agenciasVista.getTablaAgencias().getCellRect(agencias.indexOf(agencia), 0, true)));
                        agenciasVista.limpiarTextoBuscar();
                        return;
                    }
                }
                agenciasVista.mostrarMensaje("Registro no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public AgenciasVista getAgenciasVista() {
        return agenciasVista;
    }

    public void setAgenciasVista(AgenciasVista agenciasVista) {
        this.agenciasVista = agenciasVista;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    }
}
