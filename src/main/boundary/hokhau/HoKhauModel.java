package src.main.boundary.hokhau;

import src.main.boundary.ListItemizable;
import src.main.boundary.adapter.HoKhauAdapter;
import src.main.control.HoKhauControl;
import src.main.entity.HoKhau;

import javax.swing.*;

public class HoKhauModel {
    HoKhauControl control;

    public HoKhauModel(HoKhauControl control) {
        this.control = control;
    }

    public DefaultListModel<ListItemizable> getListModel() {
        DefaultListModel<ListItemizable> listModel = new DefaultListModel<>();

        for (HoKhau hoKhau : control.getList()) {
            listModel.addElement(new HoKhauAdapter(hoKhau));
        }

        return listModel;
    }
}
