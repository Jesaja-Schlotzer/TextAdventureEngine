package data;

import components.Spritesheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code AssetPool} provides methods to efficiently load and use resources.
 */
public class AssetPool {
    private static final Map<String, Shader> shaderPool = new HashMap<>();
    private static final Map<String, Texture> texturePool = new HashMap<>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<>();

    /**
     * Returns the wanted {@code Shader} object. If not already loaded, the {@code Shader} is first loaded and then returned.
     * @param resourcePath the name of the wanted shader
     * @return the wanted shader
     */
    public static Shader getShader(String resourcePath) {
        File file = new File(resourcePath);
        if (AssetPool.shaderPool.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaderPool.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(resourcePath);
            shader.compile();
            AssetPool.shaderPool.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    /**
     * Returns the wanted {@code Texture} object. If not already loaded, the {@code Texture} is first loaded and then returned.
     * @param resourcePath the name of the wanted {@code Texture}
     * @return the wanted {@code Texture}
     */
    public static Texture getTexture(String resourcePath) {
        File file = new File(resourcePath);
        if (AssetPool.texturePool.containsKey(file.getAbsolutePath())) {
            return AssetPool.texturePool.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture(resourcePath);
            AssetPool.texturePool.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }


    public static void addSpritesheet(String resourceName, Spritesheet spritesheet) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }


    public static Spritesheet getSpritesheet(String resourceName) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "Error: Tried to access spritesheet '" + resourceName + "' and it has not been added to asset pool.";
        }
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }
}