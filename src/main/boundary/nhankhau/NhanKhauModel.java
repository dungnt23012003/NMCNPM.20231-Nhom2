package src.main.boundary.nhankhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.GalleryModel;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.list.NhanKhauCellRenderer;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.util.Objects;

public class NhanKhauModel implements GalleryModel {
    NhanKhauControl control;
    ListSideGalleryController galleryController;
    DefaultListModel<GalleryItem> currentListModel;

    public NhanKhauModel(NhanKhauControl control) {
        this.control = control;

        galleryController = new ListSideGalleryController(new NhanKhauCellRenderer(), this);
        galleryController.getView().getSideList().setPrototypeCellValue(GUIConfig.NhanKhauPrototype);
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel() {
        return getNewListModel("");
    }

    @Override
    public DefaultListModel<GalleryItem> getNewListModel(String condition) {
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();

        if (Objects.equals(condition, "")) {
            for (NhanKhau nhanKhau : control.getList()) {
                listModel.addElement(new NhanKhauAdapter(nhanKhau));
            }
        } else {
            for (NhanKhau nhanKhau : control.searchNhanKhau(condition)) {
                listModel.addElement(new NhanKhauAdapter(nhanKhau));
            }
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
