package src.main.boundary.app;

import src.main.boundary.model.AppModel;

public class DefaultAppController implements AppController {
    AppModel model;
    AppView view;

    public DefaultAppController(AppModel model) {
        this.model = model;
        view = new AppView(this, model);
        view.setupUI();
    }

    public AppView getView() {
        return view;
    }

    @Override
    public void setCurrentView(String name) {
        model.setCurrentFeatureView(name);
    }
}
