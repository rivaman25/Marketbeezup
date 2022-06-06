/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOProvincia;
import com.modelos.Provincia;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manolo
 */
public class DAOProvinciaImpl extends ConexionBD implements DAOProvincia {

    public DAOProvinciaImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public List<Provincia> listar() throws SQLException {
        List<Provincia> provincias = new ArrayList<>();
        Provincia provincia;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                SELECT
                    *
                FROM
                    provincias
                ORDER BY
                    codigo                                                                                                             
                """);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                provincia = new Provincia();
                provincia.setCodigoProvincia(result.getString("codigo"));
                provincia.setNombreProvincia(result.getString("nombre"));
                provincias.add(provincia);
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return provincias;
    }

    @Override
    public Provincia obtener(String codigo) throws SQLException {
        Provincia provincia = null;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                SELECT
                    *
                FROM
                    provincias
                WHERE
                    provincias.codigo = ?                                                                                              
                """);
            pstm.setString(1, codigo);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                provincia = new Provincia();
                provincia.setCodigoProvincia(result.getString("codigo"));
                provincia.setNombreProvincia(result.getString("nombre"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return provincia;
    }

    @Override
    public void registrar(Provincia provincia) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                INSERT INTO
                    provincias (codigo, nombre)
                VALUES
                    (?, ?)                                                                                                             
                """);
            pstm.setString(1, provincia.getCodigoProvincia());
            pstm.setString(2, provincia.getNombreProvincia());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void modificar(Provincia provincia) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                UPDATE
                    provincias
                SET
                    nombre = ?     
                WHERE
                    codigo = ?                                                                                                             
                """);
            pstm.setString(1, provincia.getNombreProvincia());
            pstm.setString(2, provincia.getCodigoProvincia());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Provincia provincia) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                DELETE FROM
                    provincias                                                                                                                                            
                WHERE
                    codigo = ?                                                                                                             
                """);
            pstm.setString(1, provincia.getCodigoProvincia());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }
}
