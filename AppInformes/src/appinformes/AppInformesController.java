/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Pablo Rodriguez Pino 2ºDAM
 */
public class AppInformesController implements Initializable {

    @FXML
    private Menu botonAbout;
    @FXML
    private MenuItem listadoFacturas;
    @FXML
    private MenuItem ventasTotales;
    @FXML
    private MenuItem facturasCliente;
    @FXML
    private MenuItem subListadoFacturas;
    @FXML
    private MenuItem salir;
    @FXML
    private MenuItem about;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void menuListadoFacturas(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/facturas.jasper"));

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr,
                    null, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void menuVentasTotales(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/Ventas_Totales.jasper"));

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr,
                    null, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void menuFacturasCliente(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IntroduceClienteView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("media/icon.png"));
        stage.setTitle("FacturasCliente");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void menuSubListadoFacturas(ActionEvent event) {
        try { 
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/facturasconsubinforme.jasper")); 
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/subinformefacturas.jasper"));
            //Map de parámetros 
            Map parametros = new HashMap(); 
            parametros.put("subReportParameter", jsr); 

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion); 
            JasperViewer.viewReport(jp,false); 
        } catch (JRException ex) { 
            System.out.println("Error al recuperar el jasper"); 
            JOptionPane.showMessageDialog(null, ex); 
        }
    }

    @FXML
    private void menuAbout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("media/icon.png"));
        stage.setTitle("About");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void menuSalir(ActionEvent event) {
        Stage owner = (Stage) salir.getParentPopup().getOwnerWindow();
        Scene scene = owner.getScene();
        owner.close();
    }

}
