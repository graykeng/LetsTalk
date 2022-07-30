package Panels;

import Constants.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel{
    private JButton chatButton;
    private JButton photoButton;
    private MainPanel beLongTo;

    public SidePanel(MainPanel mainPanel) {
        beLongTo = mainPanel;

        this.setSize(Constants.SIDE_PANEL_WIDTH,Constants.HEIGHT);
        this.setLayout(new GridLayout(2,1));
        this.setLocation(0,0);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        chatButton = new JButton();
        photoButton = new JButton();

        chatButton.setSize(20,20);
        chatButton.setIcon(new ImageIcon("src/main/resources/Image/ChatButton.png"));
        chatButton.setPressedIcon(new ImageIcon("src/main/resources/Image/ChatButton_click.png"));
        chatButton.setContentAreaFilled(false);
        chatButton.setFocusPainted(false);
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switch to chat panel");
                beLongTo.UpdateState(State.ChatState);
            }
        });

        photoButton.setSize(20,20);
        photoButton.setIcon(new ImageIcon("src/main/resources/Image/PhotosButton.png"));
        photoButton.setPressedIcon(new ImageIcon("src/main/resources/Image/PhotosButton_click.png"));
        photoButton.setContentAreaFilled(false);
        photoButton.setFocusPainted(false);
        photoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switch to photos panel");
                beLongTo.UpdateState(State.PhotoState);
            }
        });

        this.add(chatButton);
        this.add(photoButton);
    }
}
