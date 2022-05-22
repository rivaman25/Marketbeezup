/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketbeezup.controladores;

import marketbeezup.dao.ConexionBD;
import marketbeezup.dao.DAOArticuloImpl;
import marketbeezup.daoInterfaces.DAOArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import marketbeezup.modelos.Articulo;
import net.sf.jasperreports.engine.JRException;
import marketbeezup.vistas.ImprimirVista;
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
        super("jdbc:mysql://", "localhost", 3306, "marketbeezup", "root", "Mrbmysql2536");
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
                InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("marketbeezup/informes/AlbaranesWEB.jasper");
                Map<String, Object> parametros = new HashMap<>();
                try {
                    this.openConnection();
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
                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(reporte, null, this.getConnection());
                    JasperViewer jv = new JasperViewer(jp, true);
                    viewer.getContentPane().add(jv.getContentPane());
                    viewer.setVisible(true);
                } catch (JRException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    this.closeConnection();
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
