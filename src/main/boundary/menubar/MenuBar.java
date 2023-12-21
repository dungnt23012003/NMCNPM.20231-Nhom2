package src.main.boundary.menubar;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JPanel {
    public MenuBar() {
        setBackground(GUIConfig.FeatureViewColor);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    public void add(JComponent component) {
        component.setAlignmentY(0.5f);
        super.add(component);
    }

    public void addButton(ImageIcon icon, ActionListener listener) {
        JButton button = new JButton(icon);
        button.setMargin(new Insets(5, 5, 5, 5));
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setBackground(GUIConfig.FeatureViewColor);
        button.addActionListener(listener);

        add(button);
    }
}
