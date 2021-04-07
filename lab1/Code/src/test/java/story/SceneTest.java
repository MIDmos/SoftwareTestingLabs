package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {
    @Test
    void play_BadAirTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Engine engine = new Engine(true);
        final Air air = new Air("комнатный воздух", true);
        final Air space = null;
        final Scene scene = new Scene(team, engine, air, space);
        assertEquals("Зажужжал мотор, комнатный воздух зашумел. Test, Test2 вылетели как конфетти из хлопушки прямиком в открытый космос. Test не может дышать, Test2 не может дышать.", scene.play());
    }

    @Test
    void play_OkAirTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Engine engine = new Engine(true);
        final Air air = new Air("комнатный воздух", true);
        final Air space = new Air("свежий воздух", true);
        final Scene scene = new Scene(team, engine, air, space);
        assertEquals("Зажужжал мотор, комнатный воздух зашумел. Test, Test2 перешли в пространство с воздухом свежий воздух. Test может дышать, Test2 может дышать.", scene.play());
    }

    @Test
    void play_BadEngineTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Engine engine = new Engine(false);
        final Air air = new Air("комнатный воздух", true);
        final Air space = new Air("свежий воздух", true);
        final Scene scene = new Scene(team, engine, air, space);
        assertEquals("Test может дышать, Test2 может дышать.", scene.play());
    }

    @Test
    void play_NullEngineTest() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Air air = new Air("комнатный воздух", true);
        final Air space = new Air("свежий воздух", true);
        final Scene scene = new Scene(team, null, air, space);
        assertEquals("Мотор не зажжужжал. Test может дышать, Test2 может дышать.", scene.play());
    }

    @Test
    void create_NullTeam() {
        final Engine engine = new Engine(true);
        final Air air = new Air("Комнатный воздух", true);
        assertThrows(IllegalArgumentException.class, () -> new Scene(null, engine, air, null));
    }

    @Test
    void create_EmptyTeam() {
        final Person[] team = {};
        final Engine engine = new Engine(true);
        final Air air = new Air("Комнатный воздух", true);
        assertThrows(IllegalArgumentException.class, () -> new Scene(team, engine, air, null));
    }

    @Test
    void create_NullAir() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Engine engine = new Engine(true);
        assertThrows(IllegalArgumentException.class, () -> new Scene(team, engine, null, null));
    }
    @Test
    void create_BadAir() {
        final Person[] team = {new Person("Test"), new Person("Test2")};
        final Engine engine = new Engine(true);
        final Air air = new Air("Воздух",false);
        assertThrows(IllegalArgumentException.class, () -> new Scene(team, engine, air, null));
    }
}
