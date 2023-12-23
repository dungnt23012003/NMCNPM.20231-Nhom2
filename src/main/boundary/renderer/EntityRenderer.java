package src.main.boundary.renderer;

import javax.swing.*;
import java.awt.*;

public interface EntityRenderer {
    Component getRenderedComponent(Renderable item);
}
