package src.main.boundary.hokhau;

import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.menubar.MenuBarDeleteButton;
import src.main.boundary.GUIConfig;
import src.main.boundary.renderer.EntityRenderer;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.renderer.Renderable;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoKhauRenderer implements EntityRenderer, ActionListener, ListSelectionListener {
    HoKhauController controller;
    JPanel subPanel;
    MultiListRenderer renderer;
    HoKhauEditor editor;
    boolean isEditing;
    HoKhauAdapter currentItem;
    JButton settingButton;

    public HoKhauRenderer(HoKhauController controller) {
        this.controller = controller;
    }

    public Component getRenderedComponent(HoKhauAdapter item) {
        currentItem = item;
        isEditing = false;

        FeatureView renderedComponent = new FeatureView();
        renderedComponent.setLayout(new BoxLayout(renderedComponent, BoxLayout.PAGE_AXIS));

        MenuBar menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        menuBar.addButton(GUIConfig.AddIcon, e -> controller.add());

        settingButton = ComponentFactory.createStickyMenuBarButton();
        settingButton.setIcon(GUIConfig.settingIcon);
        settingButton.addActionListener(this);
        menuBar.add(settingButton);

        menuBar.add(new MenuBarDeleteButton(e -> controller.delete(item)));

        renderedComponent.add(menuBar);
        renderedComponent.add(Box.createVerticalStrut(10));

        subPanel = new JPanel(new CardLayout());
        subPanel.setAlignmentX(0.0f);

        renderer = new MultiListRenderer();
        subPanel.add(renderer.getRenderedComponent(item));

        renderedComponent.add(subPanel);
        controller.model.galleryController.getView().getSideList().addListSelectionListener(this);

        return renderedComponent;
    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        if (item instanceof HoKhauAdapter) {
            return getRenderedComponent((HoKhauAdapter) item);
        }

        return ComponentFactory.createLabel("Chọn một hộ khẩu để hiển thị.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isEditing = !isEditing;
        subPanel.removeAll();

        if (isEditing) {
            editor = new HoKhauEditor(currentItem, controller, settingButton);
            subPanel.add(editor);
        } else {
            currentItem.resetString();
            controller.model.galleryController.getView().repaint();
            if (currentItem.isNew) {
                controller.model.galleryModel.getListModel().removeElement(currentItem);
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
