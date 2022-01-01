package components.nods;


import components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private Node parent;
    private List<Node> children;

    private String name;


    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }



    /* TODO evtl. nicht mehr nötig
    public void init() {

    }*/


    public void update(float deltaTime) {
        for (Node child : children) {
            child.update(deltaTime);
        }
    }



    public <T extends Node> T getChild(Class<T> childClass) {
        for (Node node : children) {
            if (childClass.isAssignableFrom(node.getClass())) {
                try {
                    return childClass.cast(node);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }
        return null;
    }


    public <T extends Node> void removeChild(Class<T> childClass) {
        for (int i=0; i < children.size(); i++) {
            Node node = children.get(i);
            if (childClass.isAssignableFrom(node.getClass())) {
                children.remove(i);
                return;
            }
        }
    }


    public void addChild(Node child) {
        if(child != null) {
            this.children.add(child);
            child.parent = this;
        }
    }

}
