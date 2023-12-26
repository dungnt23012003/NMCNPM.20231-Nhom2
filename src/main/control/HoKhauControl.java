package src.main.control;

import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoKhauControl {
    public static Statement statement_connect_to_sql_server() throws SQLException, ClassNotFoundException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            String username = "nmcnpm_user";
            String password = "nmcnpm2023";
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection.createStatement();
    }
    public ArrayList<HoKhau> getList() {
        return null;
    }

    public ArrayList<NhanKhau> getNhanKhauList() {
        return null;
    }

    public void add(HoKhau entity) {
        try{
            String sql = "insert into ho_khau values(";
            sql = sql + entity.maHoKhau;
            statement_connect_to_sql_server().execute(sql);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void delete(HoKhau entity) {

    }

    public void update(HoKhau old_entity, HoKhau new_entity) {

    }

    public static void main(String[] args) {
        HoKhau hoKhau1 = new HoKhau();
        hoKhau1.maHoKhau = "1";
        hoKhau1.khuVuc = "HN001";
        hoKhau1.diaChi = "Dia chi 1";
        hoKhau1.ngayLap = new SimpleDateFormat("22122023").get2DigitYearStart();

        hoKhau1.chuHo = new NhanKhau();
        hoKhau1.chuHo.hoTen = "Nguyễn Văn A";

         HoKhauControl control = new HoKhauControl();
         control.add(hoKhau1);
    }
}
