package src.main.boundary.hoatdong;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.hokhau.HoKhauController;
import src.main.boundary.hokhau.HoKhauModel;
import src.main.control.HoKhauControl;
import src.main.control.HoatDongControl;
import src.main.entity.HoatDong;
import src.test.control.HoatDongControlTestValue;

public class HoatDongFeature extends Feature {
    public HoatDongFeature() {
        icon = GUIConfig.HoatDongIcon;
        name = "Quản lý hoạt động";

        HoatDongModel model = new HoatDongModel(new HoatDongControl());
        HoatDongController controller = new HoatDongController(model);
        view = controller.getView();
    }
}
