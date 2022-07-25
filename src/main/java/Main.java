import Constants.*;
import JDBC.Insert;
import JDBC.JDBConnection;
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

//        JDBConnection JDBConnection = new JDBConnection();
//        Insert insert = new Insert(JDBConnection.returnCon());
//        insert.InsertUserGroup("G000001", "CMPT354 group1", "U000001", 2);
//        Statement statement = JDBConnection.returnCon().createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from usergroup");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("group_id"));
//        }

//        Connection con = DriverManager.getConnection("jdbc:mysql://letstalk354.mysql.database.azure.com/letstalk", "letstalk", "Gray&Kerla&Lester");
//        Statement statement = con.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from emoji");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("emoji_number"));
//        }
        //mainWindow.startThread();
        System.out.println("pop");
    }
}
