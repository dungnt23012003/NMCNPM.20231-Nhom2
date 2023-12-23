package src.main.boundary.hokhau;

import src.main.entity.HoKhau;

public class HoKhauController {
    HoKhauModel model;
    HoKhauView view;

    public HoKhauController(HoKhauModel model) {
        this.model = model;

        view = new HoKhauView(this, model);
        view. setupUI();
    }

    public void add() {

    }

    public void setting(HoKhau hoKhau) {

    }

    public void delete(HoKhau hoKhau) {
        model.control.delete(hoKhau);
    }

    public HoKhauView getView() {
        return view;
    }
}
