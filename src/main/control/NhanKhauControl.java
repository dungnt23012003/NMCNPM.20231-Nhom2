package src.main.control;

//import src.main.boundary.nhankhau.NhanKhauModel;
import src.main.boundary.nhankhau.NhanKhauView;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import java.sql.*;
import java.util.ArrayList;


public class NhanKhauControl {
    NhanKhauView view;

    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public ArrayList<NhanKhau> getList() {
        ArrayList<NhanKhau> dsNhanKhau = new ArrayList<NhanKhau>();
        try{
            String sql = "select * from nhan_khau order by cccd asc;";
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
                dsNhanKhau.add(nhanKhauTmp);
            }
            connection.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return dsNhanKhau;
    }

    public void add(NhanKhau entity) {
        try{
            String sql = "insert into nhan_khau values(";
            sql = sql + "'" + entity.CCCD + "'" + ", ";
            sql = sql + "N'" + entity.hoTen + "'" + ", ";
            String day = entity.namSinh.substring(0,2);
            String month = entity.namSinh.substring(3,5);
            String year = entity.namSinh.substring(6,10);
            sql = sql + "'" + year + "-" + month + "-" + day + "'" + ", ";
            sql = sql + "N'" +  entity.gioiTinh  + "'" + ", ";
            sql = sql + "N'" + entity.nguyenQuan  + "'" + ", ";
            sql = sql + "N'" + entity.danToc  + "'" + ", ";
            sql = sql + "N'" + entity.tonGiao  + "'" + ", ";
            sql = sql + "N'" + entity.quocTich  + "'" + ", ";
            sql = sql + "N'" + entity.noiThuongTru  + "'" + ");";
            System.out.println(sql);
            Connection connection = connect_to_sql_server();
            connection.createStatement().execute(sql);
            this.view.refreshUI();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void delete(NhanKhau entity) {
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();
            String information = "";

            String sql = "select * from hk_nk where cccd_nhan_khau like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa nhân khẩu trong hộ khẩu " + result.getString(1) + ".<br/>";
            }

            sql = "select * from ho_khau where cccd_chu_ho like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa hộ khẩu " + result.getString(1) + " vì nhân khẩu đang là chủ hộ" + ".<br/>";
            }

            sql = "select * from hoat_dong where cccd_nk_dang_ki like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa hoạt động " + result.getString(1) + " vì nhân khẩu đang kí hoạt động này" + ".<br/>";
            }

            sql = "select * from tam_tru where cccd_nhan_khau like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa tạm trú " + result.getString(1) + " vì nhân khẩu đang đăng kí tạm trú" + ".<br/>";
            }

            sql = "select * from tam_vang where cccd_nhan_khau like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa giấy tạm vắng " + result.getString(1) + " vì nhân khẩu đang đăng kí tạm vắng" + ".<br/>";
            }

            sql = "select * from khai_tu where cccd_nguoi_mat like ";
            sql = sql + "'" + entity.CCCD + "'" + " or cccd_nguoi_khai like " ;
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            result = statement.executeQuery(sql);
            if (result.next()){
                information = information + "Xóa nhân khẩu " + entity.hoTen + " sẽ xóa giấy khai tử " + result.getString(1) + " vì nhân khẩu có trong giấy khai tử" + ".<br/>";
            }
            if(!information.equals("")){
                if(this.view.showConfirmDialog("<html>" + information + "</html>")){
                    sql = "delete from hk_nk where cccd_nhan_khau like ";
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);

                    sql = "delete from ho_khau where cccd_chu_ho like ";
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);

                    sql = "delete from hoat_dong where cccd_nk_dang_ki like ";
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);

                    sql = "delete from tam_tru where cccd_nhan_khau like ";
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);

                    sql = "delete from tam_vang where cccd_nhan_khau like ";
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);

                    sql = "delete from khai_tu where cccd_nguoi_mat like ";
                    sql = sql + "'" + entity.CCCD + "'" + " or cccd_nguoi_khai like " ;
                    sql = sql + "'" + entity.CCCD + "'" + ";";
                    System.out.println(sql);
                    statement.execute(sql);
                }
            }

            sql = "delete from nhan_khau where cccd like ";
            sql = sql + "'" + entity.CCCD + "'" + ";";
            System.out.println(sql);
            statement.execute(sql);

            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(NhanKhau old_entity, NhanKhau new_entity) {
        try{
            String sql = "update nhan_khau set ";
            sql = sql + "ho_ten = " + "N'" + new_entity.hoTen + "'" + ", ";
            String day = new_entity.namSinh.substring(0,2);
            String month = new_entity.namSinh.substring(3,5);
            String year = new_entity.namSinh.substring(6,10);
            sql = sql + "nam_sinh = " + "'" + year + "-" + month + "-" + day + "'" + ", ";
            sql = sql + "gioi_tinh = " + "N'" +  new_entity.gioiTinh  + "'" + ", ";
            sql = sql + "nguyen_quan = " + "N'" + new_entity.nguyenQuan  + "'" + ", ";
            sql = sql + "dan_toc = " + "N'" + new_entity.danToc  + "'" + ", ";
            sql = sql + "ton_giao = " + "N'" + new_entity.tonGiao  + "'" + ", ";
            sql = sql + "quoc_tich = " + "N'" + new_entity.quocTich  + "'" + ", ";
            sql = sql + "noi_thuong_tru = " + "N'" + new_entity.noiThuongTru  + "'" + " ";
            sql = sql + "where cccd = " + old_entity.CCCD + ";";
            System.out.println(sql);
            Connection connection = connect_to_sql_server();
            connection.createStatement().execute(sql);
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setView(NhanKhauView view) {
        this.view = view;
    }

}
