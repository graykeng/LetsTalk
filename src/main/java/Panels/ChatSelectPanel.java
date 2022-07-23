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

    public ChatSelectPanel(){
        // Setting
        this.setSize(Constants.FRIEND_SELECT_PANEL_WIDTH,Constants.HEIGHT);
        this.setLocation(Constants.SIDE_PANEL_WIDTH, 0);
        this.setLayout(null);

        // Title panel
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.FRIEND_SELECT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
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
                showPopupMenu(e.getComponent(), e.getX(), e.getY());
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
            JPanel row = new JPanel(new FlowLayout());
            row.setSize(380,75);
            row.setLocation(0,75*i);
            String str = friendsName[i];
            JLabel FriendLabel = new JLabel(str);


            row.add(FriendLabel, BorderLayout.NORTH);

            row.setBorder(BorderFactory.createTitledBorder(""));
            FriendsPanel.add(row);
        }

        scrollPanel = new JScrollPane(
                FriendsPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(Constants.FRIEND_SELECT_PANEL_WIDTH,Constants.HEIGHT-Constants.TEXT_FIELD_HEIGHT-38);

        Title.setLocation(0,0);
        this.add(Title);
        scrollPanel.setLocation(0,Constants.TITLE_HEIGHT);
        this.add(scrollPanel);
    }

    public static void showPopupMenu(Component invoker, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        // 创建 一级菜单
        JMenuItem AddFriendMenuItem = new JMenuItem("Add new friend");
        JMenuItem AddGroupMenuItem = new JMenuItem("Add new group");

        // 添加 一级菜单 到 弹出菜单
        popupMenu.add(AddFriendMenuItem);
        popupMenu.addSeparator();
        popupMenu.add(AddGroupMenuItem);

        // 添加菜单项的点击监听器
        AddFriendMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add a new friend");
            }
        });
        AddGroupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add a new group");
            }
        });

        popupMenu.show(invoker, x, y);
    }

}
