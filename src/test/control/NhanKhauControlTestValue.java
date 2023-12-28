package src.test.control;

import src.main.control.NhanKhauControl;
import src.main.entity.NhanKhau;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NhanKhauControlTestValue extends NhanKhauControl {
    @Override
    public ArrayList<NhanKhau> getList() {
        ArrayList<NhanKhau> nhanKhauList = new ArrayList<>();

        NhanKhau nhanKhau1 = new NhanKhau();
        nhanKhau1.CCCD = String.valueOf(26);
        nhanKhau1.hoTen = "Trịnh Văn An";
        nhanKhau1.namSinh = "07/12/1990";
        nhanKhau1.gioiTinh = "Nam";
        nhanKhau1.nguyenQuan = "Hà Nội";
        nhanKhau1.danToc = "Kinh";
        nhanKhau1.tonGiao = "Không";
        nhanKhau1.quocTich = "Việt Nam";
        nhanKhau1.noiThuongTru = "Số 1 Tạ Quang Bửu";
        nhanKhauList.add(nhanKhau1);

        NhanKhau nhanKhau2 = new NhanKhau();
        nhanKhau2.CCCD = String.valueOf(27);
        nhanKhau2.hoTen = "Trần Thanh Duy";
        nhanKhau2.namSinh = "23/12/1997";
        nhanKhau2.gioiTinh = "Nữ";
        nhanKhau2.nguyenQuan = "Hải Phòng";
        nhanKhau2.danToc = "Kinh";
        nhanKhau2.tonGiao = "Không";
        nhanKhau2.quocTich = "Việt Nam";
        nhanKhau2.noiThuongTru = "Số 3 Trần Đại Nghĩa";
        nhanKhauList.add(nhanKhau2);

        return nhanKhauList;
    }
}
