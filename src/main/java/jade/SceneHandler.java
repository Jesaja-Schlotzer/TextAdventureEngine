package jade;

public class SceneHandler {

    public static SceneHandler instance;

    public static SceneHandler getInstance() {
        if (instance == null) {
            instance = new SceneHandler();
        }
        return instance;
    }


    private Scene currentScene;

    private SceneHandler() {
        currentScene = new TestScene();
        currentScene.init();
    }


    public Scene getCurrentScene() {
        return currentScene;
    }


    public void changeScene() {

    }
}
