package src.main.boundary.editor;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.hokhau.HoKhauEditor;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class EditorComponentFactory {
    public static FormEditorComponent createEditFormComponent(String field, String value) {
        FormEditorComponent editorComponent = new FormEditorComponent();
        editorComponent.setLayout(new BoxLayout(editorComponent, BoxLayout.LINE_AXIS));
        editorComponent.setBackground(new Color(0, 0, 0, 0));

        JLabel fieldLabel = new JLabel(field);
        fieldLabel.setFont(GUIConfig.DefaultFont);
        editorComponent.add(fieldLabel);

        editorComponent.add(Box.createRigidArea(new Dimension(GUIConfig.FormMinSpace, 0)));
        editorComponent.add(Box.createHorizontalGlue());

        JTextField valueField = ComponentFactory.createEditorTextField();
        valueField.setText(value);

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

    public static PairEditorComponent createPairEditorComponent(Pair pair) {
        PairEditorComponent editorComponent = new PairEditorComponent(
                ComponentFactory.createEditorTextField(),
                ComponentFactory.createEditorTextField()
        );

        editorComponent.firstField.setText(pair.first.toString());
        editorComponent.firstField.setForeground(Color.BLACK);

        editorComponent.secondField.setText(pair.second.toString());

        return editorComponent;
    }
}
