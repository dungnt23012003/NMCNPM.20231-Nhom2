package src.main.boundary;

import javax.swing.*;
import java.awt.*;

public class ListSeparator extends JSeparator {
    public ListSeparator() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        setMinimumSize(new Dimension(0, 2));
        setForeground(GUIConfig.ListSeparatorColor);
    }
}
