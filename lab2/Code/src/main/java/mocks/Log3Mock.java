package mocks;

import interfaces.Log3;

public class Log3Mock implements Log3 {
    @Override
    public double log_3(double x, double precision) {
        return Math.log(x) / Math.log(3.0);
    }
}
