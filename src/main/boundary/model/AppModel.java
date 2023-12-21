package src.main.boundary.model;

import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;
import src.main.boundary.feature.FeatureView;

import java.util.ArrayList;

public interface AppModel {
    String getCurrentFeatureViewName();
    ArrayList<FeatureView> getFeatureViews();
    void setCurrentFeatureView(String name);
    void addListener(FeatureListener listener);
    void removeListener(FeatureListener listener);
    void addListener(FeatureListListener listener);
    void removeListener(FeatureListListener listener);
}
