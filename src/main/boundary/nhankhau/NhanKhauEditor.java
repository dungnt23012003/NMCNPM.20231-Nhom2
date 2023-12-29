package src.main.boundary.nhankhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.hokhau.HoKhauController;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhanKhauEditor extends EditorComponent implements MultiListRenderable, DocumentListener {
    NhanKhauController controller;
    NhanKhauAdapter item;
    JButton clickWhenCanceled;

    EditorComponent cccdEditor;
    EditorComponent hotenEditor;
    EditorComponent namSinhEditor;
    EditorComponent gioiTinhEditor;
    EditorComponent nguyenQuanEditor;
    EditorComponent danTocEditor;
    EditorComponent tonGiaoEditor;
    EditorComponent quocTichEditor;
    EditorComponent noiThuongTruEditor;


    public NhanKhauEditor(NhanKhauAdapter item, NhanKhauController controller, JButton clickWhenCanceled) {
        this.controller = controller;
        this.item = item;
        this.clickWhenCanceled = clickWhenCanceled;

        setupUI();
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(GUIConfig.FeatureViewColor);

        MultiListRenderer renderer = new MultiListRenderer();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBackground(GUIConfig.FeatureViewColor);
        buttonPanel.add(ComponentFactory.createLabel("Lưu ý: Sửa đổi chỉ được lưu khi nhấn nút Lưu"));
        buttonPanel.add(Box.createHorizontalGlue());

        JButton cancelButton = ComponentFactory.createDefaultButton();
        cancelButton.addActionListener(e -> clickWhenCanceled.doClick());
        cancelButton.setText("Hủy");
        buttonPanel.add(cancelButton);

        JButton saveButton = ComponentFactory.createDefaultButton();
        saveButton.addActionListener(e -> {
            if (item.isNew) {
                controller.model.control.add(getValue().nhanKhau);
            }
            else {
                controller.model.control.update(item.nhanKhau, getValue().nhanKhau);
            }
            clickWhenCanceled.doClick();
        });
        saveButton.setText("Lưu");
        buttonPanel.add(saveButton);
        buttonPanel.setAlignmentX(0.0f);

        add(renderer.getRenderedComponent(this));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    @Override
    public ArrayList<ListRenderable> getRenderableLists() {
        ArrayList<ListRenderable> listRenderables = new ArrayList<>();

        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin");

        cccdEditor = EditorComponentFactory.createEditFormComponent("CMND/CCCD", String.valueOf(item.nhanKhau.CCCD));
        cccdEditor.setEnabled(item.isNew);
        hotenEditor = EditorComponentFactory.createEditFormComponent("Họ và tên", String.valueOf(item.nhanKhau.hoTen));
        namSinhEditor = EditorComponentFactory.createEditFormComponent("Năm sinh", item.nhanKhau.namSinh);
        gioiTinhEditor = EditorComponentFactory.createEditFormComponent("Giới tính", String.valueOf(item.nhanKhau.gioiTinh));
        nguyenQuanEditor = EditorComponentFactory.createEditFormComponent("Nguyên quán", String.valueOf(item.nhanKhau.nguyenQuan));
        danTocEditor = EditorComponentFactory.createEditFormComponent("Dân tộc", String.valueOf(item.nhanKhau.danToc));
        tonGiaoEditor = EditorComponentFactory.createEditFormComponent("Tôn giáo", String.valueOf(item.nhanKhau.tonGiao));
        quocTichEditor = EditorComponentFactory.createEditFormComponent("Quốc tịch", String.valueOf(item.nhanKhau.quocTich));
        noiThuongTruEditor = EditorComponentFactory.createEditFormComponent("Nơi thường trú", String.valueOf(item.nhanKhau.noiThuongTru));

        thongTinChungList.addComponent(cccdEditor);
        thongTinChungList.addComponent(hotenEditor);
        thongTinChungList.addComponent(namSinhEditor);
        thongTinChungList.addComponent(gioiTinhEditor);
        thongTinChungList.addComponent(nguyenQuanEditor);
        thongTinChungList.addComponent(danTocEditor);
        thongTinChungList.addComponent(tonGiaoEditor);
        thongTinChungList.addComponent(quocTichEditor);
        thongTinChungList.addComponent(noiThuongTruEditor);

        listRenderables.add(thongTinChungList);

        FormEditorComponent castedSoHoKhauEditor = (FormEditorComponent) cccdEditor;
        castedSoHoKhauEditor.getTextField().getDocument().addDocumentListener(this);

        return listRenderables;
    }

    @Override
    public NhanKhauAdapter getValue() {
        NhanKhau value = new NhanKhau();

        value.CCCD = (String) cccdEditor.getValue();
        value.hoTen = (String) hotenEditor.getValue();
        value.namSinh = (String) namSinhEditor.getValue();
        value.gioiTinh = (String) gioiTinhEditor.getValue();
        value.nguyenQuan = (String) nguyenQuanEditor.getValue();
        value.danToc = (String) danTocEditor.getValue();
        value.tonGiao = (String) tonGiaoEditor.getValue();
        value.quocTich = (String) quocTichEditor.getValue();
        value.noiThuongTru = (String) noiThuongTruEditor.getValue();

        return new NhanKhauAdapter(value);
    }

    @Override
    public void clearValue() {
        cccdEditor.clearValue();
        hotenEditor.clearValue();
        namSinhEditor.clearValue();
        gioiTinhEditor.clearValue();
        nguyenQuanEditor.clearValue();
        danTocEditor.clearValue();
        tonGiaoEditor.clearValue();
        quocTichEditor.clearValue();
        noiThuongTruEditor.clearValue();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        item.string = (String) cccdEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        item.string = (String) cccdEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    @Override
    public void setValue(Object value) {

    }
}
