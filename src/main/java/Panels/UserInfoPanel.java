package Panels;

import Constants.*;
import Helper.CopeImageUtil;
import JDBC.Delete;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.Has;
import TableStruture.Interest;
import TableStruture.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfoPanel extends JPanel {

    private JButton editOrDelete;
    private JPanel wholePanel;
    private JPanel profile;
    private JPanel information;
    private JPanel interests;
    private JPanel interestsGrid;
    private JPanel genderBirthdayAge;

    private JLabel userNameTXT;
    private JLabel image;
    private JLabel uIDTXT;
    private JLabel infoTXT;
    private JLabel sexTXT;
    private JLabel birthdayTXT;
    private JLabel ageTXT;
    private JLabel interestTXT;
    private JButton singleInterest;
    private JButton interestAddButton;

    private User user;
    private Read read;

    private MainPanel belongTo;

    public UserInfoPanel() {

    }

    public UserInfoPanel(User user,MainPanel main){
        this.belongTo=main;
        this.user = user;
        prepareGUI();
    }

    private void prepareGUI() {

        wholePanel = new JPanel();
        profile = new JPanel();
        information = new JPanel();
        interests = new JPanel();
        interestsGrid = new JPanel();
        genderBirthdayAge = new JPanel(new GridLayout(1, 3));

        userNameTXT = new JLabel();
        image = new JLabel();
        uIDTXT = new JLabel();
        infoTXT = new JLabel();
        sexTXT = new JLabel();
        birthdayTXT = new JLabel();
        ageTXT = new JLabel();
        interestTXT = new JLabel();
        interestAddButton = new JButton();
        editOrDelete = new JButton();
        Insert insert = new Insert(belongTo.getConnection());
        read = new Read(belongTo.getConnection());

        /**
         * Set the size and position for scrollPanel, information, interests, and photoWall
         */
        wholePanel.setLayout(null);
        wholePanel.setLocation(0, 0);
        wholePanel.setPreferredSize(new Dimension(Constants.USER_INFO_WIDTH, Constants.HEIGHT));

        profile.setLayout(null);
        profile.setLocation(0, 0);
        profile.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/3);

        information.setLayout(null);
        information.setLocation(0, (int)profile.getPreferredSize().getHeight());
        information.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/3);

        interests.setLayout(null);
        interests.setLocation(0, (int)(profile.getPreferredSize().getHeight() + information.getPreferredSize().getHeight()));
        interests.setSize(Constants.USER_INFO_WIDTH, Constants.HEIGHT/6);

        /**
         * Set the size, position, and content for Labels
         */

        userNameTXT.setFont(UnifiedFonts.font25B);
        userNameTXT.setText(user.getName());
        userNameTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), wholePanel.getPreferredSize().getWidth()), Constants.HEIGHT/12);
        userNameTXT.setSize((int)userNameTXT.getPreferredSize().getWidth()+10, (int)userNameTXT.getPreferredSize().getHeight());
        profile.add(userNameTXT);

        CopeImageUtil copeImageUtil = new CopeImageUtil();
        image.setIcon(copeImageUtil.blobToIcon(user.getHeadshot()));
        image.setLocation(userNameTXT.getX()-100, userNameTXT.getY());
        image.setSize((int)image.getPreferredSize().getWidth(), (int)image.getPreferredSize().getHeight());
        profile.add(image);

        uIDTXT.setFont(UnifiedFonts.font20P);
        uIDTXT.setText(user.getUser_id());
        uIDTXT.setLocation(getCentreX(userNameTXT.getPreferredSize().getWidth(), wholePanel.getPreferredSize().getWidth()), Constants.HEIGHT/12 + (int)userNameTXT.getPreferredSize().getHeight());
        uIDTXT.setSize((int)uIDTXT.getPreferredSize().getWidth()+10, (int)uIDTXT.getPreferredSize().getHeight());
        profile.add(uIDTXT);

        editOrDelete.setFont(UnifiedFonts.font15P);
        if(belongTo.getUser() == user){ editOrDelete.setText("Edit Profile"); }
        else { editOrDelete.setText("Delete"); }
        editOrDelete.setLocation(Constants.USER_INFO_WIDTH/10, Constants.HEIGHT/10);
        editOrDelete.setSize((int) editOrDelete.getPreferredSize().getWidth()+5,20);
        editOrDelete.addActionListener(new ActionListener() {
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
        profile.add(editOrDelete);

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


        interestAddButton.setFont(UnifiedFonts.font15P);
        interestAddButton.setText("Edit");
        interestAddButton.setLocation((int)interestTXT.getPreferredSize().getWidth()+10, 0);
        interestAddButton.setSize((int) interestAddButton.getPreferredSize().getWidth()+5, (int) interestAddButton.getPreferredSize().getHeight());
        interestAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (read.CountUserInterest(user.getUser_id()) < 4) {
                        Interest interest = new Interest();
                        Has has = new Has();
                        String inputInterest = JOptionPane.showInputDialog(
                                null,
                                "Input Your Interest:"
                        );
                        interest.setInterest_name(inputInterest);

                        String inputInterestType = JOptionPane.showInputDialog(
                                null,
                                "Input Your Interest's Type:"
                        );
                        interest.setType(inputInterestType);

                        String iid = "";
                        try {
                            iid = "I" + String.format("%06d", read.CountInterest());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        interest.setInterest_id(iid);

                        has.setInterest_id(iid);
                        has.setUser_id(user.getUser_id());

                        try {
                            insert.InsertInterest(interest);
                            insert.InsertHas(has);
                            belongTo.UpdateState(State.UserInfoState);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(
                                null,
                                "Normal people usually have few interests in their life.\n You are also one of them.\n Go delete one of them and come back. :)\n P.S. You can easily delete by clicking your interest.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        interests.add(interestAddButton);

        genderBirthdayAge.setLocation(0, (int)information.getPreferredSize().getHeight()/2);
        genderBirthdayAge.setSize(Constants.WIDTH - Constants.SIDE_PANEL_WIDTH, (int)sexTXT.getPreferredSize().getHeight());
        information.add(genderBirthdayAge);

        /**
         * Add all the interests onto the interest panel
         */

        // READ FIRST
        ArrayList<Interest> allInterest = new ArrayList<>();
        try {
            allInterest = read.ReadAllInterest(user);
        } catch (SQLException e){
            e.printStackTrace();
        }
        for(int i = 0; i < allInterest.size(); i++){
            String interest_id = allInterest.get(i).getInterest_id();
            String interest_name = allInterest.get(i).getInterest_name();
            singleInterest = new JButton();
            Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
            singleInterest.setBorder(border);
            singleInterest.setFont(UnifiedFonts.font20P);
            singleInterest.setText(allInterest.get(i).getInterest_name());
            singleInterest.setToolTipText(allInterest.get(i).getType());
            singleInterest.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you're not interested in " + interest_name + " anymore?",
                            "Delete Warning",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == JOptionPane.YES_OPTION){
                        try {
                            Delete delete = new Delete(belongTo.getConnection());
                            delete.DeleteHas(interest_id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        belongTo.UpdateState(State.UserInfoState);
                    }

                }
            });

            interestsGrid.add(singleInterest);
        }
        if (allInterest.size()!= 0) {
            interestsGrid.setLocation(0, (int) interestTXT.getPreferredSize().getHeight() + 20);
            interestsGrid.setSize(Constants.USER_INFO_WIDTH - Constants.SCROLL_CONTROLLER_WIDTH, (int) singleInterest.getPreferredSize().getHeight()+10);
            interests.add(interestsGrid);
        }

        /**
         * Add everything onto the scrollSection
         */
        wholePanel.add(profile);
        wholePanel.add(information);
        wholePanel.add(interests);

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
