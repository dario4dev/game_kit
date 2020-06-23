package engine;
//Creates engine and add all the engine systems

import engine.systems.GameObjectHandlerSystem;
import engine.systems.InputSystem;

public class EngineInitialiser {

    public static void init(){
        Engine engine = Engine.get();
        //Initialise Engine Systems
        Engine.get().addSystem(new GameObjectHandlerSystem());
        Engine.get().addSystem(new InputSystem());
    }

    public static void deInit() {
        Engine engine = Engine.get();
        //Remove Engine Systems
        Engine.get().removeAllSystems();
    }
}
