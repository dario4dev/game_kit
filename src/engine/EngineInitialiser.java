package engine;
//Creates engine and add all the engine systems

import engine.systems.GameObjectHandlerSystem;
import engine.systems.InputSystem;

public class EngineInitialiser {

    public static void init(){
        Engine engine = Engine.Get();
        //Initialise Engine Systems
        Engine.Get().addSystem(new GameObjectHandlerSystem());
        Engine.Get().addSystem(new InputSystem());
    }

    public static void deInit() {
        Engine engine = Engine.Get();
        //Remove Engine Systems
        Engine.Get().removeAllSystems();
    }
}
