package mocks;

import interfaces.Ln;

public class LnMock implements Ln {
    @Override
    public double ln(double x, double precision) {
        return Math.log(x);
    }
}
