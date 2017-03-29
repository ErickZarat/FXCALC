package org.calc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class CalcController implements Initializable {

    //enum for handling status of process
    private enum Operation {
        NONE, DIVISION, ADD, SUBTRACTION, MULTIPLICATION, EXPONENT, RAIZ, 
        NEG_POS, EQUAL, ERROR
    }
    
    //setting global variables
    private double number, total;
    private String result = new String(), txtOnLabel = new String();
    private Operation operation;

    //binding label from view
    @FXML private Label lblOperacion;
    @FXML private TextField txtResultado;
    
    /*
    * method for resset entire calc
    */
    @FXML private void onClearPressed(ActionEvent event) {
        txtResultado.setText("");
        lblOperacion.setText("");
        total = 0.0;
        number = 0.0;
        operation = Operation.NONE;
    }

    /*
    * method for handle Math exceptions
    */
    private void MathError(Double numX, int idNum) throws MathErrorException {
        if (numX <= 0.0 && idNum == 1) {
            lblOperacion.setText("");
            throw new MathErrorException("MATH ERROR");
        }
        if (numX == 0 && idNum == 2) {
            lblOperacion.setText("");
            throw new MathErrorException("MATH ERROR");
        }
    }

    /*
    * method for handle all number buttons
    */
    @FXML
    private void onButtonPressed(ActionEvent event) {
        //getting number from button text
        Button btn = (Button) event.getSource();
        String txt = btn.getText();
        
        //getting result text
        String txtOnResult = txtResultado.getText();

        if (operation.equals(operation.EQUAL)) {
            //if last operation is done
            //reset calc
            lblOperacion.setText("");
            operation = Operation.NONE;
            total = 0.0;
            txtResultado.setText("0");
            txtOnResult = "0";
        }

        if (txtOnResult.equals("0")) {
            //if the current operation is 0, replace by the selected number
            result = txt;
        } else {
            //else, add the selected number to the current operation
            result = txtOnResult + txt;
        }
        //setting actual operation
        txtResultado.setText(result);
    }

    /*
    * method for erease last number
    */
    @FXML
    private void onBackSpacePressed(ActionEvent event) {
        if (txtResultado.getText().length() != 0) {
            txtResultado.setText(txtResultado.getText().substring(0, 
                    txtResultado.getText().length() - 1));
        }
    }

    /*
    * method for handle all operations
    */
    @FXML
    private void onOperationPressed(ActionEvent event) {
        //getting last number inserted and convert into double datatype
        number = Double.parseDouble(txtResultado.getText());
        //reset for next input
        txtResultado.setText("0");

        //getting action and id from button pressed
        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        //handle depending the button
        switch (btnId) {
            //making operation on each case
            //setting status of process
            //show result on the view
            case "btnSuma":
                total += number;
                operation = Operation.ADD;
                lblOperacion.setText((txtOnLabel = "" + total + " + " +
                        result));
                break;
            case "btnResta":
                total -= (number * -1);
                operation = Operation.SUBTRACTION;
                lblOperacion.setText((txtOnLabel = "" + total + " - " +
                        result));
                break;
            case "btnMultiplicacion":
                total = number;
                operation = Operation.MULTIPLICATION;
                lblOperacion.setText((txtOnLabel = "" + total + " * " + 
                        result));
                break;
            case "btnDivision":
                total = number;
                operation = Operation.DIVISION;
                lblOperacion.setText((txtOnLabel = "" + total + " / "));
                break;
            case "btnExponenteCuadrado":
                total = Math.pow(number, 2);
                txtResultado.setText("" + total);
                operation = Operation.EQUAL;
                break;
            case "btnExponente":
                total = number;
                operation = Operation.EXPONENT;
                lblOperacion.setText(total + " ^ ");
                break;
            case "btnRaizCuadrada":
                try { 
                    MathError(number, 1);
                    total = Math.sqrt(number);
                    txtResultado.setText("" + total);
                    operation = Operation.EQUAL;
                } catch (MathErrorException m) {
                    //handle math error, show math error on view
                    txtResultado.setText("Math Error");
                    operation = Operation.EQUAL;
                }
                break;
            case "btnRaiz":
                try {
                    MathError(number, 1);
                    total = number;
                    operation = Operation.RAIZ;
                    lblOperacion.setText(total + " ^√");
                } catch (MathErrorException e) {
                    //handle math error, show math error on view
                    txtResultado.setText("Math Error");
                    operation = Operation.EQUAL;
                }
                break;
            case "btnLog10":
                try {
                    MathError(number, 1);
                    total = Math.log10(number);
                    txtResultado.setText("" + total);
                    operation = Operation.EQUAL;
                    lblOperacion.setText("log10( " + number + " )");
                } catch (MathErrorException k) {
                    //handle math error, show math error on view
                    txtResultado.setText("Math Error");
                    operation = Operation.EQUAL;
                }
                break;
            case "btnNegPos":
                //make number positive or negative depending the  current status
                number *= -1;
                txtResultado.setText(String.valueOf(number));
                break;
        }
    }

    /*
    * method to handle last of process
    */
    @FXML
    private void onEqualsPressed(ActionEvent event) {
        number = Double.parseDouble(txtResultado.getText());
        switch (operation) {
            //this act according the operation status
            //do the operation and show it on the view
            case ADD:
                total += number;
                lblOperacion.setText(txtOnLabel + number);
                break;
            case SUBTRACTION:
                total -= number;
                lblOperacion.setText(txtOnLabel + number);
                break;
            case MULTIPLICATION:

                total *= number;
                lblOperacion.setText(txtOnLabel + number);
                break;
            case DIVISION:
                try {
                    lblOperacion.setText((txtOnLabel = "" + total + " / " +
                            number));
                    MathError(number, 2);
                    System.out.println(number);
                    total /= number;
                } catch (MathErrorException j) {
                    txtResultado.setText("Math Error");
                    operation = Operation.ERROR;
                }
                break;
            case EXPONENT:
                lblOperacion.setText(total + " ^ " + number);
                total = Math.pow(total, number);
                break;
            case RAIZ:
                try {
                    MathError(number, 1);
                    lblOperacion.setText(lblOperacion.getText() + " " + number);
                    total = Math.pow(number, 1.0 / total);
                } catch (MathErrorException g) {
                    txtResultado.setText("Math Error");
                    operation = Operation.ERROR;
                }

                break;
            case NONE:
                total = number;
                break;
        }
        if (!operation.equals(operation.ERROR) || 
                operation.equals(operation.NONE)) {
            txtResultado.setText(String.valueOf(total));
            operation = Operation.EQUAL;
        }
        total = 0.0;
        operation = Operation.EQUAL;
    }

    /*
    * method for handle "." for prevent double "." on same input
    */
    @FXML
    private void onPuntoPressed(ActionEvent event
    ) {
        Button btn = (Button) event.getSource();
        if (txtResultado.getText().contains(".") == true) {
            result = txtResultado.getText();
        } else {
            result = txtResultado.getText() + btn.getText();
        }
        txtResultado.setText(result);
    }

    /*
    * initialize calc, like reset.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtResultado.setText("0");
        total = 0.0;
        number = 0.0;
        operation = Operation.NONE;
    }

}
