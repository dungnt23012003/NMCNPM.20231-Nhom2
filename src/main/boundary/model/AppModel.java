package src.main.boundary.model;

import src.main.boundary.feature.Feature;
import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;

import java.util.ArrayList;

public interface AppModel {
    String getCurrentFeatureName();
    ArrayList<Feature> getFeatureList();
    void setCurrentFeatureView(String name);
    void addListener(FeatureListener listener);
    void removeListener(FeatureListener listener);
    void addListener(FeatureListListener listener);
    void removeListener(FeatureListListener listener);
}
