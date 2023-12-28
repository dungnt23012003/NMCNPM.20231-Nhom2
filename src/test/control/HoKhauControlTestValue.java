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
        hoKhau1.maHoKhau = "1";
        hoKhau1.khuVuc = "HN001";
        hoKhau1.diaChi = "Dia chi 1";
        hoKhau1.ngayLap = "27-12-2023";

        hoKhau1.chuHo = new NhanKhau();
        hoKhau1.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau1);

        HoKhau hoKhau2 = new HoKhau();
        hoKhau2.maHoKhau = "2";
        hoKhau2.khuVuc = "HN002";
        hoKhau2.diaChi = "Dia chi gi day";
        hoKhau2.ngayLap = "27-12-2023";

        hoKhau2.chuHo = new NhanKhau();
        hoKhau2.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau2);

        HoKhau hoKhau3 = new HoKhau();
        hoKhau3.maHoKhau = "3";
        hoKhau3.khuVuc = "HN003";
        hoKhau3.diaChi = "Dia chi 3";
        hoKhau3.ngayLap = "27-12-2023";

        hoKhau3.chuHo = new NhanKhau();
        hoKhau3.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau3);

        HoKhau hoKhau4 = new HoKhau();
        hoKhau4.maHoKhau = "4";
        hoKhau4.khuVuc = "HN004";
        hoKhau4.diaChi = "Dia chi gi day";
        hoKhau4.ngayLap = "27-12-2023";

        hoKhau4.chuHo = new NhanKhau();
        hoKhau4.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau4);

        HoKhau hoKhau5 = new HoKhau();
        hoKhau5.maHoKhau = "5";
        hoKhau5.khuVuc = "HN005";
        hoKhau5.diaChi = "Dia chi 5";
        hoKhau5.ngayLap = "27-12-2023";

        hoKhau5.chuHo = new NhanKhau();
        hoKhau5.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau5);

        HoKhau hoKhau6 = new HoKhau();
        hoKhau6.maHoKhau = "6";
        hoKhau6.khuVuc = "HN006";
        hoKhau6.diaChi = "Dia chi gi day";
        hoKhau6.ngayLap = "27-12-2023";

        hoKhau6.chuHo = new NhanKhau();
        hoKhau6.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau6);

        HoKhau hoKhau7 = new HoKhau();
        hoKhau7.maHoKhau = "7";
        hoKhau7.khuVuc = "HN007";
        hoKhau7.diaChi = "Dia chi 7";
        hoKhau7.ngayLap = "27-12-2023";

        hoKhau7.chuHo = new NhanKhau();
        hoKhau7.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau7);

        HoKhau hoKhau8 = new HoKhau();
        hoKhau8.maHoKhau = "8";
        hoKhau8.khuVuc = "HN008";
        hoKhau8.diaChi = "Dia chi gi day";
        hoKhau8.ngayLap = "27-12-2023";

        hoKhau8.chuHo = new NhanKhau();
        hoKhau8.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau8);

        HoKhau hoKhau9 = new HoKhau();
        hoKhau9.maHoKhau = "9";
        hoKhau9.khuVuc = "HN009";
        hoKhau9.diaChi = "Dia chi 9";
        hoKhau9.ngayLap = "27-12-2023";

        hoKhau9.chuHo = new NhanKhau();
        hoKhau9.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau9);

        HoKhau hoKhau10 = new HoKhau();
        hoKhau10.maHoKhau = "10";
        hoKhau10.khuVuc = "HN0010";
        hoKhau10.diaChi = "Dia chi gi day";
        hoKhau10.ngayLap = "27-12-2023";

        hoKhau10.chuHo = new NhanKhau();
        hoKhau10.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau10);

        HoKhau hoKhau11 = new HoKhau();
        hoKhau11.maHoKhau = "11";
        hoKhau11.khuVuc = "HN0011";
        hoKhau11.diaChi = "Dia chi 11";
        hoKhau11.ngayLap = "27-12-2023";

        hoKhau11.chuHo = new NhanKhau();
        hoKhau11.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau11);

        HoKhau hoKhau12 = new HoKhau();
        hoKhau12.maHoKhau = "12";
        hoKhau12.khuVuc = "HN0012";
        hoKhau12.diaChi = "Dia chi gi day";
        hoKhau12.ngayLap = "27-12-2023";
        hoKhau12.chuHo = new NhanKhau();
        hoKhau12.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau12);

        HoKhau hoKhau13 = new HoKhau();
        hoKhau13.maHoKhau = "14";
        hoKhau13.khuVuc = "HN0013";
        hoKhau13.diaChi = "Dia chi 13";
        hoKhau13.ngayLap = "27-12-2023";

        hoKhau13.chuHo = new NhanKhau();
        hoKhau13.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau13);

        HoKhau hoKhau14 = new HoKhau();
        hoKhau14.maHoKhau = "14";
        hoKhau14.khuVuc = "HN0014";
        hoKhau14.diaChi = "Dia chi gi day";
        hoKhau14.ngayLap = "27-12-2023";

        hoKhau14.chuHo = new NhanKhau();
        hoKhau14.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau14);

        HoKhau hoKhau15 = new HoKhau();
        hoKhau15.maHoKhau = "15";
        hoKhau15.khuVuc = "HN0015";
        hoKhau15.diaChi = "Dia chi 15";
        hoKhau15.ngayLap = "27-12-2023";

        hoKhau15.chuHo = new NhanKhau();
        hoKhau15.chuHo.hoTen = "Nguyễn Văn A";

        hoKhauList.add(hoKhau15);

        HoKhau hoKhau16 = new HoKhau();
        hoKhau16.maHoKhau = "16";
        hoKhau16.khuVuc = "HN0016";
        hoKhau16.diaChi = "Dia chi gi day";
        hoKhau16.ngayLap = "27-12-2023";

        hoKhau16.chuHo = new NhanKhau();
        hoKhau16.chuHo.hoTen = "Trần Văn B";

        hoKhauList.add(hoKhau16);

        return hoKhauList;
    }

    @Override
    public void delete(HoKhau entity) {
        System.out.println("Deleted " + entity.maHoKhau);
    }
}
