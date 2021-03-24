package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {
    @Test
    void play_OkWorkTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Rocket rocket = new Rocket(new Space(), new Engine(true));
        final Scene scene = new Scene(team, rocket);
        assertEquals("Тоненький свист перерос в рев воздуха. Test, Test2 вылетели как конфетти из хлопушки прямиком в Космос.", scene.play());
    }

    @Test
    void play_OkWorkNamesSpaceTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Rocket rocket = new Rocket(new Space("космос, наполненный звездами"), new Engine(true));
        final Scene scene = new Scene(team, rocket);
        assertEquals("Тоненький свист перерос в рев воздуха. Test, Test2 вылетели как конфетти из хлопушки прямиком в космос, наполненный звездами.", scene.play());
    }

    @Test
    void play_NoWorkTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Rocket rocket = new Rocket(new Space(), new Engine(false));
        final Scene scene = new Scene(team, rocket);
        assertEquals(Rocket.NO_WORK, scene.play());
    }

    @Test
    void play_UselessWorkTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Rocket rocket = new Rocket(null, new Engine(true));
        final Scene scene = new Scene(team, rocket);
        assertEquals(Rocket.USELESS_WORK, scene.play());
    }

    @Test
    void team_Test() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Rocket rocket = new Rocket();
        final Scene scene = new Scene(team, rocket);
        assertArrayEquals(team, scene.getTeam());
    }

    @Test
    void rocket_Test() {
        final Person[] team = {new Person("Test")};
        final Rocket rocket = new Rocket();
        final Scene scene = new Scene(team, rocket);
        assertEquals(rocket, scene.getRocket());
    }


    @Test
    void create_NullTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Scene(null, new Rocket()));
    }

    @Test
    void create_EmptyTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Scene(new Person[]{}, new Rocket()));
    }

    @Test
    void create_NullRocket() {
        assertThrows(IllegalArgumentException.class, () -> new Scene(new Person[]{new Person("Test")}, null));
    }
}
