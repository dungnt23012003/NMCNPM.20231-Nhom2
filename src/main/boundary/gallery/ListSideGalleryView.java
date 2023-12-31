package src.main.boundary.gallery;

import src.main.boundary.list.CustomJList;
import src.main.boundary.GUIConfig;
import src.main.boundary.SearchField;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.list.CustomListCellRenderer;
import src.main.boundary.renderer.DefaultEntityRenderer;
import src.main.boundary.renderer.EntityRenderer;
import src.main.boundary.sidebar.BoxSidebar;
import src.main.boundary.sidebar.Sidebar;
import src.main.boundary.utility.ColorUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// TODO
public class ListSideGalleryView extends FeatureView implements ListSelectionListener {
    ListSideGalleryController controller;
    GalleryModel model;
    EntityRenderer renderer = new DefaultEntityRenderer();

    Sidebar sidebar;
    JPanel mainView;
    CustomJList<GalleryItem> sideList;

    public ListSideGalleryView(ListSideGalleryController controller, GalleryModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        sidebar = new BoxSidebar();
        sidebar.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
        sidebar.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));

        sidebar.setBorder(new EmptyBorder(10, 10, 10, 10));
        sidebar.setComponentSpacing(5);

        SearchField textField = new SearchField();
        textField.setBackground(ColorUtility.darken(GUIConfig.SideBarColor, 7));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        textField.setFont(GUIConfig.DefaultFont);

        sidebar.add(textField);

        sideList = new CustomJList<>(model.getNewListModel());
        sideList.setBackground(GUIConfig.SideBarColor);
        sideList.setSelectedIndex(0);
        sideList.addListSelectionListener(this);
        sideList.setCellRenderer(new CustomListCellRenderer<>());

        JScrollPane scrollPane = new JScrollPane(sideList);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(ColorUtility.darken(GUIConfig.SideBarColor, 1.4));
        sidebar.add(scrollPane);

        mainView = new JPanel();
        mainView.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainView.setBackground(GUIConfig.FeatureViewColor);
        mainView.setLayout(new BoxLayout(mainView, BoxLayout.PAGE_AXIS));
        mainView.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        add(sidebar);
        add(mainView);

        controller.setSelectedValue(sideList.getSelectedValue());
    }

    public void setRenderer(EntityRenderer renderer) {
        this.renderer = renderer;
        controller.setSelectedValue(sideList.getSelectedValue());
    }

    public EntityRenderer getRenderer() {
        return renderer;
    }

    public void clearSelection() {
        sideList.setSelectedIndex(-1);
    }

    public int getSelectedIndex() {
        return sideList.getSelectedIndex();
    }

    public void setSelectedIndex(int index) {
        sideList.setSelectedIndex(index);
    }

    public CustomJList<GalleryItem> getSideList() {
        return sideList;
    }

    public void refreshSideBar() {
        sideList.setModel(model.getNewListModel());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            controller.setSelectedValue(sideList.getSelectedValue());
        }
    }
}
