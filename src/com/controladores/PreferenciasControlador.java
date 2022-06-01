/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.modelos.Preferencias;
import com.vistas.PreferenciasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Manolo
 */
public class PreferenciasControlador implements ActionListener {

    private Preferencias preferencias;
    private PreferenciasVista preferenciasVista;
    private boolean guardar;

    public PreferenciasControlador(Preferencias preferencias, PreferenciasVista preferenciasVista) {
        this.preferencias = preferencias;
        this.preferenciasVista = preferenciasVista;
        guardar = false;
    }

    public void actualizarVista() {
        preferenciasVista.actualizarVista(preferencias);
        preferenciasVista.setLocationRelativeTo(null);
        preferenciasVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GuardarPreferencias":
                try {
                preferenciasVista.actualizarPreferencias();
            } catch (Exception ex) {
                preferenciasVista.mostrarMensaje("Introduzca un valor numérico válido");
                return;
            }
            if (preferencias.getDireccionIPMarket().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la IP Market");
                return;
            }
            if (preferencias.getPuertoMarket() <= 0) {
                preferenciasVista.mostrarMensaje("Introduzca un valor válido para el puerto Market");
                return;
            }
            if (preferencias.getBdMarket().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la Base de Datos Market");
                return;
            }
            if (preferencias.getUsuarioMarket().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el usuario Market");
                return;
            }
            if (preferencias.getPassMarket().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el password Market");
                return;
            }
            if (preferencias.getDiasMarket() <= 0) {
                preferenciasVista.mostrarMensaje("Introduzca un valor válido para los días Market");
                return;
            }
            if (preferencias.getDireccionIPOnline().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la IP Online");
                return;
            }
            if (preferencias.getPuertoOnline() <= 0) {
                preferenciasVista.mostrarMensaje("Introduzca un valor válido para el puerto Online");
                return;
            }
            if (preferencias.getBdOnline().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la Base de Datos Online");
                return;
            }
            if (preferencias.getUsuarioOnline().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el usuario Online");
                return;
            }
            if (preferencias.getPassOnline().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el password Online");
                return;
            }
            if (preferencias.getDireccionIPERP().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la IP ERP");
                return;
            }
            if (preferencias.getPuertoERP() <= 0) {
                preferenciasVista.mostrarMensaje("Introduzca un valor válido para el puerto ERP");
                return;
            }
            if (preferencias.getBdERP().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para la Base de Datos ERP");
                return;
            }
            if (preferencias.getUsuarioERP().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el usuario ERP");
                return;
            }
            if (preferencias.getPassERP().isBlank()) {
                preferenciasVista.mostrarMensaje("Introduzca un valor para el password ERP");
                return;
            }
            preferencias.almacenarPreferencias();
            guardar = true;
            preferenciasVista.dispose();
            break;
            case "CancelarPreferencias":
                preferenciasVista.dispose();
                break;
        }
    }

    public Preferencias getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
    }

    public PreferenciasVista getPreferenciasVista() {
        return preferenciasVista;
    }

    public void setPreferenciasVista(PreferenciasVista preferenciasVista) {
        this.preferenciasVista = preferenciasVista;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
}
