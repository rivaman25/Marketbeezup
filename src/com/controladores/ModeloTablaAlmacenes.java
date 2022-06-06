/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Almacen;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manolo
 */
public class ModeloTablaAlmacenes extends AbstractTableModel {

    private List<Almacen> almacenes;
    private final Class[] columnClass = {String.class, String.class};
    private final String[] columnNames = {"Id Almacén", "Almacén"};

    public ModeloTablaAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    @Override
    public int getRowCount() {
        return almacenes.size();
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
                return almacenes.get(row).getIdAlmacen();
            case 1:
                return almacenes.get(row).getAlmacen();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if (col == 1) {
            almacenes.get(row).setAlmacen((String) obj);
            try {
                PedidosControlador.getDaoAlmacen().modificar(almacenes.get(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos");
            }
        }
    }

    public List<Almacen> getAlmacens() {
        return almacenes;
    }

    public void setAlmacens(List<Almacen> provincias) {
        this.almacenes = provincias;
    }

}
