package src.main.boundary.hoatdong;

import src.main.boundary.feature.FeatureView;

import javax.swing.*;

public class HoatDongView extends FeatureView {
    HoatDongModel model;
    HoatDongController controller;

    public HoatDongView(HoatDongController controller, HoatDongModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        model.galleryController.getView().setRenderer(new HoatDongRenderer(controller));
        add(model.galleryController.getView());
    }
}
