package components;

import engine.GameObject;

public abstract class Component {

    public GameObject gameObject = null;

    public void init() {

    }

    public abstract void update(float deltaTime);
}