package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.app.DefaultAppController;
import src.main.boundary.model.DefaultAppModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SimpleAppViewTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        DefaultAppModel model = new DefaultAppModel();

        final int[] count = {0};
        FeatureView view1 = new FeatureView() {
            @Override
            public ImageIcon getIcon() {
                return new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/delete.png")));
            }
        };
        JButton button = new JButton("Add more feature");
        button.addActionListener(e -> {
            model.addFeatureView(new FeatureView() {
                @Override
                public ImageIcon getIcon() {
                    add(new JLabel(String.valueOf(count[0])));
                    count[0]++;
                    return new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/create.png")));
                }
            });
        });

        view1.add(button);
        view1.setBackground(Color.WHITE);
        model.addFeatureView(view1);
        model.addFeatureView(new FeatureView() {
            @Override
            public ImageIcon getIcon() {
                return new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/create.png")));
            }
        });

        model.addFeatureView(new FeatureView() {
            @Override
            public ImageIcon getIcon() {
                return new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/delete.png")));
            }
        });

        DefaultAppController controller = new DefaultAppController(model);

        frame.setContentPane(controller.getView());
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
