package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.control.PhongBanControl;

import javax.swing.*;
import java.awt.*;

public class PhongBanView extends FeatureView {
    PhongBanControl control;
    MenuBar menuBar;
    JButton settingButton;
    ListRenderer renderer = new ListRenderer();

    public PhongBanView(PhongBanControl control) {
        this.control = control;

        setupUI();
    }

    // View
    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentY(0.0f);

        menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        add(menuBar);
        add(Box.createVerticalStrut(10));

        menuBar.addButton(GUIConfig.AddIcon, e -> addButtonClicked());
        settingButton = ComponentFactory.createStickyMenuBarButton();
        settingButton.setIcon(GUIConfig.SettingIcon);
        settingButton.addActionListener(e -> settingButtonClicked());
        menuBar.add(settingButton);

        Component list = renderer.getRenderedComponent(new PhongBanListAdapter(control.getList()));
        add(list);
    }

    public void refreshUI() {

    }

    // Controller
    public void addButtonClicked() {

    }

    public void settingButtonClicked() {

    }
}
