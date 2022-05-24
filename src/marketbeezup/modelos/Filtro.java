/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketbeezup.modelos;

import marketbeezup.controladores.PedidosControlador;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Manolo
 */
public class Filtro {
    private int[] marketplace, tiendas, almacenes, agencias, estados;
    private boolean observaciones, existe, fechaSalida, fechaCompra, fechaTicket, fechaAlbaran;
    private Date fechaPedidoDesde, fechaPedidoHasta;
    private Date fechaSalidaDesde, fechaSalidaHasta, fechaCompraDesde, fechaCompraHasta, 
            fechaVentaDesde, fechaVentaHasta, fechaAlbaranDesde, fechaAlbaranHasta;
    
    
    /**
     * Devuelve una lista de Marketplace en función del filtro establecido
     *
     * @param marketSeleccionados
     * @return
     */
    public static List<String> obtenerMarketsFiltrados(int[] marketSeleccionados) {
        // El filtro almacena el índice de los marketplace seleccionados
        List<String> lista = new ArrayList<>();
        for (int i : marketSeleccionados) {
            lista.add(PedidosControlador.getMarkets().get(i));
        }
        return lista;
    }

    /**
     * Devuelve una lista de Tiendas en función del filtro establecido
     *
     * @param tiendasSeleccionadas
     * @return
     */
    public static List<String> obtenerTiendasFiltradas(int[] tiendasSeleccionadas) {
        // El filtro almacena el índice de las tiendas seleccionadas
        List<String> lista = new ArrayList<>();
        for (int i : tiendasSeleccionadas) {
            lista.add(PedidosControlador.getTiendas().get(i));
        }
        return lista;
    }

    /**
     * Devuelve una lista de estados según el filtro establecido
     *
     * @param estadosSeleccionados
     * @return
     */
    public static List<String> obtenerEstadosFiltrados(int[] estadosSeleccionados) {
        List<String> lista = new ArrayList<>();
        for (int i : estadosSeleccionados) {
            lista.add(PedidosControlador.getEstados().get(i));
        }
        return lista;
    }

    /**
     * Devuelve una lista de Agencias según el filtro establecido
     *
     * @param agenciasSeleccionadas
     * @return
     */
    public static List<String> obtenerAgenciasFiltradas(int[] agenciasSeleccionadas) {
        List<String> lista = new ArrayList<>();
        for (int i : agenciasSeleccionadas) {
            lista.add(PedidosControlador.getAgencias().get(i));
        }
        return lista;
    }

    /**
     * Devuelve una lista de almacenes según el filtro establecido
     *
     * @param almacenesSeleccionados
     * @return
     */
    public static List<String> obtenerAlmacenesFiltrados(int[] almacenesSeleccionados) {
        List<String> lista = new ArrayList<>();
        for (int i : almacenesSeleccionados) {
            lista.add(PedidosControlador.getAlmacenes().get(i));
        }
        return lista;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Arrays.hashCode(this.marketplace);
        hash = 29 * hash + Arrays.hashCode(this.tiendas);
        hash = 29 * hash + Arrays.hashCode(this.almacenes);
        hash = 29 * hash + Arrays.hashCode(this.agencias);
        hash = 29 * hash + Arrays.hashCode(this.estados);
        hash = 29 * hash + (this.observaciones ? 1 : 0);
        hash = 29 * hash + (this.existe ? 1 : 0);
        hash = 29 * hash + (this.fechaSalida ? 1 : 0);
        hash = 29 * hash + (this.fechaCompra ? 1 : 0);
        hash = 29 * hash + (this.fechaTicket ? 1 : 0);
        hash = 29 * hash + (this.fechaAlbaran ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.fechaPedidoDesde);
        hash = 29 * hash + Objects.hashCode(this.fechaPedidoHasta);
        hash = 29 * hash + Objects.hashCode(this.fechaSalidaDesde);
        hash = 29 * hash + Objects.hashCode(this.fechaSalidaHasta);
        hash = 29 * hash + Objects.hashCode(this.fechaCompraDesde);
        hash = 29 * hash + Objects.hashCode(this.fechaCompraHasta);
        hash = 29 * hash + Objects.hashCode(this.fechaVentaDesde);
        hash = 29 * hash + Objects.hashCode(this.fechaVentaHasta);
        hash = 29 * hash + Objects.hashCode(this.fechaAlbaranDesde);
        hash = 29 * hash + Objects.hashCode(this.fechaAlbaranHasta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filtro other = (Filtro) obj;
        if (this.observaciones != other.observaciones) {
            return false;
        }
        if (this.existe != other.existe) {
            return false;
        }
        if (this.fechaSalida != other.fechaSalida) {
            return false;
        }
        if (this.fechaCompra != other.fechaCompra) {
            return false;
        }
        if (this.fechaTicket != other.fechaTicket) {
            return false;
        }
        if (this.fechaAlbaran != other.fechaAlbaran) {
            return false;
        }
        if (!Arrays.equals(this.marketplace, other.marketplace)) {
            return false;
        }
        if (!Arrays.equals(this.tiendas, other.tiendas)) {
            return false;
        }
        if (!Arrays.equals(this.almacenes, other.almacenes)) {
            return false;
        }
        if (!Arrays.equals(this.agencias, other.agencias)) {
            return false;
        }
        if (!Arrays.equals(this.estados, other.estados)) {
            return false;
        }
        if (!Objects.equals(this.fechaPedidoDesde, other.fechaPedidoDesde)) {
            return false;
        }
        if (!Objects.equals(this.fechaPedidoHasta, other.fechaPedidoHasta)) {
            return false;
        }
        if (!Objects.equals(this.fechaSalidaDesde, other.fechaSalidaDesde)) {
            return false;
        }
        if (!Objects.equals(this.fechaSalidaHasta, other.fechaSalidaHasta)) {
            return false;
        }
        if (!Objects.equals(this.fechaCompraDesde, other.fechaCompraDesde)) {
            return false;
        }
        if (!Objects.equals(this.fechaCompraHasta, other.fechaCompraHasta)) {
            return false;
        }
        if (!Objects.equals(this.fechaVentaDesde, other.fechaVentaDesde)) {
            return false;
        }
        if (!Objects.equals(this.fechaVentaHasta, other.fechaVentaHasta)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlbaranDesde, other.fechaAlbaranDesde)) {
            return false;
        }
        return Objects.equals(this.fechaAlbaranHasta, other.fechaAlbaranHasta);
    }

    public int[] getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(int[] marketplace) {
        this.marketplace = marketplace;
    }

    public int[] getTiendas() {
        return tiendas;
    }

    public void setTiendas(int[] tiendas) {
        this.tiendas = tiendas;
    }

    public int[] getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(int[] almacenes) {
        this.almacenes = almacenes;
    }

    public int[] getAgencias() {
        return agencias;
    }

    public void setAgencias(int[] agencias) {
        this.agencias = agencias;
    }

    public int[] getEstados() {
        return estados;
    }

    public void setEstados(int[] estados) {
        this.estados = estados;
    }

    public boolean isObservaciones() {
        return observaciones;
    }

    public void setObservaciones(boolean observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public boolean isFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(boolean fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(boolean fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean isFechaTicket() {
        return fechaTicket;
    }

    public void setFechaTicket(boolean fechaTicket) {
        this.fechaTicket = fechaTicket;
    }

    public boolean isFechaAlbaran() {
        return fechaAlbaran;
    }

    public void setFechaAlbaran(boolean fechaAlbaran) {
        this.fechaAlbaran = fechaAlbaran;
    }

    public Date getFechaPedidoDesde() {
        return fechaPedidoDesde;
    }

    public void setFechaPedidoDesde(Date fechaPedidoDesde) {
        this.fechaPedidoDesde = fechaPedidoDesde;
    }

    public Date getFechaPedidoHasta() {
        return fechaPedidoHasta;
    }

    public void setFechaPedidoHasta(Date fechaPedidoHasta) {
        this.fechaPedidoHasta = fechaPedidoHasta;
    }

    public Date getFechaSalidaDesde() {
        return fechaSalidaDesde;
    }

    public void setFechaSalidaDesde(Date fechaSalidaDesde) {
        this.fechaSalidaDesde = fechaSalidaDesde;
    }

    public Date getFechaSalidaHasta() {
        return fechaSalidaHasta;
    }

    public void setFechaSalidaHasta(Date fechaSalidaHasta) {
        this.fechaSalidaHasta = fechaSalidaHasta;
    }

    public Date getFechaCompraDesde() {
        return fechaCompraDesde;
    }

    public void setFechaCompraDesde(Date fechaCompraDesde) {
        this.fechaCompraDesde = fechaCompraDesde;
    }

    public Date getFechaCompraHasta() {
        return fechaCompraHasta;
    }

    public void setFechaCompraHasta(Date fechaCompraHasta) {
        this.fechaCompraHasta = fechaCompraHasta;
    }

    public Date getFechaVentaDesde() {
        return fechaVentaDesde;
    }

    public void setFechaVentaDesde(Date fechaVentaDesde) {
        this.fechaVentaDesde = fechaVentaDesde;
    }

    public Date getFechaVentaHasta() {
        return fechaVentaHasta;
    }

    public void setFechaVentaHasta(Date fechaVentaHasta) {
        this.fechaVentaHasta = fechaVentaHasta;
    }

    public Date getFechaAlbaranDesde() {
        return fechaAlbaranDesde;
    }

    public void setFechaAlbaranDesde(Date fechaAlbaranDesde) {
        this.fechaAlbaranDesde = fechaAlbaranDesde;
    }

    public Date getFechaAlbaranHasta() {
        return fechaAlbaranHasta;
    }

    public void setFechaAlbaranHasta(Date fechaAlbaranHasta) {
        this.fechaAlbaranHasta = fechaAlbaranHasta;
    }
}
