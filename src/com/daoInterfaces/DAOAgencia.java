/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public interface DAOAgencia extends CRUD<String> {

    public List<String> obtener() throws SQLException;
}
