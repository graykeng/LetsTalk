import Constants.Constants;
import Panels.MainPanel;
import Panels.PopUpPanel;

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
        window.setLayout(new BorderLayout());
        window.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        window.setResizable(false);
        window.setTitle("LetsTalk");

        MainPanel mainPanel = new MainPanel();
        window.add(mainPanel);

        PopUpPanel popUpPanel = new PopUpPanel();
        window.add(popUpPanel);

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
