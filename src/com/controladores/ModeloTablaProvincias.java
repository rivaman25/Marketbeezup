/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Provincia;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manolo
 */
public class ModeloTablaProvincias extends AbstractTableModel {

    private List<Provincia> provincias;
    private final Class[] columnClass = {String.class, String.class};
    private final String[] columnNames = {"Código", "Provincia"};

    public ModeloTablaProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    @Override
    public int getRowCount() {
        return provincias.size();
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
                return provincias.get(row).getCodigoProvincia();
            case 1:
                return provincias.get(row).getNombreProvincia();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if (col == 1) {
            provincias.get(row).setNombreProvincia((String) obj);
            try {
                PedidosControlador.getDaoProvincia().modificar(provincias.get(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos");
            }
        }
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

}
