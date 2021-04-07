package implementation;

import interfaces.Ln;
import interfaces.Log3;

public class Log3Impl implements Log3 {
    final private Ln ln;

    public Log3Impl(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double log_3(double x, double precision) {
        if (x <= 0)
            return Double.NaN;
        if (x < precision * 0.5)
            return Double.NEGATIVE_INFINITY;
        return ln.ln(x, precision * precision) / ln.ln(3.0, precision * precision);
    }
}
