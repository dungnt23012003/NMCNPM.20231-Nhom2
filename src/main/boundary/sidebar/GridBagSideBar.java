package src.main.boundary.sidebar;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

@Deprecated
public class GridBagSideBar extends Sidebar {
    GridBagConstraints gbc;
    Component spacer = Box.createVerticalStrut(Integer.MAX_VALUE);

    public GridBagSideBar() {
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
    }

    @Override
    public void add(JComponent component) {
        add(component, gbc);
        ++gbc.gridy;
    }

    public void add(JComponent component, int fill, float weightx, float weighty) {
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        add(component);

        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    public void addSpacer() {
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(spacer, gbc);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    @Override
    public void setComponentSpacing(int spacing) {

    }
}
