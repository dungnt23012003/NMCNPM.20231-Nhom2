package src.main.boundary.sidebar;

import src.main.boundary.GUIConfig;

import javax.swing.*;

public abstract class Sidebar extends JPanel {
    public Sidebar() {
        setBackground(GUIConfig.SideBarColor);
    }

    public abstract void add(JComponent component);
    public abstract void setComponentSpacing(int spacing);
}
