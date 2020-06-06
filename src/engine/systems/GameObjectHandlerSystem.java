package engine.systems;
import engine.Engine;
import engine.GameObject;
import engine.GameObjectTag;
import engine.systems.System;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class GameObjectHandlerSystem extends System {

    private HashMap<GameObjectTag, LinkedList<GameObject>> mGameObjectsMap;
    private static int mSystemId = "GameObjectHandler".hashCode();

    public static int GetSystemId() {
        return mSystemId;
    }

    public GameObjectHandlerSystem() {
        super(mSystemId);
        mGameObjectsMap = new HashMap<GameObjectTag, LinkedList<GameObject>>();
    }
    
    public void Add(GameObject gameObject) {
        LinkedList<GameObject> gameObjectArray = mGameObjectsMap.get(gameObject.GetGameObjectTag());
        if(gameObjectArray == null) {
            gameObjectArray = new LinkedList<GameObject>();
            mGameObjectsMap.put(gameObject.GetGameObjectTag(), gameObjectArray);
        }
        gameObjectArray.add(gameObject);
    }

    public void Remove(GameObject gameObject) {
        LinkedList<GameObject> gameObjectArray = mGameObjectsMap.get(gameObject.GetGameObjectTag());
        if(gameObjectArray == null) {
           return;
        }
        gameObjectArray.remove(gameObject);
        if(gameObjectArray.isEmpty()) {
            mGameObjectsMap.remove(gameObject.GetGameObjectTag());
        }
    }

    public void Update(final double deltaTime) {
        for( LinkedList<GameObject> gameObjectArray : mGameObjectsMap.values()) {
            for(GameObject gameObject : gameObjectArray) {
                gameObject.onUpdate(deltaTime);
            }
        }
    }

    public void Render(final Graphics graphicsDevice) {
        for( LinkedList<GameObject> gameObjectArray : mGameObjectsMap.values()) {
            for(GameObject gameObject : gameObjectArray) {
                gameObject.onRender(graphicsDevice);
            }
        }
    }
}
