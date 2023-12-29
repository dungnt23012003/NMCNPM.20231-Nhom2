package src.main.boundary.hokhau;

import src.main.entity.HoKhau;

public class HoKhauController {
    HoKhauModel model;
    HoKhauView view;

    public HoKhauController(HoKhauModel model) {
        this.model = model;

        view = new HoKhauView(this, model);
        model.control.setView(view);
        view. setupUI();
    }

    public void add() {
        int lastListIndex = model.getCurrentListModel().size();
        model.getCurrentListModel().add(lastListIndex, new HoKhauAdapter(new HoKhau(), true));
        model.galleryController.getView().setSelectedIndex(lastListIndex);

        HoKhauRenderer renderer = (HoKhauRenderer) model.galleryController.getView().getRenderer();
        renderer.settingButton.doClick();
        renderer.editor.clearValue();
    }

    public void delete(HoKhauAdapter hoKhauAdapter) {
        model.control.delete(hoKhauAdapter.hoKhau);
    }

    public HoKhauView getView() {
        return view;
    }
}
