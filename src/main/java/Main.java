import Constants.*;
import JDBC.Insert;
import JDBC.JDBConnection;
import Panels.LogInPanel;
import Panels.MainPanel;
import Panels.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
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
    }
}
