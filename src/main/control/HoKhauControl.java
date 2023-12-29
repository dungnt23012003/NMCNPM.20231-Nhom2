package src.main.control;

import src.main.boundary.hokhau.HoKhauView;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HoKhauControl {

    public HoKhauView view;
    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public ArrayList<HoKhau> getList() {
        ArrayList<HoKhau> dsHoKhau = new ArrayList<HoKhau>();
        try{
            String sql = "select * from ho_khau;";
            System.out.println(sql);

            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();
            ResultSet dsHK = statement.executeQuery(sql);

            while(dsHK.next()){
                //Tạo ra hộ khẩu tạm thời
                HoKhau hoKhauTmp = new HoKhau();
                //Lấy mã hộ khẩu
                hoKhauTmp.maHoKhau = dsHK.getString(1);
                //Tìm chủ hộ trong danh sách nhân khẩu
                String timChuHo = "select * from nhan_khau where cccd = ";
                timChuHo = timChuHo + dsHK.getString(2) + ";";
                System.out.println(timChuHo);
                hoKhauTmp.khuVuc = dsHK.getString(3);
                hoKhauTmp.diaChi = dsHK.getString(4);
                hoKhauTmp.ngayLap = dsHK.getString(5);
                if (hoKhauTmp.ngayLap!=null){
                    String year = hoKhauTmp.ngayLap.substring(0,4);
                    String month = hoKhauTmp.ngayLap.substring(5,7);
                    String day = hoKhauTmp.ngayLap.substring(8,10);
                    hoKhauTmp.ngayLap = day + "-" + month + "-" + year;
                }
                else{
                    hoKhauTmp.ngayLap = "";
                }
                Statement statement1 = connection.createStatement();
                ResultSet chuHo = statement1.executeQuery(timChuHo);
                //Lưu thông tin chủ hộ vào nhân khẩu tạm thời

                chuHo.next();

                NhanKhau nhanKhauTmp = new NhanKhau();
                nhanKhauTmp.CCCD = chuHo.getString(1);
                nhanKhauTmp.hoTen = chuHo.getString(2);
                nhanKhauTmp.namSinh = chuHo.getString(3);
                if (nhanKhauTmp.namSinh!=null){
                    String year = nhanKhauTmp.namSinh.substring(0,4);
                    String month = nhanKhauTmp.namSinh.substring(5,7);
                    String day = nhanKhauTmp.namSinh.substring(8,10);
                    nhanKhauTmp.namSinh = day + "-" + month + "-" + year;
                }
                else{
                    nhanKhauTmp.namSinh = "";
                }
                nhanKhauTmp.gioiTinh = chuHo.getString(4);
                nhanKhauTmp.nguyenQuan = chuHo.getString(5);
                nhanKhauTmp.danToc = chuHo.getString(6);
                nhanKhauTmp.tonGiao = chuHo.getString(7);
                nhanKhauTmp.quocTich = chuHo.getString(8);
                nhanKhauTmp.noiThuongTru = chuHo.getString(9);

                //lưu chủ hộ của hộ khẩu tạm thời
                hoKhauTmp.chuHo = nhanKhauTmp;
                //tìm danh sách nhân khẩu trong hộ khẩu
                String timNhanKhauTrongHoKhau = "select * from nhan_khau inner join hk_nk on nhan_khau.cccd = hk_nk.cccd_nhan_khau where  hk_nk.ma_ho_khau = ";
                timNhanKhauTrongHoKhau = timNhanKhauTrongHoKhau + hoKhauTmp.maHoKhau + ";";
                System.out.println(timNhanKhauTrongHoKhau);
                Statement statement2 = connection.createStatement();
                ResultSet dsNK = statement2.executeQuery(timNhanKhauTrongHoKhau);

                //đẩy các nhân khẩu vào trong hộ khẩu
                while(dsNK.next()){
                    NhanKhau nhanKhauTmp1 = new NhanKhau();
                    nhanKhauTmp1.CCCD = dsNK.getString(1);
                    nhanKhauTmp1.hoTen = dsNK.getString(2);
                    nhanKhauTmp1.namSinh = dsNK.getString(3);
                    if (nhanKhauTmp1.namSinh!=null){
                        String year = nhanKhauTmp1.namSinh.substring(0,4);
                        String month = nhanKhauTmp1.namSinh.substring(5,7);
                        String day = nhanKhauTmp1.namSinh.substring(8,10);
                        nhanKhauTmp1.namSinh = day + "-" + month + "-" + year;
                    }
                    else{
                        nhanKhauTmp1.namSinh = "";
                    }
                    nhanKhauTmp1.gioiTinh = dsNK.getString(4);
                    nhanKhauTmp1.nguyenQuan = dsNK.getString(5);
                    nhanKhauTmp1.danToc = dsNK.getString(6);
                    nhanKhauTmp1.tonGiao = dsNK.getString(7);
                    nhanKhauTmp1.quocTich = dsNK.getString(8);
                    nhanKhauTmp1.noiThuongTru = dsNK.getString(9);
                    nhanKhauTmp1.quanHeVoiChuHo = dsNK.getString(12);
                    hoKhauTmp.listNhanKhau.add(nhanKhauTmp1);
                }



                //đẩy hoKhauTmp vào dsHoKhau

                dsHoKhau.add(hoKhauTmp);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return dsHoKhau;
    }

    public ArrayList<NhanKhau> getNhanKhauVoGiaCuList() {
        ArrayList<NhanKhau> dsNhanKhau = new ArrayList<NhanKhau>();
        try{
            String sql = "select * from nhan_khau where cccd not in (select cccd_nhan_khau from hn_nk)";
            System.out.println(sql);
            Connection connection = connect_to_sql_server();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while(rs.next()){
                NhanKhau nhanKhauTmp = new NhanKhau();
                nhanKhauTmp.CCCD = rs.getString(1);
                nhanKhauTmp.hoTen = rs.getString(2);
                nhanKhauTmp.namSinh = rs.getString(3);
                if (nhanKhauTmp.namSinh!=null){
                    String year = nhanKhauTmp.namSinh.substring(0,4);
                    String month = nhanKhauTmp.namSinh.substring(5,7);
                    String day = nhanKhauTmp.namSinh.substring(8,10);
                    nhanKhauTmp.namSinh = day + "-" + month + "-" + year;
                }
                else{
                    nhanKhauTmp.namSinh = "";
                }
                nhanKhauTmp.gioiTinh = rs.getString(4);
                nhanKhauTmp.nguyenQuan = rs.getString(5);
                nhanKhauTmp.danToc = rs.getString(6);
                nhanKhauTmp.tonGiao = rs.getString(7);
                nhanKhauTmp.quocTich = rs.getString(8);
                nhanKhauTmp.noiThuongTru = rs.getString(9);
                nhanKhauTmp.quanHeVoiChuHo = "";
                dsNhanKhau.add(nhanKhauTmp);
            }
            connection.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return dsNhanKhau;
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
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            for(NhanKhau x: entity.listNhanKhau){
                sql = "insert into hk_nk values(";
                sql = sql + "'" + entity.maHoKhau + "'" + ", ";
                sql = sql + "'" + x.CCCD + "'" + ", ";
                sql = sql + "'" + x.quanHeVoiChuHo + "'" + ");";
                System.out.println(sql);
                statement.execute(sql);
            }
            this.view.refreshUI();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void delete(HoKhau entity) {
        try{
            String sql = "delete from hk_nk where ma_ho_khau = ";
            sql = sql + entity.maHoKhau;
            System.out.println(sql);
            Connection connection = connect_to_sql_server();
            connection.createStatement().execute(sql);
            sql = "delete from ho_khau where ma_ho_khau = ";
            sql = sql + entity.maHoKhau;
            connection.createStatement().execute(sql);
            this.view.refreshUI();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void update(HoKhau old_entity, HoKhau new_entity) {
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = "delete from hk_nk where ma_ho_khau = ";
            sql = sql + old_entity.maHoKhau + ";";
            System.out.println(sql);
            statement.execute(sql);

            sql = "update ho_khau set ";
            sql = sql + "cccd_chu_ho = " + "'" + new_entity.chuHo.CCCD + "'" + ", ";
            sql = sql + "khu_vuc = " + "'" + new_entity.khuVuc + "'" + ", ";
            sql = sql + "dia_chi = " + "'" + new_entity.diaChi + "'" + ", ";
            String day = new_entity.ngayLap.substring(0,2);
            String month = new_entity.ngayLap.substring(3,5);
            String year = new_entity.ngayLap.substring(6,10);
            sql = sql + "ngay_lap = " + "'" + year + "-" + month + "-" + day + "'";
            sql = sql + " where ma_ho_khau = " + old_entity.maHoKhau + ";";
            System.out.println(sql);
            statement.execute(sql);

            for(NhanKhau x: new_entity.listNhanKhau){
                sql = "insert into hk_nk values(";
                sql = sql + "'" + new_entity.maHoKhau + "'" + ", ";
                sql = sql + "'" + x.CCCD + "'" + ", ";
                sql = sql + "'" + x.quanHeVoiChuHo + "'" + ");";
                System.out.println(sql);
                statement.execute(sql);
            }

            this.view.refreshUI();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void setView(HoKhauView view){
        this.view = view;
    }

}
