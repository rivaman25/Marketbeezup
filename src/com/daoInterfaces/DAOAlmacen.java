/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import com.modelos.Almacen;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public interface DAOAlmacen extends CRUD<Almacen> {

    public List<Almacen> listar() throws SQLException;
    
    public Almacen obtener(String idAlmacen) throws SQLException;
}
