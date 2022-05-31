/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controladores;

import com.dao.ConexionBD;
import com.dao.DAOArticuloImpl;
import com.daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import com.modelos.Articulo;
import net.sf.jasperreports.engine.JRException;
import com.vistas.ImprimirVista;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Manolo
 */
public class ImprimirControlador extends ConexionBD implements ActionListener {

    private ImprimirVista imprimirVista;
    private List<Articulo> articulosImpr;
    private final DAOArticulo DAO_ARTICULO;

    public ImprimirControlador(ImprimirVista imprimirVista) throws Exception {
        super("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        this.imprimirVista = imprimirVista;
        DAO_ARTICULO = new DAOArticuloImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        articulosImpr = DAO_ARTICULO.listar(null, null, new ArrayList<>(), false);
    }

    public void actualizarVista() {
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
                    if (!articulosImpr.isEmpty()) {
                        // Si introduzco el informe JasperReport en un paquete de la aplicación lo cargo en un stream
                        InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/informes/AlbaranesWEB.jasper");
                        Map<String, Object> parametros = new HashMap<>();
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
                            imprimirVista.habilitarMarcar(true);
                        } catch (JRException ex) {
                            System.out.println(ex.getMessage());
                        } finally {
                            this.closeConnection();
                        }
                    }
                    break;
                case "MarcarImpresos":
                    java.sql.Timestamp fechaHoraAct = java.sql.Timestamp.valueOf(LocalDateTime.now());
                    DAO_ARTICULO.actualizarFechaHoraImpr(fechaHoraAct, articulosImpr);
                    for (Articulo articulo : articulosImpr) {
                        articulo.setFechaHoraImpr(fechaHoraAct);
                    }
                    imprimirVista.actualizarTabla(articulosImpr);
                    imprimirVista.deshabilitarControles();
                    break;
                case "FiltrarAlbaranes":
                    articulosImpr = DAO_ARTICULO.listar(imprimirVista.getIdPedido(),
                            imprimirVista.getFechaSeleccionada(), imprimirVista.getAgenciasSeleccionadas(), false);
                    imprimirVista.actualizarTabla(articulosImpr);
                    break;
                case "LimpiarFiltroAlbaranes":
                    imprimirVista.setIdPedido(null);
                    imprimirVista.setFechaSeleccionada(null);
                    imprimirVista.setAgenciasSeleccionadas(null);
                    articulosImpr = DAO_ARTICULO.listar(imprimirVista.getIdPedido(),
                            imprimirVista.getFechaSeleccionada(), imprimirVista.getAgenciasSeleccionadas(), false);
                    imprimirVista.actualizarTabla(articulosImpr);
                case "Salir":
                    imprimirVista.dispose();
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ImprimirControlador.class.getName()).log(Level.SEVERE, null, ex);
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
}
