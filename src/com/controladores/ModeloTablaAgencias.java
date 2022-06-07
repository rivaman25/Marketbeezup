/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Agencia;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manolo
 */
public class ModeloTablaAgencias extends AbstractTableModel {

    private List<Agencia> agencias;
    private final Class[] columnClass = {String.class};
    private final String[] columnNames = {"Agencia"};

    public ModeloTablaAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    }

    @Override
    public int getRowCount() {
        return agencias.size();
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
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return agencias.get(row).getIdAgencia();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if (col == 0) {
            agencias.get(row).setIdAgencia((String) obj);
            try {
                PedidosControlador.getDaoAgencia().modificar(agencias.get(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos");
            }
        }
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    }

}
