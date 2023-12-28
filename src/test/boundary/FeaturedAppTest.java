package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import src.main.boundary.GUIConfig;
import src.main.boundary.app.DefaultAppController;
import src.main.boundary.model.DefaultAppModel;
import src.main.boundary.nhankhau.NhanKhauFeature;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ToolTipUI;
import java.awt.*;

public class FeaturedAppTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UIManager.put("Component.borderColor", new Color(210, 210, 210));
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.arc", 10);
        UIManager.put("List.selectionArc", 10);

        // New UI Key
        UIManager.put("ToolTip.background", GUIConfig.FeatureViewColor);
        UIManager.put("ToolTip.font", GUIConfig.DefaultFont);
        UIManager.put("ToolTip.border", new EmptyBorder(2, 2, 2, 2));
        ToolTipManager.sharedInstance().setInitialDelay(0);

        JFrame frame = new JFrame();

        DefaultAppModel model = new DefaultAppModel();

        // Your code here
        model.addFeature(new HoKhauFeatureTest());
        model.addFeature(new NhanKhauFeatureTest());

        DefaultAppController controller = new DefaultAppController(model);

        frame.setContentPane(controller.getView());
        frame.setTitle("._.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
}
