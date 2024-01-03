package src.test.control;

import src.main.control.HoatDongControl;
import src.main.entity.HoatDong;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.random.RandomGenerator;

public class HoatDongControlTestValue extends HoatDongControl {
    @Override
    public ArrayList<HoatDong> getList() {
        ArrayList<HoatDong> list = new ArrayList<>();

        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());
        list.add(createDummyHoatDong());

        return list;
    }

    public static HoatDong createDummyHoatDong() {
        HoatDong hoatDong = new HoatDong();
        hoatDong.maHoatDong = String.valueOf(hoatDong.hashCode());

        hoatDong.cccdNguoiDangKi = randomCCCD();

        hoatDong.csvcSuDung.add(CoSoVatChatControlTestValue.createDummyCoSoVatChat());
        hoatDong.csvcSuDung.add(CoSoVatChatControlTestValue.createDummyCoSoVatChat());
        hoatDong.csvcSuDung.add(CoSoVatChatControlTestValue.createDummyCoSoVatChat());
        hoatDong.csvcSuDung.add(CoSoVatChatControlTestValue.createDummyCoSoVatChat());

        hoatDong.phongbanSuDung.add(PhongBanControlTestValue.createDummyPhongBan());
        hoatDong.phongbanSuDung.add(PhongBanControlTestValue.createDummyPhongBan());

        hoatDong.ngayBatDau = randomDate();
        hoatDong.ngayKetThuc = randomDate();

        return hoatDong;
    }

    private static int randomInt(int min, int max) {
        return (int) Math.floor(Math.random()*(max - min + 1) + min);
    }

    private static String randomCCCD() {
        StringBuilder result = new StringBuilder("0");
        int x = randomInt(1, 96);
        result.append((x < 10) ? "0" + x : String.valueOf(x));

        x = randomInt(0, 3);
        result.append(x);

        x = randomInt(0, 99);
        result.append((x < 10) ? "0" + x : String.valueOf(x));
        result.append("00");

        x = randomInt(1000, 9999);
        result.append(x);

        return String.valueOf(result);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static String randomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return (gc.get(gc.DAY_OF_MONTH) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.YEAR));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println(randomCCCD());
        }
    }
}
