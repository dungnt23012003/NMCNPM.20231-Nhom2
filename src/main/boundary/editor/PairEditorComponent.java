package src.main.boundary.editor;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;

public class PairEditorComponent extends EditorComponent{
    public JTextField firstField;
    public JTextField secondField;

    public PairEditorComponent(JTextField firstField, JTextField secondField) {
        this.firstField = firstField;
        this.secondField = secondField;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(GUIConfig.MyListBackground);
        add(firstField);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(secondField);
    }
    
    @Override
    public Pair getValue() {
        return new Pair(firstField.getText(), secondField.getText());
    }

    @Override
    public void clearValue() {
        firstField.setText("");
        secondField.setText("");
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Pair pair) {
            firstField.setText((String) pair.first);
            secondField.setText((String) pair.second);
        } else {
            System.err.println("PairEditorComponent::setValue cannot be invoked because value is not a Pair");
        }
    }
}
