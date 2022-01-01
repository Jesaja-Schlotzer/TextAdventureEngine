package renderer;

import components.SpriteRenderer;
import engine.GameObject;

import java.util.ArrayList;
import java.util.List;


public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<BatchRenderer> batches;


    public Renderer() {
        this.batches = new ArrayList<>();
    }


    public void add(GameObject gameObject) {
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if (spriteRenderer != null) {
            add(spriteRenderer);
        }
    }


    private void add(SpriteRenderer spriteRenderer) {
        boolean added = false;
        for (BatchRenderer batch : batches) {
            if (batch.hasRoom()) {
                Texture texture = spriteRenderer.getTexture();
                if (texture == null || (batch.hasTexture(texture) || batch.hasTextureRoom())) {
                    batch.addSprite(spriteRenderer);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            BatchRenderer newBatch = new BatchRenderer(MAX_BATCH_SIZE);
            newBatch.init();
            batches.add(newBatch);
            newBatch.addSprite(spriteRenderer);
        }
    }


    public void render() {
        for (BatchRenderer batch : batches) {
            batch.render();
        }
    }
}