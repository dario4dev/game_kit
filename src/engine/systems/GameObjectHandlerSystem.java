package engine.systems;
import engine.GameObject;
import engine.GameObjectTag;
import engine.RenderingLayer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameObjectHandlerSystem extends System {

    private Map<GameObjectTag, List<GameObject>> gameObjectsMap;
    private SortedMap<RenderingLayer, List<GameObjectTag>> gameObjectsRenderLayerOrderMap;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObjectHandlerSystem that = (GameObjectHandlerSystem) o;
        return gameObjectsMap.equals(that.gameObjectsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameObjectsMap);
    }

    private static int systemId = "GameObjectHandler".hashCode();

    public static int getSystemId() {
        return systemId;
    }

    public GameObjectHandlerSystem() {
        super(systemId);
        gameObjectsMap = new HashMap<GameObjectTag, List<GameObject>>();
        gameObjectsRenderLayerOrderMap = new TreeMap<RenderingLayer, List<GameObjectTag>>();
    }
    
    public void add(GameObject gameObject) {

        gameObjectsMap.computeIfAbsent(gameObject.getGameObjectTag(), (key) -> {
            return gameObjectsMap.put(gameObject.getGameObjectTag(), new ArrayList<GameObject>(){{add(gameObject);}});
        });

        gameObjectsMap.computeIfPresent(gameObject.getGameObjectTag(), (key, value) -> {
            value.add(gameObject);
            return value;
        });

        gameObjectsRenderLayerOrderMap.computeIfAbsent(gameObject.getRenderLayer(), (key) -> {
            return gameObjectsRenderLayerOrderMap.put(key, new ArrayList<GameObjectTag>(){{add(gameObject.getGameObjectTag());}});
        });

        gameObjectsRenderLayerOrderMap.computeIfPresent(gameObject.getRenderLayer(), (key, value)->{
            value.add(gameObject.getGameObjectTag());
            return value;
        });
    }

    public void remove(GameObject gameObject) {

        gameObjectsMap.computeIfPresent(gameObject.getGameObjectTag(), (key, value) -> {
            value.remove(gameObject);
            if(value.isEmpty()) {
                gameObjectsMap.remove(key);
                List<GameObjectTag> gameObjectTags = gameObjectsRenderLayerOrderMap.get(gameObject.getRenderLayer());
                gameObjectTags.remove(key);
            }
            return value;
        });

    }

    //This function has high complexity in time, avoid using this on update
    public GameObject find(String gameObjectName) {
        Iterator it = gameObjectsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            List<GameObject> gameObjectsArray = (List<GameObject>) pair.getValue();
            GameObject result = gameObjectsArray.stream()
                    .filter(gameObject -> gameObject.getName() == gameObjectName)
                    .findAny()
                    .orElse(null);

            if(result != null) {
                return result;
            }
        }

        return null;
    }

    public void update(final double deltaTime) {
        for(List<GameObject> gameObjectArray : gameObjectsMap.values()) {
            for(GameObject gameObject : gameObjectArray) {
                gameObject.onUpdate(deltaTime);
            }
        }
    }

    public void render(final Graphics graphicsDevice) {
        for (List<GameObjectTag> gameObjectTags : gameObjectsRenderLayerOrderMap.values()) {
            for(GameObjectTag gameObjectTag : gameObjectTags) {
                List<GameObject> gameObjectArray = gameObjectsMap.get(gameObjectTag);
                for(GameObject gameObject : gameObjectArray) {
                    gameObject.onRender(graphicsDevice);
                }
            }
        }
    }
}
