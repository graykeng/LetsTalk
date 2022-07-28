package Panels;

import Constants.*;
import JDBC.Delete;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.IsFriendOf;
import TableStruture.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserInfoPanel extends JPanel {

    private JButton ChangeOrDelete;
    private JPanel wholePanel;
    private JPanel profile;
    private JPanel information;
    private JPanel interests;
    private JPanel interestsGrid;
    private JPanel photoWall;
    private JPanel genderBirthdayAge;

    private JLabel userNameTXT;
    private JLabel uIDTXT;
    private JLabel infoTXT;
    private JLabel sexTXT;
    private JLabel birthdayTXT;
    private JLabel ageTXT;
    private JLabel interestTXT;
    private JLabel singleInterest;
    private JButton interestEditButton;

    private User user;
    private String[] interestArray;

    private MainPanel belongTo;

    public UserInfoPanel() {

    }

    public UserInfoPanel(User user,MainPanel main){
        this.belongTo=main;
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

        userNameTXT = new JLabel();
        uIDTXT = new JLabel();
        infoTXT = new JLabel();
        sexTXT = new JLabel();
        birthdayTXT = new JLabel();
        ageTXT = new JLabel();
        interestTXT = new JLabel();
        interestEditButton = new JButton();
        ChangeOrDelete = new JButton();

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
        //Add Change button


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

        ChangeOrDelete.setFont(UnifiedFonts.font15P);
        if(belongTo.getUser() == user){ ChangeOrDelete.setText("Edit Profile"); }
        else { ChangeOrDelete.setText("Delete"); }
        ChangeOrDelete.setLocation(Constants.USER_INFO_WIDTH/10, Constants.HEIGHT/10);
        ChangeOrDelete.setSize((int)ChangeOrDelete.getPreferredSize().getWidth()+5,20);
        ChangeOrDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(belongTo.getUser()==user){
                    belongTo.UpdateState(State.ChangeButtonClickedState);
                }
                else{
                    System.out.println("Delete friend: " + user.getUser_id());
                    Delete delete = new Delete(belongTo.getConnection());
                    try {
                        // Delete Friend
                        delete.DeleteIsFriendOf(belongTo.getUser().getUser_id(),user.getUser_id());
                        delete.DeleteIsFriendOf(user.getUser_id(),belongTo.getUser().getUser_id());

                        // Update
                        belongTo.setFriends(new Read(belongTo.getConnection()).ReadFriendInfo(belongTo.getUser().getUser_id()));
                        JOptionPane jOptionPane = new JOptionPane();
                        JPanel parentPanel = belongTo;
                        jOptionPane.showMessageDialog(parentPanel, "Friend is deleted!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        belongTo.UpdateState(State.ChatState);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane jOptionPane = new JOptionPane();
                        JPanel parentPanel = belongTo;
                        jOptionPane.showMessageDialog(parentPanel, "Wrong User ID for your Friend!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        profile.add(ChangeOrDelete);

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

        interestEditButton.setFont(UnifiedFonts.font15P);
        interestEditButton.setText("Edit");
        interestEditButton.setLocation((int)interestTXT.getPreferredSize().getWidth()+10, 0);
        interestEditButton.setSize((int)interestEditButton.getPreferredSize().getWidth()+5, (int)interestEditButton.getPreferredSize().getHeight());
        ChangeOrDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputInterest = JOptionPane.showInputDialog(
                        null,
                        "Input Your Interest:"
                );

            }
        });
        interests.add(interestEditButton);

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
        interestsGrid.setLocation(0, (int)interestTXT.getPreferredSize().getHeight()+20);
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
