/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.daoInterfaces.DAOERP;
import com.modelos.Stock;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Manolo
 */
public class DAOERPImpl extends ConexionBD implements DAOERP {

    public DAOERPImpl(String url, String serverName, int portNumber, String databaseName, String userName, String password) {
        super(url, serverName, portNumber, databaseName, userName, password);
    }

    @Override
    public List<Stock> obtenerStock(String codigoArticulo) throws SQLException {
        List<Stock> listaStock = new ArrayList<>();
        Stock stock;
        try {
            this.openConnection();
            PreparedStatement pstm = this.getConnection().prepareStatement("");
            pstm.setString(1, codigoArticulo);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                stock = new Stock();
                stock.setCodigoArticulo(codigoArticulo);
                stock.setIdAlmacen(result.getString("idAlmacen"));
                stock.setUnidades(result.getInt("Stock"));
                listaStock.add(stock);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listaStock;
    }
}
