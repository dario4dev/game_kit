import engine.Engine;
import engine.systems.GameObjectHandlerSystem;
import engine.systems.InputSystem;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;

public abstract class Game extends Canvas implements Runnable {

    private final Window mWindow;
    private Thread mThread;
    private boolean mIsRunning = false;
    private int mNumBuffers = 3;

    public Game(IGameWindowProperties windowProperties) {
        mNumBuffers = windowProperties.getNumBuffers();
        mWindow = new Window(new Dimension(windowProperties.getWidth(), windowProperties.getHeight()), windowProperties.getTitle(), this);

    }

    public abstract void InitialiseSystems();
    public abstract void DeinitialiseSystems();
    public abstract void InitialiseComponents();
    public abstract void DeinitialiseComponents();

    protected int GetScreenScreenWidth() {
        return mWindow.mframe.getWidth();
    }
    protected int GetScreenScreenHeight() {
        return mWindow.mframe.getHeight();
    }

    public void Start() {
        start();
    }
    public void Stop() {
        stop();
    }

    private synchronized void start() {
        if(mIsRunning) return;

        mThread = new Thread(this);
        mThread.start();
        mIsRunning = true;
        InputSystem inputSystem = Engine.Get().GetSystem(InputSystem.GetSystemId());
        this.addKeyListener(inputSystem);
    }

    private  synchronized void stop() {
        if(!mIsRunning) return;

        try {
            mThread.join();
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        mIsRunning = false;
        InputSystem inputSystem = Engine.Get().GetSystem(InputSystem.GetSystemId());
        this.removeKeyListener(inputSystem);
    }

    @Override
    public void run() {
        this.requestFocus();
        long previousFrameTime = System.nanoTime();
        final double frameRate = 60.0;
        final double ns = 1000000000 / frameRate;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(mIsRunning) {
            final long currentFrameTime = System.nanoTime();
            delta += (currentFrameTime - previousFrameTime) / ns;
            previousFrameTime = currentFrameTime;
            while(delta >= 1) {
                Update(delta);
                --delta;
            }
            Render();
            ++frames;
            if(System.currentTimeMillis() - timer > 1000) {
                timer+=1000;
                frames = 0;
            }
        }
        stop();
    }

    private void Update(final double deltaTime) {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.Get().GetSystem(GameObjectHandlerSystem.GetSystemId());
        gameObjectHandlerSystem.Update(deltaTime);
    }

    private void Render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null) {
            createBufferStrategy(mNumBuffers);
            return;
        }
        Graphics graphicsDevice = bufferStrategy.getDrawGraphics();
        graphicsDevice.clearRect(0,0, GetScreenScreenWidth(), GetScreenScreenHeight());
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.Get().GetSystem(GameObjectHandlerSystem.GetSystemId());
        gameObjectHandlerSystem.Render(graphicsDevice);

        graphicsDevice.dispose();
        bufferStrategy.show();

    }
}
