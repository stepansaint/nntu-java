package com.nntu;

import javax.swing.*;

/**
 * Represents the calculator's desktop window.
 */
public class Calculator extends JFrame {

    private JPanel mainPanel;
    private JLabel IOField;

    /* Buttons for mathematical actions */
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
    private JButton divisionSign;
    private JButton exponentiationSign;
    private JButton integralSign;
    private JButton multiplicationSign;
    private JButton rootOfEquationSign;
    private JButton rootSign;
    private JButton subtractionSign;
    private JButton equalsSign;

    /* Buttons for textual actions */
    private JButton allClearSign;
    private JButton pointSign;

    /**
     * Initializes a frame for the application.
     * Shows the full application window to a user.
     */
    public Calculator() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(410,428);
        setTitle("Calculator");
        setVisible(true);
        initializeListeners();
    }

    /**
     * Initializes all listeners for this application.
     */
    private void initializeListeners() {
        /* Handlers for actions */
        MathematicalAction mathematicalAction = new MathematicalAction(this);
        TextualAction textualAction = new TextualAction(this);

        /* Mathematical actions */
        zero.addActionListener(mathematicalAction::digitAction);
        one.addActionListener(mathematicalAction::digitAction);
        two.addActionListener(mathematicalAction::digitAction);
        three.addActionListener(mathematicalAction::digitAction);
        four.addActionListener(mathematicalAction::digitAction);
        five.addActionListener(mathematicalAction::digitAction);
        six.addActionListener(mathematicalAction::digitAction);
        seven.addActionListener(mathematicalAction::digitAction);
        eight.addActionListener(mathematicalAction::digitAction);
        nine.addActionListener(mathematicalAction::digitAction);

        additionSign.addActionListener(mathematicalAction::operationAction);
        divisionSign.addActionListener(mathematicalAction::operationAction);
        exponentiationSign.addActionListener(mathematicalAction::operationAction);
        integralSign.addActionListener(mathematicalAction::operationAction);
        multiplicationSign.addActionListener(mathematicalAction::operationAction);
        rootOfEquationSign.addActionListener(mathematicalAction::operationAction);
        rootSign.addActionListener(mathematicalAction::operationAction);
        subtractionSign.addActionListener(mathematicalAction::operationAction);

        equalsSign.addActionListener(mathematicalAction::resultAction);

        /* Textual actions */
        allClearSign.addActionListener(textualAction::allClearAction);

        pointSign.addActionListener(textualAction::pointAction);
    }

    /**
     * Returns the main field of the calculator.
     *
     * @return the calculator's main field
     */
    JLabel getIOField() {
        return IOField;
    }
}
