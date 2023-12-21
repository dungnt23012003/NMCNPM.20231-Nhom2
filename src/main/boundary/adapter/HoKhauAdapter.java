package src.main.boundary.adapter;

import src.main.boundary.ListItemizable;
import src.main.entity.HoKhau;

public class HoKhauAdapter implements ListItemizable {
    HoKhau hoKhau;

    public HoKhauAdapter(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    @Override
    public String toString() {
        return String.valueOf(hoKhau.soHoKhau);
    }
}
