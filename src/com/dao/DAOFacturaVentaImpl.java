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
import com.modelos.FacturaVenta;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;

public class DAOFacturaVentaImpl extends ConexionBD implements DAOInterfaz<FacturaVenta> {

    public DAOFacturaVentaImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public FacturaVenta obtener(Articulo articulo, Filtro filtro, Connection conexion) throws SQLException {
        FacturaVenta facturaVenta = null;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM Documentosventa WHERE marketplace = ? AND "
                    + "idPedido = ? AND codigoArticulo = ?");
            pstm.setString(1, articulo.getMarketplace());
            pstm.setString(2, articulo.getIdPedido());
            pstm.setString(3, articulo.getCodigoArticulo());
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                facturaVenta = new FacturaVenta();
                facturaVenta.setNumeroFactura(result.getString("numeroVenta"));
                facturaVenta.setFechaFactura(result.getDate("fechaVenta"));
                facturaVenta.setCodigoArticulo(result.getString("codigoArticulo"));
                facturaVenta.setMarketplace(result.getString("marketplace"));
                facturaVenta.setIdPedido(result.getString("idPedido"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFacturaVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
        return facturaVenta;
    }

    @Override
    public void registrar(FacturaVenta facturaVenta, Connection conexion) throws SQLException {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO documentosventa (numeroVenta, fechaVenta, "
                    + "codigoArticulo, idPedido, marketplace) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstm.setString(1, facturaVenta.getNumeroFactura());
            pstm.setDate(2, facturaVenta.getFechaFactura());
            pstm.setString(3, facturaVenta.getCodigoArticulo());
            pstm.setString(4, facturaVenta.getIdPedido());
            pstm.setString(5, facturaVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFacturaVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modificar(FacturaVenta facturaVenta) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE documentosventa SET documentosventa.numeroVenta = ?, documentosventa.fechaVenta "
                    + "= ? WHERE documentosventa.codigoArticulo = ? AND documentosventa.idPedido = ? AND "
                    + "documentosventa.marketplace = ?");
            pstm.setString(1, facturaVenta.getNumeroFactura());
            pstm.setDate(2, facturaVenta.getFechaFactura());
            pstm.setString(3, facturaVenta.getCodigoArticulo());
            pstm.setString(4, facturaVenta.getIdPedido());
            pstm.setString(5, facturaVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFacturaVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(FacturaVenta facturaVenta) throws SQLException {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM documentosVenta WHERE documentosVenta.codigoArticulo = ? "
                            + "AND documentosVenta.idPedido = ? AND marketplace = ?");
            pstm.setString(1, facturaVenta.getCodigoArticulo());
            pstm.setString(2, facturaVenta.getIdPedido());
            pstm.setString(3, facturaVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFacturaVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

}
