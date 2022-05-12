/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Manolo
 */
public class Consultas {

    String consultaPedidoObservaciones = """
        SELECT 
            pedidos.tienda,
            pedidos.marketplace,
            pedidos.idPedido,
            pedidos.fechaPedido,
            pedidos.dni,
            pedidos.nombreApellidos,
            pedidos.direccion,
            pedidos.cp,
            pedidos.poblacion,
            pedidos.provincia,
            pedidos.telefono,
            pedidos.email,
            pedidos.importe,
            pedidos.comision,
            pedidos.costePorte
        FROM
            pedidos
                NATURAL JOIN
            articulos
                INNER JOIN
            observaciones ON (pedidos.idPedido = observaciones.idPedido
                AND pedidos.marketplace = observaciones.marketplace)
                LEFT OUTER JOIN
            envios ON (articulos.idPedido = envios.idPedido
                AND articulos.marketplace = envios.marketplace
                AND articulos.codigoArticulo = envios.codigoArticulo)
                LEFT OUTER JOIN
            compras ON (articulos.idPedido = compras.idPedido
                AND articulos.marketplace = compras.marketplace
                AND articulos.codigoArticulo = compras.codigoArticulo)
                LEFT OUTER JOIN
            documentosVenta ON (articulos.idPedido = documentosVenta.idPedido
                AND articulos.marketplace = documentosVenta.marketplace
                AND articulos.codigoArticulo = documentosVenta.codigoArticulo)
                LEFT OUTER JOIN
            albaranesVenta ON (articulos.idPedido = albaranesVenta.idPedido
                AND articulos.marketplace = albaranesVenta.marketplace
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)""";

    String consultaPedidos = """
        SELECT 
            pedidos.tienda,
            pedidos.marketplace,
            pedidos.idPedido,
            pedidos.fechaPedido,
            pedidos.dni,
            pedidos.nombreApellidos,
            pedidos.direccion,
            pedidos.cp,
            pedidos.poblacion,
            pedidos.provincia,
            pedidos.telefono,
            pedidos.email,
            pedidos.importe,
            pedidos.comision,
            pedidos.costePorte
        FROM
            pedidos
                NATURAL JOIN
            articulos
                LEFT OUTER JOIN
            observaciones ON (pedidos.idPedido = observaciones.idPedido
                AND pedidos.marketplace = observaciones.marketplace)
                LEFT OUTER JOIN
            envios ON (articulos.idPedido = envios.idPedido
                AND articulos.marketplace = envios.marketplace
                AND articulos.codigoArticulo = envios.codigoArticulo)
                LEFT OUTER JOIN
            compras ON (articulos.idPedido = compras.idPedido
                AND articulos.marketplace = compras.marketplace
                AND articulos.codigoArticulo = compras.codigoArticulo)
                LEFT OUTER JOIN
            documentosVenta ON (articulos.idPedido = documentosVenta.idPedido
                AND articulos.marketplace = documentosVenta.marketplace
                AND articulos.codigoArticulo = documentosVenta.codigoArticulo)
                LEFT OUTER JOIN
            albaranesVenta ON (articulos.idPedido = albaranesVenta.idPedido
                AND articulos.marketplace = albaranesVenta.marketplace
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)""";
}
