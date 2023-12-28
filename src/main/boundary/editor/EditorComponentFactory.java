package src.main.boundary.editor;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.hokhau.HoKhauEditor;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

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
        valueField.putClientProperty("JTextField.padding", new Insets(0, 7, 0, 0));

        valueField.setColumns(45);
        valueField.setMaximumSize(new Dimension(400, GUIConfig.ListLabelHeight));

        valueField.setText(value);

        valueField.setBackground(GUIConfig.MyListBackground);
        valueField.setForeground(GUIConfig.FormValueColor);
        valueField.setFont(GUIConfig.DefaultFont);
        editorComponent.add(valueField);
        editorComponent.setTextField(valueField);

        return editorComponent;
    }

    public static NhanKhauEditorComponent createNhanKhauEditorComponent(NhanKhauAdapter item, HoKhauEditor editor) {
        NhanKhauEditorComponent editorComponent = new NhanKhauEditorComponent(item);
        editorComponent.setBackground(GUIConfig.MyListBackground);
        editorComponent.setLayout(new BoxLayout(editorComponent, BoxLayout.LINE_AXIS));

        JLabel label = ComponentFactory.createLabel(item.getNhanKhau().hoTen + (item.isChuHo() ? " (Chủ hộ)" : ""));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConfig.ListLabelHeight));

        editorComponent.add(label);
        editorComponent.add(Box.createHorizontalGlue());

        if (!item.isChuHo()) {
            JButton chuHoButton = ComponentFactory.createDefaultButton();
            chuHoButton.setText("Đặt chủ hộ");
            chuHoButton.addActionListener(e -> editor.setChuHo(editorComponent));
            editorComponent.add(chuHoButton);
            editorComponent.add(Box.createRigidArea(new Dimension(5, 0)));
        }

        JButton closeButton = ComponentFactory.createMenuBarButton();
        closeButton.setIcon(GUIConfig.CloseIcon);
        closeButton.addActionListener(e -> editor.removeNhanKhau(item));
        editorComponent.add(closeButton);

        return editorComponent;
    }
}
