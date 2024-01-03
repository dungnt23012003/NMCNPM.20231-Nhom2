package src.main.boundary.list;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;

public class CustomJList<E> extends JList<E> {
    public CustomJList(ListModel<E> model) {
        super(model);

        setFixedCellHeight(32);
        setSelectionBackground(GUIConfig.ListSelectionBackground);
        setSelectionForeground(Color.WHITE);
        setFont(GUIConfig.DefaultFont);
        setFocusable(true);
    }
}
