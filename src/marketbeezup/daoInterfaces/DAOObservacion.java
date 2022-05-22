/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketbeezup.daoInterfaces;

import java.util.List;
import marketbeezup.modelos.Articulo;
import marketbeezup.modelos.Observacion;
import java.sql.Connection;

/**
 *
 * @author Manolo
 */
public interface DAOObservacion extends CRUD<Observacion> {

    public List<Observacion> listar(List<Articulo> articulos) throws Exception;
    
    public void registrar(List<Observacion> observaciones, Connection conexion) throws Exception;
}
