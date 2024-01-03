package src.main.boundary.cosovatchat;

import src.main.boundary.editor.EditorComponent;
import src.main.boundary.editor.EditorComponentFactory;
import src.main.entity.CoSoVatChat;

public class CoSoVatChatEditorComponent extends EditorComponent {
    CoSoVatChat item;

    public CoSoVatChatEditorComponent(CoSoVatChat item) {
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
