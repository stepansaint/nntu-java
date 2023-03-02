package my.laptop;

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
            return x / y;
        }

        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    protected abstract long apply(long x, long y);
    protected abstract double apply(double x, double y);
}
