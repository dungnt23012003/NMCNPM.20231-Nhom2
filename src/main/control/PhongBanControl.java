package src.main.control;

import src.main.boundary.cosovatchat.PhongBanListAdapter;
import src.main.entity.CoSoVatChat;
import src.main.entity.PhongBan;

import java.sql.*;
import java.util.ArrayList;

public class PhongBanControl {
    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public ArrayList<PhongBan> getList() {
        ArrayList<PhongBan> list = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = "select * from phong_ban";
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

            String sql = String.format("insert into phong_ban values (N'%s')", item.maPhongBan);
            statement.execute(sql);
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

            String sql = String.format("delete from hd_phongban where ma_phong_ban = N'%s';", item.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);

            sql = String.format("delete from phong_ban where ma_phong_ban = N'%s';", item.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);

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

            sql = String.format("update phong_ban set ma_phong_ban = N'%s' where ma_phong_ban = N'%s'", new_item.maPhongBan, old_item.maPhongBan);
            System.out.println(sql);
            statement.execute(sql);

            for(String x:hoatDongEdit){
                sql = String.format("insert into hd_phongban values (N'%s', N'%s')", x, new_item.maPhongBan);
                System.out.println(sql);
                statement.execute(sql);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
