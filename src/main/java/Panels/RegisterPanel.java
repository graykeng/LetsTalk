package Panels;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.*;
import Constants.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import Helper.*;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.User;
import org.apache.commons.io.FileUtils;

public class RegisterPanel extends JPanel {

    private JPanel p1 =new JPanel();
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
    private User user;
    private MainPanel beLongTo;
    private CopeImageUtil copeImageUtil = new CopeImageUtil();

    JLabel RegisterNewUser = new JLabel("Register New User");
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

    public RegisterPanel(MainPanel mainPanel){
        beLongTo = mainPanel;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(new BorderLayout());
        panel29 = new JPanel(new GridLayout(8,1));

        try {
            blob = new SerialBlob(convertFileContentToBlob("src/main/resources/Image/headshot.png"));
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
                try {
                    uid = "U" + String.format("%06d", read.CountUser());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                user = new User(uid, Nametext.getText(), blob, BirthdayText.getText(), GenderText.getText(), PasswordText.getText());
                try {
                    insert.InsertUser(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.showMessageDialog(panel29, "Your User ID is: " + user.getUser_id(), "Welcome", JOptionPane.INFORMATION_MESSAGE);

                beLongTo.UpdateState(State.LoginState);
            }
        });


        ChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHeadShot();
            }
        });

        RegisterNewUser.setFont(UnifiedFonts.font30B);
        p1.add(RegisterNewUser);
        p2.add(InputYourInformation);
        p3.add(Name);
        p3.add(Nametext);
        //p4.add(Age);
        //p4.add(Agetext);
        p5.add(HeadShot);
        p5.add(ChooseFile);
        p6.add(Birthday);
        p6.add(BirthdayText);
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

        this.add(p1,BorderLayout.NORTH);
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

    public void createHeadShot(){
        String filePath;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
        fileChooser.setDialogTitle("Open File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if(JFileChooser.APPROVE_OPTION == result){
            filePath = fileChooser.getSelectedFile().getPath();
            copeImageUtil.cutHeadImages(filePath, "src/main/resources/Image/newHeadShot.png");
            try {
                blob = new SerialBlob(convertFileContentToBlob("src/main/resources/Image/newHeadShot.png"));
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
