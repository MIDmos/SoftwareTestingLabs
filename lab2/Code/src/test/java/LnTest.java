import implementation.LnImpl;
import interfaces.Ln;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private static final double PRECISION = 0.00001;
    private final Ln ln = new LnImpl();

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -55})
    void test_Nan(double x) {
        assertEquals(Double.NaN, ln.ln(x, PRECISION), PRECISION);
    }

    @Test
    void test_Infinity() {
        assertEquals(Double.NEGATIVE_INFINITY, ln.ln(PRECISION * 0.1, PRECISION), PRECISION);
    }

    @Test
    void test_Zero() {
        assertEquals(0.0, ln.ln(1, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PRECISION, PRECISION * 2, 1 - PRECISION, 0.5})
    void test_negativeValues(double x) {
        double result = Math.log(x);
        assertEquals(result, ln.ln(x, PRECISION), -result * PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 + PRECISION, 10, 1000, 100000})
    void test_positiveValues(double x) {
        double result = Math.log(x);
        assertEquals(result, ln.ln(x, PRECISION), result * PRECISION);
    }

}
