package src.main.boundary.entityview;

import src.main.boundary.menubar.MenuBar;

import javax.swing.*;

// TODO
public class MultiListEntityView extends JPanel {
    MenuBar menuBar;

    public MultiListEntityView() {
        setupUI();
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        menuBar = new MenuBar();

    }
}
