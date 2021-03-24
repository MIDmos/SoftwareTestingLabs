package story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    @Test
    void canWork_TrueTest() {
        final Engine engine = new Engine(true);
        assertTrue(engine.canWork());
        assertEquals(Engine.OK_WORK, engine.doWork());
    }

    @Test
    void canWork_FalseTest() {
        final Engine engine = new Engine(false);
        assertFalse(engine.canWork());
        assertEquals(Engine.NO_WORK, engine.doWork());
    }

    @Test
    void canWork_ChangeToFalseTest() {
        final Engine engine = new Engine(true);
        engine.setCanWork(false);
        assertFalse(engine.canWork());
        assertEquals(Engine.NO_WORK, engine.doWork());
    }

    @Test
    void canWork_ChangeToTrueTest() {
        final Engine engine = new Engine(false);
        engine.setCanWork(true);
        assertTrue(engine.canWork());
        assertEquals(Engine.OK_WORK, engine.doWork());
    }
}
