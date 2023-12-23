package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.hokhau.HoKhauController;
import src.main.boundary.hokhau.HoKhauModel;
import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;
import src.test.control.HoKhauControlTestValue;

import javax.swing.*;
import java.awt.*;

public class HoKhauTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UIManager.put("Component.borderColor", new Color(210, 210, 210));
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.arc", 10);
        UIManager.put("List.selectionArc", 10);
        UIManager.put("TextField.margin", new Insets(5, 10, 5, 0));

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        HoKhauControl control = new HoKhauControlTestValue();
        HoKhauModel model = new HoKhauModel(control);
        HoKhauController controller = new HoKhauController(model);
        panel.add(controller.getView());


        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
