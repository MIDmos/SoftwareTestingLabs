package implementation;

import interfaces.Ln;

import static java.lang.Math.abs;

public class LnImpl implements Ln {
    private static final double SUM_ITERATIONS_LIMIT = 10E10;

    @Override
    public double ln(double x, double precision) {
        if (x <= 0)
            return Double.NaN;
        if (x < precision * 0.5)
            return Double.NEGATIVE_INFINITY;
        double x1 = (x - 1) / (x + 1);
        double current = x1;
        double result = 0.0;
        int n = 3;
        for (int i = 0; i < SUM_ITERATIONS_LIMIT; i++) {
            result += 2 * current;
            current *= x1 * x1 / n * (n - 2);
            n += 2;
            if (abs(2 * current) < precision * precision) {
                return result;
            }
        }
        return result;
    }
}
