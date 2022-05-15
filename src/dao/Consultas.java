/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
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

    private final String CONSULTA_PEDIDOS_IDPEDIDO = """
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
                        AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
                    WHERE pedidos.idPedido LIKE ? ORDER BY pedidos.fechaPedido""";

    private final String CONSULTA_PEDIDOS_NOMBRE = """
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
                        AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
                    WHERE pedidos.nombreApellidos LIKE ? ORDER BY pedidos.fechaPedido""";

    private final String CONSULTA_PEDIDOS_DIRECCION = """
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
                        AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
                    WHERE pedidos.direccion LIKE ? ORDER BY pedidos.fechaPedido""";

    private final String CONSULTA_PEDIDOS_TELEFONO = """
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
                        AND articulos.codigoArticulo = albaranesVenta.codigoArticulo)
                    WHERE pedidos.telefono LIKE ? ORDER BY pedidos.fechaPedido""";
    
    

   /* "Artículo");
    modeloComboBuscar.addElement (

    "Compra");
    modeloComboBuscar.addElement (

    "Ticket");
    modeloComboBuscar.addElement (

    "Albarán");*/

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

    public static String obtenerConsultaArticulos(Filtro filtro) {
        return null;
    }

    public static String obtenerConsultaArticulos(String atributo, String valor) {
        return null;
    }

    public static String obtenerConsultaObservaciones(Filtro filtro) {
        return null;
    }

    public static String obtenerConsultaObservaciones(String atributo, String valor) {
        return null;
    }
}
