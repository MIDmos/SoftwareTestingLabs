import implementation.SecImpl;
import interfaces.Sec;
import mocks.CosMock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecTest {
    private static final double PRECISION = 0.00001;
    private final Sec sec = new SecImpl(new CosMock());

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, 0.5 * Math.PI + PRECISION * 0.1, -0.5 * Math.PI, 55.5 * Math.PI, -55.5 * Math.PI})
    void test_Nan(double x) {
        assertEquals(Double.NaN, sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI - PRECISION, -0.5 * Math.PI + PRECISION, 55.5 * Math.PI + PRECISION, -55.5 * Math.PI - PRECISION,})
    void test_positiveInfinity(double x) {
        assertEquals(Double.POSITIVE_INFINITY, sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI + PRECISION, -0.5 * Math.PI - PRECISION, 55.5 * Math.PI - PRECISION, -55.5 * Math.PI + PRECISION,})
    void test_negativeInfinity(double x) {
        assertEquals(Double.NEGATIVE_INFINITY, sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0 * Math.PI, -2 * Math.PI, -2 * Math.PI + PRECISION * 0.1, 2 * Math.PI, 56 * Math.PI, -56 * Math.PI})
    void test_max(double x) {
        assertEquals(1.0, sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 * Math.PI, -1 * Math.PI, -1 * Math.PI + PRECISION * 0.1, 55 * Math.PI, 55 * Math.PI, -55 * Math.PI})
    void test_min(double x) {
        assertEquals(-1.0, sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, -0.12, -100, 100})
    void test_positiveValues(double x) {
        assertEquals(1.0 / Math.cos(x), sec.sec(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3.11, -4.2, 96.3, -96.3})
    void test_negativeValues(double x) {
        assertEquals(1.0 / Math.cos(x), sec.sec(x, PRECISION), PRECISION);
    }

}
