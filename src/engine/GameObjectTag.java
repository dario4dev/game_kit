package engine;

public class GameObjectTag {
    private final int tag;

    public GameObjectTag(final String classInfo) {
        tag = classInfo.hashCode();
    }

    int getTag() {
       return tag;
    }
}
