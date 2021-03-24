package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpaceTest {

    //Constructor test
    @Test
    void getDescription_Test() {
        final Space space = new Space("Космос со звездами");
        assertEquals("Космос со звездами", space.getDescription());
    }

    @Test
    void getDescription_TrimTest() {
        final Space space = new Space("   Космос со звездами   ");
        assertEquals("Космос со звездами", space.getDescription());
    }

    @Test
    void getDescription_NullTest() {
        final Space space = new Space(null);
        assertEquals(Space.DEFAULT_DESCRIPTION, space.getDescription());
    }

    @Test
    void getDescription_EmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Space("    "));
    }
    //Constructor test

    //Setter test
    @Test
    void getDescription_SetTest() {
        final Space space = new Space();
        space.setDescription("Открытый космос");
        assertEquals("Открытый космос", space.getDescription());
    }

    @Test
    void getDescription_SetStringTrimTest() {
        final Space space = new Space();
        space.setDescription("  Открытый космос  ");
        assertEquals("Открытый космос", space.getDescription());
    }

    @Test
    void getDescription_SetNullTest() {
        final Space space = new Space("Тест");
        space.setDescription(null);
        assertEquals(Space.DEFAULT_DESCRIPTION, space.getDescription());
    }

    @Test
    void getDescription_SetEmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Space().setDescription(" "));
    }
    //Setter test
}
