package my.laptop;

import javax.swing.*;
import java.awt.event.ActionEvent;
// delete secondNumber field
public class Calculator extends JFrame {

    private JPanel mainPanel;
    private JLabel currentNumber;
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
    private final ArithmeticOperation arithmeticOperation = new ArithmeticOperation();

    public Calculator() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(328,428);
        setTitle("Calculator");
        setVisible(true);

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

    private void pointAction(ActionEvent actionEvent) {
        String currentNumberText = currentNumber.getText();

        if (!currentNumberText.contains(".")) {
            currentNumber.setText(currentNumberText + ".");
        }
    }

    private void allClearAction(ActionEvent actionEvent) {
        currentNumber.setText("0");
        arithmeticOperation.firstNumber = null;
        arithmeticOperation.operation = null;
//        arithmeticOperation.secondNumber = null;
    }

    private class ArithmeticOperation {

        String firstNumber;
        Operation operation;
//        String secondNumber;

        private void digitAction(ActionEvent actionEvent) {
            String tappedDigit = actionEvent.getActionCommand();
            String currentNumberText = currentNumber.getText();

            if (currentNumberText.equals("0")
                || operation != null && currentNumberText.equals(firstNumber)) {
                currentNumber.setText(tappedDigit);
            } else {
                currentNumber.setText(currentNumberText + tappedDigit);
            }
        }

        private void operationAction(ActionEvent actionEvent) {
            firstNumber = currentNumber.getText();

            switch (actionEvent.getActionCommand()) {
                case "+" -> operation = Operation.ADDITION;
                case "-" -> operation = Operation.SUBTRACTION;
                case "×" -> operation = Operation.MULTIPLICATION;
                case "÷" -> operation = Operation.DIVISION;
            }
        }

        private void resultAction(ActionEvent actionEvent) {
            if (operation != null) {
                String currentNumberText = currentNumber.getText();

                if (firstNumber.contains(".") || currentNumberText.contains(".")) {
                    currentNumber.setText(Double.toString(
                        operation.apply(Double.parseDouble(firstNumber), Double.parseDouble(currentNumberText))
                    ));
                } else {
                    currentNumber.setText(Long.toString(
                        operation.apply(Long.parseLong(firstNumber), Long.parseLong(currentNumberText))
                    ));
                }

                firstNumber = currentNumber.getText();
            }
        }
    }
}
