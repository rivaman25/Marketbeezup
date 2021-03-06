/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.util.ArrayList;
import java.util.List;
import com.modelos.Articulo;
import com.modelos.Filtro;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class Consultas {
    
    static final String CONSULTA_STOCKS = """
        SELECT 
            dbo.GES_StockAlmacen.IdEmpresa,
                        dbo.GES_stockAlmacen.IdAlmacen,
            dbo.GES_StockAlmacen.UnidadesStock,
            dbo.GES_StockAlmacen.UnidadesStockReservado
        FROM
            dbo.GES_Articulos
                JOIN
            dbo.GES_StockAlmacen ON (dbo.GES_Articulos.IdArticulo = dbo.GES_StockAlmacen.idArticulo)
        WHERE
            dbo.GES_Articulos.CodigoAlternativo1 = '8806091218018'
                AND dbo.GES_Articulos.IdEmpresa = 'ZAR'
        ORDER BY dbo.GES_stockAlmacen.IdAlmacen;
                                          """;

    static final String CONSULTA_PEDIDOS_SOLO_CON_OBSERVACIONES = """
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

    static String CONSULTA_PEDIDOS = """
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
        WHERE 
            pedidos.marketplace
        IN 
            (""";
    
    static String CONSULTA_OBTENER_PEDIDO = """
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
        WHERE 
            pedidos.marketplace = ? AND
            pedidos.idPedido = ?
        ORDER BY
            pedidos.fechaPedido""";

    static final String CONSULTA_ARTICULOS_SOLO_CON_OBSERVACIONES = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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

    static final String CONSULTA_ARTICULOS_SOLO_CON_FECHA_SALIDA = """
        SELECT
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
        FROM
            pedidos
                NATURAL JOIN
            articulos
                INNER JOIN
            observaciones ON (pedidos.idPedido = observaciones.idPedido
                AND pedidos.marketplace = observaciones.marketplace)
                INNER JOIN
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

    static final String CONSULTA_ARTICULOS_REIMPRIMIR_ALBARANES = """
        SELECT
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen
        FROM
            pedidos
                NATURAL JOIN
            articulos
                INNER JOIN
            envios ON (articulos.idPedido = envios.idPedido
                AND articulos.marketplace = envios.marketplace
                AND articulos.codigoArticulo = envios.codigoArticulo)
        WHERE
            articulos.fechaHoraImpr IS NOT NULL
                AND articulos.estado <> "CANCELADO"
                AND envios.idAgencia NOT LIKE '%drop%'""";

    static final String CONSULTA_ARTICULOS_IMPRIMIR_ALBARANES = """
        SELECT
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen
        FROM
            pedidos
                NATURAL JOIN
            articulos
                INNER JOIN
            envios ON (articulos.idPedido = envios.idPedido
                AND articulos.marketplace = envios.marketplace
                AND articulos.codigoArticulo = envios.codigoArticulo)
        WHERE
            articulos.fechaHoraImpr IS NULL
                AND articulos.estado <> "CANCELADO"
                AND envios.idAgencia NOT LIKE '%drop%'""";

    static final String CONSULTA_ARTICULOS = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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

    private static final String CONSULTA_ARTICULOS_IDPEDIDO = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE pedidos.idPedido LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_NOMBRE = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE pedidos.nombreApellidos LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_DIRECCION = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE pedidos.direccion LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_TELEFONO = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE pedidos.telefono LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_ARTICULO = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE articulos.codigoArticulo LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_COMPRA = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE compras.idCompra LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_NUMERO_DOCUMENTO = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE documentosVenta.numeroVenta LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo  
            ORDER BY pedidos.fechaPedido DESC""";

    private static final String CONSULTA_ARTICULOS_NUMERO_ALBARAN = """
        SELECT 
            articulos.codigoArticulo,
            articulos.descripcion,
            articulos.precio,
            articulos.cantidad,
            articulos.estado,
            articulos.puc,
            articulos.tipoArticulo,
            articulos.fechaHoraImpr,
            articulos.idFamilia,
            articulos.idSubfamilia,
            articulos.marca,
            articulos.marketplace,
            articulos.idPedido,
            envios.fechaSalida,
            envios.idAgencia,
            envios.idAlmacen,
            compras.idCompra,
            compras.proveedor,
            compras.fechaCompra,
            compras.fechaEntrada,
            albaranesVenta.fechaAlbaran,
            albaranesVenta.numeroAlbaran,
            documentosVenta.numeroVenta,
            documentosVenta.fechaVenta
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
                AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
            WHERE albaranesVenta.numeroAlbaran LIKE ?
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY pedidos.fechaPedido DESC""";

    private final static String CONSULTA_OBSERVACIONES = """
        SELECT 
            *
        FROM
            marketbeezup.observaciones
        WHERE
            observaciones.marketplace IN (""";

    private final static String CONSULTA_ACTUALIZAR_FECHAHORAIMPR = """
            UPDATE 
            	articulos 
            SET 
            	articulos.fechaHoraImpr = ?
            WHERE
            	articulos.codigoArticulo IN (?""";

    public static String obtenerConsultaActualizarFechaHoraImpr(List<Articulo> articulos) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(CONSULTA_ACTUALIZAR_FECHAHORAIMPR);
        for (int i = 1; i < Articulo.getCodigoArticulos(articulos).size(); i++) {
            consulta.append(", ?");
        }
        consulta.append(") AND articulos.idPedido IN(?");
        for (int i = 1; i < Articulo.getIdpedidos(articulos).size(); i++) {
            consulta.append(", ?");
        }
        consulta.append(") AND articulos.marketplace IN (?");
        for (int i = 1; i < Articulo.getMarketplace(articulos).size(); i++) {
            consulta.append(", ?");
        }
        consulta.append(")");
        return consulta.toString();
    }

    public static String obtenerConsultaPedidos(Filtro filtro) {
        StringBuilder consulta = new StringBuilder();
        StringBuilder predicadoAux;
        List<String> predicados = new ArrayList<>();
        if (filtro.isObservaciones()) { // Registros con observaciones almacenadas
            consulta.append(CONSULTA_PEDIDOS_SOLO_CON_OBSERVACIONES);
        } else { // Registros con y sin observaciones registradas
            consulta.append(CONSULTA_PEDIDOS);
        }
        if (filtro.getMarketplace() != null) { // Si hay market seleccionado se a??ade el predicado correspondiente
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.marketplace IN (?");
            for (int i = 1; i < filtro.getMarketplace().length; i++) {
                predicadoAux.append(", ?"); // Se a??aden tantos par??metros como n?? de market
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getTiendas() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.tienda IN (?");
            for (int i = 1; i < filtro.getTiendas().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getFechaPedidoDesde() != null) {
            predicados.add(" pedidos.fechaPedido >= ?");
        }
        if (filtro.getFechaPedidoHasta() != null) {
            predicados.add(" pedidos.fechaPedido <= ?");
        }
        if (filtro.getEstados() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" articulos.estado IN (?");
            for (int i = 1; i < filtro.getEstados().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.isExiste()) {
            predicados.add((" (articulos.tipoArticulo <> 'EXISTE' OR articulos.tipoArticulo IS NULL)"));
        }
        if (filtro.getFechaSalidaDesde() != null) {
            predicados.add(" envios.fechaSalida >= ?");
        }
        if (filtro.getFechaSalidaHasta() != null) {
            predicados.add(" envios.fechaSalida <= ?");
        }
        if (filtro.getAlmacenes() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAlmacen IN (?");
            for (int i = 1; i < filtro.getAlmacenes().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getAgencias() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAgencia IN (?");
            for (int i = 1; i < filtro.getAgencias().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getFechaCompraDesde() != null) {
            predicados.add(" compras.fechaCompra >= ?");
        }
        if (filtro.getFechaCompraHasta() != null) {
            predicados.add(" compras.fechaCompra <= ?");
        }
        if (filtro.getFechaVentaDesde() != null) {
            predicados.add(" documentosVenta.fechaVenta >= ?");
        }
        if (filtro.getFechaVentaHasta() != null) {
            predicados.add(" documentosVenta.fechaVenta <= ?");
        }
        if (filtro.getFechaAlbaranDesde() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran >= ?");
        }
        if (filtro.getFechaAlbaranHasta() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran <= ?");
        }
        if (!predicados.isEmpty()) {
            consulta.append(" WHERE");
            for (String predicado : predicados) {
                consulta.append(predicado);
                consulta.append(" AND");
            }
            consulta.delete(consulta.length() - 4, consulta.length());
        }
        consulta.append(" GROUP BY pedidos.marketplace, pedidos.idPedido ORDER BY pedidos.fechaPedido DESC");
        return consulta.toString();
    }

    public static String obtenerConsultaObtenerPedidos(String marketplace, String idPedido) {
        return CONSULTA_OBTENER_PEDIDO;
    }

    public static String obtenerConsultaPedidos(List<Articulo> articulos) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(CONSULTA_PEDIDOS);
        List<String> idPedidos = new ArrayList<>();
        List<String> marketplace = new ArrayList<>();
        if (!articulos.isEmpty()) {
            for (Articulo articulo : articulos) {
                if (!marketplace.contains(articulo.getMarketplace())) {
                    marketplace.add(articulo.getMarketplace());
                }
                if (!idPedidos.contains(articulo.getIdPedido())) {
                    idPedidos.add(articulo.getIdPedido());
                }
            }
            consulta.append("?");
            for (int i = 1; i < marketplace.size(); i++) {
                consulta.append(", ?");
            }
            consulta.append(") AND pedidos.idPedido IN (?");
            for (int i = 1; i < idPedidos.size(); i++) {
                consulta.append(", ?");
            }
            consulta.append(") ORDER BY pedidos.fechaPedido DESC");
        }
        return consulta.toString();
    }

    public static String obtenerConsultaArticulos(Filtro filtro) {
        StringBuilder consulta = new StringBuilder();
        StringBuilder predicadoAux;
        List<String> predicados = new ArrayList<>();
        if (filtro.isObservaciones()) { // Registros con observaciones almacenadas
            consulta.append(CONSULTA_ARTICULOS_SOLO_CON_OBSERVACIONES);
        } else { // Registros con y sin observaciones registradas
            consulta.append(CONSULTA_ARTICULOS);
        }
        if (filtro.getMarketplace() != null) { // Si hay market seleccionado se a??ade el predicado correspondiente
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.marketplace IN (?");
            for (int i = 1; i < filtro.getMarketplace().length; i++) {
                predicadoAux.append(", ?"); // Se a??aden tantos par??metros como n?? de market
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getTiendas() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.tienda IN (?");
            for (int i = 1; i < filtro.getTiendas().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getFechaPedidoDesde() != null) {
            predicados.add(" pedidos.fechaPedido >= ?");
        }
        if (filtro.getFechaPedidoHasta() != null) {
            predicados.add(" pedidos.fechaPedido <= ?");
        }
        if (filtro.getEstados() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" articulos.estado IN (?");
            for (int i = 1; i < filtro.getEstados().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.isExiste()) {
            predicados.add((" (articulos.tipoArticulo <> 'EXISTE' OR articulos.tipoArticulo IS NULL)"));
        }
        if (filtro.isFechaSalida()) {
            predicados.add(" envios.fechaSalida is null");
        }
        if (filtro.getFechaSalidaDesde() != null) {
            predicados.add(" envios.fechaSalida >= ?");
        }
        if (filtro.getFechaSalidaHasta() != null) {
            predicados.add(" envios.fechaSalida <= ?");
        }
        if (filtro.getAlmacenes() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAlmacen IN (?");
            for (int i = 1; i < filtro.getAlmacenes().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.getAgencias() != null) {
            predicadoAux = new StringBuilder();
            predicadoAux.append(" envios.idAgencia IN (?");
            for (int i = 1; i < filtro.getAgencias().length; i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(")");
            predicados.add(predicadoAux.toString());
        }
        if (filtro.isFechaCompra()) {
            predicados.add(" compras.fechaCompra is null");
        }
        if (filtro.getFechaCompraDesde() != null) {
            predicados.add(" compras.fechaCompra >= ?");
        }
        if (filtro.getFechaCompraHasta() != null) {
            predicados.add(" compras.fechaCompra <= ?");
        }
        if (filtro.isFechaTicket()) {
            predicados.add(" documentosVenta.fechaVenta is null");
        }
        if (filtro.getFechaVentaDesde() != null) {
            predicados.add(" documentosVenta.fechaVenta >= ?");
        }
        if (filtro.getFechaVentaHasta() != null) {
            predicados.add(" documentosVenta.fechaVenta <= ?");
        }
        if  (filtro.isFechaAlbaran()) {
            predicados.add(" albaranesVenta.fechaAlbaran is null");
        }
        if (filtro.getFechaAlbaranDesde() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran >= ?");
        }
        if (filtro.getFechaAlbaranHasta() != null) {
            predicados.add(" albaranesVenta.fechaAlbaran <= ?");
        }
        if (!predicados.isEmpty()) {
            consulta.append(" WHERE");
            for (String predicado : predicados) {
                consulta.append(predicado);
                consulta.append(" AND");
            }
            consulta.delete(consulta.length() - 4, consulta.length());
        }
        consulta.append(" GROUP BY articulos.marketplace, articulos.idPedido,"
                + " articulos.codigoArticulo ORDER BY pedidos.fechaPedido DESC");
        return consulta.toString();
    }

    public static String obtenerConsultaArticulos(String atributo, String valor) {
        switch (atributo) {
            case "Pedido":
                return CONSULTA_ARTICULOS_IDPEDIDO;
            case "Nombre":
                return CONSULTA_ARTICULOS_NOMBRE;
            case "Direcci??n":
                return CONSULTA_ARTICULOS_DIRECCION;
            case "Tel??fono":
                return CONSULTA_ARTICULOS_TELEFONO;
            case "Art??culo":
                return CONSULTA_ARTICULOS_ARTICULO;
            case "Compra":
                return CONSULTA_ARTICULOS_COMPRA;
            case "Ticket":
                return CONSULTA_ARTICULOS_NUMERO_DOCUMENTO;
            case "Albar??n":
                return CONSULTA_ARTICULOS_NUMERO_ALBARAN;
            default:
                return null;
        }
    }

    public static String obtenerConsultaObservaciones(List<Articulo> articulos) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(CONSULTA_OBSERVACIONES);
        List<String> idPedidos = new ArrayList<>();
        List<String> marketplace = new ArrayList<>();
        if (!articulos.isEmpty()) {
            for (Articulo articulo : articulos) {
                if (!marketplace.contains(articulo.getMarketplace())) {
                    marketplace.add(articulo.getMarketplace());
                }
                if (!idPedidos.contains(articulo.getIdPedido())) {
                    idPedidos.add(articulo.getIdPedido());
                }
            }
            consulta.append("?");
            for (int i = 1; i < marketplace.size(); i++) {
                consulta.append(", ?");
            }
            consulta.append(") AND observaciones.idPedido IN (?");
            for (int i = 1; i < idPedidos.size(); i++) {
                consulta.append(", ?");
            }
            consulta.append(")");
        }
        return consulta.toString();
    }

    public static String obtenerConsultaImprimirAlbaranes(String idPedido, java.sql.Date fechaSalida, List<String> agencias, boolean reimprimir) {
        StringBuilder consulta = new StringBuilder();
        List<String> predicados = new ArrayList<>();
        StringBuilder predicadoAux = new StringBuilder();
        if (reimprimir) {
            consulta.append(CONSULTA_ARTICULOS_REIMPRIMIR_ALBARANES);
        } else {
            consulta.append(CONSULTA_ARTICULOS_IMPRIMIR_ALBARANES);
        }
        if (idPedido != null) {
            predicados.add("articulos.idPedido like ?");
        }
        if (fechaSalida != null) {
            predicados.add("envios.fechaSalida = ?");
        } else {
            predicados.add("envios.fechaSalida > ?");
        }
        if (!agencias.isEmpty()) {
            predicadoAux.append("envios.idAgencia IN (?");
            for (int i = 1; i < agencias.size(); i++) {
                predicadoAux.append(", ?");
            }
            predicadoAux.append(") ");
            predicados.add(predicadoAux.toString());
        }
        if (!predicados.isEmpty()) {
            for (String predicado : predicados) {
                consulta.append(" AND ");
                consulta.append(predicado);
            }
        }
        consulta.append("""
            GROUP BY articulos.marketplace, articulos.idPedido, articulos.codigoArticulo
            ORDER BY envios.fechaSalida DESC, pedidos.idPedido, articulos.codigoArticulo""");
        return consulta.toString();
    }
}
