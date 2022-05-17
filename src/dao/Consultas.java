/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelos.Articulo;
import modelos.Filtro;

/**
 *
 * @author Manolo
 */
public class Consultas {

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
            WHERE compras.idCompras LIKE ?
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

    public static String obtenerConsultaPedidos(Filtro filtro) {
        StringBuilder consulta = new StringBuilder();
        StringBuilder predicadoAux;
        List<String> predicados = new ArrayList<>();
        if (filtro.isObservaciones()) { // Registros con observaciones almacenadas
            consulta.append(CONSULTA_PEDIDOS_SOLO_CON_OBSERVACIONES);
        } else { // Registros con y sin observaciones registradas
            consulta.append(CONSULTA_PEDIDOS);
        }
        if (filtro.getMarketplace() != null) { // Si hay market seleccionado se añade el predicado correspondiente
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.marketplace IN (?");
            for (int i = 1; i < filtro.getMarketplace().length; i++) {
                predicadoAux.append(", ?"); // Se añaden tantos parámetros como nº de market
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
        consulta.append(" GROUP BY pedidos.marketplace, pedidos.idPedido ORDER BY pedidos.fechaPedido DESC LIMIT 3000");
        return consulta.toString();
    }

    public static String obtenerConsultaPedidos(String atributo, String valor) {
        return null;
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
        if (filtro.getMarketplace() != null) { // Si hay market seleccionado se añade el predicado correspondiente
            predicadoAux = new StringBuilder();
            predicadoAux.append(" pedidos.marketplace IN (?");
            for (int i = 1; i < filtro.getMarketplace().length; i++) {
                predicadoAux.append(", ?"); // Se añaden tantos parámetros como nº de market
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
        consulta.append(" GROUP BY articulos.marketplace, articulos.idPedido,"
                + " articulos.codigoArticulo ORDER BY pedidos.fechaPedido DESC LIMIT 3000");
        return consulta.toString();
    }

    public static String obtenerConsultaArticulos(String atributo, String valor) {
        switch (atributo) {
            case "Pedido":
                return CONSULTA_ARTICULOS_IDPEDIDO;
            case "Nombre":
                return CONSULTA_ARTICULOS_NOMBRE;
            case "Dirección":
                return CONSULTA_ARTICULOS_DIRECCION;
            case "Teléfono":
                return CONSULTA_ARTICULOS_TELEFONO;
            case "Artículo":
                return CONSULTA_ARTICULOS_ARTICULO;
            case "Compra":
                return CONSULTA_ARTICULOS_COMPRA;
            case "Ticket":
                return CONSULTA_ARTICULOS_NUMERO_DOCUMENTO;
            case "Albarán":
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
}
