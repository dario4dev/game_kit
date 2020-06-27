package test.engine;
import engine.Engine;
import engine.GameObject;
import engine.systems.GameObjectHandlerSystem;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EngineTest {
    @Test
    public void getSystemFromEngine() {
        System.out.println("Starting test: GetSystemFromEngine");

        Engine gameEngine = Engine.get();
        //create system
        GameObjectHandlerSystem gameObjectHandlerSystem = new GameObjectHandlerSystem();

        gameEngine.addSystem(gameObjectHandlerSystem);
        GameObjectHandlerSystem aSystem = gameEngine.getSystem(GameObjectHandlerSystem.getSystemId());
        assertTrue("System must not be null if added to engine", aSystem.equals(gameObjectHandlerSystem));
    }

    @Test
    public void findGameObject() {
        System.out.println("Starting test: findGameObject");

        Engine gameEngine = Engine.get();
        //create system
        GameObjectHandlerSystem gameObjectHandlerSystem = new GameObjectHandlerSystem();
        gameEngine.addSystem(gameObjectHandlerSystem);

        class MyGameObject extends GameObject {
            @Override
            protected void update(double deltaTime) {
            }
            @Override
            protected void render(Graphics graphicsDevice) {
            }
        }

        MyGameObject myGameObject = new MyGameObject();
        MyGameObject myGameObject1 = new MyGameObject();
        gameObjectHandlerSystem.add(myGameObject);
        gameObjectHandlerSystem.add(myGameObject1);

        myGameObject.setName("dario");
        myGameObject1.setName("dario1");

        GameObject darioGameObject = GameObject.find(myGameObject.getName());
        GameObject dario1GameObject = GameObject.find(myGameObject1.getName());

        assertTrue("Found objects are not equals", darioGameObject.equals(myGameObject));
        assertTrue("Found objects are not equals", dario1GameObject.equals(myGameObject1));
    }
}
