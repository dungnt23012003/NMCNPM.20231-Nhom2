package src.main.boundary.gallery;

import javax.swing.*;

public interface GalleryModel {
    DefaultListModel<GalleryItem> getNewListModel();
    DefaultListModel<GalleryItem> getCurrentListModel();
}