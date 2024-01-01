package src.main.control;

import src.main.boundary.hoatdong.HoatDongView;
import src.main.boundary.hokhau.HoKhauView;
import src.main.entity.*;

import java.sql.*;
import java.util.ArrayList;
import static src.main.control.ConnectionConfig.connect_to_sql_server;
public class HoatDongControl {
    public HoatDongView view;

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

                String timPhongBan = String.format("select * from hd_Phongban where ma_hoat_dong = N'%s'",hoatDongTmp.maHoatDong);
                System.out.println(timPhongBan);

                Statement statement1 = connection.createStatement();
                ResultSet phongBan = statement1.executeQuery(timPhongBan);

                while(phongBan.next()){
                    PhongBan phongBanTmp = new PhongBan();
                    phongBanTmp.maPhongBan = phongBan.getString(1);
                    hoatDongTmp.phongbanSuDung.add(phongBanTmp);
                }

                String timCSVC = String.format("select * from hd_csvc where ma_hoat_dong = N'%s'", hoatDongTmp.maHoatDong);
                System.out.println(timCSVC);

                Statement statement2 = connection.createStatement();
                ResultSet coSoVatChat = statement2.executeQuery(timCSVC);

                while(coSoVatChat.next()){
                    CoSoVatChat coSoVatChatTmp = new CoSoVatChat();
                    coSoVatChatTmp.maCSVC = coSoVatChat.getString(2);
                    coSoVatChatTmp.soLuong = coSoVatChat.getInt(3);
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
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();
            String announcement = "Không thể thêm hoạt động này vì thiếu cơ sở vật chất và phòng ban sau:<br/>";

            for(CoSoVatChat coSoVatChat: item.csvcSuDung){

                String day = "";
                String month = "";
                String year = "";
                if(!item.ngayBatDau.equals("")){
                    day = item.ngayBatDau.substring(0,2);
                    month = item.ngayBatDau.substring(3,5);
                    year = item.ngayBatDau.substring(6,10);
                }

                String date = String.format("select DATEDIFF(DD, GETDATE(), '%s-%s-%s')", year, month, day);
                int day_bd = statement.executeQuery(date).getInt(1);

                day = "";
                month = "";
                year = "";
                if(!item.ngayKetThuc.equals("")){
                    day = item.ngayKetThuc.substring(0,2);
                    month = item.ngayKetThuc.substring(3,5);
                    year = item.ngayKetThuc.substring(6,10);
                }
                date = String.format("select DATEDIFF(DD, GETDATE(), '%s-%s-%s')", year, month, day);
                int day_kt = statement.executeQuery(date).getInt(1);

                String checkBeforeUpdate = String.format("select hoat_dong.ma_hoat_dong, DATEDIFF(DD, GETDATE(), ngay_kt), so_luong, so_luong_su_dung\n" +
                        "from hoat_dong\n" +
                        "join hd_csvc on hoat_dong.ma_hoat_dong = hd_csvc.ma_hoat_dong\n" +
                        "join co_so_vat_chat on co_so_vat_chat.ma_csvc = hd_csvc.ma_csvc\n" +
                        "where co_so_vat_chat.ma_csvc = N'%s' and DATEDIFF(DD, GETDATE(), ngay_kt) >= 0", coSoVatChat.maCSVC);
                System.out.println(checkBeforeUpdate);
                ResultSet rs = statement.executeQuery(checkBeforeUpdate);

                int latestDay = 0;
                int slCSVCMax = 0;
                while(rs.next()){
                    if(latestDay>rs.getInt(2)){
                        latestDay = rs.getInt(2);
                    }
                }
                if(latestDay>day_kt){
                    latestDay = day_kt;
                }
                int[] slCSVCSuDungCurrent = new int[latestDay+1];
                for(int x=0; x<= latestDay; x++){
                    slCSVCSuDungCurrent[x] = 0;
                }
                checkBeforeUpdate = String.format("select hoat_dong.ma_hoat_dong, DATEDIFF(DD, GETDATE(), ngay_kt), so_luong, so_luong_su_dung, DATEDIFF(DD, GETDATE(), ngay_bd)\n" +
                        "from hoat_dong\n" +
                        "join hd_csvc on hoat_dong.ma_hoat_dong = hd_csvc.ma_hoat_dong\n" +
                        "join co_so_vat_chat on co_so_vat_chat.ma_csvc = hd_csvc.ma_csvc\n" +
                        "where co_so_vat_chat.ma_csvc = N'%s' and DATEDIFF(DD, GETDATE(), ngay_kt) >= 0", coSoVatChat.maCSVC);
                System.out.println(checkBeforeUpdate);
                rs = statement.executeQuery(checkBeforeUpdate);

                while(rs.next()){
                    slCSVCMax = rs.getInt(3);
                    if(rs.getInt(5) > 0){
                        for(int x=rs.getInt(5); x<= rs.getInt(2); x++){
                            slCSVCSuDungCurrent[x] = slCSVCSuDungCurrent[x] + rs.getInt(4);
                        }
                    }
                    else{
                        for(int x=0; x<= rs.getInt(2); x++){
                            slCSVCSuDungCurrent[x] = slCSVCSuDungCurrent[x] + rs.getInt(4);
                        }
                    }

                }


                int slThieu = 0;
                for(int y=day_bd;y<=day_kt;y++){
                    if(slCSVCSuDungCurrent[y] + coSoVatChat.soLuong - slCSVCMax > slThieu){
                        slThieu = slCSVCSuDungCurrent[y] - slCSVCMax;
                    }
                }

                if(slThieu > 0){
                    announcement = announcement + coSoVatChat.maCSVC + ": " + slThieu + ".<br/>";
                }
            }

            for(PhongBan phongBan:item.phongbanSuDung){
                String day = "";
                String month = "";
                String year = "";
                if(!item.ngayBatDau.equals("")){
                    day = item.ngayBatDau.substring(0,2);
                    month = item.ngayBatDau.substring(3,5);
                    year = item.ngayBatDau.substring(6,10);
                }
                String date_ngaybd = String.format("'%s-%s-%s'", year, month, day);

                day = "";
                month = "";
                year = "";
                if(!item.ngayKetThuc.equals("")){
                    day = item.ngayKetThuc.substring(0,2);
                    month = item.ngayKetThuc.substring(3,5);
                    year = item.ngayKetThuc.substring(6,10);
                }
                String date_ngaykt = String.format("'%s-%s-%s'", year, month, day);

                String checkBeforeUpdate = String.format("select hd_phongban.ma_phong_ban\n" +
                        "from hoat_dong\n" +
                        "join hd_phongban on hoat_dong.ma_hoat_dong = hd_phongban.ma_hoat_dong\n" +
                        "where hd_phongban.ma_phong_ban = '%s' and (hoat_dong.ngay_kt >= %s and hoat_dong.ngay_kt <= %s) or (hoat_dong.ngay_bd >= %s and hoat_dong.ngay_bd <= %s)", phongBan.maPhongBan, date_ngaybd, date_ngaykt, date_ngaybd, date_ngaykt );
                System.out.println(checkBeforeUpdate);
                ResultSet rs = statement.executeQuery(checkBeforeUpdate);

                while(rs.next()){
                    announcement = announcement + phongBan.maPhongBan + ".<br/>";
                }
            }

            if(!announcement.equals("Không thể thêm hoạt động này vì thiếu cơ sở vật chất và phòng ban sau:<br/>")){
                System.out.println(announcement);
            }
            else{
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
                if(!item.ngayKetThuc.equals("")){
                    day = item.ngayKetThuc.substring(0,2);
                    month = item.ngayKetThuc.substring(3,5);
                    year = item.ngayKetThuc.substring(6,10);
                }

                sql = sql + "'" + year + "-" + month + "-" + day + "'" + ");";
                System.out.println(sql);
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
            }

            view.refreshUI();
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

        String ngayBatDau = "";
        if(!newItem.ngayBatDau.equals("")){
            ngayBatDau = newItem.ngayBatDau.substring(6, 10) + "-" + newItem.ngayBatDau.substring(3, 5) + "-" + newItem.ngayBatDau.substring(0, 2);
        }
        String ngayKetThuc = "";
        if(!newItem.ngayKetThuc.equals("")){
            ngayKetThuc = newItem.ngayKetThuc.substring(6, 10) + "-" + newItem.ngayKetThuc.substring(3, 5) + "-" + newItem.ngayKetThuc.substring(0, 2);
        }
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
        view.refreshUI();
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
            view.refreshUI();
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
