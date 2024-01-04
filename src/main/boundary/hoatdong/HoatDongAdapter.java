package src.main.boundary.hoatdong;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.NhanKhauControl;
import src.main.entity.CoSoVatChat;
import src.main.entity.HoatDong;
import src.main.entity.NhanKhau;
import src.main.entity.PhongBan;

import java.util.ArrayList;

public class HoatDongAdapter implements GalleryItem, MultiListRenderable {
    HoatDong hoatDong;
    boolean isNew = false;
    String string;

    public HoatDongAdapter(String maHoatDong) {
        hoatDong = new HoatDong();
        hoatDong.maHoatDong = maHoatDong;
    }

    public HoatDongAdapter(HoatDong hoatDong) {
        this.hoatDong = hoatDong;
        resetString();
    }

    public HoatDongAdapter(HoatDong hoatDong, boolean isNew) {
        this.hoatDong = hoatDong;
        this.isNew = isNew;

        if (isNew) {
            string = "Hoạt động mới";
        } else {
            resetString();
        }
    }

    public void resetString() {
        string = String.valueOf(hoatDong.maHoatDong);
    }

    public boolean isNew() {
        return isNew;
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

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Tên hoạt động", hoatDong.maHoatDong));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Ngày bắt đầu", getHoatDong().ngayBatDau));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Ngày kết thúc", getHoatDong().ngayKetThuc));

        // Người đăng ký
        DefaultRenderableList nguoiDangKy = new DefaultRenderableList();
        nguoiDangKy.setTitle("Người đăng ký");

        NhanKhau nhanKhauDangKy = (new NhanKhauControl()).getSingleNhanKhau(hoatDong.cccdNguoiDangKi);
        nguoiDangKy.addComponent(ComponentFactory.createFormComponent(nhanKhauDangKy.hoTen, ""));

        // CSVC
        DefaultRenderableList CSVCSuDung = new DefaultRenderableList();
        CSVCSuDung.setTitle("Danh sách cơ sở vật chất sử dụng");

        for (CoSoVatChat coSoVatChat : hoatDong.csvcSuDung) {
            CSVCSuDung.addComponent(ComponentFactory.createFormComponent(coSoVatChat.maCSVC, String.valueOf(coSoVatChat.soLuong)));
        }

        DefaultRenderableList phongBanSuDung = new DefaultRenderableList();
        phongBanSuDung.setTitle("Danh sách phòng ban sử dụng");

        for (PhongBan phongBan : hoatDong.phongbanSuDung) {
            phongBanSuDung.addComponent(ComponentFactory.createFormComponent(phongBan.maPhongBan, ""));
        }

        list.add(thongTinChungList);
        list.add(nguoiDangKy);
        list.add(CSVCSuDung);
        list.add(phongBanSuDung);

        return list;
    }
}
