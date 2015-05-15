package org.ezarat.calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 *
 * @author Erick Zarat
 */
public class FXMLDocumentController implements Initializable {

    private double numero, total, numX, numY;

    private enum Operacion {

        NINGUNA, DIVISION, SUMA, RESTA, MULTIPLICACION, EXPONENTE, RAIZ, NEG_POS, IGUAL, ERROR
    }
    private String resultado = new String(), textoEnLabel = new String();
    private Operacion operacion;

    @FXML
    private Label lblOperacion;

    @FXML
    private TextField txtResultado;

    @FXML
    private void onClearPressed(ActionEvent event) {
        txtResultado.setText("");
        lblOperacion.setText("");
        operacion = Operacion.NINGUNA;
    }

    private void MathError(Double numeroX, int numeroId) throws MathErrorException {
        if (numeroX <= 0.0 && numeroId == 1) {
            throw new MathErrorException("HA OCURRIDO UN ERROR MATEMATICO");
        }
        if (numeroX == 0 && numeroId == 2) {
            throw new MathErrorException("HA OCURRIDO UN ERROR MATEMATICO");
        }
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
        txtResultado.setText("0");

        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        switch (btnId) {
            case "btnSuma":
                total += numero;
                operacion = Operacion.SUMA;
                lblOperacion.setText((textoEnLabel = "" + total + " + " + resultado));
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
                try {
                    MathError(numero, 1);
                    total = Math.sqrt(numero);
                    txtResultado.setText("" + total);
                    operacion = Operacion.IGUAL;
                } catch (MathErrorException m) {
                    txtResultado.setText("Math Error");
                }
                break;
            case "btnRaiz":
                try {
                    MathError(numero, 1);
                    total = numero;
                    operacion = Operacion.RAIZ;
                } catch (MathErrorException e) {
                    txtResultado.setText("Math Error");
                }
                break;
            case "btnLog10":
                try {
                    MathError(numero, 1);
                    total = Math.log10(numero);
                    txtResultado.setText("" + total);
                    operacion = Operacion.IGUAL;
                } catch (MathErrorException k) {
                    txtResultado.setText("Math Error");
                }
                break;
            case "btnNegPos":
                numero *= -1;
                txtResultado.setText(String.valueOf(numero));
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
                lblOperacion.setText(textoEnLabel + numero);
                break;
            case RESTA:
                total -= numero;
                break;
            case MULTIPLICACION:
                total *= numero;
                break;
            case DIVISION:
                try {
                    MathError(numero, 2);
                    System.out.println(numero);
                    total /= numero;
                } catch (MathErrorException j) {
                    txtResultado.setText("Math Error");
                    operacion = Operacion.ERROR;
                }
                break;
            case EXPONENTE:
                total = Math.pow(total, numero);
                break;
            case RAIZ:
                total = Math.pow(total, 1.0 / numero);
                break;
            case NINGUNA:
                txtResultado.setText(resultado);
                break;
        }
        if (!operacion.equals(Operacion.ERROR)) {
            txtResultado.setText(String.valueOf(total));

            operacion = Operacion.IGUAL;

            total = 0.0;

        }
    }

    @FXML
    private void onPuntoPressed(ActionEvent event
    ) {
        Button btn = (Button) event.getSource();
        if (txtResultado.getText().contains(".") == true) {
            resultado = txtResultado.getText();
        } else {
            resultado = txtResultado.getText() + btn.getText();
        }
        txtResultado.setText(resultado);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        txtResultado.setText("0");
        total = 0.0;
        numero = 0.0;
        numX = 0.0;
        numY = 0.0;
        operacion = Operacion.NINGUNA;
    }

}