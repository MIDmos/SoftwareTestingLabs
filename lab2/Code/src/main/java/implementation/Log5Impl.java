package implementation;

import interfaces.Ln;
import interfaces.Log5;

public class Log5Impl implements Log5 {
    final private Ln ln;

    public Log5Impl(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double log_5(double x, double precision) {
        if (x <= 0)
            return Double.NaN;
        if (x < precision * 0.5)
            return Double.NEGATIVE_INFINITY;
        return ln.ln(x, precision * precision) / ln.ln(5.0, precision * precision);
    }
}
