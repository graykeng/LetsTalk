package Panels;

import javax.swing.*;
import Constants.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterPanel extends JPanel {

    JPanel p1 =new JPanel();
    JPanel p2 =new JPanel();
    JPanel p3 =new JPanel();
    JPanel p4 =new JPanel();
    JPanel p5 =new JPanel();
    JPanel p6 =new JPanel();
    JPanel p7 =new JPanel();
    JPanel p8 =new JPanel();
    JPanel p9 =new JPanel();
    JLabel RegisterNewUser = new JLabel("Register New User");
    JLabel InputYourInformation = new JLabel("Input Your Information: ");
    JLabel Name = new JLabel("Name:");
    JTextField Nametext= new JTextField(10);
    JLabel Age = new JLabel("Age:");
    JTextField Agetext= new JTextField(10);
    JLabel HeadShot = new JLabel("HeadShot:");
    JButton ChooseFile = new JButton("Choose File");
    JLabel Birthday = new JLabel("Birthday:");
    JTextField BirthdayText= new JTextField(10);
    JLabel Gender = new JLabel("Gender:");
    JTextField GenderText= new JTextField(10);
    JLabel Password = new JLabel("Password:");
    JTextField PasswordText= new JTextField(10);
    JButton Submit= new JButton("Submit");

    public RegisterPanel(){
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(new BorderLayout());

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Send: " + Nametext.getText()  );
                System.out.println("Send: " + Agetext.getText()  );
                System.out.println("Send: " + BirthdayText.getText() );
                System.out.println("Send: " + GenderText.getText()  );
                System.out.println("Send: " + PasswordText.getText() );

            }
        });


        ChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Choose File: ");
                String filePath = "";
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
                fileChooser.setDialogTitle("Open File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION == result){
                    filePath = fileChooser.getSelectedFile().getPath();
                }
                System.out.println(filePath);
            }
        });




        RegisterNewUser.setFont(UnifiedFonts.font30B);
        p1.add(RegisterNewUser);
        p2.add(InputYourInformation);
        p3.add(Name);
        p3.add(Nametext);
        p4.add(Age);
        p4.add(Agetext);
        p5.add(HeadShot);
        p5.add(ChooseFile);
        p6.add(Birthday);
        p6.add(BirthdayText);
        p7.add(Gender);
        p7.add(GenderText);
        p8.add(Password);
        p8.add(PasswordText);
        p9.add(Submit);

        JPanel panel29 =new JPanel(new GridLayout(8,1));
        panel29.add(p2);
        panel29.add(p3);
        panel29.add(p4);
        panel29.add(p5);
        panel29.add(p6);
        panel29.add(p7);
        panel29.add(p8);
        panel29.add(p9);

        this.add(p1,BorderLayout.NORTH);
        this.add(panel29,BorderLayout.CENTER);


    }

}
