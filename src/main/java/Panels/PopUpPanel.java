package Panels;

import javax.swing.*;
import java.awt.*;

public class PopUpPanel extends JPanel {
    private JPanel topSide;
    private JPanel scrollSection;
    private JPanel space;
    private JLabel image;
    private JLabel userName;
    private JLabel uID;
    private JPanel information;
    private JPanel interests;
    private JPanel photoWall;
    private JScrollPane scrollPane;

    public PopUpPanel(){
        prepareGUI();
    }

    private void prepareGUI(){
        topSide = new JPanel();
        scrollSection = new JPanel();
        space = new JPanel();
        image = new JLabel();
        userName = new JLabel();
        uID = new JLabel();
        information = new JPanel();
        interests = new JPanel();
        photoWall = new JPanel();

        userName.setText("USERNAME");
        uID.setText("USERID");

        topSide.setLayout(new GridLayout(2, 1));
        topSide.add(userName);
        topSide.add(uID);

        scrollSection.setLayout(new GridLayout(3, 3));
        scrollSection.add(space);
        scrollSection.add(topSide);

        scrollPane = new JScrollPane(
                scrollSection,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setPreferredSize(new Dimension(600,  600));

        this.add(scrollPane);
    }

}
