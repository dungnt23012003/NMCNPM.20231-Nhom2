package src.main.control;

import src.main.entity.CoSoVatChat;
import src.main.entity.HoatDong;

import java.sql.*;
import java.util.ArrayList;

public class CoSoVatChatControl {
    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public void add(CoSoVatChat item) {
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("insert into co_so_vat_chat value(N'%s', %d)", item.maCSVC, item.soLuong);
            System.out.println(sql);
            statement.execute(sql);
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(CoSoVatChat old_item, CoSoVatChat new_item) {
        ArrayList<HoatDong> hoatDongEdit = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("select * from hd_csvc where ma_csvc = N'%s';", old_item.maCSVC);
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                HoatDong hoatDongTmp = new HoatDong();
                hoatDongTmp.maHoatDong = resultSet.getString(1);
                CoSoVatChat coSoVatChatTmp = new CoSoVatChat();
                coSoVatChatTmp.maCSVC = new_item.maCSVC;
                coSoVatChatTmp.soLuong = resultSet.getInt(3);
                hoatDongTmp.csvcSuDung.add(coSoVatChatTmp);
                hoatDongEdit.add(hoatDongTmp);
            }

            sql = String.format("delete from hd_csvc where ma_csvc = N'%s';", old_item.maCSVC);
            System.out.println(sql);
            statement.execute(sql);

            sql = String.format("update csvc  set ma_csvc = N'%s', so_luong = %d where ma_csvc = N'%s';", new_item.maCSVC, new_item.soLuong, old_item.maCSVC);
            System.out.println(sql);
            statement.execute(sql);

            for(HoatDong x:hoatDongEdit){
                sql = String.format("insert into hd_csvc values(N'%s', N'%s', %d)", x.maHoatDong, x.csvcSuDung.get(0).maCSVC, x.csvcSuDung.get(0).soLuong);
                System.out.println(sql);
                statement.execute(sql);
            }

            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<CoSoVatChat> getList() {
        ArrayList<CoSoVatChat> list = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = "select * from co_so_vat_chat";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                CoSoVatChat coSoVatChatTmp = new CoSoVatChat();
                coSoVatChatTmp.maCSVC = resultSet.getString(1);
                coSoVatChatTmp.soLuong = resultSet.getInt(2);
                list.add(coSoVatChatTmp);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    public void delete(CoSoVatChat item) {
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("delete from hd_csvc where ma_csvc = N'%s';", item.maCSVC);
            System.out.println(sql);
            statement.execute(sql);

            sql = String.format("delete from co_so_vat_chat where ma_csvc = N'%s';", item.maCSVC);
            System.out.println(sql);
            statement.execute(sql);

            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
