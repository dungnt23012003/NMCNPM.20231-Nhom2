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
        hoKhau1.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau1);

        HoKhau hoKhau2 = new HoKhau();
        hoKhau2.soHoKhau = 2;
        hoKhau2.khuVuc = "HN002";
        hoKhau2.diaChi = "Dia chi gi day";
        hoKhau2.ngayLap = new SimpleDateFormat("231211").get2DigitYearStart();

        hoKhau2.chuHo = new NhanKhau();
        hoKhau2.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau2);

        HoKhau hoKhau3 = new HoKhau();
        hoKhau3.soHoKhau = 3;
        hoKhau3.khuVuc = "HN003";
        hoKhau3.diaChi = "Dia chi 3";
        hoKhau3.ngayLap = new SimpleDateFormat("44344043").get2DigitYearStart();

        hoKhau3.chuHo = new NhanKhau();
        hoKhau3.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau3);

        HoKhau hoKhau4 = new HoKhau();
        hoKhau4.soHoKhau = 4;
        hoKhau4.khuVuc = "HN004";
        hoKhau4.diaChi = "Dia chi gi day";
        hoKhau4.ngayLap = new SimpleDateFormat("433433").get2DigitYearStart();

        hoKhau4.chuHo = new NhanKhau();
        hoKhau4.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau4);

        HoKhau hoKhau5 = new HoKhau();
        hoKhau5.soHoKhau = 5;
        hoKhau5.khuVuc = "HN005";
        hoKhau5.diaChi = "Dia chi 5";
        hoKhau5.ngayLap = new SimpleDateFormat("66566067").get2DigitYearStart();

        hoKhau5.chuHo = new NhanKhau();
        hoKhau5.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau5);

        HoKhau hoKhau6 = new HoKhau();
        hoKhau6.soHoKhau = 6;
        hoKhau6.khuVuc = "HN006";
        hoKhau6.diaChi = "Dia chi gi day";
        hoKhau6.ngayLap = new SimpleDateFormat("675655").get2DigitYearStart();

        hoKhau6.chuHo = new NhanKhau();
        hoKhau6.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau6);

        HoKhau hoKhau7 = new HoKhau();
        hoKhau7.soHoKhau = 7;
        hoKhau7.khuVuc = "HN007";
        hoKhau7.diaChi = "Dia chi 7";
        hoKhau7.ngayLap = new SimpleDateFormat("88788087").get2DigitYearStart();

        hoKhau7.chuHo = new NhanKhau();
        hoKhau7.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau7);

        HoKhau hoKhau8 = new HoKhau();
        hoKhau8.soHoKhau = 8;
        hoKhau8.khuVuc = "HN008";
        hoKhau8.diaChi = "Dia chi gi day";
        hoKhau8.ngayLap = new SimpleDateFormat("877877").get2DigitYearStart();

        hoKhau8.chuHo = new NhanKhau();
        hoKhau8.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau8);

        HoKhau hoKhau9 = new HoKhau();
        hoKhau9.soHoKhau = 9;
        hoKhau9.khuVuc = "HN009";
        hoKhau9.diaChi = "Dia chi 9";
        hoKhau9.ngayLap = new SimpleDateFormat("10109101001011").get2DigitYearStart();

        hoKhau9.chuHo = new NhanKhau();
        hoKhau9.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau9);

        HoKhau hoKhau10 = new HoKhau();
        hoKhau10.soHoKhau = 10;
        hoKhau10.khuVuc = "HN0010";
        hoKhau10.diaChi = "Dia chi gi day";
        hoKhau10.ngayLap = new SimpleDateFormat("101191099").get2DigitYearStart();

        hoKhau10.chuHo = new NhanKhau();
        hoKhau10.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau10);

        HoKhau hoKhau11 = new HoKhau();
        hoKhau11.soHoKhau = 11;
        hoKhau11.khuVuc = "HN0011";
        hoKhau11.diaChi = "Dia chi 11";
        hoKhau11.ngayLap = new SimpleDateFormat("121211121201211").get2DigitYearStart();

        hoKhau11.chuHo = new NhanKhau();
        hoKhau11.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau11);

        HoKhau hoKhau12 = new HoKhau();
        hoKhau12.soHoKhau = 12;
        hoKhau12.khuVuc = "HN0012";
        hoKhau12.diaChi = "Dia chi gi day";
        hoKhau12.ngayLap = new SimpleDateFormat("121111121111").get2DigitYearStart();

        hoKhau12.chuHo = new NhanKhau();
        hoKhau12.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau12);

        HoKhau hoKhau13 = new HoKhau();
        hoKhau13.soHoKhau = 13;
        hoKhau13.khuVuc = "HN0013";
        hoKhau13.diaChi = "Dia chi 13";
        hoKhau13.ngayLap = new SimpleDateFormat("141413141401415").get2DigitYearStart();

        hoKhau13.chuHo = new NhanKhau();
        hoKhau13.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau13);

        HoKhau hoKhau14 = new HoKhau();
        hoKhau14.soHoKhau = 14;
        hoKhau14.khuVuc = "HN0014";
        hoKhau14.diaChi = "Dia chi gi day";
        hoKhau14.ngayLap = new SimpleDateFormat("141513141313").get2DigitYearStart();

        hoKhau14.chuHo = new NhanKhau();
        hoKhau14.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau14);

        HoKhau hoKhau15 = new HoKhau();
        hoKhau15.soHoKhau = 15;
        hoKhau15.khuVuc = "HN0015";
        hoKhau15.diaChi = "Dia chi 15";
        hoKhau15.ngayLap = new SimpleDateFormat("161615161601615").get2DigitYearStart();

        hoKhau15.chuHo = new NhanKhau();
        hoKhau15.chuHo.ten = "Nguyễn Văn A";

        hoKhauList.add(hoKhau15);

        HoKhau hoKhau16 = new HoKhau();
        hoKhau16.soHoKhau = 16;
        hoKhau16.khuVuc = "HN0016";
        hoKhau16.diaChi = "Dia chi gi day";
        hoKhau16.ngayLap = new SimpleDateFormat("161515161515").get2DigitYearStart();

        hoKhau16.chuHo = new NhanKhau();
        hoKhau16.chuHo.ten = "Trần Văn B";

        hoKhauList.add(hoKhau16);

        return hoKhauList;
    }

    @Override
    public void delete(HoKhau entity) {
        System.out.println("Deleted " + entity.soHoKhau);
    }
}
