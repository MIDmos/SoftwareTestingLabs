public final class TgExpansion {
    private TgExpansion() {
    }

    private final static int N = 20;
    private final static double PI_DELTA = 0.0000000000001;

    private static double sin(double x) {
        final double halfPiDelta = Math.abs(x) - Math.PI / 2;
        if (Math.abs(halfPiDelta) < 0.001) {
            return x > 0 ? 1.0 : -1.0;
        }
        double denominator;
        double x1 = x;
        double sinx = x;
        for (int i = 1; i < N; i++) {
            denominator = 2 * i * (2 * i + 1);
            x1 = -x1 * x * x / denominator;
            sinx = sinx + x1;
        }
        return sinx;
    }

    private static double cos(double x) {
        if (Math.abs(Math.abs(x % Math.PI) - Math.PI / 2) < 0.0001) {
            return 0;
        }
        double denominator;
        double x1 = 1;
        double cosx = 1;
        for (int i = 1; i < N; i++) {
            denominator = 2 * i * (2 * i - 1);
            x1 = -x1 * x * x / denominator;
            cosx = cosx + x1;
        }
        return cosx;
    }

    static double tg(double x) {
        double xCorrected;
        int count = (int) (Math.abs(x) * 2 / Math.PI);
        if (count % 2 == 1) {
            count++;
        }
        final double diff = Math.PI * count / 2;
        if (x > 0) {
            xCorrected = x - diff;
        } else {
            xCorrected = x + diff;
        }
        final double halfPiDelta = Math.abs(xCorrected) - Math.PI / 2;
        if (Math.abs(halfPiDelta) < PI_DELTA) {
            return Double.NaN;
        }

        final double cos = cos(xCorrected);
        if (cos == 0) {
            final double sin = sin(xCorrected);
            return sin > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        return sin(xCorrected) / cos;
    }
}
