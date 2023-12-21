package src.main.boundary.renderer;

import src.main.boundary.ListRenderable;
import src.main.boundary.MultiListRenderable;

import javax.swing.*;
import java.awt.*;

public class MultiListRenderer extends JPanel {
    int listSpacing = 15;

    public void setListSpacing(int listSpacing) {
        this.listSpacing = listSpacing;
    }

    public Component getRenderedComponent(MultiListRenderable multiList) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

        ListRenderer listRenderer = new ListRenderer();

        for (ListRenderable list : multiList.getLists()) {
            Component renderedList = listRenderer.getRenderedComponent(list);

            component.add(renderedList);
            component.add(Box.createVerticalStrut(listSpacing));
        }

        return component;
    }
}
