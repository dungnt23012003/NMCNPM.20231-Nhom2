package src.main.boundary.nhankhau;

import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.hokhau.HoKhauModel;
import src.main.boundary.hokhau.HoKhauRenderer;
import src.main.boundary.hokhau.HoKhauView;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

public class NhanKhauController {
    NhanKhauModel model;
    NhanKhauView view;

    public NhanKhauController(NhanKhauModel model) {
        this.model = model;

        view = new NhanKhauView(this, model);
        view. setupUI();
    }

    public void add() {
        int lastListIndex = model.galleryModel.getListModel().size();
        model.galleryModel.getListModel().add(lastListIndex, new NhanKhauAdapter(new NhanKhau(), true));
        model.galleryController.getView().setSelectedIndex(lastListIndex);

        NhanKhauRenderer renderer = (NhanKhauRenderer) model.galleryController.getView().getRenderer();
        renderer.settingButton.doClick();
        renderer.editor.clearValue();
    }

    public void update(NhanKhau nhanKhau) {

    }

    public void delete(NhanKhauAdapter nhanKhauAdapter) {
        int current_index = model.galleryController.getView().getSelectedIndex();

        model.control.delete(nhanKhauAdapter.nhanKhau);
        model.getGalleryModel().getListModel().removeElement(nhanKhauAdapter);

        if (current_index == model.getGalleryModel().getListModel().size())
            --current_index;

        model.galleryController.getView().setSelectedIndex(current_index);
    }

    public NhanKhauView getView() {
        return view;
    }
}
