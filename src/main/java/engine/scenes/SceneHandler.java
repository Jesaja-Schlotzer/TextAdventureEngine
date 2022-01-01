package engine.scenes;

import game.scenes.MainMenuScene;

/**
 * The {@code SceneHandler}
 */
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
        currentScene = new MainMenuScene();
        currentScene.init();
    }


    public Scene getCurrentScene() {
        return currentScene;
    }


    public void changeScene() {

    }
}
