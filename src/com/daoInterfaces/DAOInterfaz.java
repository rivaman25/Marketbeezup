/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.sql.Connection;
import com.modelos.Articulo;
import com.modelos.Filtro;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 * @param <T>
 */
public interface DAOInterfaz<T> {
    
    public T obtener(Articulo articulo, Filtro filtro, Connection conexion) throws SQLException;

    public void registrar(T  objeto, Connection conexion) throws SQLException;

    public void modificar(T  objeto) throws SQLException;

    public void eliminar(T  objeto) throws SQLException;
}
