import Panels.ChatPanel;
import Panels.ChatSelectPanel;
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
        window.setLayout(new GridLayout(1,2));
        window.setPreferredSize(new Dimension(800, 600));
        window.setResizable(true);
        window.setTitle("LetsTalk");

        ChatSelectPanel selectPanel = new ChatSelectPanel();
        window.add(selectPanel);

        ChatPanel chatPanel = new ChatPanel();
        window.add(chatPanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/letstalk", "root", "b130572645");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from emoji");
        while (resultSet.next()){
            System.out.println(resultSet.getString("emoji_number"));
        }
        //mainWindow.startThread();
    }
}
