package mocks;

import interfaces.Log5;

public class Log5Mock implements Log5 {
    @Override
    public double log_5(double x, double precision) {
        return Math.log(x) / Math.log(5.0);
    }
}
