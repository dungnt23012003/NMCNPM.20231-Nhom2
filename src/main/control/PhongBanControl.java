package src.main.control;

import src.main.boundary.cosovatchat.PhongBanListAdapter;
import src.main.boundary.cosovatchat.PhongBanView;
import src.main.entity.CoSoVatChat;
import src.main.entity.PhongBan;

import java.sql.*;
import java.util.ArrayList;
import static src.main.control.ConnectionConfig.connect_to_sql_server;
public class PhongBanControl {
     public PhongBanView phongBanView;
    public ArrayList<PhongBan> searchPhongBan(String dieuKien) {
        ArrayList<PhongBan> list = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("select * from phongban where ma_phong_ban like '%%%s%%'", dieuKien);
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                PhongBan phongBanTmp = new PhongBan();
                phongBanTmp.maPhongBan = resultSet.getString(1);
                list.add(phongBanTmp);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<PhongBan> getList() {
        ArrayList<PhongBan> list = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = "select * from phongban";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                PhongBan phongBanTmp = new PhongBan();
                phongBanTmp.maPhongBan = resultSet.getString(1);
                list.add(phongBanTmp);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void add(PhongBan item){
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("insert into phongban values (N'%s')", item.maPhongBan);
            statement.execute(sql);
            phongBanView.refreshUI();
            phongBanView.showMessage("Thêm phòng ban thành công.");
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(PhongBan item){
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String checkBeforeDelete = String.format("select hoat_dong.ma_hoat_dong\n" +
                    "from hoat_dong\n" +
                    "join hd_phongban on hoat_dong.ma_hoat_dong = hd_phongban.ma_hoat_dong\n" +
                    "where DATEDIFF(dd, ngay_kt, GETDATE()) <= 0 and hd_phongban.ma_phong_ban = N'%s'", item.maPhongBan);

            System.out.println(checkBeforeDelete);
            ResultSet resultSet = statement.executeQuery(checkBeforeDelete);

            String announcement = "Không thể xóa phòng ban này có những hoạt động sau sử dụng:<br/>";

            while(resultSet.next()){
                announcement = announcement + resultSet.getString(1) + ".<br/>";
            }

            if(!announcement.equals("Không thể xóa phòng ban này có những hoạt động sau sử dụng:<br/>")){
                System.out.println(announcement);
            }
            else{
                String sql = String.format("delete from hd_phongban where ma_phong_ban = N'%s';", item.maPhongBan);
                System.out.println(sql);
                statement.execute(sql);

                sql = String.format("delete from phongban where ma_phong_ban = N'%s';", item.maPhongBan);
                System.out.println(sql);
                statement.execute(sql);
                phongBanView.refreshUI();
                phongBanView.showMessage("Xóa phòng ban thành công.");
            }


            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(PhongBan old_item, PhongBan new_item){
        ArrayList<String> hoatDongEdit = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("select ma_hoat_dong from hd_phongban where ma_phong_ban = N'%s';", old_item.maPhongBan);
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String hd = new String();
                hd = resultSet.getString(1);
                hoatDongEdit.add(hd);
            }

            sql = String.format("delete from hd_phongban where ma_phong_ban = N'%s'", old_item.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);

            sql = String.format("update phongban set ma_phong_ban = N'%s' where ma_phong_ban = N'%s'", new_item.maPhongBan, old_item.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);

            for(String x:hoatDongEdit){
                sql = String.format("insert into hd_phongban values (N'%s', N'%s')", x, new_item.maPhongBan);
                System.out.println(sql);
                statement.execute(sql);
            }
            phongBanView.refreshUI();
            phongBanView.showMessage("Chỉnh sửa phòng ban thành công.");
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void setPhongBanView(PhongBanView phongBanView) {
        this.phongBanView = phongBanView;
    }
}
