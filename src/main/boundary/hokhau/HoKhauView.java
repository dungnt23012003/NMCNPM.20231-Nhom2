package src.main.boundary.hokhau;

import src.main.boundary.ListItemizable;
import src.main.boundary.feature.ListSideFeatureView;
import src.main.control.HoKhauControl;

import javax.swing.*;

public class HoKhauView extends ListSideFeatureView {
    HoKhauModel model;
    HoKhauController controller;

    public HoKhauView(HoKhauController controller, HoKhauModel model) {
        super(model.getListModel());

        this.controller = controller;
        this.model = model;
    }

    public void setupUI() {

    }
}
