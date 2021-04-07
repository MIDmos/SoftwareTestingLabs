import implementation.Log5Impl;
import interfaces.Log5;
import mocks.LnMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log5Test {
    private static final double PRECISION = 0.00001;
    private final Log5 log5 = new Log5Impl(new LnMock());

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -55})
    void test_Nan(double x) {
        assertEquals(Double.NaN, log5.log_5(x, PRECISION), PRECISION);
    }

    @Test
    void test_Infinity() {
        assertEquals(Double.NEGATIVE_INFINITY, log5.log_5(PRECISION * 0.1, PRECISION), PRECISION);
    }

    @Test
    void test_Zero() {
        assertEquals(0.0, log5.log_5(1, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PRECISION, PRECISION * 2, 1 - PRECISION, 0.5})
    void test_negativeValues(double x) {
        double result = Math.log(x) / Math.log(5.0);
        assertEquals(result, log5.log_5(x, PRECISION), -result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 + PRECISION, 10, 1000, 100000})
    void test_positiveValues(double x) {
        double result = Math.log(x) / Math.log(5.0);
        assertEquals(result, log5.log_5(x, PRECISION), result * PRECISION);
    }

}
