/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daoInterfaces;

import com.modelos.Articulo;
import com.modelos.Envio;
import com.modelos.Filtro;
import java.sql.Connection;

/**
 *
 * @author Manolo
 */
public interface DAOEnvio {

    public Envio obtener(Articulo articulo, Filtro filtro, Connection conexion) throws Exception;

    public void registrar(Envio envio, Connection conexion) throws Exception;

    public void modificar(Envio envio) throws Exception;

    public void eliminar(Envio envio) throws Exception;
}
