import engine.Engine;
import engine.EngineInitialiser;
import engine.systems.GameObjectHandlerSystem;

public class Main {
    public static void main(String args[]) {

        //Initialise Engine
        EngineInitialiser.Init();

        //Initialise game
        YourGame yourGame = new YourGame(new YourGameWindowProperties());
        //Initialise Game Systems
        yourGame.InitialiseSystems();
        //Initialise Game Components
        yourGame.InitialiseComponents();

        yourGame.Start();
    }
}
