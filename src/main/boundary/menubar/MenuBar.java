package src.main.boundary.menubar;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JPanel {
    public MenuBar() {
        setBackground(GUIConfig.MenuBarBackground);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        setBorder(new FlatLineBorder(new Insets(1, 1, 1, 1), GUIConfig.MenuBarBackground, 1, 10));
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
        button.setBackground(GUIConfig.MenuBarBackground);
        button.addActionListener(listener);

        add(button);
    }
}
