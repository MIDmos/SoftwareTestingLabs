package mocks;

import interfaces.Log10;

public class Log10Mock implements Log10 {
    @Override
    public double log_10(double x, double precision) {
        return Math.log10(x);
    }
}
