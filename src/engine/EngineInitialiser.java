package engine;
//Creates engine and add all the engine systems

import engine.systems.GameObjectHandlerSystem;
import engine.systems.InputSystem;

public class EngineInitialiser {

    public static void Init(){
        Engine engine = Engine.Get();
        //Initialise Engine Systems
        Engine.Get().AddSystem(new GameObjectHandlerSystem());
        Engine.Get().AddSystem(new InputSystem());
    }

    public static void DeInit() {
        Engine engine = Engine.Get();
        //Remove Engine Systems
        Engine.Get().RemoveAllSystems();
    }
}
