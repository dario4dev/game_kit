package engine;

import engine.systems.GameObjectHandlerSystem;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class GameObject {

    private boolean mIsEnabled = true;
    private Transform mTransform = null;
    private GameObjectTag mGameObjectTag = null;

    public GameObject() {
        //Create unique tag per Class type
        String classId = String.valueOf(this);
        String[] parts = classId.split("@");
        mGameObjectTag = new GameObjectTag(parts[0]);

        mTransform = new Transform();
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.Get().GetSystem(GameObjectHandlerSystem.GetSystemId());
        gameObjectHandlerSystem.Add(this);
    }

    protected void finalize()
    {
        GameObjectHandlerSystem gameObjectHandlerSystem = Engine.Get().GetSystem(GameObjectHandlerSystem.GetSystemId());
        gameObjectHandlerSystem.Remove(this);
    }

    public boolean isEnabled() {
        return mIsEnabled;
    }

    public void setEnabled(boolean value) {
        mIsEnabled = value;
    }

    public GameObjectTag GetGameObjectTag() {
        return mGameObjectTag;
    }

    public void onUpdate(final double deltaTime) {
        if(mIsEnabled) {
            update(deltaTime);
        }
    }

    public void onRender(final Graphics graphicsDevice) {
        if(mIsEnabled) {
            render(graphicsDevice);
        }
    }

    protected abstract void update(final double deltaTime);
    protected abstract void render(final Graphics graphicsDevice);
}
