package src.main.boundary.hokhau;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoKhauEditor extends EditorComponent implements MultiListRenderable, DocumentListener {
    HoKhauController controller;
    HoKhauAdapter item;
    JButton clickWhenCanceled;

    EditorComponent soHoKhauEditor;
    EditorComponent khuVucEditor;
    EditorComponent diaChiEditor;
    EditorComponent ngayLapEditor;
    ArrayList<EditorComponent> nhanKhauEditors = new ArrayList<>();

    public HoKhauEditor(HoKhauAdapter item, HoKhauController controller, JButton clickWhenCanceled) {
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
            controller.model.control.update(item.hoKhau, getValue().hoKhau);
            clickWhenCanceled.doClick();
        });
        saveButton.setText("Lưu");
        buttonPanel.add(saveButton);
        buttonPanel.setAlignmentX(0.0f);

        JButton themNhanKhauButton = ComponentFactory.createMenuBarButton();
        themNhanKhauButton.setIcon(GUIConfig.AddNhanKhauIcon);

        add(renderer.getRenderedComponent(this));
        add(themNhanKhauButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    @Override
    public ArrayList<ListRenderable> getRenderableLists() {
        ArrayList<ListRenderable> listRenderables = new ArrayList<>();

        DefaultRenderableList thongTinChungList = new DefaultRenderableList();
        thongTinChungList.setTitle("Thông tin chung");

        soHoKhauEditor = EditorComponentFactory.createEditFormComponent("Số hộ khẩu", String.valueOf(item.hoKhau.soHoKhau));
        khuVucEditor = EditorComponentFactory.createEditFormComponent("Khu vực", String.valueOf(item.hoKhau.khuVuc));
        diaChiEditor = EditorComponentFactory.createEditFormComponent("Địa chỉ", String.valueOf(item.hoKhau.diaChi));
        ngayLapEditor = EditorComponentFactory.createEditFormComponent("Ngày lập", String.valueOf(item.hoKhau.ngayLap));

        thongTinChungList.addComponent(soHoKhauEditor);
        thongTinChungList.addComponent(khuVucEditor);
        thongTinChungList.addComponent(diaChiEditor);
        thongTinChungList.addComponent(ngayLapEditor);

        DefaultRenderableList nhanKhauList = new DefaultRenderableList();
        nhanKhauList.setTitle("Danh sách nhân khẩu");

        EditorComponent currentNhanKhauEditor = EditorComponentFactory.createTestNhanKhauEditComponent(item.hoKhau.chuHo);
        nhanKhauEditors.add(currentNhanKhauEditor);
        nhanKhauList.addComponent(currentNhanKhauEditor);

        listRenderables.add(thongTinChungList);
        listRenderables.add(nhanKhauList);

        FormEditorComponent castedSoHoKhauEditor = (FormEditorComponent) soHoKhauEditor;
        castedSoHoKhauEditor.getTextField().getDocument().addDocumentListener(this);

        return listRenderables;
    }

    @Override
    public HoKhauAdapter getValue() {
        HoKhau value = new HoKhau();
        value.soHoKhau = Integer.parseInt((String) soHoKhauEditor.getValue());
        value.khuVuc = (String) khuVucEditor.getValue();
        value.diaChi = (String) diaChiEditor.getValue();
        value.ngayLap = new SimpleDateFormat((String) ngayLapEditor.getValue()).get2DigitYearStart();
        value.chuHo = new NhanKhau();

        return new HoKhauAdapter(value);
    }

    @Override
    public void clearValue() {
        soHoKhauEditor.clearValue();
        khuVucEditor.clearValue();
        diaChiEditor.clearValue();
        ngayLapEditor.clearValue();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        item.string = (String) soHoKhauEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        item.string = (String) soHoKhauEditor.getValue();
        controller.model.galleryController.getView().getSideList().repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
