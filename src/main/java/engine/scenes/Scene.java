package engine.scenes;

import engine.Camera;
import engine.GameObject;
import renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene() {

    }

    public void init() {
        for (GameObject go : gameObjects) {
            go.init();
            this.renderer.add(go);
        }
        isRunning = true;
    }

    public void addGameObjectToScene(GameObject go) {
        if (!isRunning) {
            gameObjects.add(go);
        } else {
            gameObjects.add(go);
            go.init();
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera() {
        return this.camera;
    }
}
