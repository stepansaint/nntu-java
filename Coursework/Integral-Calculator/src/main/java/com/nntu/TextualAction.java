package com.nntu;

import java.awt.event.ActionEvent;

/**
 * Represents the textual logic for a user's input.
 */
class TextualAction {

    private final Calculator calculator;

    TextualAction(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Prints a point to the calculator's field.
     *
     * @param actionEvent an event
     */
    void pointAction(ActionEvent actionEvent) {
        String currentNumber = calculator.getIOField().getText();
        if (!currentNumber.contains(".")) calculator.getIOField().setText(currentNumber + ".");
    }

    /**
     * Clears everything from the calculator's field.
     *
     * @param actionEvent an event
     */
    void allClearAction(ActionEvent actionEvent) {
        calculator.getIOField().setText("0");
        MathematicalAction.firstNumber = null;
        MathematicalAction.expression = null;
        MathematicalAction.secondNumber = null;
        MathematicalAction.operation = null;
    }
}
