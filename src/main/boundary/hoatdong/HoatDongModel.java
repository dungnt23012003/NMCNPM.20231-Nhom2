package src.main.boundary.hoatdong;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.GalleryModel;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.control.HoKhauControl;
import src.main.control.HoatDongControl;
import src.main.entity.HoKhau;
import src.main.entity.HoatDong;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.util.Objects;

public class HoatDongModel implements GalleryModel {
    HoatDongControl control;
    ListSideGalleryController galleryController;
    DefaultListModel<GalleryItem> currentListModel;

    public HoatDongModel(HoatDongControl control) {
        this.control = control;

        galleryController = new ListSideGalleryController(this);
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel() {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        for (HoatDong hoatDong : control.getList()) {
            listModel.addElement(new HoatDongAdapter(hoatDong));
        }

        currentListModel = listModel;
        return listModel;
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel(String condition) {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        if (Objects.equals(condition, "")) {
            for (HoatDong hoatDong : control.getList()) {
                listModel.addElement(new HoatDongAdapter(hoatDong));
            }
        } else {
            for (HoatDong hoatDong : control.searchHoatDong(condition)) {
                listModel.addElement(new HoatDongAdapter(hoatDong));
            }
        }

        currentListModel = listModel;
        return listModel;
    }

    @Override
    public DefaultListModel<GalleryItem> getCurrentListModel() {
        return currentListModel;
    }
}
