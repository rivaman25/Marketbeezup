/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.util.List;
import com.modelos.Articulo;
import com.modelos.Filtro;
import com.modelos.Pedido;
import com.modelos.PedidoPK;
import java.sql.SQLException;

/**
 *
 * @author Manolo
 */
public interface DAOPedido extends CRUD<Pedido> {

    public List<Pedido> listar(List<Articulo> articulos) throws SQLException;

    public List<Pedido> listar(String atributo, String valor) throws SQLException;

    public List<Pedido> listar(Filtro filtros) throws SQLException;
    
    public Pedido obtener(String marketplace, String idPedido) throws SQLException;

    public void registrar(List<Pedido> pedidos) throws SQLException;

    public List<PedidoPK> listarPK() throws SQLException;

    public List<String> listarTiendas() throws SQLException;

    public List<String> listarMarket() throws SQLException;
}
