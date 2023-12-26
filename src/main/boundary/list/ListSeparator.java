package src.main.boundary.list;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import java.awt.*;

public class ListSeparator extends JSeparator {
    public ListSeparator() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        setMinimumSize(new Dimension(0, 2));
        setForeground(GUIConfig.ListSeparatorColor);
    }
}
