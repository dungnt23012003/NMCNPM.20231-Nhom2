package src.main.boundary.editor;

import javax.swing.*;

public abstract class EditorComponent extends JPanel {
    public abstract Object getValue();
    public abstract void clearValue();
}
