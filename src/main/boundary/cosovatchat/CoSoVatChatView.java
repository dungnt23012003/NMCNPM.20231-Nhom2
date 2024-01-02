package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.renderer.ListRenderer;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoSoVatChatView extends FeatureView {
    CoSoVatChatControl control;
    MenuBar menuBar;
    ListRenderer renderer = new ListRenderer();
    ArrayList<CoSoVatChat> currentList;
    DefaultRenderableList currentComponentList;
    Component list;

    public CoSoVatChatView(CoSoVatChatControl control) {
        this.control = control;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentY(0.0f);

        menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        menuBar.addButton(GUIConfig.AddIcon, e -> addButtonClicked());

        renderer.setScrollPaneWrapped(true);

        setupRenderUI();
    }

    // View section
    private void setupRenderUI() {
        add(menuBar);
        add(Box.createVerticalStrut(10));

        currentList = control.getList();
        currentComponentList = new CoSoVatChatListAdapter(currentList, control);

        list = renderer.getRenderedComponent(currentComponentList);
        add(list);
    }

    public void refreshUI() {
        removeAll();
        setupRenderUI();
        revalidate();
    }

    // Controller section
    public void addButtonClicked() {
        cancelButtonClicked();
        if (currentList.isEmpty()) {
            currentComponentList.getComponentList().clear();
        }
        currentComponentList.getComponentList().add(0, new CoSoVatChatComponent(new CoSoVatChat(), control, true, this));

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

        list = renderer.getRenderedComponent(new CoSoVatChatListAdapter(currentList, control));
        add(list);
        revalidate();
    }
}
