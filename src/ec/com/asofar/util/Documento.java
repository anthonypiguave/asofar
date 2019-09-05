/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author michaels
 */
public class Documento {

    static int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    static int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void Reporte(String mensaje, List lista, String url) {

        int panel = JOptionPane.showConfirmDialog(null, mensaje, "REPORTE", JOptionPane.YES_NO_OPTION);
        if (panel == JOptionPane.YES_OPTION) {
            try {                
                JDialog ventana = new JDialog();
                JasperReport report = (JasperReport) JRLoader.loadObject(System.getProperty("user.dir") + url);
                JasperPrint jprint = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
                JRViewer jviewer = new JRViewer(jprint);
                JasperViewer title = new JasperViewer(jprint);
                ventana.add(jviewer);
                ventana.setSize(new Dimension(ancho / 2 + 100, alto / 2 + 500));
                jviewer.setFitWidthZoomRatio();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
