package com.nntu;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Represents the logic of the <code>Notes</code>.
 */
public class Notes extends JFrame {
    private JPanel mainPanel;
    private JButton openButton;
    private JTextField pathToOpen;
    private JTextField pathToSave;
    private JButton saveButton;
    private JTextArea text;
    private JScrollPane textScroll;

    /**
     * Initializes a frame for the application.
     * Shows the full application window to a user.
     */
    public Notes() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600,400);
        setTitle("Notes");
        setVisible(true);
        initializeListeners();
    }

    /**
     * Initializes all listeners.
     */
    private void initializeListeners() {
        openButton.addActionListener((event) -> {
            // Check the correctness of a user's input
            if (isEmptyText(pathToOpen, "FILE")) {
                return;
            }

            // Read a specified file
            try (FileReader reader = new FileReader(pathToOpen.getText(), StandardCharsets.UTF_8)) {
                CharBuffer buffer = CharBuffer.allocate(1024);
                while (reader.read(buffer) != -1) {
                    buffer.flip();
                    text.setText(buffer.toString());
                    buffer.compact();
                }
            } catch (IOException e) {
                pathToOpen.setText("COULDN'T READ THIS FILE.");
            }
        });

        saveButton.addActionListener((event) -> {
            // Check the correctness of a user's input
            if (isEmptyText(pathToSave, "FILE") || isEmptyText(text, "TEXT")) {
                return;
            }

            // Write to a specified file
            try (FileWriter writer = new FileWriter(pathToSave.getText(), StandardCharsets.UTF_8, true)) {
                writer.write(text.getText());
                text.setText("EVERYTHING IS OK.");
            } catch (IOException e) {
                pathToSave.setText("COULDN'T WRITE TO THIS FILE.");
            }
        });
    }

    /**
     * Checks the emptiness of a provided <code>JTextComponent</code>.
     * @param textComponent the <code>JTextComponent</code> to be checked for an emptiness
     * @param endOfWarning the end of the string in case of warning
     * @return true if a provided <code>JTextComponent</code> doesn't contain any text
     */
    private boolean isEmptyText(JTextComponent textComponent, String endOfWarning) {
        if (textComponent.getText().length() == 0) {
            textComponent.setText("PLEASE, SPECIFY THE " + endOfWarning + "!");
            return true;
        }
        return false;
    }
}
