package src.main.boundary.model;

import src.main.boundary.feature.Feature;
import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;

import java.util.ArrayList;

public class DefaultAppModel implements AppModel {
    ArrayList<FeatureListener> featureListeners = new ArrayList<>();
    ArrayList<FeatureListListener> featureListListeners = new ArrayList<>();
    ArrayList<Feature> featureList = new ArrayList<>();
    String currentFeatureViewName = "0";

    public void addFeature(Feature feature) {
        featureList.add(feature);
        fireFeatureListChanged();
    }

    @Override
    public String getCurrentFeatureName() {
        return currentFeatureViewName;
    }

    @Override
    public ArrayList<Feature> getFeatureList() {
        return featureList;
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
