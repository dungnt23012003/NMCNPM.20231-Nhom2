package src.main.boundary.cosovatchat;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.renderer.ListRenderer;
import src.main.boundary.renderer.MultiListRenderer;
import src.main.boundary.utility.ComponentFactory;
import src.main.control.CoSoVatChatControl;
import src.main.control.PhongBanControl;
import src.main.entity.CoSoVatChat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoSoVatChatView extends FeatureView {
    CoSoVatChatControl coSoVatChatControl;
    PhongBanControl phongBanControl;

    public CoSoVatChatView() {
        coSoVatChatControl = new CoSoVatChatControl();
        phongBanControl = new PhongBanControl();

        setupUI();
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        ListRenderer renderer = new ListRenderer();
        Component coSoVatChatView = renderer.getRenderedComponent(new CoSoVatChatListAdapter(coSoVatChatControl.getList()));
        ((JComponent) coSoVatChatView).setAlignmentY(0);
        add(coSoVatChatView);
        add(Box.createRigidArea(new Dimension(20, 0)));

        Component phongBan = renderer.getRenderedComponent(new PhongBanListAdapter(phongBanControl.getList()));
        ((JComponent) phongBan).setAlignmentY(0);
        add(phongBan);
    }
}
