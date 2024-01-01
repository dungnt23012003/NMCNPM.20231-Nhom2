package src.main.boundary.hoatdong;

import src.main.boundary.hokhau.HoKhauModel;
import src.main.entity.HoatDong;

public class HoatDongController {
    HoatDongModel model;
    HoatDongView view;

    public HoatDongController(HoatDongModel model) {
        this.model = model;

        view = new HoatDongView(this, model);
        model.control.setView(view);
        view.setupUI();
    }

    public void add() {
        int lastListIndex = model.getCurrentListModel().size();
        model.getCurrentListModel().add(lastListIndex, new HoatDongAdapter(new HoatDong(), true));
        model.galleryController.getView().setSelectedIndex(lastListIndex);

        HoatDongRenderer renderer = (HoatDongRenderer) model.galleryController.getView().getRenderer();
        renderer.settingButton.doClick();
        renderer.editor.clearValue();
    }

    public void update(HoatDong hoatDong) {

    }

    public void delete(HoatDongAdapter adapter) {
        int current_index = model.galleryController.getView().getSelectedIndex();

        model.control.delete(adapter.hoatDong);
        model.getCurrentListModel().removeElement(adapter);

        if (current_index == model.getCurrentListModel().size())
            --current_index;

        model.galleryController.getView().setSelectedIndex(current_index);
    }

    public HoatDongView getView() {
        return view;
    }
}
