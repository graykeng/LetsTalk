package Panels;

import Constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChatSelectPanel extends JPanel {
    private JPanel Title;
    private JScrollPane scrollPanel;
    private MainPanel beLongTo;

    public ChatSelectPanel(MainPanel mainPanel){
        // Setting
        beLongTo = mainPanel;

        this.setSize(Constants.SELECT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLocation(Constants.SIDE_PANEL_WIDTH, 0);
        this.setLayout(null);

        // Title panel
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.SELECT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JButton UserHeadShotButton = new JButton();
        UserHeadShotButton.setIcon(new ImageIcon("src/main/java/Image/headshot.png"));
        UserHeadShotButton.setPressedIcon(new ImageIcon("src/main/java/Image/headshot_click.png"));
        UserHeadShotButton.setContentAreaFilled(false);
        UserHeadShotButton.setFocusPainted(false);
        UserHeadShotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switch to userInfo panel");
                beLongTo.UpdateState(State.UserInfoState);
            }
        });

        String User_Name = "UserName";
        String uid = "123456789";

        JLabel tileLabel = new JLabel("<html>"+User_Name+"<br>"+"(uid:"+uid+")</html>");
        tileLabel.setFont(UnifiedFonts.font20B);

        JButton addButton = new JButton();
        addButton.setIcon(new ImageIcon("src/main/java/Image/AddButton.png"));
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showThePopupMenu(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Title.add(UserHeadShotButton, BorderLayout.WEST);
        Title.add(tileLabel, BorderLayout.CENTER);
        Title.add(addButton, BorderLayout.EAST);

        // scrollPanel
        int rows = 10;
        String friendsName[] = {"Gray", "Lester", "Kerla","Austin","Gray", "Lester", "Kerla","Austin","Gray", "Lester"};
        JPanel FriendsPanel = new JPanel(null);
        FriendsPanel.setPreferredSize(new Dimension(380,75*rows));
        if (rows == 0) {
            String empty = "You have no friend, add your friend now!";
            JLabel showEmpty = new JLabel(empty);
            FriendsPanel.add(showEmpty, BorderLayout.CENTER);
        }
        for (int i = 0; i < rows; i++) {
            JPanel row = new JPanel(new BorderLayout());
            row.setSize(Constants.SELECT_PANEL_WIDTH,75);
            row.setLocation(0,75*i);
            String str = friendsName[i];

            JLabel FriendLabel = new JLabel(str);
            FriendLabel.setFont(UnifiedFonts.font20P);

            JButton FriendHeadShotButton = new JButton();
            FriendHeadShotButton.setIcon(new ImageIcon("src/main/java/Image/headshot.png"));
            FriendHeadShotButton.setPressedIcon(new ImageIcon("src/main/java/Image/headshot_click.png"));
            FriendHeadShotButton.setContentAreaFilled(false);
            FriendHeadShotButton.setFocusPainted(false);
            FriendHeadShotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Switch to userInfo panel");
                    beLongTo.UpdateState(State.UserInfoState);
                }
            });

            row.add(FriendHeadShotButton, BorderLayout.WEST);
            row.add(FriendLabel, BorderLayout.CENTER);

            row.setBorder(BorderFactory.createTitledBorder(""));
            FriendsPanel.add(row);
        }

        scrollPanel = new JScrollPane(
                FriendsPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(Constants.SELECT_PANEL_WIDTH,Constants.HEIGHT-Constants.TEXT_FIELD_HEIGHT-38);

        Title.setLocation(0,0);
        this.add(Title);
        scrollPanel.setLocation(0,Constants.TITLE_HEIGHT);
        this.add(scrollPanel);
    }

    private void showThePopupMenu(Component invoker, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem AddFriendMenuItem = new JMenuItem("Add new friend");
        JMenuItem AddGroupMenuItem = new JMenuItem("Add new group");

        popupMenu.add(AddFriendMenuItem);
        popupMenu.addSeparator();
        popupMenu.add(AddGroupMenuItem);

        AddFriendMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add a new friend");
                changeToAddFriend();
            }
        });
        AddGroupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add a new group");
                changeToAddGroup();
            }
        });

        popupMenu.show(invoker, x, y);
    }

    private void changeToAddFriend(){
        beLongTo.UpdateState(State.AddFriendState);
    }

    private void changeToAddGroup(){
        beLongTo.UpdateState(State.AddGroupState);
    }

}
