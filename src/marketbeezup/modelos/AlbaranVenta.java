/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelos;

import java.sql.Date;

/**
 *
 * @author Manolo
 */
public class AlbaranVenta {
    private String numeroAlbaran, codigoArticulo, marketplace, idPedido;;
    private Date fechaAlbaran;

    public AlbaranVenta() {
    }

    public AlbaranVenta(String numeroAlbaran, String codigoArticulo, String marketplace, String idPedido, Date fechaAlbaran) {
        this.numeroAlbaran = numeroAlbaran;
        this.codigoArticulo = codigoArticulo;
        this.marketplace = marketplace;
        this.idPedido = idPedido;
        this.fechaAlbaran = fechaAlbaran;
    }

    public String getNumeroAlbaran() {
        return numeroAlbaran;
    }

    public void setNumeroAlbaran(String numeroAlbaran) {
        this.numeroAlbaran = numeroAlbaran;
    }

    public Date getFechaAlbaran() {
        return fechaAlbaran;
    }

    public void setFechaAlbaran(Date fechaAlbaran) {
        this.fechaAlbaran = fechaAlbaran;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
}
