package Panels;

import Constants.Constants;
import Constants.State;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

    private State state;
    private JPanel LoginStatePanel;
    private JPanel RegisterStatePanel;
    private JPanel ChatStatePanel;
    private JPanel AddFriendStatePanel;
    private JPanel AddGroupStatePanel;
    private JPanel UserInfoStatePanel;
    private JPanel PhotoStatePanel;

    public MainPanel(){
        this.setLayout(null);
        initialAllPanels();

        UpdateState(State.LoginState);
    }

    private void initialAllPanels(){
        /**
         * Initialize all the StatePanel
         */
        LoginStatePanel = new JPanel();
        LoginStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        LoginStatePanel.setLayout(null);
        LoginStatePanel.setLocation(0,0);

        RegisterStatePanel = new JPanel();
        RegisterStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        RegisterStatePanel.setLayout(null);
        RegisterStatePanel.setLocation(0,0);

        ChatStatePanel = new JPanel();
        ChatStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        ChatStatePanel.setLayout(null);
        ChatStatePanel.setLocation(0,0);

        AddFriendStatePanel = new JPanel();
        AddFriendStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        AddFriendStatePanel.setLayout(null);
        AddFriendStatePanel.setLocation(0,0);

        AddGroupStatePanel = new JPanel();
        AddGroupStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        AddGroupStatePanel.setLayout(null);
        AddGroupStatePanel.setLocation(0,0);

        UserInfoStatePanel = new JPanel();
        UserInfoStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        UserInfoStatePanel.setLayout(new BorderLayout());
        UserInfoStatePanel.setLocation(0,0);

        PhotoStatePanel = new JPanel();
        PhotoStatePanel.setSize(Constants.WIDTH, Constants.HEIGHT);
        PhotoStatePanel.setLayout(null);
        PhotoStatePanel.setLocation(0,0);

        /**
         * Initialize all actual Panels
         */
        AddFriendPanel addFriendPanel = new AddFriendPanel();
        AddGroupPanel addGroupPanel = new AddGroupPanel();
        ChatPanel chatPanel = new ChatPanel();
        ChatSelectPanel chatSelectPanel = new ChatSelectPanel(this);
        ChatSelectPanel chatSelectPanel2 = new ChatSelectPanel(this);
        ChatSelectPanel chatSelectPanel3 = new ChatSelectPanel(this);
        SidePanel sidePanel = new SidePanel(this);
        SidePanel sidePanel2 = new SidePanel(this);
        SidePanel sidePanel3 = new SidePanel(this);
        LogInPanel logInPanel = new LogInPanel(this);
        PhotoPanel photoPanel = new PhotoPanel();
        PopUpPanel popUpPanel = new PopUpPanel();
        RegisterPanel registerPanel = new RegisterPanel();
        UserInfoPanel userInfoPanel = new UserInfoPanel();

        /**
         * Add ACTUAL panels to the panel that we add to the frame
         */
        // Login
        LoginStatePanel.add(logInPanel);

        // Register
        RegisterStatePanel.add(registerPanel);

        // Chat
        ChatStatePanel.add(sidePanel);
        ChatStatePanel.add(chatSelectPanel);
        ChatStatePanel.add(chatPanel);

        // User Info
        UserInfoStatePanel.add(sidePanel2, BorderLayout.WEST);
        UserInfoStatePanel.add(userInfoPanel, BorderLayout.EAST);

        // Photo
        PhotoStatePanel.add(sidePanel3);
        PhotoStatePanel.add(photoPanel);

        // Add Friend
        AddFriendStatePanel.add(new SidePanel(this));
        AddFriendStatePanel.add(chatSelectPanel2);
        AddFriendStatePanel.add(addFriendPanel);

        // Add Group
        AddGroupStatePanel.add(new SidePanel(this));
        AddGroupStatePanel.add(chatSelectPanel3);
        AddGroupStatePanel.add(addGroupPanel);
    }

    public void UpdateState(State changeToState){
        System.out.println("Update");
        state = changeToState;
        switch (this.state){
            case LoginState:
                System.out.println("LoginState");
                this.removeAll();
                this.repaint();
                this.add(LoginStatePanel);
                this.revalidate();
                break;

            case RegisterState:
                System.out.println("ResisterState");
                this.removeAll();
                this.repaint();
                this.add(RegisterStatePanel);
                this.revalidate();
                break;

            case ChatState:
                System.out.println("ChatState");
                this.removeAll();
                this.repaint();
                this.add(ChatStatePanel);
                this.revalidate();
                break;

            case AddFriendState:
                System.out.println("AddFriendState");
                this.removeAll();
                this.repaint();
                this.add(AddFriendStatePanel);
                this.revalidate();
                break;
            case AddGroupState:
                System.out.println("AddGroupState");
                this.removeAll();
                this.repaint();
                this.add(AddGroupStatePanel);
                this.revalidate();
                break;
            case UserInfoState:
                System.out.println("UserInfoState");
                this.removeAll();
                this.repaint();
                this.add(UserInfoStatePanel);
                this.revalidate();
                break;
            case PhotoState:
                System.out.println("PhotoState");
                this.removeAll();
                this.repaint();
                this.add(PhotoStatePanel);
                this.revalidate();
                break;
        }
    }
}
