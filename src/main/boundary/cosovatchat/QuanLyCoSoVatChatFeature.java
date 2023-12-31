package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;

public class QuanLyCoSoVatChatFeature extends Feature {
    public QuanLyCoSoVatChatFeature() {
        icon = GUIConfig.CoSoVatChatIcon;
        name = "Quản lý cơ sở vật chất";

        view = new QuanLyCoSoVatChatView();
    }
}
