package src.main.boundary.hokhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.control.HoKhauControl;

public class HoKhauFeature extends Feature {
    public HoKhauFeature() {
        icon = GUIConfig.HoKhauIcon;

        HoKhauModel model = new HoKhauModel(new HoKhauControl());
        HoKhauController controller = new HoKhauController(model);
        view = controller.getView();
    }
}
