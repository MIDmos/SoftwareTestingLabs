package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirTest {

    @Test
    void getDescription_Test() {
        final Air air = new Air("Test", false);
        assertEquals("Test", air.getDescription());
        assertFalse(air.hasOxygen());
    }

    @Test
    void getDescription_TrimTest() {
        final Air air = new Air("    Test   ", true);
        assertEquals("Test", air.getDescription());
        assertTrue(air.hasOxygen());
    }

    @Test
    void hasOxygen_TrueTest() {
        final Air air = new Air("Test", true);
        assertTrue(air.hasOxygen());
    }

    @Test
    void hasOxygen_FalseTest() {
        final Air air = new Air("    Test   ", false);
        assertFalse(air.hasOxygen());
    }

    @Test
    void getDescription_NullTest() {
        assertThrows(IllegalArgumentException.class, () -> new Air(null, false));
    }

    @Test
    void getDescription_EmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Air("    ", false));
    }
}
