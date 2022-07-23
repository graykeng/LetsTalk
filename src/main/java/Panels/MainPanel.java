package Panels;

import Constants.State;

import javax.swing.*;

public class MainPanel extends JPanel{

    private State state;
    private JPanel LoginStatePanel;
    private JPanel RegisterStatePanel;
    private JPanel ChatStatePanel;
    private JPanel AddFriendStatePanel;
    private JPanel AddGroupStatePanel;
    private JPanel UserInfoStatePanel;

    public MainPanel(){
        state = State.LoginState;

    }

    public void UpdateState(){

    }
}
