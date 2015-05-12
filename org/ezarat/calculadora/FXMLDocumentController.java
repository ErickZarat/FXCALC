package org.ezarat.calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author informatica
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtResultado;

    @FXML
    private void onButtonPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String texto = btn.getText();
        String textoEnResultado = txtResultado.getText();
        String resultado = "";
        if (textoEnResultado.equals("0")) {
            resultado = texto;
        } else {
            resultado = textoEnResultado + texto;
        }
        txtResultado.setText(resultado);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }
    
    @FXML
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtResultado.setText("0");
    }

}
