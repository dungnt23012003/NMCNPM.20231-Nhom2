package src.main.boundary.nhankhau;

import src.main.boundary.gallery.GalleryItem;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.NhanKhau;

import java.util.ArrayList;

public class NhanKhauAdapter implements GalleryItem, MultiListRenderable {
    NhanKhau nhanKhau;
    boolean isNew = false;
    String string;
    boolean isChuHo = false;


    public NhanKhauAdapter(NhanKhau nhanKhau) {
        this.nhanKhau = nhanKhau;
        resetString();
    }

    public NhanKhauAdapter(NhanKhau nhanKhau, boolean isNew) {
        this.nhanKhau = nhanKhau;
        this.isNew = isNew;
    }

    public void resetString() {
        string = String.valueOf(nhanKhau.CCCD);
    }

    public NhanKhau getNhanKhau() {
        return nhanKhau;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public ArrayList<ListRenderable> getRenderableLists() {
        ArrayList<ListRenderable> list = new ArrayList<>();

        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin");

        thongTinChungList.addComponent(ComponentFactory.createFormComponent("CMND/CCCD", String.valueOf(nhanKhau.CCCD)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Họ và tên", String.valueOf(nhanKhau.hoTen)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Năm sinh", nhanKhau.namSinh));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Giới tính", String.valueOf(nhanKhau.gioiTinh)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Nguyên quán", String.valueOf(nhanKhau.nguyenQuan)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Dân tộc", String.valueOf(nhanKhau.danToc)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Tôn giáo", String.valueOf(nhanKhau.tonGiao)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Quốc tịch", String.valueOf(nhanKhau.quocTich)));
        thongTinChungList.addComponent(ComponentFactory.createFormComponent("Nơi thường trú", String.valueOf(nhanKhau.noiThuongTru)));

        list.add(thongTinChungList);

        return list;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isChuHo() {
        return isChuHo;
    }

    public void setChuHo(boolean chuHo) {
        isChuHo = chuHo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NhanKhauAdapter casted) {
            if (this.isNew && casted.isNew()) return true;
            if (this.isNew || casted.isNew()) return false;
            return this.nhanKhau.CCCD.equals(casted.nhanKhau.CCCD);
        }
        return super.equals(obj);
    }

    public void setString(String string) {
        this.string = string;
    }
}
