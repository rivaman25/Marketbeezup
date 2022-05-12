/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manolo
 */
public class Articulo {

    private String codigoArticulo, descripcion, familia, subfamilia, marca,
            estado, marketplace, idPedido, tipoArticulo;
    private float precio, puc, beneficio;
    private int cantidad;
    private Timestamp fechaHoraImpr;
    private Compra compra;
    private Envio envio;
    private List<StockAlmacen> stock;
    private DocumentoVenta documentoVenta;
    private AlbaranVenta albaranVenta;

    public Articulo() {
        stock = new ArrayList<>();
    }

    public void NuevoEnvio(Date fecha, String almacen, String agencia,
            String codigoArticulo, String idPedido, String marketplace) {
        envio = new Envio();
        this.envio.setFechaSalida(fecha);
        this.envio.setIdAlmacen(almacen);
        this.envio.setIdAgencia(agencia);
        this.envio.setCodigoArticulo(codigoArticulo);
        this.envio.setIdPedido(idPedido);
        this.envio.setMarketplace(marketplace);
    }

    public void NuevaCompra(String idCompra, String proveedor, Date fechaCompra,
            Date fechaEntrega, String codigoArticulo, String idPedido,
            String marketplace) {
        compra = new Compra();
        this.compra.setIdCompra(idCompra);
        this.compra.setProveedor(proveedor);
        this.compra.setFechaCompra(fechaCompra);
        this.compra.setFechaEntrada(fechaEntrega);
        this.compra.setCodigoArticulo(codigoArticulo);
        this.compra.setIdPedido(idPedido);
        this.compra.setMarketplace(marketplace);
    }

    public void NuevoDocumentoVenta(String numeroVenta, Date fecha,
            String codigoArticulo, String idPedido, String marketplace) {
        documentoVenta = new DocumentoVenta();
        this.documentoVenta.setNumeroVenta(numeroVenta);
        this.documentoVenta.setFechaVenta(fecha);
        this.documentoVenta.setCodigoArticulo(codigoArticulo);
        this.documentoVenta.setIdPedido(idPedido);
        this.documentoVenta.setMarketplace(marketplace);

    }

    public void NuevoAlbaranVenta(String numeroAlbaran, Date fecha,
            String codigoArticulo, String idPedido, String marketplace) {
        albaranVenta = new AlbaranVenta();
        this.albaranVenta.setNumeroAlbaran(numeroAlbaran);
        this.albaranVenta.setFechaAlbaran(fecha);
        this.albaranVenta.setCodigoArticulo(codigoArticulo);
        this.albaranVenta.setIdPedido(idPedido);
        this.albaranVenta.setMarketplace(marketplace);
    }

    public AlbaranVenta getAlbaranVenta() {
        return albaranVenta;
    }

    public void setAlbaranVenta(AlbaranVenta albaranVenta) {
        this.albaranVenta = albaranVenta;
    }

    public DocumentoVenta getDocumentoVenta() {
        return documentoVenta;
    }

    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

    public List<StockAlmacen> getStock() {
        return stock;
    }

    public void setStock(List<StockAlmacen> stock) {
        this.stock = stock;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(String subfamilia) {
        this.subfamilia = subfamilia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPuc() {
        return puc;
    }

    public void setPuc(float puc) {
        this.puc = puc;
    }

    public float getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(float beneficio) {
        this.beneficio = beneficio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFechaHoraImpr() {
        return fechaHoraImpr;
    }

    public void setFechaHoraImpr(Timestamp fechaHoraImpr) {
        this.fechaHoraImpr = fechaHoraImpr;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
