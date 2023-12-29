package src.main.control;

import src.main.entity.CoSoVatChat;

import java.util.ArrayList;

public class CoSoVatChatControl {
    public void add(CoSoVatChat item) {

    }

    public void update(CoSoVatChat item) {

    }

    public ArrayList<CoSoVatChat> getList() {
        ArrayList<CoSoVatChat> list = new ArrayList<>();

        CoSoVatChat coSoVatChat1 = new CoSoVatChat();
        coSoVatChat1.maCSVC = "Máy tính";
        coSoVatChat1.soLuong = 1;
        list.add(coSoVatChat1);

        CoSoVatChat coSoVatChat2 = new CoSoVatChat();
        coSoVatChat2.maCSVC = "Máy chiếu";
        coSoVatChat2.soLuong = 1;
        list.add(coSoVatChat2);

        CoSoVatChat coSoVatChat3 = new CoSoVatChat();
        coSoVatChat3.maCSVC = "Mic";
        coSoVatChat3.soLuong = 3;
        list.add(coSoVatChat3);

        return list;
    }

    public void delete() {

    }
}
