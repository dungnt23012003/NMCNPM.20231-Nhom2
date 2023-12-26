package src.main.boundary.list;

import src.main.boundary.renderer.Renderable;

import java.util.ArrayList;

public interface MultiListRenderable extends Renderable {
    ArrayList<ListRenderable> getRenderableLists();
}
