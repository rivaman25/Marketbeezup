/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelos;

import java.util.prefs.Preferences;

/**
 * Almacena los parámetro de configuración de la aplicación
 *
 * @author Manuel Rivallo Bejarano
 */
public class Preferencias {

    private final Preferences prefsMarket, prefsOnline, prefsERP;
    private static final String DIRECCION_IP = "direccionIP";
    private static final String PUERTO = "puerto";
    private static final String BASE_DATOS = "baseDatos";
    private static final String USUARIO = "usuario";
    private static final String PASSWORD = "password";
    private static final String DIAS = "dias";

    private String direccionIPMarket;
    private String bdMarket;
    private String usuarioMarket;
    private String passMarket;
    private int puertoMarket;
    private int diasMarket;
    private String direccionIPOnline;
    private String bdOnline;
    private String usuarioOnline;
    private String passOnline;
    private int puertoOnline;
    private String direccionIPERP;
    private String bdERP;
    private String usuarioERP;
    private String passERP;
    private int puertoERP;

    /**
     * Crea unas nuevas preferencias de usuario
     */
    public Preferencias() {
        prefsMarket = Preferences.userRoot().node("/com/Market");
        prefsOnline = Preferences.userRoot().node("/com/Online");
        prefsERP = Preferences.userRoot().node("/com/ERP");
        direccionIPMarket = prefsMarket.get(DIRECCION_IP, "localhost");
        bdMarket = prefsMarket.get(BASE_DATOS, "marketbeezup");
        usuarioMarket = prefsMarket.get(USUARIO, "root");
        passMarket = prefsMarket.get(PASSWORD, "1234");
        puertoMarket = prefsMarket.getInt(PUERTO, 3306);
        diasMarket = prefsMarket.getInt(DIAS, 90);
        direccionIPOnline = prefsOnline.get(DIRECCION_IP, "localhost");
        bdOnline = prefsOnline.get(BASE_DATOS, "online");
        usuarioOnline = prefsOnline.get(USUARIO, "root");
        passOnline = prefsOnline.get(PASSWORD, "1234");
        puertoOnline = prefsOnline.getInt(PUERTO, 3306);
        direccionIPERP = prefsERP.get(DIRECCION_IP, "localhost");
        bdERP = prefsERP.get(BASE_DATOS, "erp");
        usuarioERP = prefsERP.get(USUARIO, "root");
        passERP = prefsERP.get(PASSWORD, "1234");
        puertoERP = prefsERP.getInt(PUERTO, 3306);
    }

    public void almacenarPreferencias() {
        prefsMarket.put(DIRECCION_IP, direccionIPMarket);
        prefsMarket.put(BASE_DATOS, bdMarket);
        prefsMarket.put(USUARIO, usuarioMarket);
        prefsMarket.put(PASSWORD, passMarket);
        prefsMarket.putInt(PUERTO, puertoMarket);
        prefsMarket.putInt(DIAS, diasMarket);
        prefsOnline.put(DIRECCION_IP, direccionIPOnline);
        prefsOnline.put(BASE_DATOS, bdOnline);
        prefsOnline.put(USUARIO, usuarioOnline);
        prefsOnline.put(PASSWORD, passOnline);
        prefsOnline.putInt(PUERTO, puertoOnline);
        prefsERP.put(DIRECCION_IP, direccionIPERP);
        prefsERP.put(BASE_DATOS, bdERP);
        prefsERP.put(USUARIO, usuarioERP);
        prefsERP.put(PASSWORD, passERP);
        prefsERP.putInt(PUERTO, puertoERP);
    }

    public String getDireccionIPMarket() {
        return direccionIPMarket;
    }

    public void setDireccionIPMarket(String direccionIPMarket) {
        this.direccionIPMarket = direccionIPMarket;
    }

    public String getBdMarket() {
        return bdMarket;
    }

    public void setBdMarket(String bdMarket) {
        this.bdMarket = bdMarket;
    }

    public String getUsuarioMarket() {
        return usuarioMarket;
    }

    public void setUsuarioMarket(String usuarioMarket) {
        this.usuarioMarket = usuarioMarket;
    }

    public String getPassMarket() {
        return passMarket;
    }

    public void setPassMarket(String passMarket) {
        this.passMarket = passMarket;
    }

    public int getPuertoMarket() {
        return puertoMarket;
    }

    public void setPuertoMarket(int puertoMarket) {
        this.puertoMarket = puertoMarket;
    }

    public int getDiasMarket() {
        return diasMarket;
    }

    public void setDiasMarket(int diasMarket) {
        this.diasMarket = diasMarket;
    }

    public String getDireccionIPOnline() {
        return direccionIPOnline;
    }

    public void setDireccionIPOnline(String direccionIPOnline) {
        this.direccionIPOnline = direccionIPOnline;
    }

    public String getBdOnline() {
        return bdOnline;
    }

    public void setBdOnline(String bdOnline) {
        this.bdOnline = bdOnline;
    }

    public String getUsuarioOnline() {
        return usuarioOnline;
    }

    public void setUsuarioOnline(String usuarioOnline) {
        this.usuarioOnline = usuarioOnline;
    }

    public String getPassOnline() {
        return passOnline;
    }

    public void setPassOnline(String passOnline) {
        this.passOnline = passOnline;
    }

    public int getPuertoOnline() {
        return puertoOnline;
    }

    public void setPuertoOnline(int puertoOnline) {
        this.puertoOnline = puertoOnline;
    }

    public String getDireccionIPERP() {
        return direccionIPERP;
    }

    public void setDireccionIPERP(String direccionIPERP) {
        this.direccionIPERP = direccionIPERP;
    }

    public String getBdERP() {
        return bdERP;
    }

    public void setBdERP(String bdERP) {
        this.bdERP = bdERP;
    }

    public String getUsuarioERP() {
        return usuarioERP;
    }

    public void setUsuarioERP(String usuarioERP) {
        this.usuarioERP = usuarioERP;
    }

    public String getPassERP() {
        return passERP;
    }

    public void setPassERP(String passERP) {
        this.passERP = passERP;
    }

    public int getPuertoERP() {
        return puertoERP;
    }

    public void setPuertoERP(int puertoERP) {
        this.puertoERP = puertoERP;
    }
}
