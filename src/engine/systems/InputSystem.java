package engine.systems;
import engine.GameObject;
import engine.GameObjectTag;
import engine.InputListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class InputSystem extends System implements KeyListener {

    private HashMap<Integer, LinkedList<InputListener>> mGameObjectsInputMap;
    private static int mSystemId = "InputSystem".hashCode();

    public static int GetSystemId() {
        return mSystemId;
    }

    public InputSystem() {
        super(mSystemId);
        mGameObjectsInputMap = new HashMap<Integer, LinkedList<InputListener>>();
    }

    public void AddListener(InputListener listener, Integer event) {
        LinkedList<InputListener> inputListeners = mGameObjectsInputMap.get(event);
        if(inputListeners == null) {
            inputListeners = new LinkedList<InputListener>();
        }
        if(!inputListeners.contains(listener)) {
            inputListeners.add(listener);
        }
    }

    public void RemoveListenerFromAllEvents(InputListener listener) {
        for (HashMap.Entry<Integer, LinkedList<InputListener>> entry : mGameObjectsInputMap.entrySet()) {
            RemoveListener(listener, entry.getKey());
        }
    }

    public void RemoveListener(InputListener listener, Integer event) {
        LinkedList<InputListener> inputListeners = mGameObjectsInputMap.get(event);
        if(inputListeners == null) {
            return;
        }
        final int index = inputListeners.indexOf(listener);
        if(index != -1) {
            inputListeners.remove(listener);
        }

        if(inputListeners.isEmpty()) {
            mGameObjectsInputMap.remove(inputListeners);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        LinkedList<InputListener> inputListeners = mGameObjectsInputMap.get(e);
        if(inputListeners != null) {
            for(InputListener inputListener : inputListeners) {
                inputListener.KeyPressed(e.getKeyCode());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        LinkedList<InputListener> inputListeners = mGameObjectsInputMap.get(e);
        if(inputListeners != null) {
            for(InputListener inputListener : inputListeners) {
                inputListener.KeyReleased(e.getKeyCode());
            }
        }
    }
}
