package implementation;

import interfaces.Cos;
import interfaces.Sec;

public class SecImpl implements Sec {
    final private Cos cos;

    public SecImpl(Cos cos) {
        this.cos = cos;
    }

    @Override
    public double sec(double x, double precision) {
        final double correctedX = Math.abs(x) % (Math.PI * 2);
        double halfPiDIff = Math.abs(correctedX - Math.PI / 2);
        if (halfPiDIff < precision * 1.5) {
            if (halfPiDIff >= precision * 0.5) {
                return correctedX > Math.PI / 2 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else {
                return Double.NaN;
            }
        }
        double threeHalfPiDIff = Math.abs(correctedX - 3 * Math.PI / 2);
        if (threeHalfPiDIff < precision * 1.5) {
            if (threeHalfPiDIff >= precision * 0.5) {
                return correctedX > 3 * Math.PI / 2 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            } else {
                return Double.NaN;
            }
        }
        return 1 / cos.cos(x, precision * precision);
    }
}
