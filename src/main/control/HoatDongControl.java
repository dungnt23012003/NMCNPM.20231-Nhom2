package src.main.control;

import src.main.boundary.hoatdong.HoatDongView;
import src.main.boundary.hokhau.HoKhauView;
import src.main.entity.*;

import java.sql.*;
import java.util.ArrayList;

public class HoatDongControl {
    public HoatDongView view;
    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public ArrayList<HoatDong> getList() {
        ArrayList<HoatDong> list = new ArrayList<HoatDong>();

        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = "select * from hoat_dong;";
            System.out.println(sql);
            ResultSet dsHD = statement.executeQuery(sql);

            while(dsHD.next()){
                HoatDong hoatDongTmp = new HoatDong();
                hoatDongTmp.maHoatDong = dsHD.getString(1);
                hoatDongTmp.cccdNguoiDangKi = dsHD.getString(2);
                hoatDongTmp.ngayBatDau = dsHD.getString(3);
                hoatDongTmp.ngayKetThuc = dsHD.getString(4);

                if (hoatDongTmp.ngayBatDau!=null){
                    String year = hoatDongTmp.ngayBatDau.substring(0,4);
                    String month = hoatDongTmp.ngayBatDau.substring(5,7);
                    String day = hoatDongTmp.ngayBatDau.substring(8,10);
                    hoatDongTmp.ngayBatDau = day + "-" + month + "-" + year;
                }
                else{
                    hoatDongTmp.ngayBatDau = "";
                }

                if (hoatDongTmp.ngayKetThuc!=null){
                    String year = hoatDongTmp.ngayKetThuc.substring(0,4);
                    String month = hoatDongTmp.ngayKetThuc.substring(5,7);
                    String day = hoatDongTmp.ngayKetThuc.substring(8,10);
                    hoatDongTmp.ngayKetThuc = day + "-" + month + "-" + year;
                }
                else{
                    hoatDongTmp.ngayKetThuc = "";
                }

                String timPhongBan = "select * from hd_Phongban where ma_hoat_dong = ";
                timPhongBan = timPhongBan + hoatDongTmp.maHoatDong + ";";
                System.out.println(timPhongBan);

                Statement statement1 = connection.createStatement();
                ResultSet phongBan = statement1.executeQuery(timPhongBan);

                while(phongBan.next()){
                    PhongBan phongBanTmp = new PhongBan();
                    phongBanTmp.maPhongBan = phongBan.getString(1);
                    hoatDongTmp.phongbanSuDung.add(phongBanTmp);
                }

                String timCSVC = "select * from hd_csvc where ma_hoat_dong = ";
                timCSVC = timCSVC + hoatDongTmp.maHoatDong + ";";
                System.out.println(timCSVC);

                Statement statement2 = connection.createStatement();
                ResultSet coSoVatChat = statement2.executeQuery(timCSVC);

                while(coSoVatChat.next()){
                    CoSoVatChat coSoVatChatTmp = new CoSoVatChat();
                    coSoVatChatTmp.maCSVC = coSoVatChat.getString(1);
                    coSoVatChatTmp.soLuong = coSoVatChat.getInt(2);
                    hoatDongTmp.csvcSuDung.add(coSoVatChatTmp);
                }
                list.add(hoatDongTmp);
            }
            connection.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void add(HoatDong item) {
        try{
            String sql = "insert into hoat_dong values(";
            sql = sql + "'" + item.maHoatDong + "'" + ", ";
            sql = sql + "'" + item.cccdNguoiDangKi + "'" + ", ";

            String day = "";
            String month = "";
            String year = "";
            if(!item.ngayBatDau.equals("")){
                 day = item.ngayBatDau.substring(0,2);
                 month = item.ngayBatDau.substring(3,5);
                 year = item.ngayBatDau.substring(6,10);
            }

            sql = sql + "'" + year + "-" + month + "-" + day + "'" + ", ";

            day = "";
            month = "";
            year = "";
            if(!item.ngayBatDau.equals("")){
                day = item.ngayKetThuc.substring(0,2);
                month = item.ngayKetThuc.substring(3,5);
                year = item.ngayKetThuc.substring(6,10);
            }

            sql = sql + "'" + year + "-" + month + "-" + day + "'" + ");";
            System.out.println(sql);
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();
            statement.execute(sql);

            for(CoSoVatChat x: item.csvcSuDung){
                sql = "insert into hd_csvc values(";
                sql = sql + "N'" + item.maHoatDong + "'" + ", ";
                sql = sql + "N'" + x.maCSVC + "'" + ", ";
                sql = sql +  x.soLuong +  ");";
                System.out.println(sql);
                statement.execute(sql);
            }

            for(PhongBan x: item.phongbanSuDung){
                sql = "insert into hd_phongban values(";
                sql = sql + "N'" + item.maHoatDong + "'" + ", ";
                sql = sql + "N'" + x.maPhongBan + "'" + "); ";
                System.out.println(sql);
                statement.execute(sql);
            }



            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void update(HoatDong oldItem, HoatDong newItem) {
        try{
        Connection connection = connect_to_sql_server();
        Statement statement = connection.createStatement();

        String sql = String.format("delete from hd_csvc where ma_hoat_dong = %s;", oldItem.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        sql = String.format("delete from hd_phongban where ma_hoat_dong = %s;", oldItem.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        String ngayBatDau = newItem.ngayBatDau.substring(6, 10) + "-" + newItem.ngayBatDau.substring(3, 5) + "-" + newItem.ngayBatDau.substring(0, 2);
        String ngayKetThuc = newItem.ngayKetThuc.substring(6, 10) + "-" + newItem.ngayKetThuc.substring(3, 5) + "-" + newItem.ngayKetThuc.substring(0, 2);
        sql = String.format("update hoat_dong set ma_hoat_dong = N'%s', cccd_nk_dang_ki = '%s', ngay_bd = '%s', ngay_kt = '%s' where ma_hoat_dong = '%s';",newItem.maHoatDong, newItem.cccdNguoiDangKi, ngayBatDau, ngayKetThuc, oldItem.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        for(CoSoVatChat x: newItem.csvcSuDung){
            sql = String.format("insert into hd_csvc values(N'%s', %d)", x.maCSVC, x.soLuong);
            System.out.println(sql);
            statement.execute(sql);
        }

        for(PhongBan x: newItem.phongbanSuDung){
            sql = String.format("insert into hd_phongban values(N'%s')", x.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);
        }

        connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(HoatDong item) {
        try{
        Connection connection = connect_to_sql_server();
        Statement statement = connection.createStatement();

        String sql = String.format("delete from hd_csvc where ma_hoat_dong = %s;", item.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        sql = String.format("delete from hd_phongban where ma_hoat_dong = %s;", item.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        sql = String.format("delete from hoat_dong where ma_hoat_dong = %s;", item.maHoatDong);
        System.out.println(sql);
        statement.execute(sql);

        connection.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void setView(HoatDongView view){
        this.view = view;
    }
}
