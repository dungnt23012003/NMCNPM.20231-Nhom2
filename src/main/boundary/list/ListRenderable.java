package src.main.boundary.list;

import src.main.boundary.renderer.Renderable;

import java.awt.*;
import java.util.ArrayList;

public interface ListRenderable extends Renderable {
    String getTitle();
    ArrayList<Component> getComponentList();
}
