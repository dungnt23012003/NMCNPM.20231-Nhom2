package src.test.control;

import src.main.boundary.cosovatchat.PhongBanView;
import src.main.control.PhongBanControl;
import src.main.entity.PhongBan;

import java.util.ArrayList;

public class PhongBanControlTestValue extends PhongBanControl {
    @Override
    public ArrayList<PhongBan> getList() {
        ArrayList<PhongBan> result = new ArrayList<>();

        for (int i = 0; i < 20; ++i) {
            result.add(createDummyPhongBan());
        }

        return result;
    }

    private PhongBan createDummyPhongBan() {
        PhongBan phongBan = new PhongBan();
        phongBan.maPhongBan = phongBan.toString();

        return phongBan;
    }
}
