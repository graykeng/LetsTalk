package Panels;

import javax.swing.*;
import java.awt.*;

public class ChatSelectPanel extends JPanel {
    private JPanel Title;
    private JScrollPane scrollPanel;

    public ChatSelectPanel(){
        // Setting
        this.setSize(250,600);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Title panel
        Title = new JPanel();
        Title.setSize(250,100);
        JLabel tileLabel = new JLabel("UserName"+"(uid:123456789)");
        Title.add(tileLabel);

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
        scrollPanel.setSize(250,400);


        this.add(Title, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
    }
}
