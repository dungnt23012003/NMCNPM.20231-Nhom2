package src.main.boundary.nhankhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NhanKhauFeature extends Feature {
    public NhanKhauFeature() {
        icon = GUIConfig.NhanKhauIcon;

        view = new FeatureView();
        view.setBorder(new EmptyBorder(10, 10, 10, 10));
        view.add(ComponentFactory.createLabel("Nhân khẩu view tôi chưa viết :>"));
    }
}
