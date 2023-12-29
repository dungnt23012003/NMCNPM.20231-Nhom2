package src.main.boundary;

import src.main.Main;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

public interface GUIConfig {
    Color SideBarColor = new Color(230, 230, 230);
    Color FeatureViewColor = new Color(240, 240, 240);
    Color ListSelectionBackground = new Color(0, 159, 229);

    Color MyListBackground = new Color(230, 230, 230);
    Color ListSeparatorColor = new Color(208, 208, 208);
    Color FormValueColor = Color.GRAY.darker();
    Color MenuBarBackground = new Color(230, 230, 230);
    Color FormEditColor = new Color(240, 240, 240);

    Font DefaultFont = new Font("JetBrains Mono", Font.PLAIN, 13);
    Font ListTitleFont = new Font("JetBrains Mono", Font.BOLD, 13);

    int FormMinSpace = 30;
    int ListLabelHeight = 25;

    ImageIcon AddIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/create.png")));
    ImageIcon DeleteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/delete.png")));
    ImageIcon SearchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/search.png")));
    ImageIcon SettingIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/setting.png")));
    ImageIcon HoKhauIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/book.png")));
    ImageIcon NhanKhauIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/person.png")));
    ImageIcon AddNhanKhauIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/add_person.png")));
    ImageIcon CloseIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/close.png")));
    ImageIcon HoatDongIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/activity.png")));
    ImageIcon CoSoVatChatIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("resources/icons/co_so_vat_chat.png")));

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
}
