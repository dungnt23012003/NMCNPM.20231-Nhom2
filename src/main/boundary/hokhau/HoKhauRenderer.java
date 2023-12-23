package src.main.boundary.hokhau;

import src.main.MenuBarDeleteButton;
import src.main.boundary.GUIConfig;
import src.main.boundary.renderer.EntityRenderer;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.renderer.Renderable;
import src.main.boundary.menubar.MenuBar;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class HoKhauRenderer implements EntityRenderer {
    HoKhauController controller;

    public HoKhauRenderer(HoKhauController controller) {
        this.controller = controller;
    }

    public Component getRenderedComponent(HoKhauAdapter item) {
        JPanel renderedComponent = new JPanel();
        renderedComponent.setLayout(new BoxLayout(renderedComponent, BoxLayout.PAGE_AXIS));

       MenuBar menuBar = new MenuBar();
       menuBar.setAlignmentX(0.0f);
       menuBar.addButton(GUIConfig.AddIcon, e -> controller.add());
       menuBar.addButton(GUIConfig.settingIcon, e -> controller.setting(item.hoKhau));
       menuBar.add(new MenuBarDeleteButton(e -> controller.delete(item.hoKhau)));
//       menuBar.add(Box.createHorizontalGlue());

       renderedComponent.add(menuBar);
       renderedComponent.add(Box.createVerticalStrut(10));

       MultiListRenderer listRenderer = new MultiListRenderer();
       renderedComponent.add(listRenderer.getRenderedComponent(item));

       return renderedComponent;
    }

    @Override
    public Component getRenderedComponent(Renderable item) {
        if (item instanceof HoKhauAdapter) {
            return getRenderedComponent((HoKhauAdapter) item);
        }

        return ComponentFactory.createLabel("This renderer can only render HoKhauAdapter.");
    }
}
