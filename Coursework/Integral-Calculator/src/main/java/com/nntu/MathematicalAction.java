package com.nntu;

import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Represents the mathematical logic for a user's input.
 */
class MathematicalAction {

    static String firstNumber;
    static String expression;
    static String secondNumber;
    static Operation operation;

    private final Calculator calculator;

    MathematicalAction(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Prints a specified digit to the calculator's field.
     *
     * @param actionEvent an event
     */
    void digitAction(ActionEvent actionEvent) {
        String tappedDigit = actionEvent.getActionCommand();
        String currentNumber = calculator.getIOField().getText();

        // Deal with an empty calculator's field
        // And with behavior after user's selected operation
        if (currentNumber.equals("0")
                || operation != null && currentNumber.equals(firstNumber)) {
            calculator.getIOField().setText(tappedDigit);
        } else calculator.getIOField().setText(currentNumber + tappedDigit);
    }

    /**
     * Remembers a specified operation for next calculations.
     *
     * @param actionEvent an event
     */
    void operationAction(ActionEvent actionEvent) {
        firstNumber = calculator.getIOField().getText();

        switch (actionEvent.getActionCommand()) {
            case "+":
                operation = Operation.ADDITION;
                break;
            case "÷":
                operation = Operation.DIVISION;
                break;
            case "∫":
                operation = Operation.INTEGRATION;
                FunctionForm.main(new String[0]);
                calculator.getIOField().setText("press =");
                break;
            case "×":
                operation = Operation.MULTIPLICATION;
                break;
            case "xʸ":
                operation = Operation.EXPONENTIATION;
                break;
            case "√":
                operation = Operation.ROOT;
                break;
            case "ƒ":
                operation = Operation.ROOT_OF_EQUATION;
                FunctionForm.main(new String[0]);
                calculator.getIOField().setText("press =");
                break;
            case "-":
                operation = Operation.SUBTRACTION;
                break;
            default:
                throw new UnsupportedOperationException("There's no such mathematical operation.");
        }
    }

    /**
     * Prints the result to the calculator's field.
     *
     * @param actionEvent an event
     */
    void resultAction(ActionEvent actionEvent) {
        if (operation != null) {
            if (operation != Operation.INTEGRATION && operation != Operation.ROOT_OF_EQUATION) {
                secondNumber = calculator.getIOField().getText();
            }

            double result = 0;
            try {
                result = operation.calculate(
                        Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)
                );
                calculator.getIOField().setText(String.format("%.3f", result));

                // Remember the result for next calculations
                firstNumber = calculator.getIOField().getText();

                // Save the expression to a history if it's needed
                if (operation == Operation.INTEGRATION || operation == Operation.ROOT_OF_EQUATION) {
                    FileHandler.saveExpression(FunctionForm.HISTORY_FILE, firstNumber, secondNumber,
                            expression, String.valueOf(result));
                }
            } catch (ArithmeticException ae) {
                calculator.getIOField().setText(ae.getMessage());
            } catch (IOException ioe) {
                // Try again
                try {
                    FileHandler.saveExpression(FunctionForm.HISTORY_FILE, firstNumber, secondNumber,
                            expression, String.valueOf(result));
                } catch (IOException e) {
                    System.err.println("Couldn't save the expression to a history file.\n");
                    e.printStackTrace();
                }
            } catch (UnknownFunctionOrVariableException ufove) {
                calculator.getIOField().setText("Invalid expression.");
            }
        }
    }
}
