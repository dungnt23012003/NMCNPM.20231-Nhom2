package src.main.boundary.editor;

import src.main.boundary.editor.EditorComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class FormEditorComponent extends EditorComponent {
    JTextField textField;
    Border textFieldBorder;

    public void setTextField(JTextField textField) {
        this.textField = textField;
        textFieldBorder = textField.getBorder();
    }

    public JTextField getTextField() {
        return textField;
    }

    @Override
    public String getValue() {
        return textField.getText();
    }

    @Override
    public void clearValue() {
        textField.setText("");
    }

    @Override
    public void setEnabled(boolean enabled) {
        textField.setEnabled(enabled);
        if (enabled) {
            textField.setBorder(textFieldBorder);
        } else {
            textField.setBorder(new EmptyBorder(textFieldBorder.getBorderInsets(textField)));
        }
    }
}

