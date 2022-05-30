/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Articulo;
import com.modelos.Envio;
import com.vistas.EnvioVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Manolo
 */
public class EnvioControlador implements ActionListener {
    
    private Articulo articulo;
    private EnvioVista envioVista;
    private Envio envio;

    public EnvioControlador(Articulo articulo, EnvioVista envioVista) {
        this.articulo = articulo;
        this.envioVista = envioVista;
    }
    
    public void actualizarVista() {
        envioVista.actualizarVista(articulo);
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public EnvioVista getEnvioVista() {
        return envioVista;
    }

    public void setEnvioVista(EnvioVista envioVista) {
        this.envioVista = envioVista;
    }
}