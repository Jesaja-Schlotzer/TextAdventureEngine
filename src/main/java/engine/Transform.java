package engine;

import org.joml.Vector2f;

/**
 * The {@code Transform} class holds a position vector and a scale vector,
 * to store the position and the size of a sprite.
 */
public class Transform {
    public Vector2f position;
    public Vector2f scale;


    /**
     * Constructs a standard {@code Transform} object.
     */
    public Transform() {
        this.position = new Vector2f();
        this.scale = new Vector2f();
    }


    /**
     * Constructs a {@code Transform} object with a given position.
     * @param position the position to be set
     */
    public Transform(Vector2f position) {
        this.position = position;
        this.scale = new Vector2f();
    }


    /**
     * Constructs a {@code Transform} object with a given position and scale.
     * @param position the position to be set
     * @param scale the scale to be set
     */
    public Transform(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
    }

    /**
     * Copies the position and scale of an existing {@code Transform} object.
     * @param transform the source to copy from
     */
    public Transform(Transform transform) {
        this.position = new Vector2f(transform.position);
        this.scale = new Vector2f(transform.scale);
    }


    /**
     * Copies the position and scale of this {@code Transform} object to another {@code Transform} object.
     * @param to the {@code Transform} to copy to
     */
    public void copy(Transform to) {
        to.position.set(this.position);
        to.scale.set(this.scale);
    }


    @Override
    public boolean equals(Object obj) {
        if(obj != null) {
            if(obj instanceof Transform) {
                Transform transform = (Transform) obj;
                return transform.position.equals(this.position) && transform.scale.equals(this.scale);
            }
        }
        return false;
    }
}