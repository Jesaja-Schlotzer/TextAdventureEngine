package engine.scenes;


import components.SpriteRenderer;
import components.Spritesheet;
import data.AssetPool;
import engine.Camera;
import engine.GameObject;
import engine.Transform;
import engine.io.KeyListener;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;


public class TestScene extends Scene {

    private GameObject obj1;
    private Spritesheet sprites;


    @Override
    public void init() {
        super.init();

        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));

        this.camera = new Camera(new Vector2f(-250, 0));

        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2);
        float totalHeight = (float)(300 - yOffset * 2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;
        float padding = 3;

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                float xPos = xOffset + (x * sizeX) + (padding * x);
                float yPos = yOffset + (y * sizeY) + (padding * y);
                System.out.println(xPos + "  " +  yPos);
                GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(xPos, yPos), new Vector2f(256, 256)));
                obj2.addComponent(new SpriteRenderer(sprites.getSprite(7)));
                this.addGameObjectToScene(obj2);
            }
        }

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        obj2.addComponent(new SpriteRenderer(sprites.getSprite(7)));
        this.addGameObjectToScene(obj2);

        GameObject obj3 = new GameObject("Object 3", new Transform(new Vector2f(700, 100), new Vector2f(256, 256)));
        obj3.addComponent(new SpriteRenderer(new Vector4f(0, 1, 1, 1)));
        this.addGameObjectToScene(obj3);

    }


    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update(float deltaTime) {
        spriteFlipTimeLeft -= deltaTime;
        if (spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if (spriteIndex > 3) {
                spriteIndex = 0;
            }
            obj1.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }

        for (GameObject go : this.gameObjects) {
            go.update(deltaTime);
        }

        this.renderer.render();
    }
}
