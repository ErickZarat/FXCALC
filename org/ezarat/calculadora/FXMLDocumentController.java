package org.ezarat.calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author informatica
 */
public class FXMLDocumentController implements Initializable {

    private double numero, total;

    private enum Operacion {

        NINGUNA, DIVISION, SUMA, RESTA, MULTIPLICACION
    }

    private Operacion operacion;

    @FXML
    private TextField txtResultado;

    @FXML
    private void onButtonPressed(ActionEvent event) {

        Button btn = (Button) event.getSource();

        String texto = btn.getText();
        String textoEnResultado = txtResultado.getText();
        String resultado = "";

        if (textoEnResultado.equals("0") || operacion.equals(Operacion.NINGUNA)) {
            resultado = texto;
        } else {
            resultado = textoEnResultado + texto;
        }
        txtResultado.setText(resultado);
    }

    @FXML
    private void onOperationPressed(ActionEvent event) {

        numero = Double.parseDouble(txtResultado.getText());
        System.out.println(numero);
        txtResultado.setText("0");

        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        switch (btnId) {
            case "btnSuma":
                total += numero;
                operacion = Operacion.SUMA;
                break;
        }

        System.out.println(total);
    }

    @FXML
    private void onEqualsPressed(ActionEvent event) {
        numero = Double.parseDouble(txtResultado.getText());

        switch (operacion) {
            case SUMA:
                total += numero;
                break;
            case RESTA:
                break;
        }

        txtResultado.setText(String.valueOf(total));
        total = 0.0;
        operacion = Operacion.NINGUNA;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtResultado.setText("0");
        total = 0.0;
        numero = 0.0;
        operacion = Operacion.NINGUNA;
    }

}
