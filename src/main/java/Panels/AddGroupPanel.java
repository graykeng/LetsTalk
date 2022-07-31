package Panels;

import Constants.*;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.IsFriendOf;
import TableStruture.UserInGroup;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddGroupPanel extends JPanel {
    private JLabel addGroupLabel;
    private JButton addButton;
    private JLabel label;
    private JTextField textField;
    private MainPanel beLongTo;

    public AddGroupPanel(MainPanel mainPanel) {
        // Setting
        this.beLongTo = mainPanel;
        this.setSize(Constants.CHAT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocation(Constants.SIDE_PANEL_WIDTH+Constants.SELECT_PANEL_WIDTH,0);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        // Label
        addGroupLabel = new JLabel("Add Group");
        addGroupLabel.setFont(UnifiedFonts.font30B);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2,1));

        // TextField
        label = new JLabel("      Input your group's UID:");
        textField = new JTextField(20);

        // Button
        addButton = new JButton("Join");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Join new group: " + textField.getText());
                Insert insert = new Insert(beLongTo.getConnection());
                UserInGroup userInGroup = new UserInGroup(textField.getText(),beLongTo.getUser().getUser_id());
                try {
                    insert.InsertUserInGroup(userInGroup);

                    beLongTo.setFriends(new Read(beLongTo.getConnection()).ReadFriendInfo(beLongTo.getUser().getUser_id()));
                    JOptionPane jOptionPane = new JOptionPane();
                    JPanel parentPanel = beLongTo;
                    jOptionPane.showMessageDialog(parentPanel, "Join group! Go to chat with your group member now!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    beLongTo.UpdateState(State.ChatState);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane jOptionPane = new JOptionPane();
                    JPanel parentPanel = beLongTo;
                    jOptionPane.showMessageDialog(parentPanel, "You're already in that group!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panel1.add(textField);
        panel1.add(addButton);
        panel2.add(label);
        panel2.add(panel1);
        panel.add(new JPanel());
        panel.add(panel2);
        panel.add(new JPanel());
        panel.add(new JPanel());

        this.add(addGroupLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }
}
