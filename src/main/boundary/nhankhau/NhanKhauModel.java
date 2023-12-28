package src.main.boundary.nhankhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;

public class NhanKhauModel {
    NhanKhauControl control;
    ListSideGalleryModel galleryModel;
    ListSideGalleryController galleryController;

    public NhanKhauModel(NhanKhauControl control) {
        this.control = control;

        setupGalleryModel();
        galleryController = new ListSideGalleryController(getGalleryModel());
    }

    public void setupGalleryModel() {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        for (NhanKhau nhanKhau : control.getList()) {
            listModel.addElement(new NhanKhauAdapter(nhanKhau));
        }

        galleryModel = new ListSideGalleryModel(listModel);
    }

    public ListSideGalleryModel getGalleryModel() {
        return galleryModel;
    }
}
