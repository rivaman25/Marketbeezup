/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.util.List;
import com.modelos.Pedido;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 * @param <T>
 */
public interface DAOInterfazLista<T> {

    public List<T> listar(Pedido pedido, Connection conexion) throws SQLException;

    public void registrar(T objet, Connection conexion) throws SQLException;
    
    public void registrar(List<T> objetos, Connection conexion) throws SQLException;

    public void modificar(T objetos) throws SQLException;

    public void eliminar(T objetos) throws SQLException;
}
