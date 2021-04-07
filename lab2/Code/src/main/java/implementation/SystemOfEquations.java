package implementation;

import interfaces.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SystemOfEquations {
    private final Cos cos;
    private final Sec sec;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;

    public SystemOfEquations(Cos cos, Sec sec, Log3 log3, Log5 log5, Log10 log10) {
        this.cos = cos;
        this.sec = sec;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public double system(double x, double precision) {
        if (x > 0) {
            if (x < precision)
                return 0.0;

            if (Math.abs(x - 1) < precision / 2)
                return Double.NaN;

            if (Math.abs(x - 1) <= precision * 1.5 && Math.abs(x - 1) >= precision / 2)
                return Double.NEGATIVE_INFINITY;

            return ((((log10.log_10(x, precision) + log5.log_5(x, precision))
                    / log10.log_10(x, precision)) - log3.log_3(x, precision))
                    / log10.log_10(x, precision))
                    / ((log3.log_3(x, precision) - log3.log_3(x, precision)) - log10.log_10(x, precision));
        } else {
            double xCorrected = -(-x % (Math.PI * 2));
            if (Math.abs(xCorrected + Math.PI / 2) <= precision * 1.5 && Math.abs(xCorrected + Math.PI / 2) >= precision * 0.5) {
                if (xCorrected > -Math.PI / 2) {
                    return Double.NEGATIVE_INFINITY;
                } else {
                    return Double.POSITIVE_INFINITY;
                }
            }
            if (Math.abs(xCorrected + 3 * Math.PI / 2) <= precision * 1.5 && Math.abs(xCorrected + 3 * Math.PI / 2) >= precision * 0.5) {
                if (xCorrected > -3 * Math.PI / 2) {
                    return Double.POSITIVE_INFINITY;
                } else {
                    return Double.NEGATIVE_INFINITY;
                }
            }
            return ((cos.cos(x, precision) / cos.cos(x, precision)) - sec.sec(x, precision));
        }
    }

    public void createSCV(double from, double to, double step, double precision) {
        final Ln ln = new LnImpl();
        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("X,Y,cos(X),sec(X),ln(X),log_3(X),log_5(X),log_10(X)\n");
            for (double x = from; x <= to; x += step) {
                sb.append(x)
                        .append(",").append(system(x, precision))
                        .append(",").append(cos.cos(x, precision))
                        .append(",").append(sec.sec(x, precision))
                        .append(",").append(ln.ln(x, precision))
                        .append(",").append(log3.log_3(x, precision))
                        .append(",").append(log5.log_5(x, precision))
                        .append(",").append(log10.log_10(x, precision))
                        .append("\n");
            }
            writer.write(sb.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
