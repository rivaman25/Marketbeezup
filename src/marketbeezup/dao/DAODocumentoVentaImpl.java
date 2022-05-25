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
import com.modelos.DocumentoVenta;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.modelos.Filtro;

public class DAODocumentoVentaImpl extends ConexionBD implements DAOInterfaz<DocumentoVenta> {

    @Override
    public DocumentoVenta obtener(Articulo articulo, Filtro filtro, Connection conexion) throws Exception {
        DocumentoVenta documentoVenta = null;
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
                documentoVenta = new DocumentoVenta();
                documentoVenta.setNumeroVenta(result.getString("numeroVenta"));
                documentoVenta.setFechaVenta(result.getDate("fechaVenta"));
                documentoVenta.setCodigoArticulo(result.getString("codigoArticulo"));
                documentoVenta.setMarketplace(result.getString("marketplace"));
                documentoVenta.setIdPedido(result.getString("idPedido"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAODocumentoVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
        return documentoVenta;
    }

    @Override
    public void registrar(DocumentoVenta documentoVenta, Connection conexion) throws Exception {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Documentosventa (numeroVenta, fechaVenta, "
                    + "codigoArticulo, idPedido, marketplace) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstm.setString(1, documentoVenta.getNumeroVenta());
            pstm.setDate(2, documentoVenta.getFechaVenta());
            pstm.setString(3, documentoVenta.getCodigoArticulo());
            pstm.setString(4, documentoVenta.getIdPedido());
            pstm.setString(5, documentoVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAODocumentoVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modificar(DocumentoVenta documentoVenta) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Documentosventa SET numeroVenta = ?, fechaVenta "
                    + "= ? WHERE codigoArticulo = ? AND idPedido = ? AND "
                    + "marketplace = ?");
            pstm.setString(1, documentoVenta.getNumeroVenta());
            pstm.setDate(2, documentoVenta.getFechaVenta());
            pstm.setString(3, documentoVenta.getCodigoArticulo());
            pstm.setString(4, documentoVenta.getIdPedido());
            pstm.setString(5, documentoVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAODocumentoVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(DocumentoVenta documentoVenta) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM DocumentoVenta WHERE codigoArticulo = ? AND idPedido"
                    + " = ? AND marketplace = ?");
            pstm.setString(1, documentoVenta.getCodigoArticulo());
            pstm.setString(2, documentoVenta.getIdPedido());
            pstm.setString(3, documentoVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAODocumentoVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

}
