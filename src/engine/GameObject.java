package engine;

import engine.systems.GameObjectHandlerSystem;

import java.awt.*;

public abstract class GameObject {

    private boolean isEnabled = true;
    private Transform transform = null;
    private GameObjectTag gameObjectTag = null;

    public GameObject() {
        //Create unique tag per Class type
        String classId = String.valueOf(this);
        String[] parts = classId.split("@");
        gameObjectTag = new GameObjectTag(parts[0]);

        transform = new Transform();
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.add(this);
    }

    protected void finalize()
    {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.remove(this);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean value) {
        isEnabled = value;
    }

    public GameObjectTag getGameObjectTag() {
        return gameObjectTag;
    }

    public void onUpdate(final double deltaTime) {
        if(isEnabled) {
            update(deltaTime);
        }
    }

    public void onRender(final Graphics graphicsDevice) {
        if(isEnabled) {
            render(graphicsDevice);
        }
    }

    public Transform getTransform() {
        return transform;
    }

    protected abstract void update(final double deltaTime);
    protected abstract void render(final Graphics graphicsDevice);
}
