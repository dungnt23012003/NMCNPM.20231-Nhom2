package src.main.boundary.model;

import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;
import src.main.boundary.feature.FeatureView;

import java.util.ArrayList;

public class DefaultAppModel implements AppModel {
    ArrayList<FeatureListener> featureListeners = new ArrayList<>();
    ArrayList<FeatureListListener> featureListListeners = new ArrayList<>();
    ArrayList<FeatureView> featureViews = new ArrayList<>();
    String currentFeatureViewName = "0";

    public void addFeatureView(FeatureView view) {
        featureViews.add(view);
        fireFeatureListChanged();
    }

    @Override
    public String getCurrentFeatureViewName() {
        return currentFeatureViewName;
    }

    @Override
    public ArrayList<FeatureView> getFeatureViews() {
        return featureViews;
    }

    @Override
    public void setCurrentFeatureView(String name) {
        currentFeatureViewName = name;
        fireFeatureChanged();
    }

    public void fireFeatureChanged() {
        for (FeatureListener listener : featureListeners) {
            listener.featureChanged();
        }
    }

    public void fireFeatureListChanged() {
        for (FeatureListListener listener : featureListListeners) {
            listener.featureListChanged();
        }
    }

    @Override
    public void addListener(FeatureListener listener) {
        featureListeners.add(listener);
    }

    @Override
    public void removeListener(FeatureListener listener) {
        featureListeners.remove(listener);
    }

    @Override
    public void addListener(FeatureListListener listener) {
        featureListListeners.add(listener);
    }

    @Override
    public void removeListener(FeatureListListener listener) {
        featureListListeners.remove(listener);
    }
}
