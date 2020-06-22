import engine.EngineInitialiser;

public class Main {
    public static void main(String args[]) {

        //Initialise Engine
        EngineInitialiser.init();

        //Initialise game
        YourGame yourGame = new YourGame(new YourGameWindowProperties());
        //Initialise Game Systems
        yourGame.initialiseSystems();
        //Initialise Game Components
        yourGame.initialiseComponents();

        yourGame.startGame();
    }
}
