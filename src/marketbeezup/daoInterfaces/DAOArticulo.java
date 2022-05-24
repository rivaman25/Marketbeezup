/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package marketbeezup.daoInterfaces;

import java.sql.Connection;
import java.util.List;
import marketbeezup.modelos.Articulo;
import marketbeezup.modelos.Filtro;

/**
 *
 * @author Manolo
 */
public interface DAOArticulo extends CRUD<Articulo> {
    
    public void actualizarFechaHoraImpr(java.sql.Timestamp fechaHoraImpr, List<Articulo> articulos) throws Exception;

    public List<Articulo> listar(String idPedido, java.sql.Date fechaSalida, List<String> agencias, boolean reimprimir) throws Exception;

    public List<Articulo> listar(String atributo, String valor) throws Exception;

    public List<Articulo> listar(Filtro filtro) throws Exception;

    public void registrar(Articulo objet, Connection conexion) throws Exception;

    public void registrar(List<Articulo> objetos, Connection conexion) throws Exception;

    public List<String> listarEstados() throws Exception;
}
