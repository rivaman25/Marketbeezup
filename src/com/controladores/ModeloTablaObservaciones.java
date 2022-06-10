/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Observacion;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class ModeloTablaObservaciones extends AbstractTableModel {

    private List<Observacion> observaciones;
    private final Class[] columnClass = {String.class, String.class};
    private final String[] columnNames = {"Título", "Descripción"};

    public ModeloTablaObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int getRowCount() {
        return observaciones.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int col) {
        return columnClass[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return observaciones.get(row).getTitulo();
            case 1:
                return observaciones.get(row).getDescripcion();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if (col == 1) {
            observaciones.get(row).setDescripcion((String) obj);
            try {
                PedidosControlador.getDaoObservacion().modificar(observaciones.get(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos");
            }
        }
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

}
