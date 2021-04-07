import implementation.CosImpl;
import interfaces.Cos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {
    private static final double PRECISION = 0.00001;
    private final Cos cos = new CosImpl();

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, 0.5 * Math.PI + PRECISION * 0.1, -0.5 * Math.PI, 55.5 * Math.PI, -55.5 * Math.PI})
    void test_zero(double x) {
        assertEquals(0.0, cos.cos(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0 * Math.PI, -2 * Math.PI, -2 * Math.PI + PRECISION * 0.1, 2 * Math.PI, 56 * Math.PI, -56 * Math.PI})
    void test_max(double x) {
        assertEquals(1.0, cos.cos(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 * Math.PI, -1 * Math.PI, -1 * Math.PI + PRECISION * 0.1, 55 * Math.PI, 55 * Math.PI, -55 * Math.PI})
    void test_min(double x) {
        assertEquals(-1.0, cos.cos(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, -0.12, -100, 100})
    void test_positiveValues(double x) {
        System.out.println(Math.cos(x));
        assertEquals(Math.cos(x), cos.cos(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3.11, -4.2, 96.3, -96.3})
    void test_negativeValues(double x) {
        System.out.println(Math.cos(x));
        assertEquals(Math.cos(x), cos.cos(x, PRECISION), PRECISION);
    }

}
