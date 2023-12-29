package src.main.boundary.cosovatchat;

import src.main.boundary.GUIConfig;
import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;

public class CoSoVatChatFeature extends Feature {
    public CoSoVatChatFeature() {
        icon = GUIConfig.CoSoVatChatIcon;
        name = "Quản lý cơ sở vật chất";

        view = new CoSoVatChatView();
    }
}
