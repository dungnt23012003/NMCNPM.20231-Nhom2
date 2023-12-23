package src.main.boundary.renderer;

import javax.swing.*;
import java.awt.*;

public class DefaultEntityRenderer implements EntityRenderer {
    @Override
    public Component getRenderedComponent(Renderable item) {
        return new JLabel("No renderer specified.");
    }
}
