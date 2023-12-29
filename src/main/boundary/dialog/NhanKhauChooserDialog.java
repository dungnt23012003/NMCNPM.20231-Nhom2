package src.main.boundary.dialog;

import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.nhankhau.NhanKhauController;
import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;
import src.test.control.NhanKhauControlTestValue;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class NhanKhauChooserDialog extends JDialog {
    NhanKhauController controller;
    ArrayList<NhanKhauAdapter> values;
    JLabel selectedLabel;

    public NhanKhauChooserDialog(ArrayList<NhanKhauAdapter> selectedList, HoKhauControl control, Component parent) {
        values = selectedList;

        setupUI(parent);
    }

    public void setupUI(Component parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControl());
        controller = new NhanKhauController(model, new NhanKhauChooserRenderer(this));
        panel.add(controller.getView());

        JPanel chosenListPanel = new JPanel();
        chosenListPanel.setLayout(new BoxLayout(chosenListPanel, BoxLayout.LINE_AXIS));
        chosenListPanel.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)), new EmptyBorder(5, 10, 5, 10)));
        chosenListPanel.setBackground(new Color(0xE7E7E7));
        chosenListPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, -1));
        chosenListPanel.add(ComponentFactory.createLabel("Nhân khẩu đã chọn: "));
        panel.add(chosenListPanel);

        selectedLabel = ComponentFactory.createLabel("");
        updateSelectedLabel();
        chosenListPanel.add(selectedLabel);

        setContentPane(panel);
        setModal(true);
        setSize(new Dimension(750, 580));
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public ArrayList<NhanKhauAdapter> getValues() {
        return values;
    }

    public void addValue(NhanKhauAdapter value) {
        values.add(value);
        updateSelectedLabel();
    }

    public void removeValue(NhanKhauAdapter value) {
        values.remove(value);
        updateSelectedLabel();
    }

    public String getSelectedListAsString() {
        String result = "";
        if (values.isEmpty())
            return result;

        for (int i = 0; i < values.size() - 1; i++) {
            NhanKhauAdapter adapter = values.get(i);
            result += adapter.getNhanKhau().CCCD + ", ";
        }
        result += values.get(values.size() - 1).getNhanKhau().CCCD;

        return result;
    }

    public void updateSelectedLabel() {
        selectedLabel.setText(getSelectedListAsString());
    }

    public boolean isSelected(NhanKhauAdapter value) {
        return values.contains(value);
    }
}
