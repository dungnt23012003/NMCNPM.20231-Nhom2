package src.main.boundary.hokhau;

import src.main.boundary.GUIConfig;
import src.main.boundary.dialog.NhanKhauChooserDialog;
import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.boundary.editor.FormEditorComponent;
import src.main.boundary.editor.NhanKhauEditorComponent;
import src.main.boundary.list.DefaultRenderableList;
import src.main.boundary.list.ListRenderable;
import src.main.boundary.list.MultiListRenderable;
import src.main.boundary.nhankhau.NhanKhauAdapter;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.entity.HoKhau;
import src.main.entity.NhanKhau;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class HoKhauEditor extends EditorComponent implements MultiListRenderable, DocumentListener {
    HoKhauController controller;
    HoKhauAdapter item;
    JButton clickWhenCanceled;

    EditorComponent soHoKhauEditor;
    EditorComponent khuVucEditor;
    EditorComponent diaChiEditor;
    EditorComponent ngayLapEditor;
    ArrayList<EditorComponent> nhanKhauEditors = new ArrayList<>();
    NhanKhauEditorComponent currentChuHoEditor;

    public HoKhauEditor(HoKhauAdapter item, HoKhauController controller, JButton clickWhenCanceled) {
        this.controller = controller;
        this.item = item;
        this.clickWhenCanceled = clickWhenCanceled;

        for (NhanKhau nhanKhau : item.hoKhau.listNhanKhau) {
            NhanKhauAdapter adapter = new NhanKhauAdapter(nhanKhau);
            if (nhanKhau.CCCD.equals(item.hoKhau.chuHo.CCCD)) {
                adapter.setChuHo(true);
            }
            NhanKhauEditorComponent currentNhanKhauEditor = EditorComponentFactory.createNhanKhauEditorComponent(adapter, this);
            if (nhanKhau.CCCD.equals(item.hoKhau.chuHo.CCCD)) {
                currentChuHoEditor = currentNhanKhauEditor;
            }
            nhanKhauEditors.add(currentNhanKhauEditor);
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
            HoKhauAdapter newAdapter;
            try {
                newAdapter = getValue();
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(getRootPane(), "Chưa chọn chủ hộ.");
                return;
            }
            if (item.isNew) {
                controller.model.control.add(newAdapter.hoKhau);
            } else {
                controller.model.control.update(item.hoKhau, newAdapter.hoKhau);
            }
            clickWhenCanceled.doClick();
        });
        saveButton.setText("Lưu");
        buttonPanel.add(saveButton);
        buttonPanel.setAlignmentX(0.0f);

        JButton themNhanKhauButton = ComponentFactory.createMenuBarButton();
        themNhanKhauButton.addActionListener(e -> addNhanKhau());
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

            soHoKhauEditor = EditorComponentFactory.createEditFormComponent("Số hộ khẩu", String.valueOf(item.hoKhau.maHoKhau));
            soHoKhauEditor.setEnabled(item.isNew);
            khuVucEditor = EditorComponentFactory.createEditFormComponent("Khu vực", String.valueOf(item.hoKhau.khuVuc));
            diaChiEditor = EditorComponentFactory.createEditFormComponent("Địa chỉ", String.valueOf(item.hoKhau.diaChi));
            ngayLapEditor = EditorComponentFactory.createEditFormComponent("Ngày lập", String.valueOf(item.hoKhau.ngayLap));

            thongTinChungList.addComponent(soHoKhauEditor);
            thongTinChungList.addComponent(khuVucEditor);
            thongTinChungList.addComponent(diaChiEditor);
            thongTinChungList.addComponent(ngayLapEditor);

            DefaultRenderableList nhanKhauList = new DefaultRenderableList();
            nhanKhauList.setTitle("Danh sách nhân khẩu");

            for (EditorComponent component : nhanKhauEditors) {
                nhanKhauList.addComponent(component);
            }

            listRenderables.add(thongTinChungList);
            listRenderables.add(nhanKhauList);

            FormEditorComponent castedSoHoKhauEditor = (FormEditorComponent) soHoKhauEditor;
            castedSoHoKhauEditor.getTextField().getDocument().addDocumentListener(this);

            return listRenderables;
        }

        @Override
        public HoKhauAdapter getValue() {
            HoKhau value = new HoKhau();
            value.maHoKhau = (String) soHoKhauEditor.getValue();
            value.khuVuc = (String) khuVucEditor.getValue();
            value.diaChi = (String) diaChiEditor.getValue();
            value.ngayLap = (String) ngayLapEditor.getValue();
            value.chuHo = ((NhanKhauAdapter) currentChuHoEditor.getValue()).getNhanKhau();

            for (EditorComponent component : nhanKhauEditors) {
                value.listNhanKhau.add(((NhanKhauAdapter) component.getValue()).getNhanKhau());
            }

            if (!value.listNhanKhau.contains(value.chuHo)) {
                value.chuHo = null;
            }

            return new HoKhauAdapter(value);
        }

        @Override
        public void setValue(Object value) {
            HoKhauAdapter casted = (HoKhauAdapter) value;
            soHoKhauEditor.setValue(casted.hoKhau.maHoKhau);
            khuVucEditor.setValue(casted.hoKhau.khuVuc);
            diaChiEditor.setValue(casted.hoKhau.diaChi);
            ngayLapEditor.setValue(casted.hoKhau.ngayLap);
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

        public void addNhanKhau() {
            ArrayList<NhanKhauAdapter> list = new ArrayList<>();
            for (EditorComponent editorComponent : nhanKhauEditors) {
                list.add((NhanKhauAdapter) editorComponent.getValue());
            }

            NhanKhauChooserDialog dialog = new NhanKhauChooserDialog(list, controller.model.control, getRootPane());
            nhanKhauEditors.clear();
            for (NhanKhauAdapter adapter : dialog.getValues()) {
                nhanKhauEditors.add(EditorComponentFactory.createNhanKhauEditorComponent(adapter, this));
            }
            refreshUI();

            dialog.dispose();
        }

        public void removeNhanKhau(NhanKhauAdapter item) {
            for (EditorComponent component : nhanKhauEditors) {
                if (component.getValue().equals(item)) {
                    nhanKhauEditors.remove(component);
                    break;
                }
            }
            refreshUI();
        }

        public void refreshUI() {
            HoKhauAdapter previousValue = item;
            try {
                previousValue = getValue();
            } catch (NullPointerException ignored) {}

            removeAll();
            setupUI();
            setValue(previousValue);
            revalidate();
            repaint();
        }

        public void setChuHo(NhanKhauEditorComponent item) {
            int previous_chu_ho_index = 0;
            for (int i = 0; i < nhanKhauEditors.size(); ++i) {
                if (((NhanKhauAdapter)nhanKhauEditors.get(i).getValue()).getNhanKhau().CCCD.equals(((NhanKhauAdapter)currentChuHoEditor.getValue()).getNhanKhau().CCCD)) {
                    previous_chu_ho_index = i;
                    break;
                }
                previous_chu_ho_index = -1;
            }
            if (previous_chu_ho_index != -1) {
            NhanKhauAdapter adapter = (NhanKhauAdapter) currentChuHoEditor.getValue();
            adapter.setChuHo(false);
            nhanKhauEditors.set(previous_chu_ho_index, EditorComponentFactory.createNhanKhauEditorComponent(adapter, this));
        }

        NhanKhauAdapter adapter = (NhanKhauAdapter) item.getValue();
        adapter.setChuHo(true);
        int chuHoIndexInEditorList = nhanKhauEditors.indexOf(item);
        nhanKhauEditors.set(chuHoIndexInEditorList, EditorComponentFactory.createNhanKhauEditorComponent(adapter, this));
        currentChuHoEditor = (NhanKhauEditorComponent) nhanKhauEditors.get(chuHoIndexInEditorList);
        refreshUI();
    }
}
