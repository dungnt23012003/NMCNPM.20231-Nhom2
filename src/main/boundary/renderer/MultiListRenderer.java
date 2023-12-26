package src.main.boundary.renderer;

import src.main.boundary.GUIConfig;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MultiListRenderer extends JPanel implements EntityRenderer {
    int listSpacing = 15;

    public void setListSpacing(int listSpacing) {
        this.listSpacing = listSpacing;
    }

    public Component getRenderedComponent(Renderable multiList) {
        if (multiList instanceof MultiListRenderable castedMultiList) {
            JPanel component = new JPanel();
            component.setBackground(GUIConfig.FeatureViewColor);
            component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

            ListRenderer listRenderer = new ListRenderer();

            ArrayList<ListRenderable> renderableLists = castedMultiList.getRenderableLists();
            for (int i = 0; i < renderableLists.size() - 1; i++) {
                ListRenderable list = renderableLists.get(i);
                Component renderedList = listRenderer.getRenderedComponent(list);

                component.add(renderedList);
                component.add(Box.createVerticalStrut(listSpacing));
            }

            component.add(listRenderer.getRenderedComponent(renderableLists.get(renderableLists.size() - 1)));

            return component;
        }
        else return ComponentFactory.createLabel("This renderer does not support the specified renderable.");
    }
}
