package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.nhankhau.NhanKhauRenderer;
import src.main.boundary.renderer.Renderable;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class SingleNhanKhauChooserRenderer extends NhanKhauRenderer {
    SingleNhanKhauChooserDialog parentDialog;
    public SingleNhanKhauChooserRenderer(SingleNhanKhauChooserDialog parentDialog) {
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
        menuBar.add(saveButton);

        saveButton.setText("Chọn");
        saveButton.addActionListener(e -> {
            parentDialog.setValue((NhanKhauAdapter) item);
            parentDialog.setVisible(false);
        });

        return panel;
    }
}
