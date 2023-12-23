package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDeleteDialog extends JDialog implements ActionListener {
    ActionListener listener;

    public ConfirmDeleteDialog(Component parent, ActionListener listener) {
        this.listener = listener;

        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel messageLabel = new JLabel("Bạn có chắc chắn muốn xóa?");
        messageLabel.setFont(GUIConfig.DefaultFont);
        messageLabel.setAlignmentX(0.5f);

        panel.add(messageLabel);
        panel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JButton cancelButton = new JButton("Không");
        JButton confirmButton = new JButton("Có");

        cancelButton.setFont(GUIConfig.DefaultFont);
        confirmButton.setFont(GUIConfig.DefaultFont);

        cancelButton.addActionListener(e -> this.dispose());
        confirmButton.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
        this.dispose();
    }
}
