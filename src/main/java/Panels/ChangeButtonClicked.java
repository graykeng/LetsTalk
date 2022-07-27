package Panels;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.*;
import Constants.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import Helper.CopeImageUtil;
import JDBC.Insert;
import JDBC.Read;
import JDBC.Update;
import TableStruture.User;
import org.apache.commons.io.FileUtils;

public class ChangeButtonClicked extends JPanel {

    private CopeImageUtil copeImageUtil = new CopeImageUtil();
    private JPanel p2 =new JPanel();
    private JPanel p3 =new JPanel();
    // private JPanel p4 =new JPanel();
    private JPanel p5 =new JPanel();
    private JPanel p6 =new JPanel();
    private JPanel p7 =new JPanel();
    private JPanel p8 =new JPanel();
    private JPanel p9 =new JPanel();
    private JPanel panel29;

    private Blob blob;
    private User user1;
    private MainPanel beLongTo;

    JLabel InputYourInformation = new JLabel("Input Your Information: ");
    JLabel Name = new JLabel("Name:");
    JTextField Nametext= new JTextField(10);
    JLabel Age = new JLabel("Age:");
    JTextField Agetext= new JTextField(10);
    JLabel HeadShot = new JLabel("HeadShot:");
    JButton ChooseFile = new JButton("Choose File");
    JLabel Birthday = new JLabel("Birthday: (DD/MM/YYYY)");
    JTextField BirthdayText= new JTextField(10);
    JLabel Gender = new JLabel("Gender:");
    JTextField GenderText= new JTextField(10);
    JLabel Password = new JLabel("Password:");
    JTextField PasswordText= new JTextField(10);
    JButton Submit= new JButton("Submit");

    public ChangeButtonClicked(MainPanel mainPanel){
        beLongTo = mainPanel;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(new BorderLayout());
        panel29 = new JPanel(new GridLayout(8,1));

        try {
            blob = new SerialBlob(convertFileContentToBlob("src/main/java/Image/headshot.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SerialException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uid = "";
                Read read = new Read(beLongTo.getConnection());
                Insert insert = new Insert(beLongTo.getConnection());
                Update update= new Update(beLongTo.getConnection());
                uid = user1.getUser_id();

                user1 = new User(uid, Nametext.getText(), blob, user1.getBirthday(), GenderText.getText(), PasswordText.getText());
                try {
                    update.UpdateUser(user1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                beLongTo.UpdateState(State.LoginState);
            }
        });


        ChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Choose File: ");
                createHeadShot();
            }
        });

        p2.add(InputYourInformation);
        p3.add(Name);
        p3.add(Nametext);
        //p4.add(Age);
        //p4.add(Agetext);
        p5.add(HeadShot);
        p5.add(ChooseFile);

        p7.add(Gender);
        p7.add(GenderText);
        p8.add(Password);
        p8.add(PasswordText);
        p9.add(Submit);

        panel29.add(p2);
        panel29.add(p3);
        //panel29.add(p4);
        panel29.add(p5);
        panel29.add(p6);
        panel29.add(p7);
        panel29.add(p8);
        panel29.add(p9);

        this.add(panel29,BorderLayout.CENTER);


    }


    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " +
                    e.getMessage());
        }
        return fileContent;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void createHeadShot(){
        String filePath;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
        fileChooser.setDialogTitle("Open File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if(JFileChooser.APPROVE_OPTION == result){
            filePath = fileChooser.getSelectedFile().getPath();
            copeImageUtil.cutHeadImages(filePath, "src/main/java/Image/newHeadShot.png");
            try {
                blob = new SerialBlob(convertFileContentToBlob("src/main/java/Image/newHeadShot.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SerialException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
