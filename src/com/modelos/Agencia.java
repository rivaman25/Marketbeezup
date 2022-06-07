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
public class Agencia {

    private String idAgencia;

    public String getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(String idAgencia) {
        this.idAgencia = idAgencia;
    }

    public static int obtenerIndiceAgencia(String idAgencia, List<Agencia> agencias) {
        for (Agencia agencia : agencias) {
            if (agencia.getIdAgencia().equals(idAgencia)) {
                return agencias.indexOf(agencia);
            }
        }
        return -1;
    }
}
