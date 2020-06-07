package engine;

public class GameObjectTag {
    private final int mTag;

    public GameObjectTag(final String classInfo) {
        mTag = classInfo.hashCode();
    }

    int GetTag() {
       return mTag;
    }
}
