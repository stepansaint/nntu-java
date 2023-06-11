package com.nntu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents the form's desktop window to get some information for future calculations.
 */
class FunctionForm extends JDialog {

    static final Path HISTORY_FILE = Path.of("history.txt");

    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton historyButton;
    private JButton okButton;
    private JTextField expressionField;
    private JTextField rangeField;

    FunctionForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        setResizable(false);
        setSize(410,200);
        setTitle("Function");

        cancelButton.addActionListener((actionEvent) -> closeAction());
        historyButton.addActionListener((actionEvent) -> {
            try {
                FileHandler.openFile(HISTORY_FILE);
            } catch (IOException ioe) {
                try {
                    Files.createFile(HISTORY_FILE);
                    FileHandler.openFile(HISTORY_FILE);
                } catch (IOException e) {
                    System.err.println("Couldn't open and/or create a history file.");
                    e.printStackTrace();
                }
            }
        });
        okButton.addActionListener((actionEvent) -> {
            int indexOfComma = rangeField.getText().lastIndexOf(',');
            int indexOfFirstBrace = 7;
            int indexOfLastBrace = rangeField.getText().lastIndexOf(']');

            MathematicalAction.firstNumber = rangeField.getText().substring(indexOfFirstBrace + 1, indexOfComma);
            MathematicalAction.secondNumber = rangeField.getText().substring(indexOfComma + 2, indexOfLastBrace);

            int lengthOfSentence = 11;
            MathematicalAction.expression = expressionField.getText().substring(lengthOfSentence + 1);

            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                closeAction();
            }
        });

        // Process the tapped ESCAPE
        mainPanel.registerKeyboardAction((actionEvent) -> closeAction(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Closes the form's desktop window. Nothing will be saved.
     */
    private void closeAction() {
        dispose();
    }

    public static void main(String[] args) {
        new FunctionForm().setVisible(true);
    }
}
