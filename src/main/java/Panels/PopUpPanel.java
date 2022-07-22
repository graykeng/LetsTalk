package Panels;

import Constants.*;

import javax.swing.*;
import java.awt.*;

public class PopUpPanel extends JPanel {

    private JScrollPane scrollPane;

    private JPanel scrollSection;
    private JPanel profile;
    private JPanel information;
    private JPanel interests;
    private JPanel photoWall;

    private JLabel image;
    private JLabel userNameTXT;
    private JLabel uIDTXT;
    private JLabel infoTXT;

    public PopUpPanel(){
        prepareGUI();
    }

    private void prepareGUI(){
        scrollSection = new JPanel();
        profile = new JPanel();
        information = new JPanel();
        interests = new JPanel();
        photoWall = new JPanel();

        image = new JLabel();
        userNameTXT = new JLabel();
        uIDTXT = new JLabel();
        infoTXT = new JLabel();


        /**
         * Set the size and position for scrollPanel, information, interests, and photoWall
         */
        scrollSection.setLayout(null);
        scrollSection.setLocation(0, 0);
        scrollSection.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT*2));

        profile.setLayout(null);
        profile.setLocation(0, 0);
        profile.setSize(Constants.WIDTH, Constants.HEIGHT/3);

        information.setLayout(null);
        information.setLocation(0, (int)profile.getPreferredSize().getHeight());
        information.setSize(Constants.WIDTH, Constants.HEIGHT/3);

        interests.setLayout(null);
        interests.setLocation(0, (int)(profile.getPreferredSize().getHeight() + information.getPreferredSize().getHeight()));
        interests.setSize(Constants.WIDTH, Constants.HEIGHT/3);

        photoWall.setLayout(null);
        photoWall.setLocation(0, (int)(profile.getPreferredSize().getHeight() + information.getPreferredSize().getHeight() + interests.getPreferredSize().getHeight()));
        photoWall.setSize(Constants.WIDTH, Constants.HEIGHT);

        /**
         * Set the size, position, and content for Labels
         */
        userNameTXT.setFont(UnifiedFonts.font60B);
        userNameTXT.setText("USERNAME");
        userNameTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), scrollSection.getPreferredSize().getWidth()), Constants.HEIGHT/12);
        userNameTXT.setSize((int)userNameTXT.getPreferredSize().getWidth(), (int)userNameTXT.getPreferredSize().getHeight());
        profile.add(userNameTXT);

        uIDTXT.setFont(UnifiedFonts.font30P);
        uIDTXT.setText("USERID");
        uIDTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), scrollSection.getPreferredSize().getWidth()), Constants.HEIGHT/12 + (int)userNameTXT.getPreferredSize().getHeight());
        uIDTXT.setSize((int)uIDTXT.getPreferredSize().getWidth(), (int)uIDTXT.getPreferredSize().getHeight());
        profile.add(uIDTXT);

        infoTXT.setFont(UnifiedFonts.font30B);
        infoTXT.setText("INFORMATION:");
        infoTXT.setLocation(0, 0);
        infoTXT.setSize((int)infoTXT.getPreferredSize().getWidth(), (int)infoTXT.getPreferredSize().getHeight());
        information.add(infoTXT);

        /**
         * Add everything onto the scrollSection
         */
        scrollSection.add(profile);
        scrollSection.add(information);
        scrollSection.add(interests);
        scrollSection.add(photoWall);

        scrollPane = new JScrollPane(
                scrollSection,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setPreferredSize(new Dimension(Constants.WIDTH,  Constants.HEIGHT));

        this.add(scrollPane);
    }

    /**
     * Get the X for the INSIDE element you want to set in the middle of the OUTSIDE element
     * @param inside
     * @param outside
     * @return
     */
    private int getCentreX(Double inside, Double outside){
        Double doubleValue = outside/2 - inside/2;
        int intValue = doubleValue.intValue();
        return intValue;
    }

}
