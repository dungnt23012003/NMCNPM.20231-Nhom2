package src.test.control;

import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;

public class HoKhauControlTestValue extends HoKhauControl {
    @Override
    public ArrayList<HoKhau> getList() {
        ArrayList<HoKhau> hoKhauList = new ArrayList<>();

        Date date = new Date();

        HoKhau hoKhau1 = new HoKhau();
        hoKhau1.soHoKhau = 1;
        hoKhau1.khuVuc = "HN001";
        hoKhau1.diaChi = "Dia chi 1";
        hoKhau1.ngayLap = new SimpleDateFormat("22122023").get2DigitYearStart();
        hoKhau1.chuHo = new NhanKhau();

        hoKhauList.add(hoKhau1);

        return hoKhauList;
    }
}
