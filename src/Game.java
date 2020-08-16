import engine.Engine;
import engine.systems.GameObjectHandlerSystem;
import engine.systems.InputSystem;

import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class Game extends Canvas implements Runnable {

    private final Window window;
    private Thread thread;
    private boolean isRunning = false;
    private int numBuffers = 3;

    public Game(IGameWindowProperties windowProperties) {
        numBuffers = windowProperties.getNumBuffers();
        window = new Window(new Dimension(windowProperties.getWidth(), windowProperties.getHeight()), windowProperties.getTitle(), this);
    }

    public abstract void initialiseSystems();
    public abstract void deInitialiseSystems();
    public abstract void initialiseComponents();
    public abstract void deInitialiseComponents();

    protected int getScreenScreenWidth() {
        return window.frame.getWidth();
    }
    protected int getScreenScreenHeight() {
        return window.frame.getHeight();
    }

    public void startGame() {
        start();
    }
    public void stopGame() {
        stop();
    }

    private synchronized void start() {
        if(isRunning) return;

        thread = new Thread(this);
        thread.start();
        isRunning = true;
        InputSystem inputSystem = Engine.get().getSystem(InputSystem.getSystemId());
        this.addKeyListener(inputSystem);
    }

    private  synchronized void stop() {
        if(!isRunning) return;

        try {
            thread.join();
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        isRunning = false;
        InputSystem inputSystem = Engine.get().getSystem(InputSystem.getSystemId());
        this.removeKeyListener(inputSystem);
    }

    @Override
    public void run() {
        this.requestFocus();
        long previousFrameTime = System.nanoTime();
        final double frameRate = 60.0;
        final double ns = 1000000000.0 / frameRate;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning) {
            final long currentFrameTime = System.nanoTime();
            delta += (currentFrameTime - previousFrameTime) / ns;
            double deltaTime = (currentFrameTime - previousFrameTime) / 1000000.0/frameRate;
            previousFrameTime = currentFrameTime;
            while(delta >= 1) {
                deltaTime = Math.min(delta, 1.0/frameRate);
                update(deltaTime);
                --delta;
            }
            render();
            ++frames;
            if(System.currentTimeMillis() - timer > 1000) {
                timer+=1000;
                frames = 0;
            }
        }
        stop();
    }

    private void update(final double deltaTime) {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.update(deltaTime);
    }

    private void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null) {
            createBufferStrategy(numBuffers);
            return;
        }
        Graphics graphicsDevice = bufferStrategy.getDrawGraphics();
        graphicsDevice.clearRect(0,0, getScreenScreenWidth(), getScreenScreenHeight());
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.render(graphicsDevice);

        graphicsDevice.dispose();
        bufferStrategy.show();
    }
}
