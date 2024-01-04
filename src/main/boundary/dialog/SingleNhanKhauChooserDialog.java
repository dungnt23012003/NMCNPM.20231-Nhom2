package src.main.boundary.dialog;

import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.nhankhau.NhanKhauController;
import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class SingleNhanKhauChooserDialog extends JDialog {
    NhanKhauController controller;
    NhanKhauAdapter value;

    public SingleNhanKhauChooserDialog(Component parent) {
        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xE7E7E7));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControl());
        controller = new NhanKhauController(model, new SingleNhanKhauChooserRenderer(this));
        panel.add(controller.getView());

        JPanel chosenListPanel = new JPanel();
        chosenListPanel.setLayout(new BoxLayout(chosenListPanel, BoxLayout.LINE_AXIS));
        chosenListPanel.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)), new EmptyBorder(5, 10, 5, 10)));
        chosenListPanel.setBackground(new Color(0xE7E7E7));
        chosenListPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, -1));
        panel.add(chosenListPanel);

        setContentPane(panel);
        setModal(true);
        setSize(new Dimension(1024, 580));
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public NhanKhauAdapter getValue() {
        return value;
    }

    public void setValue(NhanKhauAdapter value) {
        this.value = value;
    }
}
