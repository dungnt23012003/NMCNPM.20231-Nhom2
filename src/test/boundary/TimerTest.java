package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.SearchField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TimerTest {
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

        SearchField field = new SearchField();
        field.setMaximumSize(new Dimension(100, 59));
        JLabel label = new JLabel("Initial text");

        panel.add(field);
        panel.add(Box.createVerticalGlue());
        panel.add(label);

        Timer timer = new Timer(1000, e -> label.setText(field.getText()));
        timer.setRepeats(false);

        field.getDocument().addDocumentListener(new DocumentListener() {
            private void onDocumentUpdate() {
                timer.restart();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                onDocumentUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onDocumentUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        frame.setContentPane(panel);
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
