package src.main.boundary.app;

import com.formdev.flatlaf.ui.FlatLineBorder;
import src.main.boundary.feature.Feature;
import src.main.boundary.feature.FeatureView;
import src.main.boundary.GUIConfig;
import src.main.boundary.listener.FeatureListListener;
import src.main.boundary.listener.FeatureListener;
import src.main.boundary.sidebar.BoxSidebar;
import src.main.boundary.model.AppModel;
import src.main.boundary.sidebar.Sidebar;
import src.main.boundary.utility.ColorUtility;
import src.main.boundary.utility.ComponentFactory;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ToolTipUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
        Border margin = new EmptyBorder(3, 3, 3, 3);
        Border border = new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY);
        sidebar.setBorder(new CompoundBorder(border, margin));
        sidebar.setPreferredSize(new Dimension(37, -1));
        populateSideBar();
        highlightedButton = buttonList.get(0);

        featureView = new JPanel(new CardLayout());
        populateFeatureView();

        add(sidebar);
        add(featureView);
    }

    private void populateSideBar() {
        buttonList.clear();

        ArrayList<Feature> featureList = model.getFeatureList();
        for (int i = 0; i < model.getFeatureList().size(); i++) {
            Feature feature = featureList.get(i);

            JButton button = new JButton() {
                @Override
                public Point getToolTipLocation(MouseEvent event) {
                    return new Point(getWidth() + 3, 6);
                }
            };
            button.setMargin(new Insets(5, 5, 5, 5));
            button.setBorderPainted(false);
            button.setIcon(feature.getIcon());
            button.setFocusable(false);
            button.setBackground(GUIConfig.SideBarColor);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(this);
            button.setToolTipText(feature.getName());

            sidebar.add(button);
            buttonList.add(button);
        }
        sidebar.add(Box.createVerticalGlue());

        highlightedButton = buttonList.get(Integer.parseInt(model.getCurrentFeatureName()));
        highlightButton(highlightedButton);
    }

    private void populateFeatureView() {
        ArrayList<Feature> featureList = model.getFeatureList();
        for (int i = 0; i < featureList.size(); i++) {
            Feature feature = featureList.get(i);

            featureView.add(feature.getView(), String.valueOf(i));
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
        layout.show(featureView, model.getCurrentFeatureName());
    }
}
