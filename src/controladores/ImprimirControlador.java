/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import dao.ConexionBD;
import dao.DAOArticuloImpl;
import daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import modelos.Articulo;
import net.sf.jasperreports.engine.JRException;
import vistas.ImprimirVista;
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

    public ImprimirControlador(ImprimirVista imprimirVista) throws Exception {
        this.imprimirVista = imprimirVista;
        DAOArticulo daoArticulo = new DAOArticuloImpl("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
        articulosImpr = daoArticulo.listarArticulosImpr(false);
    }

    public void actualizarVista() {
        imprimirVista.actualizarTabla(articulosImpr);
        imprimirVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ImprimirAlbaranes":
                // Si introduzco el informe JasperReport en un paquete de la aplicación lo cargo en un stream
                InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("informes/AlbaranesWEB.jasper");
                Map<String, Object> parametros = new HashMap<>();
                try {
                    // this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Modifico el cursor para indicar que está trabajando
                    // parametros.put("Impresos", impreso);
                    // parametros.put("IdPedidos", pedidosImprimir.getIdPedidosConFiltro());
                    // parametros.put("Idarticulos", pedidosImprimir.getIdarticulosConFiltro());
                    // parametros.put("Agencias", pedidosImprimir.getAgenciasConFiltro());
                    // parametros.put("FechasSalida", pedidosImprimir.getFechasSalidaConFiltro());
                    // parametros.put("Limite", PREFERENCIAS.getLimite());
                    JDialog viewer = new JDialog(imprimirVista, "Impresión Albaranes", true);
                    viewer.setSize(800, 600);
                    viewer.setLocationRelativeTo(null);
                    JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);
                    this.openConnection();
                    /* 
                     * Si el informe está en el directorio raiz de la aplicación lo compilo así
                     * JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
                     * JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jasperFileName, parametros, connection);
                     */
                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(reporte, parametros, this.getConnection());
                    JasperViewer jv = new JasperViewer(jp, true);
                    viewer.getContentPane().add(jv.getContentPane());
                    viewer.setVisible(true);
                } catch (JRException ex) {
                }
                break;
            case "FiltrarAlbaranes":
                break;
            case "LimpiarFiltroAlbaranes":
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
