package src.main.boundary.hokhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListItemizable;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;

import javax.swing.*;
import java.awt.*;
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
        string = String.valueOf(hoKhau.soHoKhau);
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

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Số hộ khẩu", String.valueOf(hoKhau.soHoKhau)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Khu vực", String.valueOf(hoKhau.khuVuc)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Địa chỉ", String.valueOf(hoKhau.diaChi)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Ngày lập", String.valueOf(hoKhau.ngayLap)));

        DefaultRenderableList nhanKhauList = new DefaultRenderableList();
        nhanKhauList.setTitle("Danh sách nhân khẩu");

        nhanKhauList.addComponent(ComponentFactory.createFormComponent(hoKhau.chuHo.ten, ""));

        list.add(thongTinChungList);
        list.add(nhanKhauList);

        return list;
    }
}
