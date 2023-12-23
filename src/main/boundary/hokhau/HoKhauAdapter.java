package src.main.boundary.hokhau;

import src.main.boundary.DefaultRenderableList;
import src.main.boundary.ListRenderable;
import src.main.boundary.MultiListRenderable;
import src.main.boundary.GalleryItem;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;

import java.util.ArrayList;

public class HoKhauAdapter implements GalleryItem, MultiListRenderable {
    HoKhau hoKhau;

    public HoKhauAdapter(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    @Override
    public String toString() {
        return String.valueOf(hoKhau.soHoKhau);
    }

    @Override
    public ArrayList<ListRenderable> getLists() {
        ArrayList<ListRenderable> list = new ArrayList<>();

        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin chung");

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Số hộ khẩu", String.valueOf(hoKhau.soHoKhau)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Khu vực", String.valueOf(hoKhau.khuVuc)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Địa chỉ", String.valueOf(hoKhau.diaChi)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Ngày lập", String.valueOf(hoKhau.ngayLap)));

        DefaultRenderableList nhanKhauList = new DefaultRenderableList();
        nhanKhauList.setTitle("Danh sách nhân khẩu");

        nhanKhauList.addComponent(ComponentFactory.createLabel(hoKhau.chuHo.ten));

        list.add(thongTinChungList);
        list.add(nhanKhauList);

        return list;
    }
}
