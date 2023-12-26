package src.main.boundary.feature;

import javax.swing.*;

public class Feature {
    protected FeatureView view = new FeatureView();
    protected ImageIcon icon;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FeatureView getView() {
        return view;
    }

    public void setView(FeatureView view) {
        this.view = view;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
