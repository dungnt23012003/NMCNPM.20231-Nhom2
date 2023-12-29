package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.boundary.nhankhau.NhanKhauRenderer;
import src.main.boundary.renderer.Renderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.awt.*;

public class NhanKhauChooserRenderer extends NhanKhauRenderer {
    NhanKhauChooserDialog parentDialog;
    public NhanKhauChooserRenderer(NhanKhauChooserDialog parentDialog) {
        super();
        this.parentDialog = parentDialog;
    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        JPanel panel = (JPanel) super.getRenderedComponent(item);
        if (deleteButton != null)
            deleteButton.setEnabled(false);

        // Setup menu bar
        JButton selectButton = ComponentFactory.createDefaultButton();
        JButton saveButton = ComponentFactory.createDefaultButton();

//        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(Box.createRigidArea(new Dimension(3, 0)));
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBackground(GUIConfig.ListSeparatorColor);
        separator.setMaximumSize(new Dimension(2, 25));
        menuBar.add(separator);
        menuBar.add(Box.createRigidArea(new Dimension(10, 0)));
        menuBar.add(selectButton);
        menuBar.add(saveButton);

        if (item instanceof NhanKhauAdapter castedItem) {
            if (!castedItem.isNew()) {
                if (parentDialog.isSelected((NhanKhauAdapter) item)) {
                    selectButton.setText("Bỏ chọn");
//                    castedItem.resetString();
//                    castedItem.setString(castedItem + " (Đã chọn)");
                    controller.getModel().getGalleryController().getView().getSideList().repaint();
                } else {
                    selectButton.setText("Chọn");
//                    castedItem.resetString();
                    controller.getModel().getGalleryController().getView().getSideList().repaint();
                }
            } else {
                selectButton.setText("Chọn");
            }

            selectButton.addActionListener(e -> {
                if (selectButton.getText().equals("Chọn")) {
                    parentDialog.addValue(castedItem);
//                    castedItem.setString(castedItem + " (Đã chọn)");
                    controller.getModel().getGalleryController().getView().getSideList().repaint();
                    selectButton.setText("Bỏ chọn");
                } else {
                    parentDialog.removeValue(castedItem);
//                    castedItem.resetString();
                    controller.getModel().getGalleryController().getView().getSideList().repaint();
                    selectButton.setText("Chọn");
                }
            });

            if (castedItem.isNew()) {
                selectButton.setEnabled(false);
                saveButton.setEnabled(false);
            }
        } else {
            selectButton.setText("Chọn");
            selectButton.setEnabled(false);
        }

        saveButton.setText("Lưu");
        saveButton.addActionListener(e -> parentDialog.setVisible(false));

        return panel;
    }
}
