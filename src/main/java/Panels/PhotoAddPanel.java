package Panels;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Constants.Constants;
import Constants.State;
import JDBC.*;


import Helper.*;
import TableStruture.PhotoPost;
import TableStruture.Text;
import org.apache.commons.io.FileUtils;

public class PhotoAddPanel extends JPanel {
    private JButton ChooseFile = new JButton("ChoseFile");
    private JButton Upload = new JButton("Upload");
    private JLabel textLabel = new JLabel("Add Text");
    private JTextField tf = new JTextField(20);
    private CopeImageUtil copeImageUtil= new CopeImageUtil();
    private Blob blob;
    private MainPanel belongTo;
    private Read read;
    private Insert insert;
    private JPanel p1=new JPanel();
    private JPanel p2=new JPanel();
    private JPanel p3=new JPanel();
    private JPanel p4=new JPanel();
    private JPanel p5=new JPanel();
    private JPanel p6=new JPanel();

    public PhotoAddPanel(MainPanel mainPanel) {
        belongTo= mainPanel;

        this.setLayout(new GridLayout(2,3));
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLocation(0,0);
        ChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPhoto_post();
            }
        });

        Upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertPhoto_post();
            }
        });
        p1.add(textLabel);
        p2.add(tf);
        p3.add(ChooseFile);
        p5.add(Upload);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);

    }

    public void InsertPhoto_post(){
        insert = new Insert(belongTo.getConnection());
        read = new Read(belongTo.getConnection());
        String photo_id="";
        try {
            photo_id = "P" + String.format("%06d", read.CountPhoto());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timenow = LocalDateTime.now();
        System.out.println("Insert Now");
        PhotoPost photoPost = new PhotoPost(belongTo.getUser().getUser_id(),photo_id,tf.getText(),timenow,blob);
        try {
            insert.InsertPhotoPost(photoPost);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        belongTo.UpdateState(State.PhotoState);
    }

    public void createPhoto_post() {
        String filePath;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
        fileChooser.setDialogTitle("Open File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            filePath = fileChooser.getSelectedFile().getPath();
            BufferedImage avatarImage = null;
            try {
               blob= new SerialBlob(convertFileContentToBlob(filePath));


            }
            catch(Exception e){
            }
        }
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
}
