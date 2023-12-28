package src.main.boundary.dialog;

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

public class NhanKhauChooserDialog extends JDialog {
    HoKhauControl control;

    public NhanKhauChooserDialog(HoKhauControl control, Component parent) {
        this.control = control;

        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControl());
        NhanKhauController controller = new NhanKhauController(model);
        panel.add(controller.getView());

        // Setup menu bar
        JButton selectButton = ComponentFactory.createDefaultButton();
        selectButton.setText("Chọn");

        JButton saveButton = ComponentFactory.createDefaultButton();
        saveButton.setText("Lưu");

        controller.getView().getRenderer().getMenuBar().add(Box.createHorizontalGlue());
        controller.getView().getRenderer().getMenuBar().add(selectButton);
        controller.getView().getRenderer().getMenuBar().add(saveButton);

        JPanel chosenListPanel = new JPanel();
        chosenListPanel.setLayout(new BoxLayout(chosenListPanel, BoxLayout.LINE_AXIS));
        chosenListPanel.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)), new EmptyBorder(5, 10, 5, 10)));
        chosenListPanel.setBackground(new Color(0xE7E7E7));
        chosenListPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, -1));
        chosenListPanel.add(ComponentFactory.createLabel("Nhân khẩu đã chọn:"));
        chosenListPanel.add(ComponentFactory.createLabel(" 26"));
        chosenListPanel.add(ComponentFactory.createLabel(" 27"));
        panel.add(chosenListPanel);

        setContentPane(panel);
        setModal(true);
        setSize(new Dimension(580, 570));
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
