/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoInterfaces;

import java.util.List;

/**
 *
 * @author Manolo
 */
public interface DAOAlmacen extends CRUD<String> {

    public List<String> obtener() throws Exception;
}
