package src.main.boundary.nhankhau;

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

public class NhanKhauRenderer implements EntityRenderer, ActionListener, ListSelectionListener {
    protected NhanKhauController controller;
    JPanel subPanel;
    MultiListRenderer renderer;
    NhanKhauEditor editor;
    boolean isEditing;
    NhanKhauAdapter currentItem;
    JButton settingButton;
    protected JButton deleteButton;
    protected MenuBar menuBar;

    public NhanKhauRenderer() {}

    public NhanKhauRenderer(NhanKhauController controller) {
        this.controller = controller;
    }

    public void setController(NhanKhauController controller) {
        this.controller = controller;
    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        isEditing = false;

        FeatureView renderedComponent = new FeatureView();
        renderedComponent.setLayout(new BoxLayout(renderedComponent, BoxLayout.PAGE_AXIS));

        menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);
        renderedComponent.add(menuBar);
        renderedComponent.add(Box.createVerticalStrut(10));

        menuBar.addButton(GUIConfig.AddIcon, e -> controller.renderAdd());

        if (item instanceof NhanKhauAdapter castedItem) {
            currentItem = castedItem;

            settingButton = ComponentFactory.createStickyMenuBarButton();
            settingButton.setIcon(GUIConfig.settingIcon);
            settingButton.addActionListener(this);
            menuBar.add(settingButton);

            deleteButton = new MenuBarDeleteButton(e -> controller.delete(castedItem));
            menuBar.add(deleteButton);

            subPanel = new JPanel(new CardLayout());
            subPanel.setAlignmentX(0.0f);

            renderer = new MultiListRenderer();
            subPanel.add(renderer.getRenderedComponent(item));

            renderedComponent.add(subPanel);
            controller.model.galleryController.getView().getSideList().addListSelectionListener(this);
        } else {
            renderedComponent.add(ComponentFactory.createLabel("Không có nhân khẩu nào được chọn."));
        }

        return renderedComponent;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isEditing = !isEditing;
        subPanel.removeAll();

        if (isEditing) {
            editor = new NhanKhauEditor(currentItem, controller, settingButton);
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
