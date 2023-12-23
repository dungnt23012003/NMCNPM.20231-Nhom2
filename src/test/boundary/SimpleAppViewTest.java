package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.app.DefaultAppController;
import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.model.DefaultAppModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleAppViewTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        DefaultAppModel model = new DefaultAppModel();

        final Integer[] i = {0};
        Feature feature1 = new Feature();
        feature1.setIcon(new ImageIcon("/Users/hoanmai/NMCNPM.20231-Nhom2/icons/create.png"));

        FeatureView view1 = new FeatureView();

        JButton button = new JButton("Click me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Feature new_feature = new Feature();
                new_feature.setIcon(new ImageIcon("/Users/hoanmai/NMCNPM.20231-Nhom2/icons/create.png"));
                FeatureView view = new FeatureView();
                view.add(new JLabel(String.valueOf(i[0])));

                new_feature.setView(view);
                model.addFeature(new_feature);
                i[0]++;
            }
        });

        view1.add(button);
        feature1.setView(view1);
        model.addFeature(feature1);

        Feature feature2 = new Feature();
        feature2.setIcon(new ImageIcon("/Users/hoanmai/NMCNPM.20231-Nhom2/icons/delete.png"));
        feature2.setView(new FeatureView());
        model.addFeature(feature2);

        DefaultAppController controller = new DefaultAppController(model);

        frame.setContentPane(controller.getView());
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
