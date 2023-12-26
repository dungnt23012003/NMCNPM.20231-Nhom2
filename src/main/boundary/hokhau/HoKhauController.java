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
        int lastListIndex = model.galleryModel.getListModel().size();
        model.galleryModel.getListModel().add(lastListIndex, new HoKhauAdapter(new HoKhau(), true));
        model.galleryController.getView().setSelectedIndex(lastListIndex);

        HoKhauRenderer renderer = (HoKhauRenderer) model.galleryController.getView().getRenderer();
        renderer.settingButton.doClick();
        renderer.editor.clearValue();
    }

    public void update(HoKhau hoKhau) {

    }

    public void delete(HoKhauAdapter hoKhauAdapter) {
        int current_index = model.galleryController.getView().getSelectedIndex();

        model.control.delete(hoKhauAdapter.hoKhau);
        model.getGalleryModel().getListModel().removeElement(hoKhauAdapter);

        if (current_index == model.getGalleryModel().getListModel().size())
            --current_index;

        model.galleryController.getView().setSelectedIndex(current_index);
    }

    public HoKhauView getView() {
        return view;
    }
}
