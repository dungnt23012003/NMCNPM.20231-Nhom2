package src.main.boundary.dialog;

import src.main.boundary.renderer.EntityRenderer;
import src.main.boundary.renderer.Renderable;

import javax.swing.*;
import java.awt.*;

public class NhanKhauChooserRenderer implements EntityRenderer {
    public NhanKhauChooserRenderer() {

    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        return new JLabel("Hello");
    }
}
