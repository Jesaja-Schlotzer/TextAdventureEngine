package engine.scenes;


import components.SpriteRenderer;
import components.Spritesheet;
import data.AssetPool;
import data.FileHandler;
import engine.Camera;
import engine.GameObject;
import engine.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import textHandler.Font;

import java.util.Arrays;


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

        this.camera = new Camera(new Vector2f(0, 0));

        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");


        obj1 = new GameObject("Object 1", new Transform(new Vector2f(25, 25), new Vector2f(100, 100)));
        obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(175, 25), new Vector2f(100, 100)));
        obj2.addComponent(new SpriteRenderer(sprites.getSprite(7)));
        this.addGameObjectToScene(obj2);


        GameObject leftBottom = new GameObject("Object 3", new Transform(new Vector2f(0, 0), new Vector2f(10, 10)));
        leftBottom.addComponent(new SpriteRenderer(new Vector4f(0.3f, 1, 0.4f, 1)));
        this.addGameObjectToScene(leftBottom);

        GameObject rightBottom = new GameObject("Object 4", new Transform(new Vector2f(374, 0), new Vector2f(10, 10)));
        rightBottom.addComponent(new SpriteRenderer(new Vector4f(0.3f, 1, 0.4f, 1)));
        this.addGameObjectToScene(rightBottom);

        GameObject leftTop = new GameObject("Object 3", new Transform(new Vector2f(0, 206), new Vector2f(10, 10)));
        leftTop.addComponent(new SpriteRenderer(new Vector4f(0.3f, 1, 0.4f, 1)));
        this.addGameObjectToScene(leftTop);

        GameObject rightTop = new GameObject("Object 4", new Transform(new Vector2f(374, 206), new Vector2f(10, 10)));
        rightTop.addComponent(new SpriteRenderer(new Vector4f(0.3f, 1, 0.4f, 1)));
        this.addGameObjectToScene(rightTop);

        Font font = new Font(Font.standardPath.apply("pixelFont.font"));

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
