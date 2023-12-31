package src.main.boundary.cosovatchat;

import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;

import java.awt.*;
import java.util.ArrayList;

public class CoSoVatChatListAdapter extends DefaultRenderableList {
    CoSoVatChatControl control;
    ArrayList<CoSoVatChat> list;
    public CoSoVatChatListAdapter(ArrayList<CoSoVatChat> list, CoSoVatChatControl control) {
        this.control = control;
        this.list = list;

        if (list.isEmpty()) {
            addComponent(ComponentFactory.createFormComponent("Không có cơ sở vật chất nào.", ""));
        } else {
            for (CoSoVatChat coSoVatChat : list) {
                addComponent(new CoSoVatChatComponent(coSoVatChat, control));
            }
        }
    }

    @Override
    public String getTitle() {
        return "Danh sách cơ sở vật chất";
    }
}
