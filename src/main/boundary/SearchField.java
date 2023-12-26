package src.main.boundary;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.Main;
import src.main.boundary.utility.ColorUtility;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SearchField extends JTextField {
    public SearchField() {
        putClientProperty("JTextField.showClearButton", true);
        putClientProperty("JTextField.leadingIcon", new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/search.png"))));
        putClientProperty("JTextField.placeholderText", "Search...");
        putClientProperty("JTextField.padding", new Insets(0, 2, 0, 0));
        setMargin(new Insets(5, 10, 5, 0));
    }
}
