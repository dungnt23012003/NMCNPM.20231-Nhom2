package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.app.DefaultAppController;
import src.main.boundary.feature.*;
import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.gallery.ListSideGalleryController;
import src.main.boundary.gallery.ListSideGalleryModel;
import src.main.boundary.gallery.ListSideGalleryView;
import src.main.boundary.model.DefaultAppModel;

import javax.swing.*;
import java.awt.*;

public class ListSideFeatureViewTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UIManager.put("Component.borderColor", new  Color(210, 210, 210));
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.arc", 10);
        UIManager.put("List.selectionArc", 10);
        UIManager.put("TextField.margin", new Insets(5, 10, 5, 0));

        JFrame frame = new JFrame();

        DefaultAppModel model = new DefaultAppModel();

        // Custom code here
        DefaultListModel<GalleryItem> listModel = new DefaultListModel<>();
        listModel.addElement(new GalleryItem() {
            @Override
            public String toString() {
                return "HK00001";
            }
        });
        listModel.addElement(new GalleryItem() {
            @Override
            public String toString() {
                return "HK00002";
            }
        });
        listModel.addElement(new GalleryItem() {
            @Override
            public String toString() {
                return "HK00003";
            }
        });

        ListSideGalleryModel listSideGalleryModel = new ListSideGalleryModel(listModel);

        ListSideGalleryController listSideGalleryController = new ListSideGalleryController(listSideGalleryModel);
        ListSideGalleryView featureView = listSideGalleryController.getView();

        Feature testFeature = new Feature();
        testFeature.setView(featureView);
        testFeature.setIcon(new ImageIcon("/Users/hoanmai/NMCNPM.20231-Nhom2/src/test/resources/icons/create.png"));

        model.addFeature(testFeature);

        DefaultAppController controller = new DefaultAppController(model);

        frame.setContentPane(controller.getView());
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
