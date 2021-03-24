import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class TgExpansionTest {

    private final double functionDelta = 0.00000000001;
    private final double piDelta = 0.00000001;

    @ParameterizedTest
    @CsvFileSource(resources = "/tg_test.csv", numLinesToSkip = 1)
    void tg_testValues(double x, double y) {
        assertEquals(y, TgExpansion.tg(x), functionDelta);
    }

    @Test
    void tg_equalityTestPositive() {
        double x = 4.12;
        assertEquals(TgExpansion.tg(x), TgExpansion.tg(x + Math.PI * 20), functionDelta);
    }

    @Test
    void tg_equalityTestNegative() {
        double x = -4.12;
        assertEquals(TgExpansion.tg(x), TgExpansion.tg(x + Math.PI * 20), functionDelta);
    }

    @Test
    void tg_zeroTest() {
        assertEquals(0, TgExpansion.tg(0));
    }

    @Test
    void tg_InfinityPositiveTest() {
        assertEquals(Double.NaN, TgExpansion.tg(Double.POSITIVE_INFINITY));
    }

    @Test
    void tg_InfinityNegativeTest() {
        assertEquals(Double.NaN, TgExpansion.tg(Double.NEGATIVE_INFINITY));
    }

    @Test
    void tg_NaNTest() {
        assertEquals(Double.NaN, TgExpansion.tg(Double.NaN));
    }

    @Test
    void tg_Minus5HalfPiTest() {
        assertEquals(Double.NaN, TgExpansion.tg(-Math.PI * 5 / 2));
    }

    @Test
    void tg_Minus5HalfPiPositiveDeltaTest() {
        assertEquals(Double.NEGATIVE_INFINITY, TgExpansion.tg(-Math.PI * 5 / 2 + piDelta));
    }

    @Test
    void tg_Minus5HalfPiNegativeDeltaTest() {
        assertEquals(Double.POSITIVE_INFINITY, TgExpansion.tg(-Math.PI * 5 / 2 - piDelta));
    }

    @Test
    void tg_7HalfPiTest() {
        assertEquals(Double.NaN, TgExpansion.tg(Math.PI * 7 / 2));
    }

    @Test
    void tg_7HalfPiPositiveDeltaTest() {
        assertEquals(Double.NEGATIVE_INFINITY, TgExpansion.tg(-Math.PI * 5 / 2 + piDelta));
    }

    @Test
    void tg_7HalfPiNegativeDeltaTest() {
        assertEquals(Double.POSITIVE_INFINITY, TgExpansion.tg(-Math.PI * 5 / 2 - piDelta));
    }

}
