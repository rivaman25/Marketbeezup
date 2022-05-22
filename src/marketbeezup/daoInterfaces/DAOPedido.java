/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package marketbeezup.daoInterfaces;

import java.util.List;
import marketbeezup.modelos.Filtro;
import marketbeezup.modelos.Pedido;
import marketbeezup.modelos.PedidoPK;

/**
 *
 * @author Manolo
 */
public interface DAOPedido extends CRUD<Pedido> {

    public List<Pedido> listarAlbaranesImpr() throws Exception;

    public List<Pedido> listar(String atributo, String valor) throws Exception;

    public List<Pedido> listar(Filtro filtros) throws Exception;

    public void registrar(List<Pedido> pedidos) throws Exception;

    public List<PedidoPK> listarPK() throws Exception;

    public List<String> listarTiendas() throws Exception;

    public List<String> listarMarket() throws Exception;
}
