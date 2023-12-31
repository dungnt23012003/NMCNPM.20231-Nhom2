package src.main.boundary.renderer;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.ListSeparator;
import src.main.boundary.utility.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ListRenderer {
    int listComponentSpacing = 10;
    int titleListSpacing = 5;
    int listArc = 10;
    Insets listInsets = new Insets(10, 10, 10, 10);
    boolean isScrollPaneWrapped = false;

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

    public void setScrollPaneWrapped(boolean scrollPaneWrapped) {
        isScrollPaneWrapped = scrollPaneWrapped;
    }

    public Component getRenderedComponent(ListRenderable list) {
        JPanel component = new JPanel();
        component.setBackground(GUIConfig.FeatureViewColor);
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
        if (componentList.isEmpty()) {
            return component;
        }

        for (int i = 0; i < componentList.size() - 1; i++) {
            Component listItem = componentList.get(i);

            listPanel.add(listItem);
            listPanel.add(Box.createVerticalStrut(listComponentSpacing));
            listPanel.add(new ListSeparator());
            listPanel.add(Box.createVerticalStrut(listComponentSpacing));
        }
        listPanel.add(componentList.get(componentList.size() - 1));

        if (isScrollPaneWrapped) {
            JPanel listPanelWrapper = new JPanel();
            listPanelWrapper.setLayout(new BoxLayout(listPanelWrapper, BoxLayout.PAGE_AXIS));
            listPanelWrapper.setBackground(GUIConfig.FeatureViewColor);
            listPanelWrapper.add(listPanel);

            JScrollPane scrollPane = new JScrollPane(listPanelWrapper);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getVerticalScrollBar().setBackground(ColorUtility.darken(GUIConfig.FeatureViewColor, 1.4));
            scrollPane.getVerticalScrollBar().setUnitIncrement(8);
            scrollPane.setAlignmentX(0.0f);

            component.add(scrollPane);
            return component;
        }

        component.add(listPanel);

        return component;
    }
}
