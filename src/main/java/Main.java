import Windows.MainWindow;

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
        window.setPreferredSize(new Dimension(600, 600));
        window.setResizable(true);
        window.setTitle("LetsTalk");

        MainWindow mainWindow = new MainWindow();
        window.add(mainWindow);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/letstalk", "root", "Lixingjian1026");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from emoji");
        while (resultSet.next()){
            System.out.println(resultSet.getString("emoji_number"));
        }
        //mainWindow.startThread();
    }
}
