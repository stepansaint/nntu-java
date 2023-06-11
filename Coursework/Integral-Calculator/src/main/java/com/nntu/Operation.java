package com.nntu;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Provides arithmetic operations.
 */
enum Operation {

    ADDITION {
        @Override
        double calculate(double first, double second) {
            return first + second;
        }
    },
    DIVISION {
        @Override
        double calculate(double first, double second) {
            return first / second;
        }
    },
    EXPONENTIATION {
        @Override
        double calculate(double first, double second) {
            return Math.pow(first, second);
        }
    },
    INTEGRATION {
        /**
         * Calculates the integral at a specified interval.
         *
         * @param start the start of an interval
         * @param end the end of an interval
         */
        @Override
        double calculate(double start, double end) {
            // maintain integration in both directions
            boolean isRangeReversed = false;

            if (end < start) {
                isRangeReversed = true;
                double temp = end;
                end = start;
                start = temp;
            }

            Expression expression = new ExpressionBuilder(MathematicalAction.expression)
                    .variable("x").build();

            double step = 1e-3;
            double sum = 0;
            while (start <= end) {
                sum += step * expression.setVariable("x", start).evaluate();
                start += step;
            }

            return isRangeReversed
                    ? sum * (-1)
                    : sum;
        }
    },
    MULTIPLICATION {
        @Override
        double calculate(double first, double second) {
            return first * second;
        }
    },
    ROOT {
        @Override
        double calculate(double first, double second) {
            return Math.pow(first, 1 / second);
        }
    },
    ROOT_OF_EQUATION {
        private double lowerBound;
        private double upperBound;

        /**
         * Calculates the root of equation at a specified interval using bisection method.
         *
         * @param start the start of an interval
         * @param end the end of an interval
         */
        @Override
        double calculate(double start, double end) {
            if (end < start) throw new ArithmeticException("Wrong range.");
            lowerBound = start;
            upperBound = end;
            return calculateRoot(start, end);
        }

        /**
         * Recursively finds the root of equation at a specified interval.
         */
        private double calculateRoot(double start, double end) {
            double epsilon = 1e-3;
            if (end - start <= epsilon) return start;

            double x = start + (end - start) / 2;

            if (Math.abs(lowerBound - x) <= epsilon || Math.abs(upperBound - x) <= epsilon)
                throw new ArithmeticException("There's no root.");

            Expression expression = new ExpressionBuilder(MathematicalAction.expression)
                    .variable("x").build();

            if (expression.setVariable("x", start).evaluate()
                    * expression.setVariable("x", x).evaluate() > 0) {
                return calculateRoot(x, end);
            } else return calculateRoot(start, x);
        }
    },
    SUBTRACTION {
        @Override
        double calculate(double first, double second) {
            return first - second;
        }
    };

    /**
     * Calculates some result using two specified numbers.
     *
     * @param first the first number
     * @param second the second number
     * @return the result
     */
    abstract double calculate(double first, double second);
}
