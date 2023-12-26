package src.main.boundary.hokhau;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.gallery.ListSideGalleryController;

import javax.swing.*;

public class HoKhauView extends FeatureView {
    HoKhauModel model;
    HoKhauController controller;

    public HoKhauView(HoKhauController controller, HoKhauModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        model.galleryController.getView().setRenderer(new HoKhauRenderer(controller));
        add(model.galleryController.getView());
    }
}
