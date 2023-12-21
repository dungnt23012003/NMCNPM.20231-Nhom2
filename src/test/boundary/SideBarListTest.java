package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.sidebar.BoxSidebar;
import src.main.boundary.sidebar.Sidebar;

import javax.swing.*;
import java.awt.*;

public class SideBarListTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        Sidebar sidebar = new BoxSidebar();
        sidebar.setPreferredSize(new Dimension(200, -1));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));

        sidebar.add(textField);

        panel.add(sidebar);

        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
