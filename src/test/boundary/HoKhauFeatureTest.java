package src.test.boundary;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.hokhau.HoKhauController;
import src.main.boundary.hokhau.HoKhauFeature;
import src.main.boundary.hokhau.HoKhauModel;
import src.main.control.HoKhauControl;
import src.test.control.HoKhauControlTestValue;

public class HoKhauFeatureTest extends Feature {
    public HoKhauFeatureTest() {
        icon = GUIConfig.HoKhauIcon;
        name = "Quản lý hộ khẩu";

        HoKhauModel model = new HoKhauModel(new HoKhauControlTestValue());
        HoKhauController controller = new HoKhauController(model);
        view = controller.getView();
    }
}
