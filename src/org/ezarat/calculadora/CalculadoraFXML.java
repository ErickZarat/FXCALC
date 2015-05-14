/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ezarat.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author informatica
 */
public class CalculadoraFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("org/ezarat/icono/icon.png"));
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Erick Zarat
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
