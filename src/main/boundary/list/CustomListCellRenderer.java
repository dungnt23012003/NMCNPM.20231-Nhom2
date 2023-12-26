package src.main.boundary.list;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class CustomListCellRenderer<E> implements javax.swing.ListCellRenderer<E> {
    @Override
    public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));
        component.setBackground(new Color(0, 0, 0, 0));
        component.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(0, 0, 0, 0), 1, 10));

        JLabel valueLabel = ComponentFactory.createLabel(value.toString());
        component.add(valueLabel);

        if (isSelected) {
            component.setBackground(GUIConfig.ListSelectionBackground);
            valueLabel.setForeground(Color.WHITE);
        }

        return component;
    }
}
