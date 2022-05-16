/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoInterfaces;

import java.sql.Connection;
import java.util.List;
import modelos.Articulo;
import modelos.Filtro;
import modelos.Pedido;

/**
 *
 * @author Manolo
 */
public interface DAOArticulo extends CRUD<Articulo> {
    
    public List<Articulo> listar(String atributo, String valor) throws Exception;

    public List<Articulo> listar(Filtro filtro) throws Exception;

    public void registrar(Articulo objet, Connection conexion) throws Exception;

    public void registrar(List<Articulo> objetos, Connection conexion) throws Exception;
    
    public List<String> listarEstados() throws Exception;
}
