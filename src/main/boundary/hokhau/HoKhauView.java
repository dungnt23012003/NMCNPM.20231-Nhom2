package src.main.boundary.hokhau;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.ListSideGalleryController;

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

        ListSideGalleryController galleryController = new ListSideGalleryController(model.getGalleryModel());
        galleryController.getView().setRenderer(new HoKhauRenderer(controller));
        add(galleryController.getView());
    }
}
