package org.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //loading view resouce
        Parent root = FXMLLoader.load(getClass().getResource("CalcView.fxml"));

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("org/calc/icon/icon.png"));
        stage.setTitle("Calc");
        
        //setting style params, like icon, background, size....
        root.setStyle("-fx-background-image: url('" + "org/calc/icon/back.jpg" + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: 300 300;"
                + "-fx-background-repeat: stretch;");
        
        //setting and showing scene
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launching app
        launch(args);
    }

}
