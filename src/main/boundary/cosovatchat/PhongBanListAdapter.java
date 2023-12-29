package src.main.boundary.cosovatchat;

import src.main.boundary.list.ListRenderable;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.PhongBan;

import java.awt.*;
import java.util.ArrayList;

public class PhongBanListAdapter implements ListRenderable {
    ArrayList<PhongBan> list;

    public PhongBanListAdapter(ArrayList<PhongBan> list) {
        this.list = list;
    }

    @Override
    public String getTitle() {
        return "Danh sách phòng ban";
    }

    @Override
    public ArrayList<Component> getComponentList() {
        ArrayList<Component> components = new ArrayList<>();

        for (PhongBan phongBan : list) {
            components.add(ComponentFactory.createFormComponent(phongBan.maPhongBan, ""));
        }

        return components;
    }
}
