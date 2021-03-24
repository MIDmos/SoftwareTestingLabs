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
    void getName_NullTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null));
    }

    @Test
    void getName_EmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person("    "));
    }
}
