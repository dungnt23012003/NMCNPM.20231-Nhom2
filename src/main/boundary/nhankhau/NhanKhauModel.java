package src.main.boundary.nhankhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.GalleryModel;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;

public class NhanKhauModel implements GalleryModel {
    NhanKhauControl control;
    ListSideGalleryController galleryController;
    DefaultListModel<GalleryItem> currentListModel;

    public NhanKhauModel(NhanKhauControl control) {
        this.control = control;

        galleryController = new ListSideGalleryController(this);
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel() {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        for (NhanKhau nhanKhau : control.getList()) {
            listModel.addElement(new NhanKhauAdapter(nhanKhau));
        }

        currentListModel = listModel;
        return listModel;
    }

    @Override
    public DefaultListModel<GalleryItem> getCurrentListModel() {
        return currentListModel;
    }

    public ListSideGalleryController getGalleryController() {
        return galleryController;
    }
}
