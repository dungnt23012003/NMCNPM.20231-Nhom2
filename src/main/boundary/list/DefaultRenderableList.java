package src.main.boundary.list;

import java.awt.*;
import java.util.ArrayList;

public class DefaultRenderableList implements ListRenderable{
    String title = "";
    ArrayList<Component> componentList = new ArrayList<>();

    public void addComponent(Component component) {
        componentList.add(component);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ArrayList<Component> getComponentList() {
        return componentList;
    }
}
