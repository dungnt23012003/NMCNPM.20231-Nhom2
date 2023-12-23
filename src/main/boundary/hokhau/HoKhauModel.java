package src.main.boundary.hokhau;

import src.main.boundary.GalleryItem;
import src.main.boundary.ListSideGalleryModel;
import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;

import javax.swing.*;

public class HoKhauModel {
    HoKhauControl control;
    ListSideGalleryModel galleryModel;

    public HoKhauModel(HoKhauControl control) {
        this.control = control;

        setupGalleryModel();
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
