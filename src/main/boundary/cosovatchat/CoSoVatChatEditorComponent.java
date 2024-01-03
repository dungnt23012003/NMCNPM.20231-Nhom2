package src.main.boundary.cosovatchat;

import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.entity.CoSoVatChat;

import javax.swing.*;

public class CoSoVatChatEditorComponent extends EditorComponent {
    JTextField inputField;
    CoSoVatChat item;

    public CoSoVatChatEditorComponent(CoSoVatChat item) {
        this.item = item;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public Object getValue() {
        item.soLuong = Integer.parseInt(inputField.getText());
        return item;
    }

    @Override
    public void clearValue() {}

    @Override
    public void setValue(Object value) {}
}
