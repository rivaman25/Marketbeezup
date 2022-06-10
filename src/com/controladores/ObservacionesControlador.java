/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Observacion;
import com.modelos.Pedido;
import com.vistas.ObservacionesVista;
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
public class ObservacionesControlador implements ActionListener, KeyListener {

    private ObservacionesVista observacionesVista;
    private final Pedido pedido;

    public ObservacionesControlador(ObservacionesVista observacionesVista, Pedido pedido) {
        this.observacionesVista = observacionesVista;
        this.pedido = pedido;
    }

    public void actualizarVista() {
        observacionesVista.actualizarVista(pedido.getObservaciones());
        observacionesVista.setLocationRelativeTo(null);
        observacionesVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Observacion observacion;
        switch (e.getActionCommand()) {
            case "Registrar":
                observacion = observacionesVista.obtenerObservacion();
                if (observacion.getTitulo().isBlank()) {
                    observacionesVista.mostrarMensaje("Introduzca un valor para el título de observación");
                    return;
                }
                if (observacion.getTitulo().length() > 30) {
                    observacionesVista.mostrarMensaje("El título de observación solo puede tener 30 caracteres");
                    return;
                }
                if (observacion.getDescripcion().isBlank()) {
                    observacionesVista.mostrarMensaje("Introduzca un valor para la descripción de la observación");
                    return;
                }
                try {
                    if (!Observacion.existeTitulo(observacion.getTitulo(), pedido.getObservaciones())) {
                        observacion.setIdPedido(pedido.getIdPedido());
                        observacion.setMarketplace(pedido.getMarketplace());
                        PedidosControlador.getDaoObservacion().registrar(observacion);
                        pedido.getObservaciones().add(0, observacion);
                        observacionesVista.getModeloTablaObservaciones().fireTableDataChanged();
                        observacionesVista.limpiarTexto();
                    } else {
                        observacionesVista.mostrarMensaje("El título de observación ya existe en la Base de Datos");
                    }
                } catch (SQLException ex) {
                    observacionesVista.mostrarMensaje("No hay conexión con la Base de Datos");
                }
                break;
            case "Eliminar":
                if (observacionesVista.obtenerObservacionSeleccionada() == null) {
                    observacionesVista.mostrarMensaje("No se ha seleccionado ninguna observacion");
                } else {
                    if (JOptionPane.showConfirmDialog(observacionesVista, "¿Desea anular la observacion seleccionada?",
                            "Eliminar Observacion", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        try {
                            PedidosControlador.getDaoObservacion().eliminar(observacionesVista.obtenerObservacionSeleccionada());
                            pedido.getObservaciones().remove(observacionesVista.obtenerFilaSeleccionada());
                            observacionesVista.getModeloTablaObservaciones().fireTableDataChanged();
                        } catch (SQLException ex) {
                            observacionesVista.mostrarMensaje("Error de conexión, no se ha podido eliminar");
                        }
                    }
                }
                break;
            case "Salir":
                observacionesVista.dispose();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String textoBuscar = observacionesVista.obtenerTextoBuscar();
            if (!textoBuscar.isBlank()) {
                for (Observacion observacion : pedido.getObservaciones()) {
                    if (observacion.getTitulo().toUpperCase().contains(textoBuscar.toUpperCase())
                            || observacion.getDescripcion().toUpperCase().contains(textoBuscar.toUpperCase())) {
                        observacionesVista.getTablaObservaciones().getSelectionModel().
                                setSelectionInterval(pedido.getObservaciones().indexOf(observacion), pedido.getObservaciones().indexOf(observacion));
                        observacionesVista.getTablaObservaciones().scrollRectToVisible(
                                new Rectangle(observacionesVista.getTablaObservaciones().getCellRect(pedido.getObservaciones().indexOf(observacion), 0, true)));
                        observacionesVista.limpiarTextoBuscar();
                        return;
                    }
                }
                observacionesVista.mostrarMensaje("Registro no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public ObservacionesVista getObservacionesVista() {
        return observacionesVista;
    }

    public void setObservacionesVista(ObservacionesVista observacionesVista) {
        this.observacionesVista = observacionesVista;
    }
}
