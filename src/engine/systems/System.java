package engine.systems;
public abstract class System {

    protected int mId;

    public System(final int id) {
        mId = id;
    }

    public int GetId() {
        return mId;
    }
}
