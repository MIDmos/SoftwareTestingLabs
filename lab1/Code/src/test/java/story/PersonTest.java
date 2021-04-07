package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    @Test
    void getName_Test() {
        final Person person = new Person("Ваня");
        assertEquals("Ваня", person.getName());
    }

    @Test
    void getName_TrimTest() {
        final Person person = new Person("    Ваня   ");
        assertEquals("Ваня", person.getName());
    }

    @Test
    void canBreathe_NullTest() {
        final Person person = new Person("    Ваня   ");
        assertFalse(person.canBreathe(null));
    }

    @Test
    void canBreathe_TrueTest() {
        final Person person = new Person("    Ваня   ");
        assertTrue(person.canBreathe(new Air("Test", true)));
    }

    @Test
    void canBreathe_FalseTest() {
        final Person person = new Person("    Ваня   ");
        assertFalse(person.canBreathe(new Air("Test", false)));
    }

    @Test
    void getName_NullTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null));
    }

    @Test
    void getName_EmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person("    "));
    }
}
