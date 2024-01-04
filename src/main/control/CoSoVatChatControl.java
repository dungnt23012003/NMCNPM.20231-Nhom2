package src.main.control;

import src.main.boundary.cosovatchat.CoSoVatChatView;
import src.main.entity.CoSoVatChat;
import src.main.entity.HoatDong;

import java.sql.*;
import java.util.ArrayList;


import static src.main.control.ConnectionConfig.connect_to_sql_server;

public class CoSoVatChatControl {

    public CoSoVatChatView view;

    public ArrayList<CoSoVatChat> searchCoSoVatChat(String dieuKien) {
        ArrayList<CoSoVatChat> list = new ArrayList<>();
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("select * from co_so_vat_chat where ma_csvc like '%%%s%%'", dieuKien);
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

    public void add(CoSoVatChat item) {
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("insert into co_so_vat_chat values(N'%s', %d)", item.maCSVC, item.soLuong);
            System.out.println(sql);
            statement.execute(sql);
            view.refreshUI();
            view.showMessage("Thêm cơ sở vật chất thành công.");
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
            String announcement = "Chỉnh sửa số lượng cơ sở vật chất này khiến những hoạt động sau không đủ cơ sở vật chất sử dụng:<br/>";

            String checkBeforeUpdate = String.format("select hoat_dong.ma_hoat_dong, DATEDIFF(DD, GETDATE(), ngay_kt), so_luong, so_luong_su_dung\n" +
                    "from hoat_dong\n" +
                    "join hd_csvc on hoat_dong.ma_hoat_dong = hd_csvc.ma_hoat_dong\n" +
                    "join co_so_vat_chat on co_so_vat_chat.ma_csvc = hd_csvc.ma_csvc\n" +
                    "where co_so_vat_chat.ma_csvc = N'%s' and DATEDIFF(DD, GETDATE(), ngay_kt) >= 0", old_item.maCSVC);
            System.out.println(checkBeforeUpdate);
            ResultSet rs = statement.executeQuery(checkBeforeUpdate);

            int latestDay = 0;
            while(rs.next()){
                if(latestDay>rs.getInt(2)){
                    latestDay = rs.getInt(2);
                }
            }
            int[] slCSVCSuDungCurrent = new int[latestDay+1];
            for(int x=0; x<= latestDay; x++){
                slCSVCSuDungCurrent[x] = 0;
            }

            checkBeforeUpdate = String.format("select hoat_dong.ma_hoat_dong, DATEDIFF(DD, GETDATE(), ngay_kt), so_luong, so_luong_su_dung, DATEDIFF(DD, GETDATE(), ngay_bd)\n" +
                    "from hoat_dong\n" +
                    "join hd_csvc on hoat_dong.ma_hoat_dong = hd_csvc.ma_hoat_dong\n" +
                    "join co_so_vat_chat on co_so_vat_chat.ma_csvc = hd_csvc.ma_csvc\n" +
                    "where co_so_vat_chat.ma_csvc = N'%s' and DATEDIFF(DD, GETDATE(), ngay_kt) >= 0", old_item.maCSVC);
            System.out.println(checkBeforeUpdate);
            rs = statement.executeQuery(checkBeforeUpdate);

            while(rs.next()){
                if(rs.getInt(5) > 0){
                    for(int x=rs.getInt(5); x<= rs.getInt(2); x++){
                        slCSVCSuDungCurrent[x] = slCSVCSuDungCurrent[x] + rs.getInt(4);
                        System.out.println(x + "" + slCSVCSuDungCurrent[x]);
                        if(slCSVCSuDungCurrent[x] > new_item.soLuong){
                            announcement = announcement + rs.getString(1) + ".<br/>";
                        }
                    }
                }
                else{
                    for(int x=0; x<= rs.getInt(2); x++){
                        slCSVCSuDungCurrent[x] = slCSVCSuDungCurrent[x] + rs.getInt(4);
                        System.out.println(x + "" + slCSVCSuDungCurrent[x]);
                        if(slCSVCSuDungCurrent[x] > new_item.soLuong){
                            announcement = announcement + rs.getString(1) + ".<br/>";
                        }
                    }
                }


            }

            if(!announcement.equals("Chỉnh sửa số lượng cơ sở vật chất này khiến những hoạt động sau không đủ cơ sở vật chất sử dụng:<br/>")){
                view.showMessage("<html>" + announcement + "</html>");

            }
            else{
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

                sql = String.format("update co_so_vat_chat  set ma_csvc = N'%s', so_luong = %d where ma_csvc = N'%s';", new_item.maCSVC, new_item.soLuong, old_item.maCSVC);
                System.out.println(sql);
                statement.execute(sql);

                for(HoatDong x:hoatDongEdit){
                    sql = String.format("insert into hd_csvc values(N'%s', N'%s', %d)", x.maHoatDong, x.csvcSuDung.get(0).maCSVC, x.csvcSuDung.get(0).soLuong);
                    System.out.println(sql);
                    statement.execute(sql);
                }
                view.refreshUI();
                view.showMessage("Chỉnh sửa cơ sở vật chất thành công.");
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

            String checkBeforeDelete = String.format("select hoat_dong.ma_hoat_dong\n" +
                    "from hoat_dong\n" +
                    "join hd_csvc on hoat_dong.ma_hoat_dong = hd_csvc.ma_hoat_dong\n" +
                    "join co_so_vat_chat on hd_csvc.ma_csvc = co_so_vat_chat.ma_csvc\n" +
                    "where DATEDIFF(dd, ngay_kt, GETDATE()) <= 0 and hd_csvc.ma_csvc = N'%s'", item.maCSVC);

            System.out.println(checkBeforeDelete);
            ResultSet resultSet = statement.executeQuery(checkBeforeDelete);

            String announcement = "Không thể xóa cơ sở vật chất này có những hoạt động sau sử dụng:<br/>";

            while(resultSet.next()){
                announcement = announcement + resultSet.getString(1) + ".<br/>";
            }

            if(announcement.equals("Không thể xóa cơ sở vật chất này có những hoạt động sau sử dụng:<br/>")){

                String sql = String.format("delete from hd_csvc where ma_csvc = N'%s';", item.maCSVC);
                System.out.println(sql);
                statement.execute(sql);

                sql = String.format("delete from co_so_vat_chat where ma_csvc = N'%s';", item.maCSVC);
                System.out.println(sql);
                statement.execute(sql);
                view.refreshUI();
            }
            else{
                view.showMessage("Xóa cơ sở vật chất thành công.");
            }




            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void setView(CoSoVatChatView view) {
        this.view = view;
    }
}
