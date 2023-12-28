package src.main.boundary.editor;

import src.main.boundary.nhankhau.NhanKhauAdapter;

import javax.swing.*;

public class NhanKhauEditorComponent extends EditorComponent {
    NhanKhauAdapter item;
    public NhanKhauEditorComponent(NhanKhauAdapter item) {
        this.item = item;
    }

    @Override
    public Object getValue() {
        return item;
    }

    @Override
    public void clearValue() {}
}
