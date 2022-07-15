import Windows.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(true);
        window.setTitle("LetsTalk");

        MainWindow mainWindow = new MainWindow();
        window.add(mainWindow);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        mainWindow.startThread();
    }
}
