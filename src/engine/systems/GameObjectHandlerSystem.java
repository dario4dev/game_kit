package engine.systems;
import engine.GameObject;
import engine.GameObjectTag;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameObjectHandlerSystem extends System {

    private Map<GameObjectTag, List<GameObject>> gameObjectsMap;
    private static int systemId = "GameObjectHandler".hashCode();

    public static int getSystemId() {
        return systemId;
    }

    public GameObjectHandlerSystem() {
        super(systemId);
        gameObjectsMap = new HashMap<GameObjectTag, List<GameObject>>();
    }
    
    public void add(GameObject gameObject) {
        List<GameObject> gameObjectArray = gameObjectsMap.get(gameObject.getGameObjectTag());
        if(gameObjectArray == null) {
            gameObjectArray = new ArrayList<GameObject>();
            gameObjectsMap.put(gameObject.getGameObjectTag(), gameObjectArray);
            gameObjectArray.add(gameObject);
        } else if(!gameObjectArray.contains(gameObject)) {
            gameObjectArray.add(gameObject);
        }
    }

    public void remove(GameObject gameObject) {
        List<GameObject> gameObjectArray = gameObjectsMap.get(gameObject.getGameObjectTag());
        if(gameObjectArray == null) {
           return;
        }

        gameObjectArray.remove(gameObject);

        if(gameObjectArray.isEmpty()) {
            gameObjectsMap.remove(gameObject.getGameObjectTag());
        }
    }

    public void update(final double deltaTime) {
        for(List<GameObject> gameObjectArray : gameObjectsMap.values()) {
            for(GameObject gameObject : gameObjectArray) {
                gameObject.onUpdate(deltaTime);
            }
        }
    }

    public void render(final Graphics graphicsDevice) {
        for(List<GameObject> gameObjectArray : gameObjectsMap.values()) {
            for(GameObject gameObject : gameObjectArray) {
                gameObject.onRender(graphicsDevice);
            }
        }
    }
}
