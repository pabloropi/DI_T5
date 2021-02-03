/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Pablo Rodriguez Pino 2ºDAM
 */
public class AppInformes extends Application {
    
    public static Connection conexion = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        conectaBD();
        Parent root = FXMLLoader.load(getClass().getResource("AppInformesView.fxml"));
        

        
        Scene scene = new Scene(root);
        
        primaryStage.getIcons().add(new Image("media/icon.png"));
        primaryStage.setTitle("AppInformes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {
        try {

            DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/xdb;shutdown=true");
        } catch (Exception ex) {
            System.out.println("No se pudo cerrar la conexion a la BD");
        }
    }
    
    public void conectaBD() {
        //Establecemos conexión con la BD
        String baseDatos = "jdbc:hsqldb:hsql://localhost:9001/xdb";
        String usuario = "sa";
        String clave = "";
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(baseDatos, usuario, clave);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        } catch (java.lang.InstantiationException sqlex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
