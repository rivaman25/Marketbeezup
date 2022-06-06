/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Almacen;
import com.vistas.AlmacenesVista;
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
public class AlmacenesControlador implements ActionListener, KeyListener {

    private AlmacenesVista almacenesVista;
    private List<Almacen> almacenes;

    public AlmacenesControlador(AlmacenesVista almacenesVista, List<Almacen> almacenes) {
        this.almacenesVista = almacenesVista;
        this.almacenes = almacenes;
    }

    public void actualizarVista() {
        almacenesVista.actualizarVista(almacenes);
        almacenesVista.setLocationRelativeTo(null);
        almacenesVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Almacen almacen;
        switch (e.getActionCommand()) {
            case "Registrar":
                almacen = almacenesVista.obtenerAlmacen();
                if (almacen.getIdAlmacen().isBlank()) {
                    almacenesVista.mostrarMensaje("Introduzca un valor para el código de almacen");
                    return;
                }
                /**
                 * if (almacen.getIdAlmacen().length() > 4) {
                 * almacenesVista.mostrarMensaje("El código de almacen solo
                 * puede tener 4 caracteres"); return; } *
                 */
                if (almacen.getIdAlmacen().isBlank()) {
                    almacenesVista.mostrarMensaje("Introduzca un valor para el nombre de la almacen");
                    return;
                }
                try {
                    if (PedidosControlador.getDaoAlmacen().obtener(almacen.getIdAlmacen()) == null) {
                        PedidosControlador.getDaoAlmacen().registrar(almacen);
                        PedidosControlador.getAlmacenes().add(0, almacen);
                        almacenesVista.getModeloTablaAlmacenes().fireTableDataChanged();
                        almacenesVista.limpiarTexto();
                    } else {
                        almacenesVista.mostrarMensaje("El código de almacen ya existe en la Base de Datos");
                    }
                } catch (SQLException sqle) {
                    switch (sqle.getErrorCode()) {
                        case 1406:
                            almacenesVista.mostrarMensaje("Valor demasiado largo para el identificador de almacén");
                            break;
                        default:
                            almacenesVista.mostrarMensaje("No hay conexión con la Base de Datos");
                    }
                    System.out.println("Código de Error: " + sqle.getErrorCode() + "\n"
                            + "SLQState: " + sqle.getSQLState() + "\n"
                            + "Mensaje: " + sqle.getMessage() + "\n");
                    Throwable t = sqle.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t + "\n");
                        t = t.getCause();
                    }
                    SQLException exc = sqle.getNextException();
                    while (exc != null) {
                        System.out.println("Código de Error: " + sqle.getErrorCode() + "\n"
                                + "SLQState: " + sqle.getSQLState() + "\n"
                                + "Mensaje: " + sqle.getMessage() + "\n");
                    }
                }
                break;
            case "Eliminar":
                if (almacenesVista.obtenerAlmacenSeleccionado() == null) {
                    almacenesVista.mostrarMensaje("No se ha seleccionado ninguna almacen");
                } else {
                    if (JOptionPane.showConfirmDialog(almacenesVista, "¿Desea anular la almacen seleccionada?",
                            "Eliminar Almacen", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        try {
                            PedidosControlador.getDaoAlmacen().eliminar(almacenesVista.obtenerAlmacenSeleccionado());
                            PedidosControlador.getAlmacenes().remove(almacenesVista.obtenerFilaSeleccionada());
                            almacenesVista.getModeloTablaAlmacenes().fireTableDataChanged();
                        } catch (SQLException sqle) {
                            switch (sqle.getErrorCode()) {
                                case 1451:
                                    almacenesVista.mostrarMensaje("No se puede borrar, hay registros relacionados");
                                    break;
                                default:
                                    almacenesVista.mostrarMensaje("Error de conexión, no se ha podido eliminar");
                            }                
                            System.out.println("Código de Error: " + sqle.getErrorCode() + "\n"
                                    + "SLQState: " + sqle.getSQLState() + "\n"
                                    + "Mensaje: " + sqle.getMessage() + "\n");
                            Throwable t = sqle.getCause();
                            while (t != null) {
                                System.out.println("Causa: " + t + "\n");
                                t = t.getCause();
                            }
                            SQLException exc = sqle.getNextException();
                            while (exc != null) {
                                System.out.println("Código de Error: " + sqle.getErrorCode() + "\n"
                                        + "SLQState: " + sqle.getSQLState() + "\n"
                                        + "Mensaje: " + sqle.getMessage() + "\n");
                            }
                        }
                    }
                }
                break;
            case "Salir":
                almacenesVista.dispose();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String textoBuscar = almacenesVista.obtenerTextoBuscar();
            if (!textoBuscar.isBlank()) {
                for (Almacen almacen : almacenes) {
                    if (almacen.getIdAlmacen().toUpperCase().contains(textoBuscar.toUpperCase())
                            || almacen.getAlmacen().toUpperCase().contains(textoBuscar.toUpperCase())) {
                        almacenesVista.getTablaAlmacenes().getSelectionModel().
                                setSelectionInterval(almacenes.indexOf(almacen), almacenes.indexOf(almacen));
                        almacenesVista.getTablaAlmacenes().scrollRectToVisible(
                                new Rectangle(almacenesVista.getTablaAlmacenes().getCellRect(almacenes.indexOf(almacen), 0, true)));
                        almacenesVista.limpiarTextoBuscar();
                        return;
                    }
                }
                almacenesVista.mostrarMensaje("Registro no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public AlmacenesVista getAlmacenesVista() {
        return almacenesVista;
    }

    public void setAlmacenesVista(AlmacenesVista almacenesVista) {
        this.almacenesVista = almacenesVista;
    }

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }
}
