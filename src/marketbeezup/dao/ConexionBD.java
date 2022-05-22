/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketbeezup.dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Permite obtener una conexión con la BD en función de los parámetro
 * almacenados
 *
 * @author Manuel Rivallo
 */
public class ConexionBD {

    private Connection connection;
    private final String url;
    private final String serverName;
    private final int portNumber;
    private final String databaseName;
    private final String userName;
    private final String password;

    /**
     * Constructor que inicializa los parámetro de conexión con la BD conforme a
     * las preferencias almacenadas
     *
     * @param url
     * @param serverName
     * @param portNumber
     * @param databaseName
     * @param userName
     * @param password
     */
    public ConexionBD(String url, String serverName, int portNumber, String databaseName,
            String userName, String password) {
        connection = null;
        this.url = url;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
    }
    
    public ConexionBD() {
        this.url = "jdbc:mysql://";
        this.serverName = "localhost";
        this.portNumber = 3306;
        this.databaseName = "online";
        this.userName = "root";
        this.password = "Mrbmysql2536";
    }

    /**
     *
     * @return Conexión con la base de datos
     */
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Obtiene la URL en función de los parámetros almacenados
    private String getConnectionUrl() {
        return url + serverName + ":" + portNumber + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
    }

    /**
     * Obtiene una conexión con la BD
     *
     * @return Conexión con la BD
     */
    public Connection openConnection() {
        try {
            connection = java.sql.DriverManager.getConnection(getConnectionUrl(),
                    userName, password);
            if (connection == null) {
                JOptionPane.showMessageDialog(null, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión con la Base de Datos, revise los parámetro de conexión: \n" + e.getMessage(), "Error al conectar", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    /**
     * Cierra la conexión con la BD
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión con la Base de Datos: \n" + e.getMessage(), "Error al conectar", JOptionPane.ERROR_MESSAGE);
        }
    }
}
