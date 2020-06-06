package test.engine;
import engine.Engine;
import engine.systems.GameObjectHandlerSystem;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EngineTest {
    @Test
    public void GetSystemFromEngine() {
        System.out.println("Starting test: GetSystemFromEngine");

        Engine gameEngine = Engine.Get();
        //create system
        GameObjectHandlerSystem gameObjectHandlerSystem = new GameObjectHandlerSystem();
        GameObjectHandlerSystem aSystem = gameEngine.GetSystem(GameObjectHandlerSystem.GetSystemId());
        assertEquals("System must be null if you dont add to engine",  null, aSystem);

        gameEngine.AddSystem(gameObjectHandlerSystem);
        gameEngine.AddSystem(gameObjectHandlerSystem);
        aSystem = gameEngine.GetSystem(GameObjectHandlerSystem.GetSystemId());
        assertEquals("System must not be null if added to engine", aSystem, aSystem);

        gameEngine.RemoveSystem(gameObjectHandlerSystem.GetId());
        aSystem = gameEngine.GetSystem(GameObjectHandlerSystem.GetSystemId());
        assertEquals("System must be null if you removed it from engine", null, aSystem);

        gameEngine.AddSystem(gameObjectHandlerSystem);
        aSystem = gameEngine.GetSystem(GameObjectHandlerSystem.GetSystemId());
        assertEquals("System must not be null if added to engine", aSystem, aSystem);

    }

}
