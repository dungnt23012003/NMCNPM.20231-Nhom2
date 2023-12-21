package src.main.boundary.feature;

import src.main.boundary.GUIConfig;

import javax.swing.*;

public abstract class FeatureView extends JPanel {
    ImageIcon icon;

    public FeatureView() {
        setBackground(GUIConfig.FeatureViewColor);
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
