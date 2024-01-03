package src.main.boundary.cosovatchat;

import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.PhongBanControl;
import src.main.entity.CoSoVatChat;
import src.main.entity.PhongBan;

import java.awt.*;
import java.util.ArrayList;

public class PhongBanListAdapter extends DefaultRenderableList {
    PhongBanControl control;
    ArrayList<PhongBan> list;

    public PhongBanListAdapter(ArrayList<PhongBan> list, PhongBanControl control) {
        this.control = control;
        this.list = list;

        if (list.isEmpty()) {
            addComponent(ComponentFactory.createFormComponent("Không có phòng ban nào.", ""));
        } else {
            for (PhongBan phongBan : list) {
                addComponent(new PhongBanComponent(phongBan, control));
            }
        }
    }

    @Override
    public String getTitle() {
        return "Danh sách phòng ban";
    }
}
