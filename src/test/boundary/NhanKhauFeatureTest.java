package src.test.boundary;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.nhankhau.NhanKhauController;
import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.control.NhanKhauControl;
import src.test.control.NhanKhauControlTestValue;

public class NhanKhauFeatureTest extends Feature {
    public NhanKhauFeatureTest() {
        icon = GUIConfig.NhanKhauIcon;
        name = "Quản lý nhân khẩu";

        NhanKhauModel model = new NhanKhauModel(new NhanKhauControlTestValue());
        NhanKhauController controller = new NhanKhauController(model);
        view = controller.getView();
    }
}
