package src.main.boundary.hokhau;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.gallery.ListSideGalleryController;

import javax.swing.*;
import java.awt.*;

public class HoKhauView extends FeatureView {
    HoKhauModel model;
    HoKhauController controller;

    public HoKhauView(HoKhauController controller, HoKhauModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new GridLayout(1, 0));

        model.galleryController.getView().setRenderer(new HoKhauRenderer(controller));
        add(model.galleryController.getView());
    }

    public void refreshUI() {
        model.galleryController.updateSideList("");
    }
}
