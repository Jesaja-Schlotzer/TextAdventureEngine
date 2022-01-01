package components.nods;

import engine.Transform;
import renderer.Renderable;

public class UINode extends Node implements Renderable {
    public Transform transform;


    public UINode(String name, Transform transform) {
        super(name);
        this.transform = transform;
    }





    @Override
    public void render() {

    }

    @Override
    public void dirty() {

    }

    @Override
    public void clean() {

    }

    @Override
    public boolean isDirty() {
        return false;
    }
}
