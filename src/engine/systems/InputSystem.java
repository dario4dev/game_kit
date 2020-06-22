package engine.systems;
import engine.InputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class InputSystem extends System implements KeyListener {

    private Map<Integer, List<InputListener>> gameObjectsInputMap;
    private static int systemId = "InputSystem".hashCode();

    public static int getSystemId() {
        return systemId;
    }

    public InputSystem() {
        super(systemId);
        gameObjectsInputMap = new HashMap<Integer, List<InputListener>>();
    }

    public void addListener(InputListener listener, Integer event) {
        List<InputListener> inputListeners = gameObjectsInputMap.get(event);
        if(inputListeners == null) {
            inputListeners = new ArrayList<InputListener>();
            inputListeners.add(listener);
            gameObjectsInputMap.put(event, inputListeners);
        } else if(!inputListeners.contains(listener)) {
            inputListeners.add(listener);
        }
    }

    public void removeListenerFromAllEvents(InputListener listener) {
        for (Map.Entry<Integer, List<InputListener>> entry : gameObjectsInputMap.entrySet()) {
            removeListener(listener, entry.getKey());
        }
    }

    public void removeListener(InputListener listener, Integer event) {
        List<InputListener> inputListeners = gameObjectsInputMap.get(event);
        if(inputListeners == null) {
            return;
        }

        inputListeners.remove(listener);

        if(inputListeners.isEmpty()) {
            gameObjectsInputMap.remove(event);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        List<InputListener> inputListeners = gameObjectsInputMap.get(e.getKeyCode());
        if(inputListeners != null) {
            for(InputListener inputListener : inputListeners) {
                inputListener.keyPressed(e.getKeyCode());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        List<InputListener> inputListeners = gameObjectsInputMap.get(e.getKeyCode());
        if(inputListeners != null) {
            for(InputListener inputListener : inputListeners) {
                inputListener.keyReleased(e.getKeyCode());
            }
        }
    }
}
