package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.editor.*;
import src.main.boundary.menubar.ConfirmDeleteButton;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.PhongBanControl;
import src.main.entity.PhongBan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PhongBanComponent extends JPanel {
    PhongBanControl control;
    PhongBan item;
    PairEditorComponent editorComponent;
    PhongBanView parent;
    boolean isNew;

    public PhongBanComponent(PhongBan item, PhongBanControl control) {
        this(item, control, false, null);
    }

    public PhongBanComponent(PhongBan item, PhongBanControl control, boolean isNew, PhongBanView parent) {
        this.control = control;
        this.item = item;
        this.parent = parent;
        this.isNew = isNew;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(GUIConfig.SideBarColor);

        if (isNew) {
            setupEditorUI();
        } else {
            setupRenderUI();
        }
    }

    // View
    private void setupRenderUI() {
        add(ComponentFactory.createFormComponent(item.maPhongBan, ""));
        add(Box.createRigidArea(new Dimension(10, 0)));

        JButton settingButton = ComponentFactory.createStickyMenuBarButton();
        settingButton.setIcon(GUIConfig.SettingIcon);
        settingButton.addActionListener(e -> settingButtonClicked());
        add(settingButton);

        JButton deleteButton = new ConfirmDeleteButton(e -> deleteButtonClicked());
        deleteButton.setIcon(GUIConfig.CloseIcon);
        add(deleteButton);
    }

    private void setupEditorUI() {
        editorComponent = EditorComponentFactory.createPairEditorComponent(new Pair(item.maPhongBan, ""));
        editorComponent.setBorder(new EmptyBorder(4, 0, 3, 0));

        editorComponent.firstField.setColumns(20);
        editorComponent.secondField.setVisible(false);
        editorComponent.firstField.putClientProperty("JTextField.placeholderText", "Tên phòng ban");;

        add(editorComponent);
        add(Box.createRigidArea(new Dimension(10, 0)));

        JButton cancelButton = ComponentFactory.createDefaultButton();
        cancelButton.setText("Hủy");
        cancelButton.addActionListener(e -> cancelButtonClicked());
        add(cancelButton);

        JButton saveButton = ComponentFactory.createDefaultButton();
        saveButton.setText("Lưu");
        saveButton.addActionListener(e -> saveButtonClicked());
        add(saveButton);
    }

    // Model
    public PhongBan getValue() {
        PhongBan phongBan = new PhongBan();
        phongBan.maPhongBan = editorComponent.firstField.getText();

        return phongBan;
    }

    // Controller
    public void deleteButtonClicked() {
        control.delete(item);
    }

    public void settingButtonClicked() {
        removeAll();
        setupEditorUI();
        revalidate();
    }

    public void cancelButtonClicked() {
        if (parent != null) {
            parent.cancelButtonClicked();
        } else {
            removeAll();
            setupRenderUI();
            revalidate();
            repaint();
        }
    }

    public void saveButtonClicked() {
        if (isNew) {
            control.add(getValue());
        } else {
            control.update(item, getValue());
        }
        cancelButtonClicked();
    }
}
