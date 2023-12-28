package src.main.boundary.gallery;

// TODO
public class ListSideGalleryController {
    GalleryModel model;
    ListSideGalleryView view;

    public ListSideGalleryController(GalleryModel model) {
        this.model = model;

        view = new ListSideGalleryView(this, model);
        view.setupUI();
    }

    public void setSelectedValue(GalleryItem item) {
        view.mainView.removeAll();
        view.mainView.add(view.renderer.getRenderedComponent(item));

        view.mainView.revalidate();
        view.mainView.repaint();
    }

    public ListSideGalleryView getView() {
        return view;
    }
}
