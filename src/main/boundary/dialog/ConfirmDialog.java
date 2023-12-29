package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfirmDialog extends JDialog {
    boolean value = false;

    public ConfirmDialog(String message, Component parent) {
        setupUI(message, parent);
    }

    public void setupUI(String message, Component parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel messageLabel = ComponentFactory.createLabel(message);
        messageLabel.setAlignmentX(0.5f);

        panel.add(messageLabel);
        panel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JButton cancelButton = ComponentFactory.createDefaultButton();
        JButton confirmButton = ComponentFactory.createDefaultButton();

        cancelButton.setText("Không");
        confirmButton.setText("Có");

        cancelButton.addActionListener(e -> this.dispose());
        confirmButton.addActionListener(e -> {
            value = true;
            this.dispose();
        });

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(confirmButton);

        panel.add(buttonPanel);

        setContentPane(panel);
        setResizable(false);
        setModal(true);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean getValue() {
        return value;
    }
}
