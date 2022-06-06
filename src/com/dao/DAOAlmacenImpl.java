/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOAlmacen;
import com.modelos.Almacen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class DAOAlmacenImpl extends ConexionBD implements DAOAlmacen {

    public DAOAlmacenImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public List<Almacen> listar() throws SQLException {
        List<Almacen> lista = new ArrayList<>();
        Almacen almacen;
        try {
            this.openConnection();
            Statement st = this.getConnection().createStatement();
            ResultSet result = st.executeQuery("SELECT almacenes.idAlmacen, almacenes.almacen FROM almacenes ORDER BY idAlmacen");
            while (result.next()) {
                almacen = new Almacen();
                almacen.setIdAlmacen(result.getString(1));
                almacen.setAlmacen(result.getString(2));
                lista.add(almacen);
            }
            result.close();
            st.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public Almacen obtener(String idAlmacen) throws SQLException {
        Almacen almacen = null;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                SELECT
                    *
                FROM
                    almacenes
                WHERE
                    almacenes.idAlmacen = ?                                                                                              
                """);
            pstm.setString(1, idAlmacen);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                almacen = new Almacen();
                almacen.setIdAlmacen(result.getString("idAlmacen"));
                almacen.setAlmacen(result.getString("almacen"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
        return almacen;
    }

    @Override
    public void registrar(Almacen almacen) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                INSERT INTO
                    almacenes (idAlmacen, almacen)
                VALUES
                    (?, ?)                                                                                                             
                """);
            pstm.setString(1, almacen.getIdAlmacen());
            pstm.setString(2, almacen.getAlmacen());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void modificar(Almacen almacen) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                UPDATE
                    almacenes
                SET
                    Almacen = ?     
                WHERE
                    iDalmacen = ?                                                                                                             
                """);
            pstm.setString(1, almacen.getAlmacen());
            pstm.setString(2, almacen.getIdAlmacen());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Almacen almacen) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("""
                DELETE FROM
                    almacenes                                                                                                                                           
                WHERE
                    idAlmacen = ?                                                                                                             
                """);
            pstm.setString(1, almacen.getIdAlmacen());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeConnection();
        }
    }
}
