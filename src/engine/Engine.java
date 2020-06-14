package engine;

import engine.systems.System;
import java.util.HashMap;

public class Engine {
    private HashMap<Integer, System> mSystems;
    private static Engine mInstance;

    public static Engine Get() {
        if(mInstance == null) {
            mInstance = new Engine();
        }
        return mInstance;
    }

    private Engine() {
        mSystems = new HashMap<Integer, System>();
    }
    public <T extends System> T GetSystem(final int id) {
       System system = mSystems.get(id);
       assert (system != null) : "System must be not null!";
       return (T) system;
    }

    public <T extends System> void AddSystem(System system) {
        if (!mSystems.containsKey(system.GetId())) {
            mSystems.put(system.GetId(), system);
        }
    }

    public void RemoveSystem(final int id) {
        if (mSystems.containsKey(id)) {
            mSystems.remove(id);
        }
    }

    public void RemoveAllSystems() {
        mSystems.clear();
    }
}
