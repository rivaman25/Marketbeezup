/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketbeezup.dao;

import marketbeezup.daoInterfaces.DAOInterfaz;
import marketbeezup.modelos.AlbaranVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import marketbeezup.modelos.Articulo;
import marketbeezup.modelos.Filtro;

public class DAOAlbaranVentaImpl extends ConexionBD implements DAOInterfaz<AlbaranVenta> {

    @Override
    public AlbaranVenta obtener(Articulo articulo, Filtro filtro, Connection conexion) throws Exception {
        AlbaranVenta albaranVenta = null;
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM Albaranesventa WHERE marketplace = ? AND "
                    + "idPedido = ? AND codigoArticulo = ?");
            pstm.setString(1, articulo.getMarketplace());
            pstm.setString(2, articulo.getIdPedido());
            pstm.setString(3, articulo.getCodigoArticulo());
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                albaranVenta = new AlbaranVenta();
                albaranVenta.setNumeroAlbaran(result.getString("numeroAlbaran"));
                albaranVenta.setFechaAlbaran(result.getDate("fechaAlbaran"));
                albaranVenta.setCodigoArticulo(result.getString("codigoArticulo"));
                albaranVenta.setMarketplace(result.getString("marketplace"));
                albaranVenta.setIdPedido(result.getString("idPedido"));
            }
            result.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlbaranVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Si no he recibido la conexión por parámetro, cierro la que he obtenido
            // En caso contrario la conexión se cerrará desde el objeto que use esta instancia
            if (conexion == null) {
                this.closeConnection();
            }
        }
        return albaranVenta;
    }

    @Override
    public void registrar(AlbaranVenta albaranVenta, Connection conexion) throws Exception {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Albaranesventa (fechaAlbaran, numeroAlbaran, "
                    + "codigoArticulo, idPedido, marketplace) VALUES (?, ?, ?, ?, ?)");
            pstm.setDate(1, albaranVenta.getFechaAlbaran());
            pstm.setString(2, albaranVenta.getNumeroAlbaran());
            pstm.setString(3, albaranVenta.getCodigoArticulo());
            pstm.setString(4, albaranVenta.getIdPedido());
            pstm.setString(5, albaranVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlbaranVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modificar(AlbaranVenta albaranVenta) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Albaranesventa SET fechaAlbaran = ?, numeroAlbaran = ? "
                    + "WHERE codigoArticulo = ? AND idPedido = ? AND marketplace = ?");
            pstm.setDate(1, albaranVenta.getFechaAlbaran());
            pstm.setString(2, albaranVenta.getNumeroAlbaran());
            pstm.setString(3, albaranVenta.getCodigoArticulo());
            pstm.setString(4, albaranVenta.getIdPedido());
            pstm.setString(5, albaranVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlbaranVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void eliminar(AlbaranVenta albaranVenta) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM Albaranesventa WHERE codigoArticulo = ? AND "
                    + "idPedido = ? AND marketplace = ?");
            pstm.setString(1, albaranVenta.getCodigoArticulo());
            pstm.setString(2, albaranVenta.getIdPedido());
            pstm.setString(3, albaranVenta.getMarketplace());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlbaranVentaImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.closeConnection();
        }
    }
}
