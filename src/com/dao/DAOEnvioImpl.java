/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOEnvio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import com.modelos.Articulo;
import com.modelos.Envio;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;

public class DAOEnvioImpl extends ConexionBD implements DAOEnvio {

    public DAOEnvioImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public Envio obtener(Articulo articulo, Filtro filtro, Connection conexion) throws SQLException {
        Envio envio = null;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM Envios WHERE marketplace = ? AND "
                    + "idPedido = ? AND codigoArticulo = ?");
            pstm.setString(1, articulo.getMarketplace());
            pstm.setString(2, articulo.getIdPedido());
            pstm.setString(3, articulo.getCodigoArticulo());
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                envio = new Envio();
                envio.setFechaSalida(result.getDate("fechaSalida"));
                envio.setIdAlmacen(result.getString("idAlmacen"));
                envio.setIdAgencia(result.getString("idAgencia"));
                envio.setCodigoArticulo(result.getString("codigoArticulo"));
                envio.setMarketplace(result.getString("marketplace"));
                envio.setIdPedido(result.getString("idPedido"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnvioImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
        return envio;
    }

    @Override
    public void registrar(Envio envio, Connection conexion) throws SQLException {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Envios (fechaSalida, idAgencia, idAlmacen, "
                    + "codigoArticulo, idPedido, marketplace) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            pstm.setDate(1, envio.getFechaSalida());
            pstm.setString(2, envio.getIdAgencia());
            pstm.setString(3, envio.getIdAlmacen());
            pstm.setString(4, envio.getCodigoArticulo());
            pstm.setString(5, envio.getIdPedido());
            pstm.setString(6, envio.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnvioImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
    }

    @Override
    public void modificar(Envio envio) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Envios SET fechaSalida = ?, idAgencia = ?, idAlmacen "
                    + "= ? WHERE codigoArticulo = ? AND idPedido = ? AND "
                    + "marketplace = ?");
            pstm.setDate(1, envio.getFechaSalida());
            pstm.setString(2, envio.getIdAgencia());
            pstm.setString(3, envio.getIdAlmacen());
            pstm.setString(4, envio.getCodigoArticulo());
            pstm.setString(5, envio.getIdPedido());
            pstm.setString(6, envio.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnvioImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Envio envio) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM Envios WHERE codigoArticulo = ? AND idPedido"
                    + " = ? AND marketplace = ?");
            pstm.setString(1, envio.getCodigoArticulo());
            pstm.setString(2, envio.getIdPedido());
            pstm.setString(3, envio.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnvioImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

}
