/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.sql.Connection;
import java.util.List;
import com.modelos.Articulo;
import com.modelos.Filtro;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public interface DAOArticulo extends CRUD<Articulo> {

    public List<Articulo> listar(String idPedido, java.sql.Date fechaSalida, List<String> agencias, boolean reimprimir) throws SQLException;

    public List<Articulo> listar(String atributo, String valor) throws SQLException;

    public List<Articulo> listar(Filtro filtro) throws SQLException;

    public void registrar(Articulo objet, Connection conexion) throws SQLException;

    public void registrar(List<Articulo> objetos, Connection conexion) throws SQLException;
    
    public void eliminar(String marketplace, String idPedido) throws SQLException;

    public List<String> listarEstados() throws SQLException;
}
