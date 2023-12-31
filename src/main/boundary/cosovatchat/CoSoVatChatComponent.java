package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.editor.*;
import src.main.boundary.menubar.ConfirmDeleteButton;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CoSoVatChatComponent extends JPanel {
    CoSoVatChatControl control;
    CoSoVatChat item;
    PairEditorComponent editorComponent;
    CoSoVatChatView parent;

    public CoSoVatChatComponent(CoSoVatChat item, CoSoVatChatControl control) {
        this(item, control, false, null);
    }

    public CoSoVatChatComponent(CoSoVatChat item, CoSoVatChatControl control, boolean isNew, CoSoVatChatView parent) {
        this.control = control;
        this.item = item;
        this.parent = parent;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(GUIConfig.SideBarColor);

        if (isNew) {
            setupEditorUI(true);
        } else {
            setupRenderUI();
        }
    }

    // View
    private void setupRenderUI() {
        add(ComponentFactory.createFormComponent(item.maCSVC, String.valueOf(item.soLuong)));
        add(Box.createRigidArea(new Dimension(10, 0)));

        JButton settingButton = ComponentFactory.createStickyMenuBarButton();
        settingButton.setIcon(GUIConfig.SettingIcon);
        settingButton.addActionListener(e -> settingButtonClicked());
        add(settingButton);

        JButton deleteButton = new ConfirmDeleteButton(e -> deleteButtonClicked());
        deleteButton.setIcon(GUIConfig.CloseIcon);
        add(deleteButton);
    }

    private void setupEditorUI(boolean isNew) {
        editorComponent = EditorComponentFactory.createPairEditorComponent(new Pair(item.maCSVC, item.soLuong));
//        editorComponent.setMinimumSize(new Dimension(-1, 50));
        editorComponent.setBorder(new EmptyBorder(4, 0, 3, 0));

        if (!isNew) {
            editorComponent.firstField.setBorder(BorderFactory.createEmptyBorder());
            editorComponent.firstField.setEditable(false);
            editorComponent.firstField.putClientProperty("JTextField.padding", new Insets(0, 0, 0, 0));
        } else {
            editorComponent.secondField.setText("");
        }
        editorComponent.firstField.setColumns(20);
        editorComponent.secondField.setColumns(10);

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

    // Controller
    public void deleteButtonClicked() {
        control.delete(item);
    }

    public void settingButtonClicked() {
        removeAll();
        setupEditorUI(false);
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
        cancelButtonClicked();
    }
}
