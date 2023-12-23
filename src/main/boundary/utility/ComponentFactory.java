package src.main.boundary.utility;

import src.main.boundary.GUIConfig;

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

        return component;
    }

    public static Component createLabel(String text) {
        JLabel label = new JLabel(text);

        label.setFont(GUIConfig.DefaultFont);

        return label;
    }
}
