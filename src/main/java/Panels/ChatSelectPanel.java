package Panels;

import Constants.*;
import Helper.CopeImageUtil;
import TableStruture.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatSelectPanel extends JPanel {
    private JPanel Title;
    private JScrollPane scrollPanel;
    private MainPanel beLongTo;
    private User user;
    private ArrayList<User> friends;
    private CopeImageUtil copeImageUtil = new CopeImageUtil();

    public ChatSelectPanel() {

    }

    public ChatSelectPanel(MainPanel mainPanel){
        // Setting
        beLongTo = mainPanel;
        user = beLongTo.getUser();
        friends = beLongTo.getFriends();

        this.setSize(Constants.SELECT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLocation(Constants.SIDE_PANEL_WIDTH, 0);
        this.setLayout(null);

        // Title panel
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.SELECT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JButton UserHeadShotButton = new JButton();

        UserHeadShotButton.setIcon(copeImageUtil.blobToIcon(user.getHeadshot()));
        UserHeadShotButton.setContentAreaFilled(false);
        UserHeadShotButton.setFocusPainted(false);
        UserHeadShotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switch to userInfo panel");
                beLongTo.setCurrUser(beLongTo.getUser());
                beLongTo.UpdateState(State.UserInfoState);
            }
        });

        String User_Name = user.getName();
        String uid = user.getUser_id();

        JLabel tileLabel = new JLabel("<html>"+User_Name+"<br>"+"(uid:"+uid+")</html>");
        tileLabel.setFont(UnifiedFonts.font20B);

        JButton addButton = new JButton();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Image/AddButton.png")));
            addButton.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        int rows = friends.size();
        JPanel FriendsPanel = new JPanel(null);
        FriendsPanel.setPreferredSize(new Dimension(380,75*rows));
        if (rows == 0) {
            String empty = "You have no friend,\uD83D\uDE05 \uD83D\uDE05 \uD83D\uDE05";
            JLabel showEmpty = new JLabel(empty);
            showEmpty.setLocation(0,200);
            showEmpty.setSize(Constants.SELECT_PANEL_WIDTH,50);
            showEmpty.setFont(UnifiedFonts.font25B);
            FriendsPanel.add(showEmpty);
        }
        for (int i = 0; i < rows; i++) {
            JPanel row = new JPanel(new BorderLayout());
            row.setSize(Constants.SELECT_PANEL_WIDTH,75);
            row.setLocation(0,75*i);
            int num = i;
            row.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Change to talk with "+ (num+1) +"th friend");
                    beLongTo.setCurrUser(friends.get(num));
                    beLongTo.UpdateState(State.ChatState);
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

            // Label
            String str = friends.get(i).getName();
            JLabel FriendLabel = new JLabel(str);
            FriendLabel.setFont(UnifiedFonts.font20P);

            ImageIcon FriendIcon = new ImageIcon();
            try{
                byte[] imageByte = friends.get(i).getHeadshot().getBytes(1, (int)friends.get(i).getHeadshot().length());
                ByteArrayInputStream in = new ByteArrayInputStream(imageByte);
                try{
                    BufferedImage imag = ImageIO.read(in);
                    FriendIcon = new ImageIcon(imag);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }

            JButton FriendHeadShotButton = new JButton();
            FriendHeadShotButton.setIcon(FriendIcon);
            FriendHeadShotButton.setContentAreaFilled(false);
            FriendHeadShotButton.setFocusPainted(false);
            FriendHeadShotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Switch to "+ (num+1) +"th friendInfo panel");
                    beLongTo.setCurrUser(friends.get(num));
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
