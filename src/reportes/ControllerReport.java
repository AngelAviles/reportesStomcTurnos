/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dominio.Turn;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author angel
 */
public class ControllerReport {

    public void generarReporte(List<Turn> turnos) {
        InputStream inputStream = null;
        JasperPrint jasperPrint = null;

        TurnosDataSource dataSource = new TurnosDataSource();

        for (Turn turno : turnos) {
            dataSource.addTurno(turno);
        }

        inputStream = this.getClass().getClassLoader().getResourceAsStream("reportes/reportesTurnos.jrxml");

        try {
            BufferedImage img = ImageIO.read(this.getClass().getClassLoader().getResource("reportes/logo.png"));
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("logo", img);

            System.out.println(img);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            //JasperViewer.viewReport(jasperPrint, false);

            File pdf = File.createTempFile("output.", ".pdf");
            System.out.println("Lo exportamos a " + pdf.getAbsolutePath());

            //JasperExportManager.exportReportToPdfFile(jasperPrint, "src/reportes/reporte.pdf"); 
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdf.getAbsolutePath());

            if (pdf.exists()) {

                if (Desktop.isDesktopSupported()) {

//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(ControllerReport.class.getName()).log(Level.SEVERE, null, ex);
//                    }

                    Desktop.getDesktop().open(pdf);

                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ControllerReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
