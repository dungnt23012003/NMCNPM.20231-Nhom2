package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.dialog.ConfirmDeleteDialog;
import src.main.boundary.dialog.ConfirmDialog;

import javax.swing.*;
import java.awt.*;

public class ConfirmDialogTest {
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

        JButton button = new JButton("Click me.");
        button.addActionListener(e -> {
            ConfirmDialog dialog = new ConfirmDialog("It's a long way down... Do you want to jump?", frame.getRootPane());
            System.out.println(dialog.getValue());
        });

        panel.add(button);

        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
