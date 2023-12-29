package src.main.control;

import src.main.boundary.cosovatchat.PhongBanListAdapter;
import src.main.entity.PhongBan;

import java.util.ArrayList;

public class PhongBanControl {
    public ArrayList<PhongBan> getList() {
        ArrayList<PhongBan> list = new ArrayList<>();

        PhongBan phongBan1 = new PhongBan();
        phongBan1.maPhongBan = "Phòng tiếp khách";

        PhongBan phongBan2 = new PhongBan();
        phongBan2.maPhongBan = "Phòng họp chính";

        PhongBan phongBan3 = new PhongBan();
        phongBan3.maPhongBan = "Phòng truyền thống";

        PhongBan phongBan4 = new PhongBan();
        phongBan4.maPhongBan = "Sân chính";

        PhongBan phongBan5 = new PhongBan();
        phongBan5.maPhongBan = "Sân thể thao";

        list.add(phongBan1);
        list.add(phongBan2);
        list.add(phongBan3);
        list.add(phongBan4);
        list.add(phongBan5);

        return list;
    }
}
