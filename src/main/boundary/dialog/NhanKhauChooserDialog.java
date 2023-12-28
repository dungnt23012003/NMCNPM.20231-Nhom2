package src.main.boundary.dialog;

import src.main.boundary.nhankhau.NhanKhauController;
import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.control.NhanKhauControl;

import javax.swing.*;
import java.awt.*;

public class NhanKhauChooserDialog extends JDialog {
    public NhanKhauChooserDialog(Component parent) {
        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControl());
        NhanKhauController controller = new NhanKhauController(model);

        panel.add(controller.getView());

        setContentPane(panel);
        setModal(true);
        setSize(new Dimension(512, 600));
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
