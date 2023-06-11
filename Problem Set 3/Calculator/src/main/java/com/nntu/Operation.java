package com.nntu;

/**
 * Provides arithmetic operations.
 */
public enum Operation {

    ADDITION {
        @Override
        public long apply(long x, long y) {
            return x + y;
        }

        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACTION {
        @Override
        public long apply(long x, long y) {
            return x - y;
        }

        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLICATION {
        @Override
        public long apply(long x, long y) {
            return x * y;
        }

        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVISION {
        @Override
        public long apply(long x, long y) {
            try {
                return x / y;
            } catch (ArithmeticException ae) {
                throw new DivisionException("Can't divide by zero");
            }
        }

        @Override
        public double apply(double x, double y) {
            try {
                return x / y;
            } catch (ArithmeticException ae) {
                throw new DivisionException("Can't divide by zero");
            }
        }
    };

    /**
     * Applies some action to two specified numbers.
     * @param x first number
     * @param y second number
     * @return the result of applying
     */
    abstract long apply(long x, long y);

    /**
     * Look at {@link #apply(long, long)}.
     */
    abstract double apply(double x, double y);
}
