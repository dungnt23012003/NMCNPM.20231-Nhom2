package src.main.entity;

import java.util.ArrayList;
import java.util.Date;


public class HoKhau {
    public String maHoKhau;
    public NhanKhau chuHo = new NhanKhau();
    public String khuVuc;
    public String diaChi;
    public String ngayLap;
    public ArrayList<NhanKhau> listNhanKhau = new ArrayList<>();
}
