/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import com.modelos.Agencia;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public interface DAOAgencia extends CRUD<Agencia> {

    public List<Agencia> listar() throws SQLException;

    public Agencia obtener(String idAgencia) throws SQLException;
}
