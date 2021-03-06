/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import com.modelos.Articulo;
import com.modelos.Compra;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;

public class DAOCompraImpl extends ConexionBD implements DAOInterfaz<Compra> {

    public DAOCompraImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public Compra obtener(Articulo articulo, Filtro filtro, Connection conexion) throws SQLException {
        Compra compra = null;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM Compras WHERE marketplace = ? AND "
                    + "idPedido = ? AND codigoArticulo = ?");
            pstm.setString(1, articulo.getMarketplace());
            pstm.setString(2, articulo.getIdPedido());
            pstm.setString(3, articulo.getCodigoArticulo());
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                compra = new Compra();
                compra.setIdCompra(result.getString("idCompra"));
                compra.setProveedor(result.getString("proveedor"));
                compra.setFechaCompra(result.getDate("fechaCompra"));
                compra.setFechaEntrada(result.getDate("fechaEntrada"));
                compra.setCodigoArticulo(result.getString("codigoArticulo"));
                compra.setMarketplace(result.getString("marketplace"));
                compra.setIdPedido(result.getString("idPedido"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCompraImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
        return compra;
    }

    @Override
    public void registrar(Compra compra, Connection conexion) throws SQLException {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Compras (idCompra, proveedor, fechaCompra, "
                    + "fechaEntrada, codigoArticulo, idPedido, marketplace) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, compra.getIdCompra());
            pstm.setString(2, compra.getProveedor());
            pstm.setDate(3, compra.getFechaCompra());
            pstm.setDate(4, compra.getFechaEntrada());
            pstm.setString(5, compra.getCodigoArticulo());
            pstm.setString(6, compra.getIdPedido());
            pstm.setString(7, compra.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCompraImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modificar(Compra compra) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Compras SET idCompra = ?, proveedor = ?, fechaCompra "
                    + "= ?, fechaEntrada = ? WHERE codigoArticulo = ? AND "
                    + "idPedido = ? AND marketplace = ?");
            pstm.setString(1, compra.getIdCompra());
            pstm.setString(2, compra.getProveedor());
            pstm.setDate(3, compra.getFechaCompra());
            pstm.setDate(4, compra.getFechaEntrada());
            pstm.setString(5, compra.getCodigoArticulo());
            pstm.setString(6, compra.getIdPedido());
            pstm.setString(7, compra.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCompraImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(Compra compra) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM Compras WHERE codigoArticulo = ? AND idPedido"
                    + " = ? AND marketplace = ?");
            pstm.setString(1, compra.getCodigoArticulo());
            pstm.setString(2, compra.getIdPedido());
            pstm.setString(3, compra.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCompraImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

}
