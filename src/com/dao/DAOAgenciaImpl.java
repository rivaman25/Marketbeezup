/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOAgencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class DAOAgenciaImpl extends ConexionBD implements DAOAgencia {

    public DAOAgenciaImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public List<String> obtener() throws SQLException {
        List<String> lista = new ArrayList<>();
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT idAgencia FROM Agencias ORDER BY idAgencia");
            while (result.next()) {
                lista.add(result.getString(1));
            }
            result.close();
            st.close();
        } catch (SQLException ex) {

        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public void registrar(String objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(String objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
