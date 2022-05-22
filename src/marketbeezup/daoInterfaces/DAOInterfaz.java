/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package marketbeezup.daoInterfaces;

import java.sql.Connection;
import marketbeezup.modelos.Articulo;
import marketbeezup.modelos.Filtro;

/**
 *
 * @author Manolo
 * @param <T>
 */
public interface DAOInterfaz<T> {
    
    public T obtener(Articulo articulo, Filtro filtro, Connection conexion) throws Exception;

    public void registrar(T  objeto, Connection conexion) throws Exception;

    public void modificar(T  objeto) throws Exception;

    public void eliminar(T  objeto) throws Exception;
}
