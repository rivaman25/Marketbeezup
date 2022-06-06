/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelos;

import java.util.List;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class Almacen {

    private String idAlmacen, almacen;

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
    
    public static int obtenerIndiceAlmacen(String idAlmacen, List<Almacen> almacenes) {
        for (Almacen almacen : almacenes) {
            if (almacen.getIdAlmacen().equals(idAlmacen))
                return almacenes.indexOf(almacen);
        }
        return -1;
    }
}
