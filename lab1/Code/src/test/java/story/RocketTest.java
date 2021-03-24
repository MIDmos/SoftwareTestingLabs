package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {
    @Test
    void canWork_GoodEngineTest() {
        final Rocket rocket = new Rocket(new Space(), new Engine(true));
        assertTrue(rocket.canWork());
        assertEquals(Rocket.OK_WORK, rocket.doWork());
    }

    @Test
    void canWork_BadEngineTest() {
        final Rocket rocket = new Rocket(new Space(), new Engine(false));
        assertFalse(rocket.canWork());
        assertEquals(Rocket.NO_WORK, rocket.doWork());
    }

    @Test
    void canWork_BadEngineNullSpaceTest() {
        final Rocket rocket = new Rocket(null, new Engine(false));
        assertFalse(rocket.canWork());
        assertEquals(Rocket.NO_WORK, rocket.doWork());
    }

    @Test
    void canWork_GoodEngineNullSpaceTest() {
        final Rocket rocket = new Rocket(null, new Engine(true));
        assertTrue(rocket.canWork());
        assertEquals(Rocket.USELESS_WORK, rocket.doWork());
    }

    @Test
    void canWork_NullEngineTest() {
        final Rocket rocket = new Rocket(new Space(), null);
        assertFalse(rocket.canWork());
        assertEquals(Rocket.NO_WORK, rocket.doWork());
    }

    @Test
    void canWork_SetEngineTest() {
        final Rocket rocket = new Rocket(new Space(), new Engine(true));
        assertTrue(rocket.canWork());
        rocket.setEngine(null);
        assertFalse(rocket.canWork());
        rocket.setEngine(new Engine(false));
        assertFalse(rocket.canWork());
        rocket.setEngine(new Engine(true));
        assertTrue(rocket.canWork());
    }

    @Test
    void canWork_ChangeEngineTest() {
        final Rocket rocket = new Rocket(new Space(), new Engine(true));
        assertTrue(rocket.canWork());
        rocket.getEngine().setCanWork(false);
        assertFalse(rocket.canWork());
        rocket.getEngine().setCanWork(true);
        assertTrue(rocket.canWork());
    }

    @Test
    void canGoToSpace_SetTargetSpaceTest() {
        final Rocket rocket = new Rocket(null, new Engine(true));
        assertFalse(rocket.canGoToSpace());
        rocket.setTargetSpace(new Space());
        assertTrue(rocket.canGoToSpace());
    }

    @Test
    void canGoToSpace_ChangeTargetSpaceTest() {
        final Rocket rocket = new Rocket(new Space(), new Engine(true));
        assertTrue(rocket.canGoToSpace());
        rocket.getTargetSpace().setDescription("Тест");
        assertTrue(rocket.canGoToSpace());
    }
}
