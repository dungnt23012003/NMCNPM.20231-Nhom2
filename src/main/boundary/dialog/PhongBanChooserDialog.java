package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.PhongBanControl;
import src.main.entity.PhongBan;
import src.test.control.PhongBanControlTestValue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class PhongBanChooserDialog extends JDialog {
    PhongBanControl control;
    ArrayList<PhongBan> values;

    public PhongBanChooserDialog(ArrayList<PhongBan> selectedList, Component parent) {
        values = new ArrayList<>(selectedList);
        control = new PhongBanControl();

        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setBackground(GUIConfig.FeatureViewColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        MenuBar menuBar = new MenuBar();
        menuBar.setAlignmentX(0.0f);

        JButton cancelButton = ComponentFactory.createDefaultButton();
        cancelButton.setText("Hủy");
        cancelButton.addActionListener(e -> this.setVisible(false));

        JButton saveButton = ComponentFactory.createDefaultButton();
        saveButton.setText("Lưu");
        saveButton.addActionListener(e -> this.setVisible(false));

        menuBar.add(cancelButton);
        menuBar.add(saveButton);

        panel.add(menuBar);
        panel.add(Box.createVerticalStrut(10));

        ListRenderer renderer = new ListRenderer();
        renderer.setScrollPaneWrapped(true);
        panel.add(renderer.getRenderedComponent(getRenderableList()));

        setContentPane(panel);
        setModal(true);
        setSize(new Dimension(750, 580));
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private DefaultRenderableList getRenderableList() {
        DefaultRenderableList list = new DefaultRenderableList();
        list.setTitle("Danh sách phòng ban");

        ArrayList<PhongBan> phongBans = control.getList();
        for (PhongBan phongBan : phongBans) {
            list.addComponent(getPhongBanChooserComponent(phongBan));
        }

        return list;
    }

    private Component getPhongBanChooserComponent(PhongBan item) {
        JPanel panel = new JPanel();
        panel.setBackground(GUIConfig.MyListBackground);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        JCheckBox checkBox = new JCheckBox(item.maPhongBan);
        checkBox.setFont(GUIConfig.DefaultFont);
        checkBox.setIconTextGap(10);
        checkBox.setFocusPainted(false);
        checkBox.setSelected(values.contains(item));

        checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                values.add(item);
            } else {
                values.remove(item);
            }
        });

        panel.add(checkBox);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    public ArrayList<PhongBan> getValues() {
        return values;
    }
}
