package src.main.boundary.sidebar;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;

public class BoxSidebar extends Sidebar {
    int componentSpacing = 0;

    public BoxSidebar() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void add(JComponent component) {
        component.setAlignmentX(0.5f);
        add((Component) component);
        super.add(Box.createRigidArea(new Dimension(0, componentSpacing)));
    }

    @Override
    public void setComponentSpacing(int componentSpacing) {
        this.componentSpacing = componentSpacing;
    }
}
