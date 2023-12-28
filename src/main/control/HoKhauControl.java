package src.main.control;

import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

    public ArrayList<NhanKhau> getNhanKhauVoGiaCuList() {
        return null;
    }


    public void add(HoKhau entity) {
        try{
            String sql = "insert into ho_khau values(";
            sql = sql + "'" + entity.maHoKhau + "'" + ", ";
            sql = sql + "'" + entity.chuHo.CCCD + "'" + ", ";
            sql = sql + "'" +  entity.khuVuc  + "'" + ", ";
            sql = sql + "'" + entity.diaChi  + "'" + ", ";
            String day = entity.ngayLap.substring(0,2);
            String month = entity.ngayLap.substring(3,5);
            String year = entity.ngayLap.substring(6,10);
            sql = sql + "'" + year + "-" + month + "-" + day + "'" + ");";
            System.out.println(sql);
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
        hoKhau1.ngayLap = "27-12-2023";


        hoKhau1.chuHo.CCCD = "1";

        HoKhauControl control = new HoKhauControl();control.add(hoKhau1);

    }
}
