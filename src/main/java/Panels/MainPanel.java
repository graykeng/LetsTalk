package Panels;

import Constants.State;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

    private State state = State.ChatState;
    private JPanel LoginStatePanel;
    private JPanel RegisterStatePanel;
    private JPanel ChatStatePanel;
    private JPanel AddFriendStatePanel;
    private JPanel AddGroupStatePanel;
    private JPanel UserInfoStatePanel;

    public MainPanel(){
        /**
         * Initialize all the StatePanel
         */
        LoginStatePanel = new JPanel();
        RegisterStatePanel = new JPanel();
        ChatStatePanel = new JPanel();
        AddFriendStatePanel = new JPanel();
        AddGroupStatePanel = new JPanel();
        UserInfoStatePanel = new JPanel();

        /**
         * Initialize all actual Panels
         */
        AddFriendPanel addFriendPanel = new AddFriendPanel();
        ChatPanel chatPanel = new ChatPanel();
        ChatSelectPanel chatSelectPanel = new ChatSelectPanel();
        LogInPanel logInPanel = new LogInPanel();
        PhotoPanel photoPanel = new PhotoPanel();
        PopUpPanel popUpPanel = new PopUpPanel();
        RegisterPanel registerPanel = new RegisterPanel();
        SidePanel sidePanel = new SidePanel();
        UserInfoPanel userInfoPanel = new UserInfoPanel();

        /**
         * Add ACTUAL panels to the panel that we add to the frame
         */
        LoginStatePanel.add(logInPanel);

        ChatStatePanel.setLayout(null);
        ChatStatePanel.add(sidePanel);
        ChatStatePanel.add(chatSelectPanel);
        ChatStatePanel.add(chatPanel);

        UserInfoStatePanel.setLayout(new BorderLayout());
        UserInfoStatePanel.add(sidePanel, BorderLayout.WEST);
        UserInfoStatePanel.add(userInfoPanel, BorderLayout.EAST);

        UpdateState(state);
    }

    public void UpdateState(State state){
        switch (state){
            case LoginState:
                System.out.println("LoginState");
                this.add(LoginStatePanel);
                break;
            case RegisterState:
                System.out.println("ResisterState");
                break;
            case ChatState:
                System.out.println("ChatState");
                this.add(ChatStatePanel);
                break;
            case AddFriendState:
                System.out.println("AddFriendState");
                break;
            case AddGroupState:
                System.out.println("AddGroupState");
                break;
            case UserInfoState:
                System.out.println("UserInfoState");
                break;
        }
    }
}
