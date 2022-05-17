/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Observacion;
import daoInterfaces.DAOObservacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Articulo;

public class DAOObservacionImpl extends ConexionBD implements DAOObservacion {

    public DAOObservacionImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    public DAOObservacionImpl() {
    }

    @Override
    public List<Observacion> listar(List<Articulo> articulos) throws Exception {
        List<String> idPedidos = new ArrayList<>();
        List<String> marketplace = new ArrayList<>();
        Observacion observacion;
        List<Observacion> observaciones = new ArrayList<>();
        int indice = 1;
        if (!articulos.isEmpty()) {
            try {
                this.openConnection();
                PreparedStatement pstm = this.getConnection().prepareStatement(Consultas.obtenerConsultaObservaciones(articulos));
                for (Articulo articulo : articulos) {
                    // Se obtiene la lista de marketplace para introducir en los parámetros de la consulta
                    if (!marketplace.contains(articulo.getMarketplace())) {
                        marketplace.add(articulo.getMarketplace());
                    }
                    // Se obtiene la lista de idArticulo para introducir en los parámetros de la consulta
                    if (!idPedidos.contains(articulo.getIdPedido())) {
                        idPedidos.add(articulo.getIdPedido());
                    }
                }
                // Añado los valores de marketplace e idPedidos a buscar en los parámetros de la consulta
                for (String market : marketplace) {
                    pstm.setString(indice, market);
                    indice++;
                }
                for (String idPedido : idPedidos) {
                    pstm.setString(indice, idPedido);
                    indice++;
                }
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
                this.closeConnection();
            }
        }
        return observaciones;
    }

    @Override
    public void registrar(Observacion observacion) throws Exception {
        try {
            this.openConnection();
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
            this.closeConnection();
        }
    }

    @Override
    public void registrar(List<Observacion> observaciones) throws Exception {
        try {
            this.openConnection();
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
            this.closeConnection();
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
