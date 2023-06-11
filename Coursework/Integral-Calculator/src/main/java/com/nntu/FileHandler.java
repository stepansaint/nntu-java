package com.nntu;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Represents a utility handler for writing, opening and saving files or expressions.
 */
class FileHandler {

    /**
     * Opens in a new desktop window the specified file.
     *
     * @param path file to open
     * @throws IOException if an I/O error occurs
     */
    static void openFile(Path path) throws IOException {
        new ProcessBuilder()
                .command("open", path.toString())
                .start();
    }

    /**
     * Saves to the specified file
     * the specified mathematical expression with its range and its result.
     *
     * @param path file to save to
     * @param lowerBoundOfRange the left border of the range
     * @param upperBoundOfRange the right border of the range
     * @param expression the mathematical expression
     * @param answer the result of calculating
     *
     * @throws IOException if an I/O error occurs
     */
    static void saveExpression(Path path, String lowerBoundOfRange, String upperBoundOfRange,
                        String expression, String answer) throws IOException {
        try (FileWriter writer = new FileWriter(path.toString(), StandardCharsets.UTF_8, true)) {
            writer.write("Range: [" + lowerBoundOfRange + ", " + upperBoundOfRange + "]. ");
            writer.write("Expression: " + expression + ". ");
            writer.write("Answer: " + answer + ";\n");
        }
    }
}
