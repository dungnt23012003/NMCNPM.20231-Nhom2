package src.test.control;

import src.main.boundary.cosovatchat.CoSoVatChatView;
import src.main.control.CoSoVatChatControl;
import src.main.entity.CoSoVatChat;

import java.util.ArrayList;

public class CoSoVatChatControlTestValue extends CoSoVatChatControl {
    @Override
    public ArrayList<CoSoVatChat> getList() {
        ArrayList<CoSoVatChat> list = new ArrayList<>();

        CoSoVatChat item1 = new CoSoVatChat();
        item1.maCSVC = "Bàn";
        item1.soLuong = 10;
        list.add(item1);

        CoSoVatChat item2 = new CoSoVatChat();
        item2.maCSVC = "Ghế";
        item2.soLuong = 40;
        list.add(item2);

        list.add(createDummyCoSoVatChat());
        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());
//        list.add(createDummyCoSoVatChat());

        CoSoVatChat item3 = new CoSoVatChat();
        item3.maCSVC = "Golf";
        item3.soLuong = 10;
        list.add(item3);

        return list;
    }

    public static CoSoVatChat createDummyCoSoVatChat() {
        CoSoVatChat item1 = new CoSoVatChat();
        item1.maCSVC = String.valueOf(item1.hashCode());
        item1.soLuong = HoatDongControlTestValue.randBetween(1, 20);
        return item1;
    }
}
