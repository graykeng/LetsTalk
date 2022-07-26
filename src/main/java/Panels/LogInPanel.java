package Panels;

import javax.swing.*;
import Constants.*;
import JDBC.Read;
import TableStruture.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LogInPanel extends JPanel {
    private JButton loginButton=new JButton("Login");
    private JButton newUser=new JButton("New User");
    private JLabel userID=new JLabel("User ID");
    private JLabel password= new JLabel("Password");
    private JLabel Letstalk=new JLabel("                            Welcome to LetsTalk");
    private JTextField userIDtext = new JTextField(15);
    private JPasswordField passwordtext = new JPasswordField(15);
    private JPanel down=new JPanel();
    private JPanel down1= new JPanel();
    private JPanel down2=new JPanel();
    private MainPanel belongTo;
    private User user;

    public LogInPanel(MainPanel mainPanel){
        belongTo= mainPanel;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(new BorderLayout());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user = new Read(belongTo.getConnection()).UserLogin(userIDtext.getText(), passwordtext.getText());
                    belongTo.setUser(user);
                    belongTo.UpdateState(State.UserInfoState);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane jOptionPane = new JOptionPane();
                    jOptionPane.showMessageDialog(belongTo, "Wrong User ID or Password!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                belongTo.UpdateState(State.RegisterState);
            }
        });



        Letstalk.setFont(UnifiedFonts.font30B);
        this.add(Letstalk,BorderLayout.NORTH);
        down.setLayout(new GridLayout(8,1));


        down1.add(userID);
        down1.add(userIDtext);
        down1.add(newUser);


        down2.add(password);
        down2.add(passwordtext);
        down2.add(loginButton);

        down.add(new JPanel());
        down.add(new JPanel());
        down.add(down1);
        down.add(down2);
        down.add(new JPanel());
        down.add(new JPanel());
        down.add(new JPanel());
        down.add(new JPanel());

        this.add(down,BorderLayout.CENTER);





    }



}
