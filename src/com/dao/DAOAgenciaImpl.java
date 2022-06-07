/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOAgencia;
import com.modelos.Agencia;
import java.sql.PreparedStatement;
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
    public List<Agencia> listar() throws SQLException {
        List<Agencia> agencias = new ArrayList<>();
        Agencia agencia;
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT agencias.idAgencia FROM agencias ORDER BY idAgencia");
            while (result.next()) {
                agencia = new Agencia();
                agencia.setIdAgencia(result.getString(1));
                agencias.add(agencia);
            }
            result.close();
            st.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return agencias;
    }

    @Override
    public Agencia obtener(String idAgencia) throws SQLException {
        Agencia agencia = null;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                SELECT
                    *
                FROM
                    agencias
                WHERE
                    agencias.idAgencia = ?                                                                                              
                """);
            pstm.setString(1, idAgencia);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                agencia = new Agencia();
                agencia.setIdAgencia(result.getString("idAgencia"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return agencia;
    }

    @Override
    public void registrar(Agencia objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Agencia objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Agencia objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
