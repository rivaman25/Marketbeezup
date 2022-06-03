/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import com.modelos.Provincia;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Manolo
 */
public interface DAOProvincia extends CRUD<Provincia> {

    public List<Provincia> listar() throws SQLException;

    public Provincia obtener(String codigo) throws SQLException;
}
