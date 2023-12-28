package src.main.boundary.hokhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.GalleryModel;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;

import javax.swing.*;

public class HoKhauModel implements GalleryModel {
    HoKhauControl control;
    ListSideGalleryController galleryController;
    DefaultListModel<GalleryItem> currentListModel;

    public HoKhauModel(HoKhauControl control) {
        this.control = control;

        galleryController = new ListSideGalleryController(this);
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel() {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        for (HoKhau hoKhau : control.getList()) {
            listModel.addElement(new HoKhauAdapter(hoKhau));
        }

        currentListModel = listModel;
        return listModel;
    }

    @Override
    public DefaultListModel<GalleryItem> getCurrentListModel() {
        return currentListModel;
    }
}
