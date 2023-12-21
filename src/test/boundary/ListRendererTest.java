package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.ListRenderable;
import src.main.boundary.renderer.ListRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListRendererTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        ListRenderer listRenderer = new ListRenderer();

        panel.add(listRenderer.getRenderedComponent(new ListRenderable() {
            @Override
            public String getTitle() {
                return "This is the title";
            }

            @Override
            public ArrayList<Component> getComponentList() {
                ArrayList<Component> list = new ArrayList<>();
                list.add(new JLabel("Item1"));
                list.add(new JLabel("Item2"));
                list.add(new JLabel("Item3"));
                list.add(new JLabel("Item4"));
                list.add(new JLabel("Item5"));

                return list;
            }
        }));

        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
