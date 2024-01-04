package src.main.boundary.hoatdong;

import src.main.boundary.GUIConfig;
import src.main.boundary.cosovatchat.CoSoVatChatEditorComponent;
import src.main.boundary.dialog.*;
import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.editor.NhanKhauEditorComponent;
import src.main.boundary.hokhau.HoKhauAdapter;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.NhanKhauControl;
import src.main.control.PhongBanControl;
import src.main.entity.CoSoVatChat;
import src.main.entity.HoatDong;
import src.main.entity.NhanKhau;
import src.main.entity.PhongBan;
import src.test.control.PhongBanControlTestValue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class HoatDongEditor extends EditorComponent implements MultiListRenderable, DocumentListener {
    HoatDongController controller;
    HoatDongAdapter item;
    JButton clickWhenCanceled;

    EditorComponent maHoatDongEditor;
    EditorComponent ngayBatDauEditor;
    EditorComponent ngayKetThucEditor;
    NhanKhauEditorComponent nguoiDangKyEditorComponent;
    ArrayList<EditorComponent> coSoVatChatEditorComponents = new ArrayList<>();
    ArrayList<EditorComponent> phongBanEditorComponents = new ArrayList<>();

    public HoatDongEditor(HoatDongAdapter item, HoatDongController controller, JButton clickWhenCanceled) {
        this.controller = controller;
        this.item = item;
        this.clickWhenCanceled = clickWhenCanceled;

        for (CoSoVatChat coSoVatChat : item.hoatDong.csvcSuDung) {
            coSoVatChatEditorComponents.add(EditorComponentFactory.createCoSoVatChatEditorComponent(coSoVatChat, this));
        }

        for (PhongBan phongBan : item.hoatDong.phongbanSuDung) {
            phongBanEditorComponents.add(EditorComponentFactory.createPhongBanEditorComponent(phongBan, this));
        }

        if (!item.hoatDong.cccdNguoiDangKi.isEmpty()) {
            NhanKhau nhanKhauDangKy = (new NhanKhauControl()).getSingleNhanKhau(item.hoatDong.cccdNguoiDangKi);
            nguoiDangKyEditorComponent = EditorComponentFactory.createNhanKhauEditorComponent(new NhanKhauAdapter(nhanKhauDangKy), this);
        }

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
            if (nguoiDangKyEditorComponent == null) {
                JOptionPane.showMessageDialog(getRootPane(), "Chưa chọn người đăng ký");
                return;
            }

            if (item.isNew) {
                controller.model.control.add(getValue().hoatDong);
            }
            else {
                controller.model.control.update(item.hoatDong, getValue().hoatDong);
            }
//            clickWhenCanceled.doClick();
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

        // Thông tin chung
        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin chung");

        maHoatDongEditor = EditorComponentFactory.createEditFormComponent("Tên hoạt động", item.hoatDong.maHoatDong);
        ngayBatDauEditor = EditorComponentFactory.createEditFormComponent("Ngày bắt đầu", item.hoatDong.ngayBatDau);
        ngayKetThucEditor = EditorComponentFactory.createEditFormComponent("Ngày kết thúc", item.hoatDong.ngayKetThuc);

        thongTinChungList.addComponent(maHoatDongEditor);
        thongTinChungList.addComponent(ngayBatDauEditor);
        thongTinChungList.addComponent(ngayKetThucEditor);

        // Người đăng ký
        DefaultRenderableList nguoiDangKy = new DefaultRenderableList();
        nguoiDangKy.setTitle("Người đăng ký");

        if (nguoiDangKyEditorComponent == null) {
            nguoiDangKy.addComponent(ComponentFactory.createFormComponent("Chưa chọn người đăng ký", ""));
        } else {
            nguoiDangKy.addComponent(nguoiDangKyEditorComponent);
        }

        JPanel setNhanKhauPanel = new JPanel();
        setNhanKhauPanel.setBackground(GUIConfig.MyListBackground);
        setNhanKhauPanel.setLayout(new BoxLayout(setNhanKhauPanel, BoxLayout.LINE_AXIS));
        setNhanKhauPanel.add(Box.createHorizontalGlue());

        JButton setNhanKhauButton = ComponentFactory.createDefaultButton();
        setNhanKhauButton.setText("Đặt người đăng ký");
        setNhanKhauPanel.add(setNhanKhauButton);
        setNhanKhauButton.addActionListener(e -> setNguoiDangKy());

        nguoiDangKy.addComponent(setNhanKhauPanel);

        // Danh sách cơ sở vật chất
        DefaultRenderableList coSoVatChatList = new DefaultRenderableList();
        coSoVatChatList.setTitle("Danh sách cơ sở vật chất sử dụng");

        for (EditorComponent editorComponent : coSoVatChatEditorComponents) {
            editorComponent.setAlignmentX(1.0f);
            coSoVatChatList.addComponent(editorComponent);
        }
        JButton themCoSoVatChatButton = ComponentFactory.createDefaultButton();
        themCoSoVatChatButton.setText("Thêm cơ sở vật chất");
        themCoSoVatChatButton.setAlignmentX(1.0f);
        themCoSoVatChatButton.addActionListener(e -> addCoSoVatChat());
        coSoVatChatList.addComponent(themCoSoVatChatButton);

        // Danh sách phòng ban
        DefaultRenderableList phongBanList = new DefaultRenderableList();
        phongBanList.setTitle("Danh sách phòng ban sử dụng");

        for (EditorComponent editorComponent : phongBanEditorComponents) {
            editorComponent.setAlignmentX(1.0f);
            phongBanList.addComponent(editorComponent);
        }
        JButton themPhongBan = ComponentFactory.createDefaultButton();
        themPhongBan.setText("Thêm phòng ban");
        themPhongBan.setAlignmentX(1.0f);
        themPhongBan.addActionListener(e -> addPhongBan());
        phongBanList.addComponent(themPhongBan);

        listRenderables.add(thongTinChungList);
        listRenderables.add(nguoiDangKy);
        listRenderables.add(coSoVatChatList);
        listRenderables.add(phongBanList);

        FormEditorComponent castedEditor = (FormEditorComponent) maHoatDongEditor;
        castedEditor.getTextField().getDocument().addDocumentListener(this);

        return listRenderables;
    }

    public void addPhongBan() {
        ArrayList<PhongBan> list = new ArrayList<>();
        for (EditorComponent editorComponent : phongBanEditorComponents) {
            list.add((PhongBan) editorComponent.getValue());
        }

        PhongBanChooserDialog dialog = new PhongBanChooserDialog(list, getRootPane());
        phongBanEditorComponents.clear();
        for (PhongBan phongBan : dialog.getValues()) {
            phongBanEditorComponents.add(EditorComponentFactory.createPhongBanEditorComponent(phongBan, this));
        }
        refreshUI();

        dialog.dispose();
    }

    public void setNguoiDangKy() {
        SingleNhanKhauChooserDialog dialog = new SingleNhanKhauChooserDialog(getRootPane());

        if (dialog.getValue() == null) {
            nguoiDangKyEditorComponent = null;
        } else {
            nguoiDangKyEditorComponent = EditorComponentFactory.createNhanKhauEditorComponent(dialog.getValue(), this);
        }
        refreshUI();
    }

    public void removePhongBan(PhongBan item) {
        for (EditorComponent editorComponent : phongBanEditorComponents) {
            if (item.equals(editorComponent.getValue())) {
                phongBanEditorComponents.remove(editorComponent);
                break;
            }
        }
        refreshUI();
    }

    public void addCoSoVatChat() {
        ArrayList<CoSoVatChat> list = new ArrayList<>();
        for (EditorComponent editorComponent : coSoVatChatEditorComponents) {
            list.add((CoSoVatChat) editorComponent.getValue());
        }

        CoSoVatChatChooserDialog dialog = new CoSoVatChatChooserDialog(list, getRootPane());
        coSoVatChatEditorComponents.clear();
        for (CoSoVatChat coSoVatChat : dialog.getValues()) {
            coSoVatChatEditorComponents.add(EditorComponentFactory.createCoSoVatChatEditorComponent(coSoVatChat, this));
        }
        refreshUI();

        dialog.dispose();
    }

    public void removeCoSoVatChat(CoSoVatChat item) {
        for (EditorComponent editorComponent : coSoVatChatEditorComponents) {
            if (item.equals(editorComponent.getValue())) {
                coSoVatChatEditorComponents.remove(editorComponent);
                break;
            }
        }
        refreshUI();
    }

    public void refreshUI() {
        HoatDongAdapter temp = getValue();

        removeAll();
        setupUI();
        setValue(temp);
        revalidate();
        repaint();
    }

    @Override
    public HoatDongAdapter getValue() {
        HoatDong value = new HoatDong();

        value.maHoatDong = (String) maHoatDongEditor.getValue();
        try {
            value.cccdNguoiDangKi = ((NhanKhauAdapter) nguoiDangKyEditorComponent.getValue()).getNhanKhau().CCCD;
        } catch (NullPointerException e) {System.out.println("Người đăng ký rỗng khi getValue");}

        value.ngayBatDau = (String) ngayBatDauEditor.getValue();
        value.ngayKetThuc = (String) ngayKetThucEditor.getValue();

        for (EditorComponent editorComponent : coSoVatChatEditorComponents) {
            value.csvcSuDung.add((CoSoVatChat) editorComponent.getValue());
        }

        for (EditorComponent editorComponent : phongBanEditorComponents) {
            value.phongbanSuDung.add((PhongBan) editorComponent.getValue());
        }

        return new HoatDongAdapter(value);
    }

    @Override
    public void clearValue() {
        maHoatDongEditor.clearValue();
        ngayBatDauEditor.clearValue();
        ngayKetThucEditor.clearValue();
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
    public void changedUpdate(DocumentEvent e) {}

    @Override
    public void setValue(Object value) {
        if (value instanceof HoatDongAdapter castedValue) {
            maHoatDongEditor.setValue(castedValue.hoatDong.maHoatDong);
            ngayBatDauEditor.setValue(castedValue.hoatDong.ngayBatDau);
            ngayKetThucEditor.setValue(castedValue.hoatDong.ngayKetThuc);
        }
    }
}
