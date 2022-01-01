package game.scenes;

import data.AssetPool;
import engine.Camera;
import engine.GameObject;
import engine.scenes.Scene;
import org.joml.Vector2f;
import screen.ScreenHandler;
import textHandler.Font;
import textHandler.TextHandler;


public class MainMenuScene extends Scene {


    public static float currentFPS;

    @Override
    public void init() {
        super.init();

        TextHandler.textFont(Font.PIXEL_FONT);

        AssetPool.getShader("assets/shaders/default.glsl");

        this.camera = new Camera(new Vector2f(0, 0));

    }

    @Override
    public void update(float deltaTime) {
        currentFPS = 1 / deltaTime;

        ScreenHandler.getInstance().draw();

        ScreenHandler.getInstance().drawScreenBuffer(this);

        for (GameObject go : this.gameObjects) {
            go.update(deltaTime);
        }

        this.renderer.render();
    }
}
