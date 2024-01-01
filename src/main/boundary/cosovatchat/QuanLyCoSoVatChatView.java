package src.main.boundary.cosovatchat;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.renderer.ListRenderer;
import src.main.control.CoSoVatChatControl;
import src.main.control.PhongBanControl;
import src.test.control.CoSoVatChatControlTestValue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuanLyCoSoVatChatView extends FeatureView {
    CoSoVatChatControl coSoVatChatControl;
    PhongBanControl phongBanControl;

    public QuanLyCoSoVatChatView() {
        coSoVatChatControl = new CoSoVatChatControl();
        phongBanControl = new PhongBanControl();

        setupUI();
    }

    public void setupUI() {
        // Use grid layout to ensure 2 subview has equal sizes.
        setLayout(new GridLayout(0, 2));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        CoSoVatChatView coSoVatChatView = new CoSoVatChatView(new CoSoVatChatControlTestValue());
        coSoVatChatView.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(coSoVatChatView);
        coSoVatChatControl.setView(coSoVatChatView);

        PhongBanView phongBanView = new PhongBanView(new PhongBanControl());
        phongBanView.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(phongBanView);
        phongBanControl.setPhongBanView(phongBanView);
    }
}
