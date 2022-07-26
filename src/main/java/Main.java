import Constants.*;
import JDBC.Insert;
import JDBC.JDBConnection;
import Panels.MainPanel;
import TableStruture.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

        System.out.println("pop");
    }
}
