package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.CopeImageUtil;
import JDBC.*;

import Constants.*;
import TableStruture.PhotoPost;


public class PhotoPanel extends JPanel {
    private Read read;
    private JScrollPane scroll_panel;
    private JPanel Search_Add_panel= new JPanel();
    private JButton AddButton = new JButton("Add your Photo");
    private JButton searchButton =new JButton("Search in format UID/DD/MM/YYYY");
    private JTextField SearchInsert = new JTextField(20);
    private JLabel textLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    public ArrayList<PhotoPost> photoArray = new ArrayList<>();
    private MainPanel belongTo;

    public PhotoPanel(MainPanel mainPanel){

        belongTo= mainPanel;
        read=new Read(belongTo.getConnection());
        this.setLayout(null);
        this.setLocation(Constants.SIDE_PANEL_WIDTH,0);
        this.setSize(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH,Constants.HEIGHT);

        //上部的搜索框和add
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                belongTo.UpdateState(State.PhotoAddPanelState);
            }
        });
        Search_Add_panel.setSize(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH,70);
        Search_Add_panel.setLocation(0,0);
        Search_Add_panel.add(SearchInsert);
        Search_Add_panel.add(searchButton);
        Search_Add_panel.add(AddButton);
        this.add(Search_Add_panel);
        scroll_panel= new JScrollPane();
        this.add(scroll_panel);
    }





    public void getPhotoFromUser(){
        this.remove(scroll_panel);
        this.repaint();
        try {
            photoArray = read.PhotoPostByUser(belongTo.getUser().getUser_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int row = photoArray.size();

        JPanel photos = new JPanel(null);
        photos.setPreferredSize(new Dimension(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH-20,200*row));
        if (row == 0) {
            String empty = "You have no photo,\uD83D\uDE05 \uD83D\uDE05 \uD83D\uDE05";
            JLabel showEmpty = new JLabel(empty);
            showEmpty.setLocation(0,200);
            showEmpty.setSize(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH,50);
            showEmpty.setFont(UnifiedFonts.font25B);
            photos.add(showEmpty);
        }
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        for(int i=0;i<row;i++){
            JPanel singlePhotoBlock = new JPanel(new BorderLayout());
            singlePhotoBlock.setSize(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH-20,190);
            singlePhotoBlock.setLocation(0,i*200);
            singlePhotoBlock.setBorder(blackLine);
            Blob blob = photoArray.get(i).getContent();
            textLabel = new JLabel(photoArray.get(i).getText());
            dateLabel = new JLabel(photoArray.get(i).getDate_time_str());

            CopeImageUtil cop= new CopeImageUtil();
            BufferedImage buf1=cop.blobToBufferedImage(blob);
            BufferedImage buf2=cop.scaleByPercentage(buf1,200,180);
            JLabel picLabel= new JLabel(new ImageIcon(buf2));
            singlePhotoBlock.add(picLabel,BorderLayout.WEST);
            singlePhotoBlock.add(textLabel,BorderLayout.CENTER);
            singlePhotoBlock.add(dateLabel,BorderLayout.NORTH);

            photos.add(singlePhotoBlock);

        }
        scroll_panel = new JScrollPane(
                photos,
                scroll_panel.VERTICAL_SCROLLBAR_ALWAYS,
                scroll_panel.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll_panel.setSize(Constants.WIDTH-Constants.SIDE_PANEL_WIDTH-20,Constants.HEIGHT-150);
        scroll_panel.setLocation(0,70);

        this.add(scroll_panel);
        this.revalidate();
    }




}
