package src.main.boundary.cosovatchat;

import src.main.boundary.list.ListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.CoSoVatChat;

import java.awt.*;
import java.util.ArrayList;

public class CoSoVatChatListAdapter implements ListRenderable {
    ArrayList<CoSoVatChat> list;
    public CoSoVatChatListAdapter(ArrayList<CoSoVatChat> list) {
        this.list = list;
    }

    @Override
    public String getTitle() {
        return "Danh sách cơ sở vật chất";
    }

    @Override
    public ArrayList<Component> getComponentList() {
        ArrayList<Component> components = new ArrayList<>();

        for (CoSoVatChat coSoVatChat : list) {
            components.add(ComponentFactory.createFormComponent(coSoVatChat.maCSVC, String.valueOf(coSoVatChat.soLuong)));
        }

        return components;
    }
}
