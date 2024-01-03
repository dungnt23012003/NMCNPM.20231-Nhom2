package src.main.boundary.cosovatchat;

import src.main.boundary.editor.EditorComponent;
import src.main.entity.PhongBan;

public class PhongBanEditorComponent extends EditorComponent {
    PhongBan item;

    public PhongBanEditorComponent(PhongBan item) {
        this.item = item;
    }

    @Override
    public Object getValue() {
        return item;
    }

    @Override
    public void clearValue() {}

    @Override
    public void setValue(Object value) {}
}
