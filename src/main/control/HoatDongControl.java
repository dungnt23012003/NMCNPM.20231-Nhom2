package src.main.control;

import src.main.entity.HoatDong;

import java.util.ArrayList;

public class HoatDongControl {
    public ArrayList<HoatDong> getList() {
        ArrayList<HoatDong> list = new ArrayList<>();

        HoatDong hoatDong1 = new HoatDong();
        hoatDong1.maHoatDong = "HD0001";
        hoatDong1.cccdNguoiDangKi = "12341234";
        hoatDong1.csvcSuDung = new ArrayList<>();
        hoatDong1.phongbanSuDung = new ArrayList<>();

        HoatDong hoatDong2 = new HoatDong();
        hoatDong2.maHoatDong = "HD0002";
        hoatDong2.cccdNguoiDangKi = "43214321";
        hoatDong2.csvcSuDung = new ArrayList<>();
        hoatDong2.phongbanSuDung = new ArrayList<>();

        list.add(hoatDong1);
        list.add(hoatDong2);

        return list;
    }

    public void add(HoatDong item) {

    }

    public void update(HoatDong oldItem, HoatDong newItem) {

    }

    public void delete(HoatDong item) {

    }
}
