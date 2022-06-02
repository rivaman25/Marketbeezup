/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.daoInterfaces.DAOArticulo;
import com.daoInterfaces.DAOEnvio;
import com.modelos.Articulo;
import com.modelos.Envio;
import com.vistas.EnvioVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class EnvioControlador implements ActionListener {

    private Articulo articulo;
    private EnvioVista envioVista;
    private Envio envio;
    private boolean guardado;
    private boolean editar;
    private DAOEnvio daoEnvio;
    private DAOArticulo daoArticulo;

    public EnvioControlador(Articulo articulo, EnvioVista envioVista) {
        this.articulo = articulo;
        this.envioVista = envioVista;
        guardado = false;
        editar = false;
        daoEnvio = PedidosControlador.getDaoEnvio();
        daoArticulo = PedidosControlador.getDaoArticulo();
    }

    public void actualizarVista() {
        envioVista.actualizarVista(articulo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GuardarNuevoEnvio":
                envio = envioVista.obtenerEnvio();
                if (envio.getFechaSalida() == null) {
                    envioVista.mostrarMensaje("Debe introducir una fecha de salida");
                    return;
                }
                if (envio.getIdAgencia() == null) {
                    envioVista.mostrarMensaje("Debe introducir una agencia de transporte");
                    return;
                }
                if (envio.getIdAlmacen() == null) {
                    envioVista.mostrarMensaje("Debe introducir un almacén de salida");
                    return;
                }
                try {
                    if (editar) {
                        daoEnvio.modificar(envio);
                    } else {
                        articulo.setEstado("IMPRIMIR");
                        daoArticulo.modificar(articulo);
                        daoEnvio.registrar(envio, null);
                    }
                    guardado = true;
                    envioVista.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(envioVista, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "CancelarNuevoEnvio":
                envioVista.dispose();
                break;
        }
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

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public DAOEnvio getDaoEnvio() {
        return daoEnvio;
    }

    public void setDaoEnvio(DAOEnvio daoEnvio) {
        this.daoEnvio = daoEnvio;
    }

    public DAOArticulo getDaoArticulo() {
        return daoArticulo;
    }

    public void setDaoArticulo(DAOArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }
}
