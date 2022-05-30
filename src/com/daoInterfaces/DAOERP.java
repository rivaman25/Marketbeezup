/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import com.modelos.Stock;
import java.util.List;

/**
 *
 * @author Manolo
 */
public interface DAOERP {
    public List<Stock> obtenerStock(String codigoArticulo) throws Exception;
}
