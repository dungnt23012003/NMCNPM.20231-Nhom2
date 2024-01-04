package src.main.boundary.hoatdong;

import src.main.boundary.feature.FeatureView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HoatDongView extends FeatureView {
    HoatDongModel model;
    HoatDongController controller;

    public HoatDongView(HoatDongController controller, HoatDongModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new GridLayout(1, 0));

        model.galleryController.getView().setRenderer(new HoatDongRenderer(controller));
        add(model.galleryController.getView());
    }

    public void refreshUI() {
        model.galleryController.updateSideList("");
    }
}
