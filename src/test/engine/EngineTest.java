package test.engine;
import engine.Engine;
import engine.systems.GameObjectHandlerSystem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTest {
    @Test
    public void getSystemFromEngine() {
        System.out.println("Starting test: GetSystemFromEngine");

        Engine gameEngine = Engine.get();
        //create system
        GameObjectHandlerSystem gameObjectHandlerSystem = new GameObjectHandlerSystem();

        gameEngine.addSystem(gameObjectHandlerSystem);
        GameObjectHandlerSystem aSystem = gameEngine.getSystem(GameObjectHandlerSystem.getSystemId());
        assertEquals("System must not be null if added to engine", aSystem, aSystem);
    }
}
