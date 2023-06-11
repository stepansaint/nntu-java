package com.nntu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the logic of the calculator.
 */
public class Calculator extends JFrame {

    private JPanel mainPanel;
    private JLabel IOField;
    private JButton zero;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton additionSign;
    private JButton subtractionSign;
    private JButton multiplicationSign;
    private JButton divisionSign;
    private JButton equalsSign;
    private JButton pointSign;
    private JButton allClear;
    private ArithmeticOperation arithmeticOperation;

    /**
     * Initializes a frame for the application.
     * Shows the full application window to a user.
     */
    public Calculator() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(328,428);
        setTitle("Calculator");
        setVisible(true);
        initializeListeners();
    }

    /**
     * Initializes all listeners for this application.
     */
    private void initializeListeners() {
        arithmeticOperation = new ArithmeticOperation();

        zero.addActionListener(arithmeticOperation::digitAction);
        one.addActionListener(arithmeticOperation::digitAction);
        two.addActionListener(arithmeticOperation::digitAction);
        three.addActionListener(arithmeticOperation::digitAction);
        four.addActionListener(arithmeticOperation::digitAction);
        five.addActionListener(arithmeticOperation::digitAction);
        six.addActionListener(arithmeticOperation::digitAction);
        seven.addActionListener(arithmeticOperation::digitAction);
        eight.addActionListener(arithmeticOperation::digitAction);
        nine.addActionListener(arithmeticOperation::digitAction);

        additionSign.addActionListener(arithmeticOperation::operationAction);
        subtractionSign.addActionListener(arithmeticOperation::operationAction);
        multiplicationSign.addActionListener(arithmeticOperation::operationAction);
        divisionSign.addActionListener(arithmeticOperation::operationAction);

        equalsSign.addActionListener(arithmeticOperation::resultAction);

        pointSign.addActionListener(this::pointAction);
        allClear.addActionListener(this::allClearAction);
    }

    /**
     * Prints a point to the calculator's field.
     * @param actionEvent an event
     */
    private void pointAction(ActionEvent actionEvent) {
        String currentNumberText = IOField.getText();

        if (!currentNumberText.contains(".")) {
            IOField.setText(currentNumberText + ".");
        }
    }

    /**
     * Clears everything from the calculator's field.
     * @param actionEvent an event
     */
    private void allClearAction(ActionEvent actionEvent) {
        IOField.setText("0");
        arithmeticOperation.firstNumberText = null;
        arithmeticOperation.operation = null;
    }

    /**
     * Represents the arithmetic logic for a user's input.
     */
    private class ArithmeticOperation {
        String firstNumberText;
        Operation operation;

        /**
         * Prints a specified digit from <code>actionEvent</code> to the calculator's field.
         * @param actionEvent an event
         */
        private void digitAction(ActionEvent actionEvent) {
            String tappedDigit = actionEvent.getActionCommand();
            String currentNumberText = IOField.getText();

            // Deal with an empty calculator's field
            // And with behavior after user's selected operation
            if (currentNumberText.equals("0")
                || operation != null && currentNumberText.equals(firstNumberText)) {
                IOField.setText(tappedDigit);
            } else {
                IOField.setText(currentNumberText + tappedDigit);
            }
        }

        /**
         * Remembers a specified operation for next calculations.
         * @param actionEvent an event
         */
        private void operationAction(ActionEvent actionEvent) {
            firstNumberText = IOField.getText();

            switch (actionEvent.getActionCommand()) {
                case "+" -> operation = Operation.ADDITION;
                case "-" -> operation = Operation.SUBTRACTION;
                case "×" -> operation = Operation.MULTIPLICATION;
                case "÷" -> operation = Operation.DIVISION;
            }
        }

        /**
         * Prints the result to the calculator's field.
          * @param actionEvent an event
         */
        private void resultAction(ActionEvent actionEvent) {
            if (operation != null) {
                String currentNumberText = IOField.getText();

                try {
                    // Check the type of the numbers to calculate
                    if (firstNumberText.contains(".") || currentNumberText.contains(".")) {
                        IOField.setText(Double.toString(
                                operation.apply(Double.parseDouble(firstNumberText), Double.parseDouble(currentNumberText))
                        ));
                    } else {
                        IOField.setText(Long.toString(
                                operation.apply(Long.parseLong(firstNumberText), Long.parseLong(currentNumberText))
                        ));
                    }

                    // Remember the result for next calculations
                    firstNumberText = IOField.getText();
                } catch (DivisionException de) {
                    IOField.setText(de.getMessage());
                }
            }
        }
    }
}
