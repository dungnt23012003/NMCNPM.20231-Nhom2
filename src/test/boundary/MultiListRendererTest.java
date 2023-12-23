package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.GUIConfig;
import src.main.boundary.ListRenderable;
import src.main.boundary.MultiListRenderable;
import src.main.boundary.renderer.MultiListRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MultiListRendererTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        MultiListRenderer renderer = new MultiListRenderer();

        MultiListRenderable multiList = new MultiListRenderable() {
            @Override
            public ArrayList<ListRenderable> getLists() {
                ArrayList<ListRenderable> lists = new ArrayList<>();

                lists.add(new ListRenderable() {
                    @Override
                    public String getTitle() {
                        return "First title";
                    }

                    @Override
                    public ArrayList<Component> getComponentList() {
                        ArrayList<Component> components = new ArrayList<>();

                        JPanel component1 = new JPanel();
                        component1.setBackground(GUIConfig.MyListBackground);
                        component1.setLayout(new BoxLayout(component1, BoxLayout.LINE_AXIS));

                        JLabel label1 = new JLabel("Field1");
                        label1.setFont(GUIConfig.DefaultFont);
                        component1.add(label1);
                        component1.add(Box.createHorizontalGlue());

                        JLabel label2 = new JLabel("Value1");
                        label2.setForeground(Color.GRAY);
                        label2.setFont(GUIConfig.DefaultFont);
                        component1.add(label2);

                        components.add(component1);

                        JPanel component2 = new JPanel();
                        component2.setBackground(GUIConfig.MyListBackground);
                        component2.setLayout(new BoxLayout(component2, BoxLayout.LINE_AXIS));

                        JLabel label3 = new JLabel("A Button");
                        label3.setFont(GUIConfig.DefaultFont);
                        component2.add(label3);
                        component2.add(Box.createHorizontalGlue());

                        JButton button = new JButton("I'm a button");
                        button.setFont(GUIConfig.DefaultFont);
                        component2.add(button);

                        components.add(component2);

                        return components;
                    }
                });

                lists.add(new ListRenderable() {
                    @Override
                    public String getTitle() {
                        return "Second title";
                    }

                    @Override
                    public ArrayList<Component> getComponentList() {
                        ArrayList<Component> components = new ArrayList<>();

                        JPanel component1 = new JPanel();
                        component1.setBackground(GUIConfig.MyListBackground);
                        component1.setLayout(new BoxLayout(component1, BoxLayout.LINE_AXIS));

                        JLabel label1 = new JLabel("Field1");
                        label1.setFont(GUIConfig.DefaultFont);
                        component1.add(label1);
                        component1.add(Box.createHorizontalGlue());

                        JLabel label2 = new JLabel("Value1");
                        label2.setForeground(Color.GRAY);
                        label2.setFont(GUIConfig.DefaultFont);
                        component1.add(label2);

                        components.add(component1);

                        JPanel component2 = new JPanel();
                        component2.setBackground(GUIConfig.MyListBackground);
                        component2.setLayout(new BoxLayout(component2, BoxLayout.LINE_AXIS));

                        JLabel label3 = new JLabel("A Button");
                        label3.setFont(GUIConfig.DefaultFont);
                        component2.add(label3);
                        component2.add(Box.createHorizontalGlue());

                        JButton button = new JButton("I'm a button");
                        button.setFont(GUIConfig.DefaultFont);
                        component2.add(button);

                        components.add(component2);

                        return components;
                    }
                });

                return lists;
            }
        };

        panel.add(renderer.getRenderedComponent(multiList));
//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
