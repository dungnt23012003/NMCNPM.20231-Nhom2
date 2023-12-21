package src.main.boundary.app;

import src.main.boundary.feature.FeatureView;
import src.main.boundary.GUIConfig;
import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;
import src.main.boundary.sidebar.BoxSidebar;
import src.main.boundary.model.AppModel;
import src.main.boundary.sidebar.Sidebar;
import src.main.boundary.utility.ColorUtility;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppView extends JPanel implements FeatureListener, FeatureListListener, ActionListener {
    AppController controller;
    AppModel model;
    Sidebar sidebar;
    JPanel featureView;
    ArrayList<JButton> buttonList = new ArrayList<>();
    JButton highlightedButton;

    public AppView(AppController controller, AppModel model) {
        this.controller = controller;
        this.model = model;

        model.addListener((FeatureListener) this);
        model.addListener((FeatureListListener) this);
    }

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        sidebar = new BoxSidebar();
        Border margin = new EmptyBorder(2, 2, 2, 2);
        Border border = new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY);
        sidebar.setBorder(new CompoundBorder(border, margin));
        sidebar.setPreferredSize(new Dimension(37, -1));
        populateSideBar();

        highlightedButton = buttonList.get(0);
        highlightButton(highlightedButton);

        featureView = new JPanel(new CardLayout());
        populateFeatureView();

        add(sidebar);
        add(featureView);
    }

    private void populateSideBar() {
        buttonList.clear();

        ArrayList<FeatureView> featureViews = model.getFeatureViews();
        for (int i = 0; i < model.getFeatureViews().size(); i++) {
            FeatureView view = featureViews.get(i);

            JButton button = new JButton();
            button.setMargin(new Insets(5, 5, 5, 5));
//            button.setBorder(new EmptyBorder(5, 5, 5, 5));
            button.setBorderPainted(false);
            button.setIcon(view.getIcon());
            button.setFocusable(false);
            button.setBackground(GUIConfig.SideBarColor);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(this);

            sidebar.add(button);
            buttonList.add(button);
        }
        sidebar.add(Box.createVerticalGlue());
    }

    private void populateFeatureView() {
        ArrayList<FeatureView> featureViews = model.getFeatureViews();
        for (int i = 0; i < featureViews.size(); i++) {
            FeatureView view = featureViews.get(i);

            featureView.add(view, String.valueOf(i));
        }
    }

    private void highlightButton(JButton button) {
        button.setBackground(ColorUtility.darken(GUIConfig.SideBarColor, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        highlightedButton.setBackground(GUIConfig.SideBarColor);
        controller.setCurrentView(e.getActionCommand());
        highlightedButton = (JButton) e.getSource();
        highlightButton(highlightedButton);
    }

    @Override
    public void featureListChanged() {
        sidebar.removeAll();
        populateSideBar();

        featureView.removeAll();
        populateFeatureView();
    }

    @Override
    public void featureChanged() {
        CardLayout layout = (CardLayout) featureView.getLayout();
        layout.show(featureView, model.getCurrentFeatureViewName());
    }
}
