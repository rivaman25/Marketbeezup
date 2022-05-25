/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manolo
 */
public class Pedido {

    private String tienda, marketplace, idPedido, dni, nombreApellidos,
            direccion, cp, poblacion, provincia, telefono, email;
    private Timestamp fechaPedido;
    private float importe, comision, costePorte;
    private List<Articulo> articulos;
    private List<Observacion> observaciones;
    
    /**
     *
     * @param marketplace
     * @param idPedido
     * @param pedidos
     * @return
     */
    public static int existePedido(String marketplace, String idPedido, List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.getMarketplace().equalsIgnoreCase(marketplace)
                    & pedido.getIdPedido().equalsIgnoreCase(idPedido)) {
                return pedidos.indexOf(pedido);
            }
        }
        return -1;
    }

    public Pedido() {
        articulos = new ArrayList<>();
        observaciones = new ArrayList<>();
    }
    
    public void NuevoArticulo(Articulo articulo) {
        articulos.add(articulo);
    }
    
    public void NuevaObservacion(Observacion observacion) {
        observaciones.add(observacion);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Timestamp getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Timestamp fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public float getCostePorte() {
        return costePorte;
    }

    public void setCostePorte(float costePorte) {
        this.costePorte = costePorte;
    }
}
