package src.main.boundary;

import javax.swing.*;
import java.awt.*;

public class CustomJList<E> extends JList<E> {
    public CustomJList(ListModel<E> model) {
        super(model);

        setFixedCellHeight(30);
        setSelectionBackground(GUIConfig.ListSelectionBackground);

        setSelectionForeground(Color.WHITE);
        setFont(GUIConfig.DefaultFont);
        setFocusable(true);
    }
}
