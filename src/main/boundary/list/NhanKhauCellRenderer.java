package src.main.boundary.list;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.hoatdong.HoatDongAdapter;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.utility.ColorUtility;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class NhanKhauCellRenderer implements ListCellRenderer<GalleryItem> {
    @Override
    public Component getListCellRendererComponent(JList<? extends GalleryItem> list, GalleryItem value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof NhanKhauAdapter castedValue) {
            JPanel component = new JPanel();
            component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));
            component.setBackground(new Color(0, 0, 0, 0));
            component.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(0, 0, 0, 0), 1, 10));

            JLabel CCCDLabel = ComponentFactory.createLabel(castedValue.getNhanKhau().CCCD);
            CCCDLabel.setForeground(ColorUtility.darken(Color.LIGHT_GRAY, 15));
            CCCDLabel.setFont(GUIConfig.DefaultFont.deriveFont(12f));
            component.add(CCCDLabel);
            component.add(Box.createRigidArea(new Dimension(0, 3)));

            JLabel nameLabel = ComponentFactory.createLabel("<html><p style=\"width:125px\">" + value.toString() + "</p></html>");
            component.add(nameLabel);

            if (isSelected) {
                component.setBackground(GUIConfig.ListSelectionBackground);
                CCCDLabel.setForeground(Color.WHITE);
                nameLabel.setForeground(Color.WHITE);
            }

            return component;
        }

        return new JLabel("Renderer không hỗ trợ");
    }
}
