package src.main.boundary;

import javax.swing.*;

// TODO
public class ListSideGalleryModel {
    ListModel<GalleryItem> listModel;

    public ListSideGalleryModel(ListModel<GalleryItem> listModel) {
        this.listModel = listModel;
    }

    public ListModel<GalleryItem> getListModel() {
        return listModel;
    }

    public void setListModel(ListModel<GalleryItem> listModel) {
        this.listModel = listModel;
    }
}
