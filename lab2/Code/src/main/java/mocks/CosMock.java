package mocks;

import interfaces.Cos;

public class CosMock implements Cos {
    @Override
    public double cos(double x, double precision) {
        final double correctedX = Math.abs(x) % (Math.PI * 2);
        final double cos = Math.cos(correctedX > precision ? correctedX : 0.0);
        if (Math.abs(cos) < precision)
            return 0.0;
        return cos;
    }
}
