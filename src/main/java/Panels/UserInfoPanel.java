package Panels;

import Constants.*;

import javax.swing.*;
import java.awt.*;

public class UserInfoPanel extends JPanel {

    private JScrollPane scrollPane;

    private JPanel scrollSection;
    private JPanel profile;
    private JPanel information;
    private JPanel interests;
    private JPanel interestsGrid;
    private JPanel photoWall;

    private JLabel image;
    private JLabel userNameTXT;
    private JLabel uIDTXT;
    private JLabel infoTXT;
    private JLabel sexTXT;
    private JLabel birthdayTXT;
    private JLabel ageTXT;
    private JLabel interestTXT;
    private JLabel photoWallTXT;
    private JLabel singleInterest;

    private String[] interestArray;

    public UserInfoPanel(){
        prepareGUI();
    }

    private void prepareGUI(){
        scrollSection = new JPanel();
        profile = new JPanel();
        information = new JPanel();
        interests = new JPanel();
        interestsGrid = new JPanel(new GridLayout(1, 4));
        photoWall = new JPanel();

        image = new JLabel();
        userNameTXT = new JLabel();
        uIDTXT = new JLabel();
        infoTXT = new JLabel();
        sexTXT = new JLabel();
        birthdayTXT = new JLabel();
        ageTXT = new JLabel();
        interestTXT = new JLabel();
        photoWallTXT = new JLabel();

        interestArray = new String[]{"Basketball", "LOL", "WOW", "COOK"};

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
        interests.setSize(Constants.WIDTH, Constants.HEIGHT/6);

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

        sexTXT.setFont(UnifiedFonts.font20P);
        sexTXT.setText("SEX: M/F");
        sexTXT.setLocation(0, (int)(information.getPreferredSize().getHeight()/2 - infoTXT.getPreferredSize().getHeight()));
        sexTXT.setSize((int)sexTXT.getPreferredSize().getWidth(), (int)sexTXT.getPreferredSize().getWidth());
        information.add(sexTXT);

        birthdayTXT.setFont(UnifiedFonts.font20P);
        birthdayTXT.setText("BIRTHDAY: //");
        birthdayTXT.setLocation(getCentreX(birthdayTXT.getPreferredSize().getWidth(), information.getPreferredSize().getWidth()), (int)information.getPreferredSize().getHeight()/2);
        birthdayTXT.setSize((int)birthdayTXT.getPreferredSize().getWidth(), (int)birthdayTXT.getPreferredSize().getHeight());
        information.add(birthdayTXT);

        ageTXT.setFont(UnifiedFonts.font20P);
        ageTXT.setText("AGE: ");
        ageTXT.setLocation((int)(information.getPreferredSize().getWidth()-ageTXT.getPreferredSize().getWidth())-Constants.SCROLL_CONTROLLER_WIDTH, (int)information.getPreferredSize().getHeight()/2);
        ageTXT.setSize((int)ageTXT.getPreferredSize().getWidth(), (int)ageTXT.getPreferredSize().getHeight());
        information.add(ageTXT);

        interestTXT.setFont(UnifiedFonts.font30B);
        interestTXT.setText("INTERESTS:");
        interestTXT.setLocation(0, 0);
        interestTXT.setSize((int)interestTXT.getPreferredSize().getWidth(), (int)interestTXT.getPreferredSize().getHeight());
        interests.add(interestTXT);

        photoWallTXT.setFont(UnifiedFonts.font30B);
        photoWallTXT.setText("PHOTOWALL:");
        photoWallTXT.setLocation(0, 0);
        photoWallTXT.setSize((int)photoWallTXT.getPreferredSize().getWidth(), (int)photoWallTXT.getPreferredSize().getHeight());
        photoWall.add(photoWallTXT);

        /**
         * Add all the interests onto the interest panel
         */
        for(int i = 0; i < interestArray.length; i++){
            singleInterest = new JLabel();
            singleInterest.setFont(UnifiedFonts.font20P);
            singleInterest.setText(interestArray[i]);
            singleInterest.setText(interestArray[i]);
            singleInterest.setToolTipText("INTEREST TYPE");
            interestsGrid.add(singleInterest);
        }
        interestsGrid.setLocation(0, (int)interestTXT.getPreferredSize().getHeight());
        interestsGrid.setSize(Constants.WIDTH - Constants.SCROLL_CONTROLLER_WIDTH, (int) singleInterest.getPreferredSize().getHeight());
        interests.add(interestsGrid);

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
        double doubleValue = outside/2 - inside/2;
        return (int)doubleValue;
    }

}
