package src.main.boundary.feature;

import src.main.boundary.CustomJList;
import src.main.boundary.GUIConfig;
import src.main.boundary.ListItemizable;
import src.main.boundary.SearchField;
import src.main.boundary.sidebar.BoxSidebar;
import src.main.boundary.sidebar.Sidebar;
import src.main.boundary.utility.ColorUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// TODO
public class ListSideFeatureView extends FeatureView {
    Sidebar sidebar;
    JPanel mainView;

    public ListSideFeatureView(ListModel<ListItemizable> listModel) {
        setupUI(listModel);
    }

    public void setupUI(ListModel<ListItemizable> listModel) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        sidebar = new BoxSidebar();
        sidebar.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));

        sidebar.setBorder(new EmptyBorder(10, 10, 10, 10));
        sidebar.setComponentSpacing(5);

        SearchField textField = new SearchField();
        textField.setBackground(ColorUtility.darken(GUIConfig.SideBarColor, 7));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        textField.setFont(GUIConfig.DefaultFont);

        sidebar.add(textField);

        CustomJList<ListItemizable> customJList = new CustomJList<>(listModel);
        customJList.setBackground(GUIConfig.SideBarColor);
        customJList.setSelectedIndex(0);

        JScrollPane scrollPane = new JScrollPane(customJList);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(ColorUtility.darken(GUIConfig.SideBarColor, 1.5));
        sidebar.add(scrollPane);

        mainView = new JPanel();
        mainView.setLayout(new BoxLayout(mainView, BoxLayout.LINE_AXIS));

        add(sidebar);
        add(mainView);
    }
}
