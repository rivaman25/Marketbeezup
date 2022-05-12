/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoInterfaces;

import java.util.List;
import modelos.Pedido;

/**
 *
 * @author Manolo
 */
public interface DAOPedidoNuevos {

    public List<Pedido> obtenerPedidosNuevos() throws Exception;
}
