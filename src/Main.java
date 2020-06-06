import engine.Engine;
import engine.systems.GameObjectHandlerSystem;

public class Main {
    public static void main(String args[]) {

        //Initialise Engine
        Engine.Get();
        //Initialise Engine Systems
        Engine.Get().AddSystem(new GameObjectHandlerSystem());

        //Initialise game
        YourGame yourGame = new YourGame(new YourGameWindowProperties());
        //Initialise Game Systems
        yourGame.InitialiseSystems();
        //Initialise Game Components
        yourGame.InitialiseComponents();

        yourGame.Start();
    }
}
