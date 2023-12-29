package src.main.boundary.hokhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import java.util.ArrayList;

public class HoKhauAdapter implements GalleryItem, MultiListRenderable {
    HoKhau hoKhau;
    boolean isNew = false;
    String string;

    public HoKhauAdapter(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
        resetString();
    }

    public HoKhauAdapter(HoKhau hoKhau, boolean isNew) {
        this.hoKhau = hoKhau;
        this.isNew = isNew;
    }

    public void resetString() {
        string = String.valueOf(hoKhau.maHoKhau);
    }

    public HoKhau getHoKhau() {
        return hoKhau;
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

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Số hộ khẩu", String.valueOf(hoKhau.maHoKhau)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Khu vực", String.valueOf(hoKhau.khuVuc)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Địa chỉ", String.valueOf(hoKhau.diaChi)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Ngày lập", String.valueOf(hoKhau.ngayLap)));

        DefaultRenderableList nhanKhauList = new DefaultRenderableList();
        nhanKhauList.setTitle("Danh sách nhân khẩu");

        for (NhanKhau nhanKhau : hoKhau.listNhanKhau) {
            boolean isChuHo = nhanKhau.CCCD.equals(hoKhau.chuHo.CCCD);
            nhanKhauList.addComponent(ComponentFactory.createFormComponent(nhanKhau.hoTen + (isChuHo ? " (Chủ hộ)" : ""), ""));
        }

        list.add(thongTinChungList);
        list.add(nhanKhauList);

        return list;
    }
}
