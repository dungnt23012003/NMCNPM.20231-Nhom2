package src.main.boundary.hoatdong;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.CoSoVatChat;
import src.main.entity.HoatDong;
import src.main.entity.PhongBan;

import java.util.ArrayList;

public class HoatDongAdapter implements GalleryItem, MultiListRenderable {
    HoatDong hoatDong;
    boolean isNew = false;
    String string;

    public HoatDongAdapter(HoatDong hoatDong) {
        this.hoatDong = hoatDong;
        resetString();
    }

    public HoatDongAdapter(HoatDong hoatDong, boolean isNew) {
        this.hoatDong = hoatDong;
        this.isNew = isNew;
    }

    public void resetString() {
        string = String.valueOf(hoatDong.maHoatDong);
    }

    public HoatDong getHoatDong() {
        return hoatDong;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public ArrayList<ListRenderable> getRenderableLists() {
        ArrayList<ListRenderable> list = new ArrayList<>();

        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin chung");

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Mã hoạt động", hoatDong.maHoatDong));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("CCCD của người đăng ký", hoatDong.cccdNguoiDangKi));

        DefaultRenderableList CSVCSuDung = new DefaultRenderableList();
        CSVCSuDung.setTitle("Danh sách cơ sở vật chất");

        for (CoSoVatChat coSoVatChat : hoatDong.csvcSuDung) {
            CSVCSuDung.addComponent(ComponentFactory.createFormComponent(coSoVatChat.maCSVC, String.valueOf(coSoVatChat.soLuong)));
        }

        DefaultRenderableList phongBanSuDung = new DefaultRenderableList();
        phongBanSuDung.setTitle("Danh sách phòng ban sử dụng");

        for (PhongBan phongBan : hoatDong.phongbanSuDung) {
            CSVCSuDung.addComponent(ComponentFactory.createFormComponent(phongBan.maPhongBan, ""));
        }

        list.add(thongTinChungList);
        list.add(CSVCSuDung);

        return list;
    }
}
