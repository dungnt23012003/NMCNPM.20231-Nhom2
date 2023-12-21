package src.test.boundary;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.boundary.CustomJList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomJListTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UIManager.put("List.selectionArc", 10);


        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");
        listModel.addElement("HK00001");
        listModel.addElement("HK00002");
        listModel.addElement("HK00003");
        listModel.addElement("HK00004");
        listModel.addElement("HK00005");

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setLayout(new GridBagLayout());


        CustomJList<String> list = new CustomJList<>(listModel);
//        list.setPreferredSize(new Dimension(200,  -1));
//        list.setMaximumSize(new Dimension(100, -1));
        list.setFocusable(false);
        list.setSelectedIndex(0);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setFocusable(false);
        scrollPane.setPreferredSize(new Dimension(200, -1));
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
//        scrollPane.getVerticalScrollBar().
//        scrollPane.setMaximumSize(new Dimension(200, -1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;

//        panel.add(list, gbc);

        gbc.gridx = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(scrollPane, gbc);

//        panel.add(scrollPane);
        frame.setContentPane(panel);

        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
