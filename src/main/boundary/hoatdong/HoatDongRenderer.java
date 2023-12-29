package src.main.boundary.hoatdong;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.menubar.MenuBarDeleteButton;
import src.main.boundary.renderer.EntityRenderer;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.renderer.Renderable;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoatDongRenderer implements EntityRenderer, ListSelectionListener, ActionListener {
    HoatDongController controller;
    JPanel subPanel;
    MultiListRenderer renderer;
    HoatDongEditor editor;
    boolean isEditing;
    HoatDongAdapter currentItem;
    JButton settingButton;

    public HoatDongRenderer(HoatDongController controller) {
        this.controller = controller;
    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        isEditing = false;

        FeatureView renderedComponent = new FeatureView();
        renderedComponent.setLayout(new BoxLayout(renderedComponent, BoxLayout.PAGE_AXIS));

        src.main.boundary.menubar.MenuBar menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        renderedComponent.add(menuBar);
        renderedComponent.add(Box.createVerticalStrut(10));

        menuBar.addButton(GUIConfig.AddIcon, e -> controller.add());

        if (item instanceof HoatDongAdapter castedItem) {
            currentItem = castedItem;

            settingButton = ComponentFactory.createStickyMenuBarButton();
            settingButton.setIcon(GUIConfig.SettingIcon);
            settingButton.addActionListener(this);
            menuBar.add(settingButton);
            menuBar.add(new MenuBarDeleteButton(e -> controller.delete(castedItem)));

            subPanel = new JPanel(new CardLayout());
            subPanel.setAlignmentX(0.0f);

            renderer = new MultiListRenderer();
            subPanel.add(renderer.getRenderedComponent(item));

            renderedComponent.add(subPanel);
            controller.model.galleryController.getView().getSideList().addListSelectionListener(this);
        } else {
            renderedComponent.add(ComponentFactory.createLabel("Không có hộ khẩu nào được chọn."));
        }

        return renderedComponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isEditing = !isEditing;
        subPanel.removeAll();

        if (isEditing) {
            editor = new HoatDongEditor(currentItem, controller, settingButton);
            subPanel.add(editor);
        } else {
            currentItem.resetString();
            controller.model.galleryController.getView().repaint();
            if (currentItem.isNew) {
                controller.model.getCurrentListModel().removeElement(currentItem);
                return;
            }
            subPanel.add(renderer.getRenderedComponent(currentItem));
        }
        subPanel.revalidate();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (isEditing)
            settingButton.doClick();
    }
}
