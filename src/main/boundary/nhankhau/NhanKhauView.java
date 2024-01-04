package src.main.boundary.nhankhau;

import src.main.boundary.dialog.ConfirmDialog;
import src.main.boundary.feature.FeatureView;

import javax.swing.*;
import java.awt.*;

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
        setLayout(new GridLayout(1, 0));

        model.galleryController.getView().setRenderer(renderer);
        add(model.galleryController.getView());
    }

    public NhanKhauRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(NhanKhauRenderer renderer) {
        this.renderer = renderer;
    }

    public void refreshUI() {
        model.galleryController.updateSideList();
    }
}
