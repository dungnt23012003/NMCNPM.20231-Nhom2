package src.main.boundary.nhankhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.hokhau.HoKhauController;
import src.main.boundary.hokhau.HoKhauModel;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.HoKhauControl;
import src.main.control.NhanKhauControl;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NhanKhauFeature extends Feature {
    public NhanKhauFeature() {
        icon = GUIConfig.NhanKhauIcon;
        name = "Quản lý nhân khẩu";

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControl());
        NhanKhauController controller = new NhanKhauController(model);
        view = controller.getView();
    }
}
