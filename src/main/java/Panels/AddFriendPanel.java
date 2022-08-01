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
    private JButton countAllUser;
    private int countUser = 0;
    private JButton showUser;
    private JButton showUser_aboveAvgAge;
    private JButton showGender;
    private JButton findUserHasAllFriends;
    private Read read;

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
        panel.setLayout(new GridLayout(5,1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2,1));

        // TextField
        label = new JLabel("      Input your friend's UID:");
        textField = new JTextField(20);

        // Buttons
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

        countAllUser = new JButton("Count all user");
        countAllUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    read = new Read(beLongTo.getConnection());
                    countUser = read.CountUser();
                    JOptionPane jOptionPane = new JOptionPane();
                    JPanel parentPanel = beLongTo;
                    jOptionPane.showMessageDialog(parentPanel, "The number of all user currently: \n" + countUser, "Count all users", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        showUser = new JButton("Show all user");
        showUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel parentPanel = beLongTo;
                showUser(beLongTo.getOwner(), parentPanel, true);
            }
        });

        showUser_aboveAvgAge = new JButton("Find user over average age");
        showUser_aboveAvgAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel parentPanel = beLongTo;
                showUser(beLongTo.getOwner(), parentPanel, false);
            }
        });

        showGender = new JButton("<html>Count users of different gender over average age</html>");
        showGender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel parentPanel = beLongTo;
                showCount_withGender(beLongTo.getOwner(), parentPanel);
            }
        });

        findUserHasAllFriends = new JButton("<html>Find user who has all users as friend</html>");
        findUserHasAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel parentPanel = beLongTo;
                showUserHasAllFriend(beLongTo.getOwner(), parentPanel);
            }
        });

        JPanel panel3 = new JPanel();
        panel3.add(countAllUser);
        panel3.add(showUser);
        panel3.add(showUser_aboveAvgAge);
        panel3.add(showGender);

        JPanel panel4 = new JPanel();
        panel4.add(findUserHasAllFriends);

        panel1.add(textField);
        panel1.add(addButton);
        panel2.add(label);
        panel2.add(panel1);
        panel.add(new JPanel());
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(new JPanel());

        this.add(addFriendLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }

    private void showUser(Frame owner, Component parentComponent, boolean all_or_overAvg) {
        final JDialog dialog = new JDialog(owner, "Show all user", true);
        dialog.setSize(250, 150);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parentComponent);

        JPanel panel = new JPanel(new BorderLayout());

        Object[] columnNames = {"User ID", "User Name"};

        Object[][] rowData = null;
        read = new Read(beLongTo.getConnection());
        try{
            if(all_or_overAvg)
                rowData = read.ReadUserID_Name();
            else{
                rowData = read.ReadUserID_Name_overAvg();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JTable table = new JTable(rowData, columnNames);

        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

        JScrollPane scrollPanel = new JScrollPane(
                panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(250,150);

        dialog.setContentPane(scrollPanel);
        dialog.setVisible(true);
    }

    //CountUser_overAvg_OfEachGender();
    private void showCount_withGender(Frame owner, Component parentComponent){
        final JDialog dialog = new JDialog(owner, "Count users with different gender and their average age", true);
        dialog.setResizable(false);
        dialog.setSize(250, 150);
        dialog.setLocationRelativeTo(parentComponent);

        JPanel panel = new JPanel(new BorderLayout());

        Object[] columnNames = {"Gender", "count", "avg_age"};

        Object[][] rowData = null;
        read = new Read(beLongTo.getConnection());
        try{
            rowData = read.CountUser_overAvg_OfEachGender();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JTable table = new JTable(rowData, columnNames);

        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private void showUserHasAllFriend(Frame owner, Component parentComponent){
        final JDialog dialog = new JDialog(owner, "Find user who has all users as friend", true);
        dialog.setResizable(false);
        dialog.setSize(250, 150);
        dialog.setLocationRelativeTo(parentComponent);

        JPanel panel = new JPanel(new BorderLayout());

        Object[] columnNames = {"User ID", "User Name"};

        Object[][] rowData = null;
        read = new Read(beLongTo.getConnection());
        try{
            rowData = read.ReadUserID_Name_hasAllFriend();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JTable table = new JTable(rowData, columnNames);

        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

        JScrollPane scrollPanel = new JScrollPane(
                panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(250,150);

        dialog.setContentPane(scrollPanel);
        dialog.setVisible(true);
    }
}
