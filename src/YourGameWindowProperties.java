public class YourGameWindowProperties implements IGameWindowProperties {

    @Override
    public int getNumBuffers() {
        return 3;
    }

    @Override
    public int getWidth() {
        return 800;
    }

    @Override
    public int getHeight() {
        return 600;
    }

    @Override
    public String getTitle() {
        return "Your Game Title";
    }
}
