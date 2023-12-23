package src.main.boundary.renderer;

import src.main.boundary.ListRenderable;
import src.main.boundary.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MultiListRenderer extends JPanel implements EntityRenderer {
    int listSpacing = 15;

    public void setListSpacing(int listSpacing) {
        this.listSpacing = listSpacing;
    }

    public Component getRenderedComponent(Renderable multiList) {
        if (multiList instanceof MultiListRenderable castedMultiList) {
            JPanel component = new JPanel();
            component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

            ListRenderer listRenderer = new ListRenderer();

            for (ListRenderable list : castedMultiList.getLists()) {
                Component renderedList = listRenderer.getRenderedComponent(list);

                component.add(renderedList);
                component.add(Box.createVerticalStrut(listSpacing));
            }

            return component;
        }
        else return ComponentFactory.createLabel("This renderer does not support the specified renderable.");
    }
}
