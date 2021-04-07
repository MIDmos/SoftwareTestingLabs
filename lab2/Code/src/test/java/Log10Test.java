import implementation.Log10Impl;
import interfaces.Log10;
import mocks.LnMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log10Test {
    private static final double PRECISION = 0.00001;
    private final Log10 log10 = new Log10Impl(new LnMock());

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -55})
    void test_Nan(double x) {
        assertEquals(Double.NaN, log10.log_10(x, PRECISION), PRECISION);
    }

    @Test
    void test_Infinity() {
        assertEquals(Double.NEGATIVE_INFINITY, log10.log_10(PRECISION * 0.1, PRECISION), PRECISION);
    }

    @Test
    void test_Zero() {
        assertEquals(0.0, log10.log_10(1, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PRECISION, PRECISION * 2, 1 - PRECISION, 0.5})
    void test_negativeValues(double x) {
        double result = Math.log10(x);
        assertEquals(result, log10.log_10(x, PRECISION), -result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 + PRECISION, 10, 1000, 100000})
    void test_positiveValues(double x) {
        double result = Math.log10(x);
        assertEquals(result, log10.log_10(x, PRECISION), result * PRECISION);
    }

}
