package src.main.boundary.utility;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.GUIConfig;
import src.main.boundary.editor.EditorComponent;

import javax.swing.*;
import java.awt.*;

public class ComponentFactory {
    public static Component createFormComponent(String field, String value) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.LINE_AXIS));
        component.setBackground(new Color(0, 0, 0, 0));

        JLabel fieldLabel = new JLabel(field);
        fieldLabel.setFont(GUIConfig.DefaultFont);
        component.add(fieldLabel);

        component.add(Box.createRigidArea(new Dimension(GUIConfig.FormMinSpace, 0)));
        component.add(Box.createHorizontalGlue());

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(GUIConfig.FormValueColor);
        valueLabel.setFont(GUIConfig.DefaultFont);
        component.add(valueLabel);

        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConfig.ListLabelHeight));

        return component;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);

        label.setFont(GUIConfig.DefaultFont);

        return label;
    }

    public static JButton createDefaultButton() {
        JButton button = new JButton();
        button.setFont(GUIConfig.DefaultFont);
        button.setFocusable(false);

        return button;
    }

    public static JButton createMenuBarButton() {
        JButton button = new JButton();
        button.setMargin(new Insets(5, 5, 5, 5));
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setBackground(GUIConfig.MenuBarBackground);

        return button;
    }

    public static JButton createStickyMenuBarButton() {
        JButton button = createMenuBarButton();
        button.addActionListener(e -> {
            Color highlightColor = ColorUtility.darken(GUIConfig.MenuBarBackground, 10);
            if (button.getBackground().equals(highlightColor))
                button.setBackground(GUIConfig.MenuBarBackground);
            else
                button.setBackground(highlightColor);
        });

        return button;
    }
}
