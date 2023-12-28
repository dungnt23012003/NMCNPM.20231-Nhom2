package src.main.boundary.nhankhau;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.hokhau.HoKhauRenderer;

import javax.swing.*;

public class NhanKhauView extends FeatureView {
    NhanKhauModel model;
    NhanKhauController controller;

    public NhanKhauView(NhanKhauController controller, NhanKhauModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        model.galleryController.getView().setRenderer(new NhanKhauRenderer(controller));
        add(model.galleryController.getView());
    }
}
