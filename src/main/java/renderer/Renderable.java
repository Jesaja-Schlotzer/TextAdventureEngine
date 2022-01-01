package renderer;

public interface Renderable {

    void render();

    void dirty();
    void clean();
    boolean isDirty();
}
