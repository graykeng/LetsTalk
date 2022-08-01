package Panels;

import Constants.Constants;
import Constants.State;
import JDBC.JDBConnection;
import JDBC.Read;
import TableStruture.User;
import Thread.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPanel extends JPanel{
    private Connection connection;
    private State state;
    private JFrame owner;

    // Actual Panels
    private JPanel UserInfoStatePanel;
    private AddFriendPanel addFriendPanel;
    private AddGroupPanel addGroupPanel;
    private ChatPanel chatPanel;
    private ChatSelectPanel chatSelectPanel;
    private SidePanel sidePanel;
    private LogInPanel logInPanel;
    private PhotoPanel photoPanel;
    private PopUpPanel popUpPanel;
    private RegisterPanel registerPanel;
    private UserInfoPanel userInfoPanel;

    private ChangeButtonClicked ChangePanel;

    private PhotoAddPanel photoAddPanel;

    private User user;
    private ArrayList<User> friends;
    private User currUser;

    private MessageReceiver messageReceiver;
    private Thread messageReceiverThread;

    public MainPanel() throws SQLException {
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLayout(null);
        this.setLocation(0,0);
        initialAllPanels();
        connection = new JDBConnection().returnCon();

        UpdateState(State.LoginState);
    }

    public Connection getConnection(){
        return this.connection;
    }

    private void initialAllPanels(){
        /**
         * Initialize the StatePanel
         */
        UserInfoStatePanel = new JPanel();
        UserInfoStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        UserInfoStatePanel.setLayout(new BorderLayout());
        UserInfoStatePanel.setLocation(0,0);

        /**
         * Initialize all actual Panels
         */
        addFriendPanel = new AddFriendPanel(this);
        addGroupPanel = new AddGroupPanel(this);
        chatPanel = new ChatPanel(this);
        chatSelectPanel = new ChatSelectPanel();
        sidePanel = new SidePanel(this);
        logInPanel = new LogInPanel(this);
        photoPanel = new PhotoPanel(this);
        popUpPanel = new PopUpPanel();
        registerPanel = new RegisterPanel(this);
        userInfoPanel = new UserInfoPanel();
        ChangePanel = new ChangeButtonClicked(this);
        photoAddPanel = new PhotoAddPanel(this);
    }

    public void UpdateState(State changeToState){
        SidePanel sidePanel = new SidePanel(this);
        state = changeToState;
        switch (this.state){
            case LoginState:
                this.removeAll();
                this.repaint();
                UpdateLoginStatePanel();
                this.revalidate();
                break;

            case RegisterState:
                this.removeAll();
                this.repaint();
                UpdateRegisterStatePanel();
                this.revalidate();
                break;

            case ChatState:
                this.removeAll();
                this.repaint();

                try{
                    setFriends(new Read(this.getConnection()).ReadFriendInfo(this.getUser().getUser_id()));
                }catch (Exception e){
                    e.printStackTrace();
                }
                chatSelectPanel = new ChatSelectPanel(this);
                chatPanel = new ChatPanel(this);
                chatPanel.UpdateMessage();

                UpdateChatStatePanel();
                this.revalidate();
                break;

            case AddFriendState:
                this.removeAll();
                this.repaint();
                UpdateAddFriendStatePanel();
                this.revalidate();
                break;

            case AddGroupState:
                this.removeAll();
                this.repaint();
                UpdateAddGroupStatePanel();
                this.revalidate();
                break;

            case UserInfoState:
                this.removeAll();
                this.repaint();
                UserInfoStatePanel.removeAll();
                UserInfoStatePanel.repaint();
                UserInfoPanel userInfoPanel = new UserInfoPanel(currUser,this);
                UserInfoStatePanel.add(sidePanel, BorderLayout.WEST);
                UserInfoStatePanel.add(userInfoPanel, BorderLayout.EAST);
                UserInfoStatePanel.revalidate();
                this.add(UserInfoStatePanel);
                this.revalidate();
                break;

            case PhotoState:
                this.removeAll();
                this.repaint();
                UpdatePhotoStatePanel();
                this.revalidate();
                break;

            case ChangeButtonClickedState:
                this.removeAll();
                this.repaint();
                UpdateChangeButtonClickedPanel();
                this.revalidate();
                break;

            case PhotoAddPanelState:
                this.removeAll();
                this.repaint();
                UpdatePhotoAddPanel();
                this.revalidate();
                break;
        }
    }

    /**
     * Add ACTUAL panels to the LoginStatePanel that we add to the frame
     */
    public void UpdateChangeButtonClickedPanel(){
        ChangePanel.setUser1(user);
        this.add(ChangePanel);
    }

    public void UpdatePhotoAddPanel(){
        this.add(photoAddPanel);
    }

    public void UpdateLoginStatePanel(){
        this.add(logInPanel);
    }

    /**
     * Add ACTUAL panels to the RegisterStatePanel that we add to the frame
     */
    public void UpdateRegisterStatePanel(){
        this.add(registerPanel);
    }

    /**
     * Add ACTUAL panels to the ChatStatePanel that we add to the frame
     */
    public void UpdateChatStatePanel(){
        this.add(sidePanel);
        this.add(chatSelectPanel);
        this.add(chatPanel);
    }

    /**
     * Add ACTUAL panels to the InfoStatePanel that we add to the frame
     */
    public void UpdateUserInfoStatePanel(){
        UserInfoStatePanel.add(sidePanel, BorderLayout.WEST);
        UserInfoStatePanel.add(userInfoPanel, BorderLayout.EAST);
        this.add(UserInfoStatePanel);
    }

    /**
     * Add ACTUAL panels to the PhotoStatePanel that we add to the frame
     */
    public void UpdatePhotoStatePanel(){
        photoPanel.getPhotoFromUser();
        this.add(sidePanel);
        this.add(photoPanel);
    }

    /**
     * Add ACTUAL panels to the AddFriendStatePanel that we add to the frame
     */
    public void UpdateAddFriendStatePanel(){
        this.add(new SidePanel(this));
        this.add(chatSelectPanel);
        this.add(addFriendPanel);
    }

    /**
     * Add ACTUAL panels to the AddFriendStatePanel that we add to the frame
     */
    public void UpdateAddGroupStatePanel(){
        this.add(new SidePanel(this));
        this.add(chatSelectPanel);
        this.add(addGroupPanel);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public void setOwner(JFrame window) {
        owner = window;
    }

    public JFrame getOwner() {
        return owner;
    }

    public State getState(){
        return state;
    }
}
