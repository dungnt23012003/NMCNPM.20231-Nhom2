package src.main.boundary.gallery;

import javax.swing.*;

public class ListSideGalleryModel {
    DefaultListModel<GalleryItem> listModel;

    public ListSideGalleryModel(DefaultListModel<GalleryItem> listModel) {
        this.listModel = listModel;
    }

    public DefaultListModel<GalleryItem> getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel<GalleryItem> listModel) {
        this.listModel = listModel;
    }
}
