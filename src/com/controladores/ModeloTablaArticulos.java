/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Articulo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class ModeloTablaArticulos extends AbstractTableModel {

    private List<Articulo> articulos;
    private final Class[] COLUMN_CLASS = {String.class, String.class, Float.class, Integer.class};
    private final String[] COLUMN_NAMES = {"Id Articulo", "Descripción", "Precio", "Unidades"};

    public ModeloTablaArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public int getRowCount() {
        return articulos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return articulos.get(row).getCodigoArticulo();
            case 1:
                return articulos.get(row).getDescripcion();
            case 2:
                return articulos.get(row).getPrecio();
            case 3:
                return articulos.get(row).getCantidad();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     *
     * @param columnIndex
     * @return
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASS[columnIndex];
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}
