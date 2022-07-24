package Panels;

import Constants.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupPanel extends JPanel {
    private JLabel addGroupLabel;
    private JButton addButton;
    private JTextField textField;

    public AddGroupPanel() {
        // Setting
        this.setSize(Constants.CHAT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocation(Constants.SIDE_PANEL_WIDTH+Constants.SELECT_PANEL_WIDTH,0);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        // Label
        addGroupLabel = new JLabel("Add Group");
        addGroupLabel.setFont(UnifiedFonts.font30B);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JPanel panel1 = new JPanel();

        // TextField
        textField = new JTextField("(Input the group's ID)");

        // Button
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add new Group: " + textField.getText());
            }
        });

        panel1.add(textField);
        panel1.add(addButton);
        panel.add(new JPanel());
        panel.add(panel1);
        panel.add(new JPanel());

        this.add(addGroupLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }
}
