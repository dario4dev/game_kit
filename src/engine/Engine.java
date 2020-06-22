package engine;

import engine.systems.System;
import java.util.HashMap;
import java.util.Map;

public class Engine {
    private Map<Integer, System> systems;
    private static Engine instance;

    public static Engine Get() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    private Engine() {
        systems = new HashMap<Integer, System>();
    }
    public <T extends System> T getSystem(final int id) {
       System system = systems.get(id);
       assert (system != null) : "System must be not null!";
       return (T) system;
    }

    public <T extends System> void addSystem(System system) {
        if (!systems.containsKey(system.getId())) {
            systems.put(system.getId(), system);
        }
    }

    public void removeSystem(final int id) {
        if (systems.containsKey(id)) {
            systems.remove(id);
        }
    }

    public void removeAllSystems() {
        systems.clear();
    }
}
