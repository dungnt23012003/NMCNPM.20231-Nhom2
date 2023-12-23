package src.main.boundary;

import src.main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public interface GUIConfig {
    Color SideBarColor = new Color(230, 230, 230);
    Color FeatureViewColor = new Color(240, 240, 240);
    Color ListSelectionBackground = new Color(0, 159, 229);
    Color MyListBackground = new Color(230, 230, 230);
    Color ListSeparatorColor = new Color(208, 208, 208);
    Color FormValueColor = Color.GRAY;
    Color MenuBarBackground = new Color(230, 230, 230);

    Font DefaultFont = new Font("JetBrains Mono", Font.PLAIN, 13);
    Font ListTitleFont = new Font("JetBrains Mono", Font.BOLD, 13);

    int FormMinSpace = 30;

    ImageIcon AddIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/create.png")));
    ImageIcon DeleteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/delete.png")));
    ImageIcon SearchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/search.png")));
    ImageIcon settingIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/setting.png")));
    ImageIcon HoKhauIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/book.png")));
}
