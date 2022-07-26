package Panels;

import Constants.*;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.IsFriendOf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddFriendPanel extends JPanel {
    private JLabel addFriendLabel;
    private JButton addButton;
    private JLabel label;
    private JTextField textField;
    private MainPanel beLongTo;

    public AddFriendPanel(MainPanel mainPanel) {
        // Setting
        this.beLongTo = mainPanel;
        this.setSize(Constants.CHAT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocation(Constants.SIDE_PANEL_WIDTH+Constants.SELECT_PANEL_WIDTH,0);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        // Label
        addFriendLabel = new JLabel("Add Friend");
        addFriendLabel.setFont(UnifiedFonts.font30B);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2,1));

        // TextField
        label = new JLabel("      Input your friend's UID:");
        textField = new JTextField(20);

        // Button
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add new friend: " + textField.getText());
                Insert insert = new Insert(beLongTo.getConnection());
                IsFriendOf isFriendOf = new IsFriendOf(beLongTo.getUser().getUser_id(),textField.getText());
                IsFriendOf isFriendOf2 = new IsFriendOf(textField.getText(),beLongTo.getUser().getUser_id());
                try {
                    // Add Friend
                    insert.InsertIsFriendOf(isFriendOf);
                    insert.InsertIsFriendOf(isFriendOf2);

                    // Update
                    beLongTo.setFriends(new Read(beLongTo.getConnection()).ReadFriendInfo(beLongTo.getUser().getUser_id()));
                    JOptionPane jOptionPane = new JOptionPane();
                    JPanel parentPanel = beLongTo;
                    jOptionPane.showMessageDialog(parentPanel, "Friend Added! Go to chat with him/her now!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    beLongTo.UpdateState(State.ChatState);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane jOptionPane = new JOptionPane();
                    JPanel parentPanel = beLongTo;
                    jOptionPane.showMessageDialog(parentPanel, "Wrong User ID for your Friend or he/she already be your friend!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
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

        this.add(addFriendLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }
}
