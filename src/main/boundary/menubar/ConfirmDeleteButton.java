package src.main.boundary.menubar;

import src.main.boundary.GUIConfig;
import src.main.boundary.dialog.ConfirmDeleteDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDeleteButton extends JButton implements ActionListener {
    ActionListener listener;

    public ConfirmDeleteButton(ActionListener listener) {
        setBackground(GUIConfig.MenuBarBackground);
        setIcon(GUIConfig.DeleteIcon);
        setMargin(new Insets(5, 5, 5, 5));
        setBorderPainted(false);
        setFocusable(false);
        super.addActionListener(this);

        this.listener = listener;
    }

    @Override
    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ConfirmDeleteDialog(getRootPane(), listener);
    }
}
