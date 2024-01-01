package src.main.boundary.feature;

import src.main.boundary.GUIConfig;
import src.main.boundary.dialog.ConfirmDialog;

import javax.swing.*;

public class FeatureView extends JPanel {
    public FeatureView() {
        setBackground(GUIConfig.FeatureViewColor);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(getRootPane(), message);
    }

    public boolean showConfirmDialog(String message) {
        ConfirmDialog dialog = new ConfirmDialog(message, getRootPane());
        return dialog.getValue();
    }
}
