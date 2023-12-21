package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.app.DefaultAppController;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.model.DefaultAppModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DefaultAppTemplate {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        DefaultAppModel model = new DefaultAppModel();

        // Your code here

        DefaultAppController controller = new DefaultAppController(model);

        frame.setContentPane(controller.getView());
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
