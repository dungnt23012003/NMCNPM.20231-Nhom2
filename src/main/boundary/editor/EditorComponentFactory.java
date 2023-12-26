package src.main.boundary.editor;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.Main;
import src.main.boundary.GUIConfig;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.hokhau.HoKhauEditor;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EditorComponentFactory {
    public static EditorComponent createEditFormComponent(String field, String value) {
        FormEditorComponent editorComponent = new FormEditorComponent();
        editorComponent.setLayout(new BoxLayout(editorComponent, BoxLayout.LINE_AXIS));
        editorComponent.setBackground(new Color(0, 0, 0, 0));

        JLabel fieldLabel = new JLabel(field);
        fieldLabel.setFont(GUIConfig.DefaultFont);
        editorComponent.add(fieldLabel);

        editorComponent.add(Box.createRigidArea(new Dimension(GUIConfig.FormMinSpace, 0)));
        editorComponent.add(Box.createHorizontalGlue());

        JTextField valueField = new JTextField();
        valueField.setBorder(new FlatLineBorder(new Insets(0, 0, 0, 0), GUIConfig.ListSeparatorColor, 1.25f, 10));
        valueField.putClientProperty("JTextField.padding", new Insets(7, 7, 7, 7));

        valueField.setColumns(50);
        valueField.setMaximumSize(new Dimension(400, 30));

        valueField.setText(value);

        valueField.setBackground(GUIConfig.MyListBackground);
        valueField.setForeground(GUIConfig.FormValueColor);
        valueField.setFont(GUIConfig.DefaultFont);
        editorComponent.add(valueField);
        editorComponent.setTextField(valueField);

        return editorComponent;
    }

    public static EditorComponent createTestNhanKhauEditComponent(NhanKhau item) {
        EditorComponent editorComponent = new EditorComponent() {
            @Override
            public Object getValue() {
                return item;
            }

            @Override
            public void clearValue() {

            }
        };

        editorComponent.setLayout(new BoxLayout(editorComponent, BoxLayout.LINE_AXIS));

        JLabel label = ComponentFactory.createLabel(item.ten);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConfig.ListLabelHeight));

        editorComponent.add(label);
        editorComponent.add(Box.createHorizontalGlue());
        editorComponent.setBackground(GUIConfig.MyListBackground);

        JButton closeButton = ComponentFactory.createMenuBarButton();
        closeButton.setIcon(GUIConfig.CloseIcon);
        editorComponent.add(closeButton);

        return editorComponent;
    }
}
