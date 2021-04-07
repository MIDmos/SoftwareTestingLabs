import implementation.SystemOfEquations;
import interfaces.*;
import mocks.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemOfEquationsTest {
    private static final double PRECISION = 0.00001;
    private static SystemOfEquations system;

    @BeforeAll
    static void setup() {
        Cos cos = new CosMock();
        Sec sec = new SecMock();
        Log3 log3 = new Log3Mock();
        Log5 log5 = new Log5Mock();
        Log10 log10 = new Log10Mock();
        system = new SystemOfEquations(cos, sec, log3, log5, log10);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-0.5 * Math.PI, -0.5 * Math.PI + PRECISION * 0.1, -55.5 * Math.PI})
    void test_F1_Nan(double x) {
        assertEquals(Double.NaN, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.5 * Math.PI - PRECISION, -1.5 * Math.PI + PRECISION, -55.5 * Math.PI + PRECISION, -56.5 * Math.PI - PRECISION})
    void test_F1_positiveInfinity(double x) {
        assertEquals(Double.POSITIVE_INFINITY, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.5 * Math.PI + PRECISION, -1.5 * Math.PI - PRECISION, -55.5 * Math.PI - PRECISION, -56.5 * Math.PI + PRECISION})
    void test_F1_negativeInfinity(double x) {
        assertEquals(Double.NEGATIVE_INFINITY, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2 * Math.PI, -2 * Math.PI + PRECISION * 0.1, -8 * Math.PI - PRECISION * 0.1, -56 * Math.PI})
    void test_F1_max(double x) {
        assertEquals(0.0, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1 * Math.PI, -1 * Math.PI + PRECISION * 0.1, -7 * Math.PI - PRECISION * 0.1, -55 * Math.PI})
    void test_F1_min(double x) {
        assertEquals(2.0, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-4.2, -96.3, -4.2 - Math.PI * 2, -96.3 - Math.PI * 2})
    void test_F1_positiveValues(double x) {
        double result = Math.cos(x) / Math.cos(x) - 1.0 / Math.cos(x);
        assertEquals(result, system.system(x, PRECISION), result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.12, -100, -0.12 - Math.PI * 2, -100 - Math.PI * 2,})
    void test_F1_negativeValues(double x) {
        double result = Math.cos(x) / Math.cos(x) - 1.0 / Math.cos(x);
        assertEquals(result, system.system(x, PRECISION), -result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -PRECISION * 0.1, PRECISION * 0.1})
    void test_MiddlePoint(double x) {
        assertEquals(0.0, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 1 - PRECISION * 0.1, 1 + PRECISION * 0.1})
    void test_F2_Nan(double x) {
        assertEquals(Double.NaN, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 - PRECISION, 1 + PRECISION})
    void test_F2_Infinity(double x) {
        assertEquals(Double.NEGATIVE_INFINITY, system.system(x, PRECISION), PRECISION);
    }

    // zero point is e^(log(3)+(log(3)*log(10))/log(5)) = 14.4453293867163457569
    @ParameterizedTest
    @ValueSource(doubles = {14.44533, 14.44533 + PRECISION * 0.1, 14.44533 - PRECISION * 0.1})
    void test_F2_Zero(double x) {
        assertEquals(0.0, system.system(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PRECISION, PRECISION * 2, 14.44533 - PRECISION, 7.2})
    void test_F2_negativeValues(double x) {
        double ln = Math.log(x);
        double log3 = ln / Math.log(3.0);
        double log5 = ln / Math.log(5.0);
        double log10 = Math.log10(x);
        double result = ((((log10 + log5) / log10) - log3) / log10) / ((log3 - log3) - log10);
        assertEquals(result, system.system(x, PRECISION), -result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {14.44533 + PRECISION, 20, 2000, 200000})
    void test_F2_positiveValues(double x) {
        double ln = Math.log(x);
        double log3 = ln / Math.log(3.0);
        double log5 = ln / Math.log(5.0);
        double log10 = Math.log10(x);
        double result = ((((log10 + log5) / log10) - log3) / log10) / ((log3 - log3) - log10);
        assertEquals(result, system.system(x, PRECISION), result * PRECISION);
    }

}
