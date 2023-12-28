package src.main.boundary.nhankhau;

import src.main.boundary.dialog.NhanKhauChooserRenderer;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.hokhau.HoKhauRenderer;

import javax.swing.*;

public class NhanKhauView extends FeatureView {
    NhanKhauModel model;
    NhanKhauController controller;
    NhanKhauRenderer renderer;

    public NhanKhauView(NhanKhauController controller, NhanKhauModel model) {
        this.controller = controller;
        this.model = model;
        renderer = new NhanKhauRenderer(controller);
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        model.galleryController.getView().setRenderer(renderer);
        add(model.galleryController.getView());
    }

    public NhanKhauRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(NhanKhauRenderer renderer) {
        this.renderer = renderer;
    }
}
