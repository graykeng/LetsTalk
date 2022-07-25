import Constants.*;
import Panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        /**
         * Setting Frame
         */
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        window.setResizable(false);
        window.setTitle("LetsTalk");

        MainPanel mainPanel = new MainPanel();
        window.add(mainPanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        Connection con = DriverManager.getConnection("jdbc:mysql://letstalk354.mysql.database.azure.com/letstalk", "letstalk", "Gray&Kerla&Lester");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_birthday_and_age");
        while (resultSet.next()){
            System.out.println(resultSet.getString("age"));
        }

//        ResultSet resultSet2 = statement.executeQuery("select * from user");
//        while (resultSet2.next()){
//            System.out.println(resultSet.getString("name")+resultSet.getString("user_id"));
//        }
        //mainWindow.startThread();
    }
}
