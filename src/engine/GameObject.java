package engine;

import engine.systems.GameObjectHandlerSystem;

import java.awt.*;
import java.util.Objects;

public abstract class GameObject {

    private boolean isEnabled = true;
    private Transform transform = null;
    private GameObjectTag gameObjectTag = null;
    private String name;
    private static long id = 0;

    private RenderingLayer renderLayer;

    public GameObject() {
        //Create unique tag per Class type
        String classId = String.valueOf(this);
        String[] parts = classId.split("@");
        gameObjectTag = new GameObjectTag(parts[0]);
        transform = new Transform();
        name = "GameObject" + id;
        ++id;

        renderLayer = new RenderingLayer(0);
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return isEnabled == that.isEnabled &&
                transform.equals(that.transform) &&
                gameObjectTag.equals(that.gameObjectTag) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnabled, transform, gameObjectTag, name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        gameObjectHandlerSystem.add(this);
        this.name = name;
    }

    public GameObjectTag getGameObjectTag() {
        return gameObjectTag;
    }

    public RenderingLayer getRenderLayer() {
        return renderLayer;
    }

    public void setRenderLayer(RenderingLayer renderLayer) {
        this.renderLayer = renderLayer;
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

    public static GameObject find(String name) {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.get().getSystem(GameObjectHandlerSystem.getSystemId());
        return gameObjectHandlerSystem.find(name);
    }


    public Transform getTransform() {
        return transform;
    }

    protected abstract void update(final double deltaTime);
    protected abstract void render(final Graphics graphicsDevice);
}
