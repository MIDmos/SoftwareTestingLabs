import implementation.*;
import interfaces.*;

import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {
        Cos cos = new CosImpl();
        Sec sec = new SecImpl(cos);
        Ln ln = new LnImpl();
        Log3 log3 = new Log3Impl(ln);
        Log5 log5 = new Log5Impl(ln);
        Log10 log10 = new Log10Impl(ln);
        SystemOfEquations systemOfEquations = new SystemOfEquations(cos, sec, log3, log5, log10);
        systemOfEquations.createSCV(-2 * PI, 2 * PI, 0.01, 0.00001);
    }
}
