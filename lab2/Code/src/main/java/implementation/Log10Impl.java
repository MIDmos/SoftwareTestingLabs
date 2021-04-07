package implementation;

import interfaces.Ln;
import interfaces.Log10;

public class Log10Impl implements Log10 {
    final private Ln ln;

    public Log10Impl(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double log_10(double x, double precision) {
        if (x <= 0)
            return Double.NaN;
        if (x < precision * 0.5)
            return Double.NEGATIVE_INFINITY;
        return ln.ln(x, precision * precision) / ln.ln(10.0, precision * precision);
    }
}
