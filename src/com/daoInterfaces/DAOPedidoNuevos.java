/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.util.List;
import com.modelos.Pedido;
import java.sql.SQLException;

/**
 *
 * @author Manolo
 */
public interface DAOPedidoNuevos {

    public List<Pedido> obtenerPedidosNuevos() throws SQLException;
}
