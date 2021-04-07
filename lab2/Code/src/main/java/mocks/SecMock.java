package mocks;

import interfaces.Sec;

public class SecMock implements Sec {
    @Override
    public double sec(double x, double precision) {
        return 1.0 / Math.cos(x);
    }
}
