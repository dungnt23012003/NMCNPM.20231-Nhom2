package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.control.PhongBanControl;
import src.main.entity.CoSoVatChat;
import src.main.entity.PhongBan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PhongBanView extends FeatureView {
    PhongBanControl control;
    MenuBar menuBar;
    ListRenderer renderer = new ListRenderer();
    ArrayList<PhongBan> currentList;
    Component list;
    DefaultRenderableList currentComponentList;

    public PhongBanView(PhongBanControl control) {
        this.control = control;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentY(0.0f);

        menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        menuBar.addButton(GUIConfig.AddIcon, e -> addButtonClicked());

        renderer.setScrollPaneWrapped(true);

        setupUI();
    }

    // View
    public void setupUI() {
        add(menuBar);
        add(Box.createVerticalStrut(10));

        currentList = control.getList();
        currentComponentList = new PhongBanListAdapter(currentList, control);

        list = renderer.getRenderedComponent(currentComponentList);
        add(list);
    }

    public void refreshUI() {
        removeAll();
        setupUI();
        revalidate();
    }

    // Controller
    public void addButtonClicked() {
        cancelButtonClicked();
        if (currentList.isEmpty()) {
            currentComponentList.getComponentList().clear();
        }
        currentComponentList.getComponentList().add(0, new PhongBanComponent(new PhongBan(), control, true, this));

        removeAll();
        add(menuBar);
        add(Box.createVerticalStrut(10));

        list = renderer.getRenderedComponent(currentComponentList);
        add(list);
        revalidate();
    }

    public void cancelButtonClicked() {
        removeAll();
        add(menuBar);
        add(Box.createVerticalStrut(10));

        list = renderer.getRenderedComponent(new PhongBanListAdapter(currentList, control));
        add(list);
        revalidate();
    }

    // Getter
    public MenuBar getMenuBar() {
        return menuBar;
    }
}
