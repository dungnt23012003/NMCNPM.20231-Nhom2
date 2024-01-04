package src.main.boundary.gallery;

import src.main.entity.NhanKhau;

import javax.swing.*;

// TODO
public class ListSideGalleryController {
    GalleryModel model;
    ListSideGalleryView view;

    public ListSideGalleryController(GalleryModel model) {
        this.model = model;

        view = new ListSideGalleryView(this, model);
        view.setupUI();
    }

    public ListSideGalleryController(ListCellRenderer<GalleryItem> listCellRenderer, GalleryModel model) {
        this.model = model;

        view = new ListSideGalleryView(listCellRenderer, this, model);
        view.setupUI();
    }

    public void setSelectedValue(GalleryItem item) {
        view.mainView.removeAll();
        view.mainView.add(view.renderer.getRenderedComponent(item));

        view.mainView.revalidate();
        view.mainView.repaint();
    }

    public ListSideGalleryView getView() {
        return view;
    }

    public void updateSideList(String condition) {
        view.getSideList().setModel(model.getNewListModel(condition));
    }

//    public void setSelectSideList(NhanKhau item) {
//        model.getCurrentListModel().
//        view.getSideList().setSelectedValue(true);
//    }
}
