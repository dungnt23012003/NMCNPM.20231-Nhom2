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

public class PhongBanView extends FeatureView {
    PhongBanControl control;
    MenuBar menuBar;
    JButton settingButton;
    ListRenderer renderer = new ListRenderer();
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

        currentComponentList = new PhongBanListAdapter(control.getList(), control);
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
        DefaultRenderableList addedComponentList = currentComponentList;
        addedComponentList.getComponentList().add(0, new PhongBanComponent(new PhongBan(), control, true, this));

        removeAll();
        add(menuBar);
        add(Box.createVerticalStrut(10));

        list = renderer.getRenderedComponent(addedComponentList);
        add(list);
        revalidate();
    }

    public void cancelButtonClicked() {
        refreshUI();
    }
}
