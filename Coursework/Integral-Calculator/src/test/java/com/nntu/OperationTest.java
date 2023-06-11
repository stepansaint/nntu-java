package com.nntu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    private static final double EPSILON = 0.1;

    @DisplayName("Use addition, division, exponentiation, multiplication, root and subtraction")
    @Test
    void complete_simple_arithmetic_operations() {
        Operation addition = setAndGetOperation(Operation.ADDITION);
        assertAll("Use basic arithmetic",
                () -> assertEquals(-1, addition.calculate(-1.1, 0.1), EPSILON),
                () -> assertEquals(0.9, addition.calculate(0.35, 0.55), EPSILON),
                () -> assertEquals(-11.2, addition.calculate(-10.1, -1.1), EPSILON)
        );

        Operation division = setAndGetOperation(Operation.DIVISION);
        assertAll("Use basic arithmetic",
                () -> assertEquals(-11, division.calculate(-1.1, 0.1), EPSILON),
                () -> assertEquals(0.6, division.calculate(0.35, 0.55), EPSILON),
                () -> assertEquals(9.2, division.calculate(-10.1, -1.1), EPSILON)
        );

        Operation exponentiation = setAndGetOperation(Operation.EXPONENTIATION);
        assertAll("Use basic arithmetic",
                () -> assertEquals(Double.NaN, exponentiation.calculate(-1.1, 0.1), EPSILON),
                () -> assertEquals(0.6, exponentiation.calculate(0.35, 0.55), EPSILON),
                () -> assertEquals(Double.NaN, exponentiation.calculate(-10.1, -1.1), EPSILON)
        );

        Operation multiplication = setAndGetOperation(Operation.MULTIPLICATION);
        assertAll("Use basic arithmetic",
                () -> assertEquals(-0.1, multiplication.calculate(-1.1, 0.1), EPSILON),
                () -> assertEquals(0.2, multiplication.calculate(0.35, 0.55), EPSILON),
                () -> assertEquals(11.1, multiplication.calculate(-10.1, -1.1), EPSILON)
        );

        Operation root = setAndGetOperation(Operation.ROOT);
        assertAll("Use basic arithmetic",
                () -> assertEquals(2.6, root.calculate(1.1, 0.1), EPSILON),
                () -> assertEquals(0.2, root.calculate(0.35, 0.55), EPSILON),
                () -> assertEquals(8.2, root.calculate(10.1, 1.1), EPSILON)
        );

        Operation subtraction = setAndGetOperation(Operation.SUBTRACTION);
        assertAll("Use basic arithmetic",
                () -> assertEquals(-1.2, subtraction.calculate(-1.1, 0.1), EPSILON),
                () -> assertEquals(0.9, subtraction.calculate(0.35, -0.55), EPSILON),
                () -> assertEquals(9, subtraction.calculate(10.1, 1.1), EPSILON)
        );
    }

    @DisplayName("Find roots of simple expressions")
    @Test
    void find_roots_of_simple_expressions_at_different_ranges() {
        Operation findingRoot = setAndGetOperation(Operation.ROOT_OF_EQUATION);

        setExpression("cos(x^5) + x^4 - 345.3 * x - 23");
        assertAll("Use different ranges",
                () -> assertEquals(7.038, findingRoot.calculate(1, 10), EPSILON),
                () -> assertThrows(ArithmeticException.class, () -> findingRoot.calculate(10, 1)),
                () -> assertEquals(0, findingRoot.calculate(-15, 20), EPSILON),
                () -> assertThrows(ArithmeticException.class, () -> findingRoot.calculate(10, 20))
        );

        setExpression("x^8 + x^3 - 20");
        assertAll("Use different ranges including decimal",
                () -> assertEquals(-1.43, findingRoot.calculate(-7, 0), EPSILON),
                () -> assertEquals(1.43, findingRoot.calculate(0, 7), EPSILON),
                () -> assertEquals(1.43, findingRoot.calculate(1.40, 1.43), EPSILON),
                () -> assertThrows(ArithmeticException.class, () -> findingRoot.calculate(1.40, 1.42)),
                () -> assertThrows(ArithmeticException.class, () -> findingRoot.calculate(10, 100))
        );
    }

    @DisplayName("Integrate simple expressions")
    @Test
    void integrate_simple_expressions_at_different_ranges() {
        Operation integration = setAndGetOperation(Operation.INTEGRATION);

        setExpression("1.2x - sin(x)");
        assertAll("Use different ranges",
                () -> assertEquals(113.3, integration.calculate(-3, 14), EPSILON),
                () -> assertEquals(14.3, integration.calculate(0, 5), EPSILON),
                () -> assertEquals(-14.3, integration.calculate(-5, 0), EPSILON),
                () -> assertEquals(-14.3, integration.calculate(5, 0), EPSILON),
                // increase the epsilon up to 0.8
                () -> assertEquals(-599999.4, integration.calculate(1000, -1), EPSILON + 0.7),
                () -> assertEquals(-70701.6, integration.calculate(343.3, 4.3), EPSILON + 0.7),
                () -> assertEquals(70701.6, integration.calculate(4.3, 343.3), EPSILON + 0.7)
        );

        setExpression("cos(x)");
        assertEquals(-0.95, integration.calculate(0, 5), EPSILON);
    }

    private Operation setAndGetOperation(Operation operation) {
        MathematicalAction.operation = operation;
        return operation;
    }

    private void setExpression(String expression) {
        MathematicalAction.expression = expression;
    }
}