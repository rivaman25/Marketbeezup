/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.ConexionBD;
import com.daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import com.modelos.Articulo;
import com.modelos.Preferencias;
import com.principal.Main;
import net.sf.jasperreports.engine.JRException;
import com.vistas.ImprimirVista;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Manuel Rivallo Bejarano
 */
public class ImprimirControlador extends ConexionBD implements ActionListener {

    private ImprimirVista imprimirVista;
    private List<Articulo> articulosImpr;
    private final DAOArticulo DAO_ARTICULO;
    private boolean impreso, reimprimir;

    public ImprimirControlador(boolean reimprimir, ImprimirVista imprimirVista, Preferencias preferencias) throws SQLException {
        super("jdbc:mysql://", preferencias.getDireccionIPMarket(), preferencias.getPuertoMarket(),
                preferencias.getBdMarket(), preferencias.getUsuarioMarket(), preferencias.getPassMarket());
        this.imprimirVista = imprimirVista;
        this.reimprimir = reimprimir;
        DAO_ARTICULO = PedidosControlador.getDaoArticulo();
        // En función de la variable reimprimir obtengo los pedidos impresos o pendientes de imprimir
        if (reimprimir) {
            articulosImpr = DAO_ARTICULO.listar(null, null, new ArrayList<>(), reimprimir);
        } else {
            articulosImpr = DAO_ARTICULO.listar(null, Main.fechaActual(), new ArrayList<>(), reimprimir);
        }
        impreso = false;
    }

    public void actualizarVista() {
        if (!reimprimir) {
            imprimirVista.setFechaSeleccionada(Main.fechaActual());
        }
        imprimirVista.actualizarTabla(articulosImpr);
        imprimirVista.setVisible(true);
    }

    public void actualizarAgenciasVista() {
        imprimirVista.actualizarAgencias(articulosImpr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "ImprimirAlbaranes":
                    if (reimprimir) {
                        articulosImpr = imprimirVista.obtenerArticulosSeleccionados();
                    }
                    if (articulosImpr.isEmpty()) {
                        if (reimprimir) {
                            imprimirVista.mostrarMensaje("Seleccione pedido para reimprimir albarán");
                        } else {
                            imprimirVista.mostrarMensaje("No hay albaranes para imprimir");
                        }
                    } else {
                        // Si introduzco el informe JasperReport en un paquete de la aplicación lo cargo en un stream
                        InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/informes/AlbaranesWEB.jasper");
                        Map<String, Object> parametros = new HashMap<>();
                        // Si quiero reimprimir Albaranes obtengo los que selecciona el usuario
                        try {
                            this.openConnection();
                            // this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Modifico el cursor para indicar que está trabajando
                            parametros.put("idPedidos", Articulo.getIdpedidos(articulosImpr));
                            parametros.put("marketplaces", Articulo.getMarketplace(articulosImpr));
                            parametros.put("codigoArticulos", Articulo.getCodigoArticulos(articulosImpr));
                            JDialog viewer = new JDialog(imprimirVista, "Impresión Albaranes", true);
                            viewer.setSize(800, 600);
                            viewer.setLocationRelativeTo(null);
                            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);
                            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(reporte, parametros, this.getConnection());
                            JasperViewer jv = new JasperViewer(jp, true);
                            viewer.getContentPane().add(jv.getContentPane());
                            viewer.setVisible(true);
                            if (!reimprimir) {
                                imprimirVista.habilitarMarcar(true);
                            }
                        } catch (JRException ex) {
                            JOptionPane.showMessageDialog(imprimirVista, "No se puede obtener el Albarán", "Error Albarán", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            this.closeConnection();
                        }
                    }
                    break;
                case "MarcarImpresos":
                    java.sql.Timestamp fechaHoraAct = java.sql.Timestamp.valueOf(LocalDateTime.now());
                    for (Articulo articulo : articulosImpr) {
                        articulo.setFechaHoraImpr(fechaHoraAct);
                        articulo.setEstado("ENVIAR");
                        DAO_ARTICULO.modificar(articulo);
                    }
                    imprimirVista.actualizarTabla(articulosImpr);
                    imprimirVista.deshabilitarControles();
                    impreso = true;
                    break;
                case "FiltrarAlbaranes":
                    articulosImpr = DAO_ARTICULO.listar(imprimirVista.getIdPedido(),
                            imprimirVista.getFechaSeleccionada(), imprimirVista.getAgenciasSeleccionadas(), reimprimir);
                    imprimirVista.actualizarTabla(articulosImpr);
                    break;
                case "LimpiarFiltroAlbaranes":
                    imprimirVista.setIdPedido(null);
                    imprimirVista.setFechaSeleccionada(null);
                    imprimirVista.setAgenciasSeleccionadas(null);
                    articulosImpr = DAO_ARTICULO.listar(imprimirVista.getIdPedido(),
                            imprimirVista.getFechaSeleccionada(), imprimirVista.getAgenciasSeleccionadas(), reimprimir);
                    imprimirVista.actualizarTabla(articulosImpr);
                    break;
                case "Salir":
                    imprimirVista.dispose();
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(imprimirVista, "No hay conexión con la Base de Datos", "Error al conectar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Articulo> getArticulosImpr() {
        return articulosImpr;
    }

    public void setArticulosImpr(List<Articulo> articulosImpr) {
        this.articulosImpr = articulosImpr;
    }

    public ImprimirVista getImprimirVista() {
        return imprimirVista;
    }

    public void setImprimirVista(ImprimirVista imprimirVista) {
        this.imprimirVista = imprimirVista;
    }

    public boolean isImpreso() {
        return impreso;
    }

    public void setImpreso(boolean impreso) {
        this.impreso = impreso;
    }

    public boolean isReimprimir() {
        return reimprimir;
    }

    public void setReimprimir(boolean reimprimir) {
        this.reimprimir = reimprimir;
    }
}
