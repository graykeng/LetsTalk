package Panels;

import Constants.*;
import JDBC.Read;
import TableStruture.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UserInfoPanel extends JPanel {

    private JPanel wholePanel;
    private JPanel profile;
    private JPanel information;
    private JPanel interests;
    private JPanel interestsGrid;
    private JPanel photoWall;
    private JPanel genderBirthdayAge;

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

    private User user;
    private String[] interestArray;

    public UserInfoPanel() {

    }

    public UserInfoPanel(User user){
        this.user = user;
        prepareGUI();
    }

    private void prepareGUI(){
        wholePanel = new JPanel();
        profile = new JPanel();
        information = new JPanel();
        interests = new JPanel();
        interestsGrid = new JPanel(new GridLayout(1, 4));
        photoWall = new JPanel();
        genderBirthdayAge = new JPanel(new GridLayout(1, 3));

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
        wholePanel.setLayout(null);
        wholePanel.setLocation(0, 0);
        wholePanel.setPreferredSize(new Dimension(Constants.USER_INFO_WIDTH, Constants.HEIGHT*2));

        profile.setLayout(null);
        profile.setLocation(0, 0);
        profile.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/3);

        information.setLayout(null);
        information.setLocation(0, (int)profile.getPreferredSize().getHeight());
        information.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/3);

        interests.setLayout(null);
        interests.setLocation(0, (int)(profile.getPreferredSize().getHeight() + information.getPreferredSize().getHeight()));
        interests.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/6);

        photoWall.setLayout(null);
        photoWall.setLocation(0, (int)(profile.getPreferredSize().getHeight() + information.getPreferredSize().getHeight() + interests.getPreferredSize().getHeight()));
        photoWall.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT);

        /**
         * Set the size, position, and content for Labels
         */
        userNameTXT.setFont(UnifiedFonts.font25B);
        userNameTXT.setText(user.getName());
        userNameTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), wholePanel.getPreferredSize().getWidth()), Constants.HEIGHT/12);
        userNameTXT.setSize((int)userNameTXT.getPreferredSize().getWidth()+10, (int)userNameTXT.getPreferredSize().getHeight());
        profile.add(userNameTXT);

        uIDTXT.setFont(UnifiedFonts.font20P);
        uIDTXT.setText(user.getUser_id());
        uIDTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), wholePanel.getPreferredSize().getWidth()), Constants.HEIGHT/12 + (int)userNameTXT.getPreferredSize().getHeight());
        uIDTXT.setSize((int)uIDTXT.getPreferredSize().getWidth()+10, (int)uIDTXT.getPreferredSize().getHeight());
        profile.add(uIDTXT);

        infoTXT.setFont(UnifiedFonts.font20B);
        infoTXT.setText("INFORMATION:");
        infoTXT.setLocation(0, 0);
        infoTXT.setSize((int)infoTXT.getPreferredSize().getWidth()+10, (int)infoTXT.getPreferredSize().getHeight());
        information.add(infoTXT);

        sexTXT.setFont(UnifiedFonts.font20P);
        sexTXT.setText("GENDER: " + user.getGender());
        sexTXT.setLocation(0, (int)(information.getPreferredSize().getHeight()/2 - infoTXT.getPreferredSize().getHeight()));
        sexTXT.setSize((int)sexTXT.getPreferredSize().getWidth()+10, (int)sexTXT.getPreferredSize().getWidth());
        genderBirthdayAge.add(sexTXT);

        birthdayTXT.setFont(UnifiedFonts.font20P);
        birthdayTXT.setText("BIRTHDAY: " + user.getBirthday());
        birthdayTXT.setLocation(getCentreX(birthdayTXT.getPreferredSize().getWidth(), information.getPreferredSize().getWidth()), (int)information.getPreferredSize().getHeight()/2);
        birthdayTXT.setSize((int)birthdayTXT.getPreferredSize().getWidth()+10, (int)birthdayTXT.getPreferredSize().getHeight());
        genderBirthdayAge.add(birthdayTXT);

        ageTXT.setFont(UnifiedFonts.font20P);
        ageTXT.setText("AGE: " + user.getAge() + "     ");
        ageTXT.setLocation((int)(information.getPreferredSize().getWidth()-ageTXT.getPreferredSize().getWidth())-Constants.SCROLL_CONTROLLER_WIDTH, (int)information.getPreferredSize().getHeight()/2);
        ageTXT.setSize((int)ageTXT.getPreferredSize().getWidth()+10, (int)ageTXT.getPreferredSize().getHeight());
        genderBirthdayAge.add(ageTXT);

        interestTXT.setFont(UnifiedFonts.font20B);
        interestTXT.setText("INTERESTS:");
        interestTXT.setLocation(0, 0);
        interestTXT.setSize((int)interestTXT.getPreferredSize().getWidth()+10, (int)interestTXT.getPreferredSize().getHeight());
        interests.add(interestTXT);

//        photoWallTXT.setFont(UnifiedFonts.font20B);
//        photoWallTXT.setText("PHOTO WALL:");
//        photoWallTXT.setLocation(0, 0);
//        photoWallTXT.setSize(photoWallTXT.getPreferredSize());
//        photoWall.add(photoWallTXT);

        genderBirthdayAge.setLocation(0, (int)information.getPreferredSize().getHeight()/2);
        genderBirthdayAge.setSize(Constants.WIDTH - Constants.SIDE_PANEL_WIDTH, (int)sexTXT.getPreferredSize().getHeight());
        information.add(genderBirthdayAge);

        /**
         * Add all the interests onto the interest panel
         */
        for(int i = 0; i < interestArray.length; i++){
            singleInterest = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
            singleInterest.setBorder(border);
            singleInterest.setFont(UnifiedFonts.font20P);
            singleInterest.setText(interestArray[i]);
            singleInterest.setText(interestArray[i]);
            singleInterest.setToolTipText("INTEREST TYPE");
            interestsGrid.add(singleInterest);
        }
        interestsGrid.setLocation(0, (int)interestTXT.getPreferredSize().getHeight());
        interestsGrid.setSize(Constants.USER_INFO_WIDTH - Constants.SCROLL_CONTROLLER_WIDTH, (int) singleInterest.getPreferredSize().getHeight());
        interests.add(interestsGrid);

        /**
         * Add everything onto the scrollSection
         */
        wholePanel.add(profile);
        wholePanel.add(information);
        wholePanel.add(interests);
        wholePanel.add(photoWall);

        this.setSize((int)this.getPreferredSize().getWidth(), (int)this.getPreferredSize().getHeight());
        this.add(wholePanel);
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

    public void setUser(User user){
        this.user = user;
    }

}
