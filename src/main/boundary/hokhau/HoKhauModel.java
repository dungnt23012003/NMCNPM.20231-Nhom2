package src.main.boundary.hokhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;

import javax.swing.*;

public class HoKhauModel {
    HoKhauControl control;
    ListSideGalleryModel galleryModel;
    ListSideGalleryController galleryController;

    public HoKhauModel(HoKhauControl control) {
        this.control = control;

        setupGalleryModel();
        galleryController = new ListSideGalleryController(getGalleryModel());
    }

    public void setupGalleryModel() {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        for (HoKhau hoKhau : control.getList()) {
            listModel.addElement(new HoKhauAdapter(hoKhau));
        }

        galleryModel = new ListSideGalleryModel(listModel);
    }

    public ListSideGalleryModel getGalleryModel() {
        return galleryModel;
    }
}
