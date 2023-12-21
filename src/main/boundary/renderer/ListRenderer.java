package src.main.boundary.renderer;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.ListRenderable;
import src.main.boundary.ListSeparator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ListRenderer {
    int listComponentSpacing = 10;
    int titleListSpacing = 5;
    int listArc = 10;
    Insets listInsets = new Insets(10, 10, 10, 10);

    public void setListArc(int listArc) {
        this.listArc = listArc;
    }

    public void setListInsets(Insets listInsets) {
        this.listInsets = listInsets;
    }

    public void setListComponentSpacing(int listComponentSpacing) {
        this.listComponentSpacing = listComponentSpacing;
    }

    public void setTitleListSpacing(int titleListSpacing) {
        this.titleListSpacing = titleListSpacing;
    }

    public Component getRenderedComponent(ListRenderable list) {
        JPanel component = new JPanel();

        component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

        JLabel titleLabel = new JLabel(list.getTitle());
        titleLabel.setAlignmentX(0);
        titleLabel.setFont(GUIConfig.ListTitleFont);
        component.add(titleLabel);
        component.add(Box.createVerticalStrut(titleListSpacing));

        JPanel listPanel = new JPanel();
        listPanel.setAlignmentX(0);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listPanel.setBackground(GUIConfig.MyListBackground);
        listPanel.setBorder(new FlatLineBorder(listInsets, GUIConfig.MyListBackground, 0, listArc));

        listPanel.add(Box.createHorizontalGlue());

        ArrayList<Component> componentList = list.getComponentList();
        for (int i = 0; i < componentList.size() - 1; i++) {
            Component listItem = componentList.get(i);

            listPanel.add(listItem);
            listPanel.add(Box.createVerticalStrut(listComponentSpacing));
            listPanel.add(new ListSeparator());
            listPanel.add(Box.createVerticalStrut(listComponentSpacing));
        }
        listPanel.add(componentList.getLast());

        component.add(listPanel);

        return component;
    }
}
