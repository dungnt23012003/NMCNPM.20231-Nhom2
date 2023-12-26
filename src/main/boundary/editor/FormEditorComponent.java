package src.main.boundary.editor;

import src.main.boundary.editor.EditorComponent;

import javax.swing.*;

public class FormEditorComponent extends EditorComponent {
    JTextField textField;

    public void setTextField(JTextField textField) {
        this.textField = textField;
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
}
