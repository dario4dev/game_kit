package engine;

import engine.systems.GameObjectHandlerSystem;

import java.awt.*;

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

    public boolean IsEnabled() {
        return mIsEnabled;
    }

    public void SetEnabled(boolean value) {
        mIsEnabled = value;
    }

    public GameObjectTag GetGameObjectTag() {
        return mGameObjectTag;
    }

    public void OnUpdate(final double deltaTime) {
        if(mIsEnabled) {
            Update(deltaTime);
        }
    }

    public void OnRender(final Graphics graphicsDevice) {
        if(mIsEnabled) {
            Render(graphicsDevice);
        }
    }

    public Transform GetTransform() {
        return mTransform;
    }

    protected abstract void Update(final double deltaTime);
    protected abstract void Render(final Graphics graphicsDevice);
}
