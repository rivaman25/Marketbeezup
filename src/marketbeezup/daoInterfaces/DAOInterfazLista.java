/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package marketbeezup.daoInterfaces;

import java.util.List;
import marketbeezup.modelos.Pedido;
import java.sql.Connection;

/**
 *
 * @author Manolo
 * @param <T>
 */
public interface DAOInterfazLista<T> {

    public List<T> listar(Pedido pedido, Connection conexion) throws Exception;

    public void registrar(T objet, Connection conexion) throws Exception;
    
    public void registrar(List<T> objetos, Connection conexion) throws Exception;

    public void modificar(T objetos) throws Exception;

    public void eliminar(T objetos) throws Exception;
}
