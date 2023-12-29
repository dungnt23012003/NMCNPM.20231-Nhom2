package src.main.boundary.hoatdong;

import src.main.boundary.GUIConfig;
import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoatDong;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class HoatDongEditor extends EditorComponent implements MultiListRenderable, DocumentListener {
    HoatDongController controller;
    HoatDongAdapter item;
    JButton clickWhenCanceled;

    EditorComponent maHoatDongEditor;
    EditorComponent cccdNguoiDangKyEditor;


    public HoatDongEditor(HoatDongAdapter item, HoatDongController controller, JButton clickWhenCanceled) {
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
                controller.model.control.add(getValue().hoatDong);
            }
            else {
                controller.model.control.update(item.hoatDong, getValue().hoatDong);
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

        maHoatDongEditor = EditorComponentFactory.createEditFormComponent("Mã hoạt động", item.hoatDong.maHoatDong);
        cccdNguoiDangKyEditor = EditorComponentFactory.createEditFormComponent("Mã hoạt động", item.hoatDong.cccdNguoiDangKi);

        thongTinChungList.addComponent(maHoatDongEditor);
        thongTinChungList.addComponent(cccdNguoiDangKyEditor);



        listRenderables.add(thongTinChungList);

        FormEditorComponent castedEditor = (FormEditorComponent) maHoatDongEditor;
        castedEditor.getTextField().getDocument().addDocumentListener(this);

        return listRenderables;
    }

    @Override
    public HoatDongAdapter getValue() {
        HoatDong value = new HoatDong();

        value.maHoatDong = (String) maHoatDongEditor.getValue();

        return new HoatDongAdapter(value);
    }

    @Override
    public void clearValue() {
        maHoatDongEditor.clearValue();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        item.string = (String) maHoatDongEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        item.string = (String) maHoatDongEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    @Override
    public void setValue(Object value) {

    }
}
