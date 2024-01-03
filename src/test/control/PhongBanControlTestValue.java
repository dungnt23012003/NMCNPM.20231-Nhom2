package src.test.control;

import src.main.boundary.cosovatchat.PhongBanView;
import src.main.control.PhongBanControl;
import src.main.entity.PhongBan;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhongBanControlTestValue extends PhongBanControl {
    static ArrayList<PhongBan> phongBans = new ArrayList<>();

    public PhongBanControlTestValue() {
        for (int i = 0; i < 20; ++i) {
            phongBans.add(createDummyPhongBan());
        }
    }

    @Override
    public ArrayList<PhongBan> getList() {
        return phongBans;
    }

    public static PhongBan createDummyPhongBan() {
        PhongBan phongBan = new PhongBan();
        phongBan.maPhongBan = String.valueOf(phongBan.hashCode());

        return phongBan;
    }
}
