package src.main.boundary.dialog;

import src.main.boundary.GUIConfig;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;
import src.test.control.CoSoVatChatControlTestValue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CoSoVatChatChooserDialog extends JDialog {
    CoSoVatChatControl control;
    ArrayList<CoSoVatChat> values;

    public CoSoVatChatChooserDialog(ArrayList<CoSoVatChat> selectedList, Component parent) {
        values = new ArrayList<>(selectedList);
        control = new CoSoVatChatControl();

        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setBackground(GUIConfig.FeatureViewColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        src.main.boundary.menubar.MenuBar menuBar = new MenuBar();
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
        list.setTitle("Danh sách cơ sở vật chất");

        JPanel listHeader = new JPanel();
        listHeader.setBackground(GUIConfig.MyListBackground);
        listHeader.setLayout(new BoxLayout(listHeader, BoxLayout.LINE_AXIS));
        listHeader.add(Box.createRigidArea(new Dimension(25, 0)));
        listHeader.add(ComponentFactory.createBoldLabel("Tên cơ sở vật chất"));
        listHeader.add(Box.createHorizontalGlue());
        listHeader.add(ComponentFactory.createBoldLabel("Tổng số lượng"));
        list.addComponent(listHeader);

        ArrayList<CoSoVatChat> coSoVatChats = control.getList();
        for (CoSoVatChat coSoVatChat : coSoVatChats) {
            list.addComponent(getCoSoVatChatChooserComponent(coSoVatChat));
        }

        return list;
    }

    private Component getCoSoVatChatChooserComponent(CoSoVatChat item) {
        JPanel panel = new JPanel();
        panel.setBackground(GUIConfig.MyListBackground);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        JCheckBox checkBox = new JCheckBox(item.maCSVC);
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

        panel.add(ComponentFactory.createLabel(String.valueOf(item.soLuong)));

        return panel;
    }

    public ArrayList<CoSoVatChat> getValues() {
        return values;
    }
}
