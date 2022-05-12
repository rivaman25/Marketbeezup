/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import modelos.Observacion;
import modelos.Pedido;
import daoInterfaces.DAOInterfazLista;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOObservacionImpl extends ConexionBD implements DAOInterfazLista<Observacion> {

    @Override
    public List<Observacion> listar(Pedido pedido, Connection conexion) throws Exception {
        Observacion observacion;
        List<Observacion> observaciones = new ArrayList<>();
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "SELECT * FROM Observaciones WHERE marketplace = ? AND "
                    + "idPedido = ?");
            pstm.setString(1, pedido.getMarketplace());
            pstm.setString(2, pedido.getIdPedido());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                observacion = new Observacion();
                observacion.setTitulo(result.getString("titulo"));
                observacion.setDescripcion(result.getString("descripcion"));
                observacion.setFechaHora(result.getTimestamp("fechaHora"));
                observacion.setMarketplace(result.getString("marketplace"));
                observacion.setIdPedido(result.getString("idPedido"));
                observaciones.add(observacion);
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
        return observaciones;
    }

    @Override
    public void registrar(Observacion observacion, Connection conexion) throws Exception {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Observaciones (titulo, descripcion, fechaHora, "
                    + "idPedido, marketplace) VALUES (?, ?, ?, ?, ?)");
            pstm.setString(1, observacion.getTitulo());
            pstm.setString(2, observacion.getDescripcion());
            pstm.setTimestamp(3, observacion.getFechaHora());
            pstm.setString(4, observacion.getIdPedido());
            pstm.setString(5, observacion.getMarketplace());
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
    public void registrar(List<Observacion> observaciones, Connection conexion) throws Exception {
        try {
            // Si recibo una conexión a la BD por parámetro no creo una nueva
            if (conexion == null) {
                this.openConnection();
            } else {
                this.setConnection(conexion);
            }
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "INSERT INTO Observaciones (titulo, descripcion, fechaHora, "
                    + "idPedido, marketplace) VALUES (?, ?, ?, ?, ?)");
            for (Observacion observacion : observaciones) {
                pstm.setString(1, observacion.getTitulo());
                pstm.setString(2, observacion.getDescripcion());
                pstm.setTimestamp(3, observacion.getFechaHora());
                pstm.setString(4, observacion.getIdPedido());
                pstm.setString(5, observacion.getMarketplace());
                pstm.executeUpdate();
                pstm.clearParameters();
            }
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
    public void modificar(Observacion observacion) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "UPDATE Observaciones SET descripcion = ?, "
                    + "fechaHora = ? WHERE titulo = ? AND idPedido = ? AND marketplace = ?");
            pstm.setString(1, observacion.getDescripcion());
            pstm.setTimestamp(2, observacion.getFechaHora());
            pstm.setString(3, observacion.getTitulo());
            pstm.setString(4, observacion.getIdPedido());
            pstm.setString(5, observacion.getMarketplace());
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
    public void eliminar(Observacion observacion) throws Exception {
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement(
                    "DELETE FROM Observaciones WHERE titulo = ? AND idPedido = ? "
                    + "AND marketplace = ?");
            pstm.setString(1, observacion.getTitulo());
            pstm.setString(2, observacion.getIdPedido());
            pstm.setString(3, observacion.getMarketplace());
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
