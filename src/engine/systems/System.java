package engine.systems;
public abstract class System {

    protected int id;

    public System(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
