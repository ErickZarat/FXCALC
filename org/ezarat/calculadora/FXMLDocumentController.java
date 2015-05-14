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

    private double numero, total, numX, numY;

    private enum Operacion {

        NINGUNA, DIVISION, SUMA, RESTA, MULTIPLICACION, EXPONENTE, RAIZ, NEG_POS, IGUAL
    }
    private String resultado = new String();
    private Operacion operacion;

    @FXML
    private TextField txtResultado;

    @FXML
    private void onClearPressed(ActionEvent event) {
        txtResultado.setText("");
        operacion = Operacion.NINGUNA;

    }

    @FXML
    private void onButtonPressed(ActionEvent event) {

        Button btn = (Button) event.getSource();
        String texto = btn.getText();
        String textoEnResultado = txtResultado.getText();
        String resultado = "";

        if (operacion.equals(Operacion.IGUAL)) {
            operacion = Operacion.NINGUNA;
            total = 0.0;
            txtResultado.setText("0");
            textoEnResultado = "0";
        }

        if (textoEnResultado.equals("0")) {
            resultado = texto;
        } else {
            resultado = textoEnResultado + texto;
        }
        txtResultado.setText(resultado);
    }

    @FXML
    private void onBackSpacePressed(ActionEvent event) {
        if (txtResultado.getText().length() != 0) {
            txtResultado.setText(txtResultado.getText().substring(0, txtResultado.getText().length() - 1));
        }
    }

    @FXML
    private void onOperationPressed(ActionEvent event) {

        numero = Double.parseDouble(txtResultado.getText());
        System.out.println(numero);
        txtResultado.setText("");

        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        switch (btnId) {
            case "btnSuma":
                total += numero;
                operacion = Operacion.SUMA;
                break;
            case "btnResta":
                total = (total * -1) - numero;
                operacion = Operacion.RESTA;
                break;
            case "btnMultiplicacion":
                total = numero;
                operacion = Operacion.MULTIPLICACION;
                break;
            case "btnDivision":
                total = numero;
                operacion = Operacion.DIVISION;
                break;
            case "btnExponenteCuadrado":
                total = Math.pow(numero, 2);
                txtResultado.setText("" + total);
                operacion = Operacion.IGUAL;
                break;
            case "btnExponente":
                total = numero;
                operacion = Operacion.EXPONENTE;
                break;
            case "btnRaizCuadrada":
                total = Math.sqrt(numero);
                txtResultado.setText("" + total);
                operacion = Operacion.IGUAL;
                break;
            case "btnRaiz":
                total = numero;
                operacion = Operacion.RAIZ;
                break;
            case "btnLog10":
                total = Math.log10(numero);
                txtResultado.setText("" + total);
                operacion = Operacion.IGUAL;
                break;
            case "btnNegPos":
                numero *= -1;
                txtResultado.setText("" + numero);
                break;
            //  enum equls  == enum Equals / resetear calculadora
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
                total -= numero;
                break;
            case MULTIPLICACION:
                total *= numero;
                break;
            case DIVISION:
                total /= numero;
                break;
            case EXPONENTE:
                total = Math.pow(total, numero);
                break;
            case RAIZ:
                total = Math.pow(total, 1.0 / numero);
                break;
        }
        operacion = Operacion.IGUAL;
        txtResultado.setText(String.valueOf(total));
        total = 0.0;

    }

    @FXML
    private void onPuntoPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (txtResultado.getText().contains(".") == true) {
            resultado = txtResultado.getText();
        } else {
            resultado = txtResultado.getText() + btn.getText();
        }
        txtResultado.setText(resultado);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtResultado.setText("0");
        total = 0.0;
        numero = 0.0;
        numX = 0.0;
        numY = 0.0;
        operacion = Operacion.NINGUNA;
    }

}
