package implementation;

import interfaces.Cos;

public class CosImpl implements Cos {
    private static final double SUM_ITERATIONS_LIMIT = 10E10;

    @Override
    public double cos(double x, double precision) {
        if (Math.abs(Math.abs(x % Math.PI) - Math.PI / 2) < precision) {
            return 0;
        }
        final double correctedX = Math.abs(x) % (Math.PI * 2);

        double denominator;
        double x1 = 1;
        double cosx = 1;
        for (int i = 1; i <= SUM_ITERATIONS_LIMIT; i++) {
            denominator = 2 * i * (2 * i - 1);
            x1 = -x1 * correctedX * correctedX / denominator;
            cosx = cosx + x1;
            if (Math.abs(x1) < precision) {
                return cosx;
            }
        }
        return cosx;
    }
}
