/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.*;

/**
 * Permite obtener una conexión con la BD en función de los parámetros
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
     * Inicializa los parámetro de conexión con la BD
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
     * @throws java.sql.SQLException
     */
    public Connection openConnection() throws SQLException {
        try {
            connection = java.sql.DriverManager.getConnection(getConnectionUrl(),
                    userName, password);
            if (connection == null) {
                throw new SQLException("No hay conexión con la Base de Datos");
            }
        } catch (SQLException e) {
            throw e;
        }
        return connection;
    }

    /**
     * Cierra la conexión con la BD
     *
     * @throws java.sql.SQLException
     */
    public void closeConnection() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            throw e;
        }
    }
}
