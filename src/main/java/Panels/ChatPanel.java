package Panels;

import Constants.*;
import Helper.CopeImageUtil;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.Message;
import TableStruture.User;
import org.apache.commons.io.FileUtils;
import Thread.*;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatPanel extends JPanel{
    private MainPanel beLongTo;
    private Read read;
    private MessageReceiver messageReceiver;

    private JPanel Title;
    private JPanel msgSendPanel;
    private JScrollPane scrollPanel;
    private JPanel messagePanel = new JPanel(null);
    //private JTextArea messageArea = new JTextArea();
    private JTextArea inputTextArea = new JTextArea();
    private JButton sendButton = new JButton("Send");
    private JButton addButton = new JButton();
    private User user;
    private User friend;
    private ArrayList<Message> allMsg = new ArrayList<>();
    private JScrollBar jScrollBar;

    private Border blackLine = BorderFactory.createLineBorder(Color.black);

    public ChatPanel(MainPanel mainPanel) {
        beLongTo = mainPanel;
        read = new Read(beLongTo.getConnection());
        user = beLongTo.getUser();
        friend = beLongTo.getCurrUser();

        // Setting
        this.setSize(Constants.CHAT_PANEL_WIDTH, Constants.HEIGHT);
        this.setLocation(Constants.SIDE_PANEL_WIDTH+Constants.SELECT_PANEL_WIDTH, 0);
        this.setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);


        // Title panel
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JLabel tileLabel = new JLabel("FriendName");
        tileLabel.setFont(UnifiedFonts.font20B);
        Title.add(tileLabel, BorderLayout.CENTER);

        // scrollPanel
        scrollPanel = new JScrollPane(
                messagePanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.CHAT_FIELD_HEIGHT);

        // msgPanel
        msgSendPanel = new JPanel();
        msgSendPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TEXT_FIELD_HEIGHT-38);
        msgSendPanel.setLayout(new BorderLayout());
        inputTextArea.setLineWrap(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgSend = inputTextArea.getText();
                int wordCount = msgSend.length();
                Blob msgSend_inBlob = null;
                try {
                    // Change msg to Blob
                    msgSend_inBlob = new SerialBlob(msgSend.getBytes("GBK"));

                    // Send blob
                    Insert insert = new Insert(beLongTo.getConnection());
                    insert.sendTextMsg(user.getUser_id(),friend.getUser_id(),msgSend_inBlob, wordCount);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }

                beLongTo.UpdateState(State.ChatState);
                inputTextArea.setText("");
            }
        });

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Image/AddButton_small.png")));
            addButton.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addButton.setSize(20,20);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showThePopupMenu(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);
        buttonPanel.add(addButton);

        msgSendPanel.add(inputTextArea,BorderLayout.CENTER);
        msgSendPanel.add(buttonPanel, BorderLayout.EAST);

        Title.setLocation(0,0);
        Title.setBorder(blackline);
        this.add(Title);

        scrollPanel.setLocation(0,Constants.TITLE_HEIGHT);
        scrollPanel.setBorder(blackline);
        this.add(scrollPanel);

        msgSendPanel.setLocation(0,Constants.HEIGHT-Constants.TEXT_FIELD_HEIGHT);
        msgSendPanel.setBorder(blackline);
        this.add(msgSendPanel);
    }

    private int CalculateAllHeight(int[] heights) {
        int height = 0;
        for(int i = 0; i < heights.length; i++){
            height += heights[i];
        }
        return height;
    }

    private void showThePopupMenu(Component invoker, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem send_emoji = new JMenuItem("Send Emoji");
        JMenuItem send_image = new JMenuItem("Send Image");
        JMenuItem send_voice = new JMenuItem("Send Voice");

        popupMenu.add(send_emoji);
        popupMenu.addSeparator();
        popupMenu.add(send_image);
        popupMenu.addSeparator();
        popupMenu.add(send_voice);

        send_emoji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendEmoji();
            }
        });
        send_image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendImage();
            }
        });
        send_voice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendVoice();
            }
        });

        popupMenu.show(invoker, x, y);
    }

    private void SendEmoji(){
        ArrayList<String> emoji_string = null;
        try{
            emoji_string = read.ReadUserOwnEmoji(beLongTo.getUser().getUser_id());
        }catch (SQLException e){
            e.printStackTrace();
        }

        String[] emojis = emoji_string.toArray(new String[emoji_string.size()]);

        int optionSelected = JOptionPane.showOptionDialog(
                this,
                "Choose the emoji that you want to send",
                "Emoji",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                emojis,
                null
        );

        if (optionSelected >= 0) {
            inputTextArea.setText(inputTextArea.getText()+ " " + emojis[optionSelected]);
        }
    }

    private void SendImage(){
        String filePath = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
        fileChooser.setDialogTitle("Open image File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        Blob blob = null;
        int result = fileChooser.showOpenDialog(null);
        if(JFileChooser.APPROVE_OPTION == result){
            CopeImageUtil copeImageUtil = new CopeImageUtil();
            filePath = fileChooser.getSelectedFile().getPath();

            BufferedImage imageSend = null;
            try {
                // read image
                imageSend = ImageIO.read(new File(filePath));
                // cut image
                int newWidth = imageSend.getWidth()*Constants.IMAGE_HEIGHT/imageSend.getHeight();
                imageSend = copeImageUtil.scaleByPercentage(imageSend, newWidth, Constants.IMAGE_HEIGHT);
                // write image to outputPath
                String outputPath = filePath.substring(0,filePath.length()-5) + ".png";

                OutputStream os = new FileOutputStream(outputPath);
                ImageIO.write(imageSend, "PNG", os);

                // Convert it to blob
                blob = new SerialBlob(convertFileContentToBlob(outputPath));

            }catch (Exception e){
                e.printStackTrace();
            }

            // Send blob
            try{
                Insert insert = new Insert(beLongTo.getConnection());
                insert.sendImageMsg(user.getUser_id(),friend.getUser_id(),blob, "PNG");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        beLongTo.UpdateState(State.ChatState);
    }

    private static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " +
                    e.getMessage());
        }
        return fileContent;
    }

    private void SendVoice(){
        String filePath = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
        fileChooser.setDialogTitle("Open audio File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if(JFileChooser.APPROVE_OPTION == result){
            filePath = fileChooser.getSelectedFile().getPath();
        }
    }

    private void UpdateMessagePanel(){
        if(messageReceiver == null) {
            messageReceiver = new MessageReceiver(beLongTo, this, allMsg);
            messageReceiver.start();
        }

        messagePanel = new JPanel(null);
        int rows = allMsg.size();
        int[] heights = getHeightForEachMsg();
        int heightForAllMsg = CalculateAllHeight(heights);
        messagePanel.setPreferredSize(new Dimension(Constants.CHAT_PANEL_WIDTH, heightForAllMsg+20));
        int current_height = 0;
        for (int i = 0; i < rows; i++) {
            JPanel row = new JPanel();

            Message message = allMsg.get(i);
            String SenderNameAndTime = message.getSenderName() + " (" + message.getDate_and_time() +")" +": <br>";
            if(message.getMessage_id().substring(7,8).equals("T")){
                // it is a Text
                String content = "";
                try{
                    content = new String(message.getContent().getBytes(1, (int) message.getContent().length()),"GBK");
                }catch (Exception e){
                    e.printStackTrace();
                }
                String allTogether = "<html>" + SenderNameAndTime+ content +"</html>";
                JLabel rowLabel = new JLabel(allTogether);
                rowLabel.setPreferredSize(new Dimension(Constants.CHAT_PANEL_WIDTH-20, heights[i]));
                row.add(rowLabel);
            }
            else{
                // it is an Image
                JLabel rowLabel_str = new JLabel("<html>"+SenderNameAndTime+"</html>");
                rowLabel_str.setPreferredSize(new Dimension(Constants.CHAT_PANEL_WIDTH-20, 30));
                row.add(rowLabel_str);


                ImageIcon imageIcon = new CopeImageUtil().blobToIcon(message.getContent());
                //rowLabel_image.setPreferredSize(new Dimension(Constants.CHAT_PANEL_WIDTH-20, Constants.IMAGE_HEIGHT));
                JLabel rowLabel_image = new JLabel(imageIcon);
                row.add(rowLabel_image);
            }
            row.setSize(Constants.CHAT_PANEL_WIDTH-10, heights[i]);
            row.setLocation(0, current_height);
            //row.setBorder(blackLine);
            messagePanel.add(row);

            current_height += heights[i];
        }
    }

    public void UpdateMessage(){
        // Get new friend name and update title
        this.remove(Title);
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JLabel tileLabel = new JLabel("   " + friend.getName());
        tileLabel.setFont(UnifiedFonts.font20B);
        Title.add(tileLabel, BorderLayout.CENTER);

        Title.setLocation(0,0);
        Title.setBorder(blackLine);
        this.add(Title);

        // Get new message list in Message
        try{
            allMsg = read.ReadMsg_betweenUIDs(user.getUser_id(),friend.getUser_id());
        }catch (Exception e){
            e.printStackTrace();
        }
        this.remove(scrollPanel);
        UpdateMessagePanel();
        scrollPanel = new JScrollPane(
                messagePanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.doLayout();

        jScrollBar = scrollPanel.getVerticalScrollBar();

        if (jScrollBar != null) {
            jScrollBar.setValue(jScrollBar.getMaximum());
        }

        scrollPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.CHAT_FIELD_HEIGHT);
        scrollPanel.setLocation(0,Constants.TITLE_HEIGHT);
        scrollPanel.setBorder(blackLine);
        this.add(scrollPanel);
    }

    private int getHeightOfMsgLabel(int maxWidth, String content){
        JLabel jLabel = new JLabel("<html>" + content + "</html>");

        javax.swing.text.View v = javax.swing.plaf.basic.BasicHTML.createHTMLView(jLabel, jLabel.getText());

        v.setSize(maxWidth, Integer.MAX_VALUE);
        int h = (int) v.getMinimumSpan(View.Y_AXIS);
        return h;
    }

    private int[] getHeightForEachMsg(){
        int[] heights = new int[allMsg.size()];
        for(int i = 0; i < allMsg.size(); i++){
            int height = 0;
            Message message = allMsg.get(i);
            String SenderNameAndTime = message.getSenderName() + " (send at " + message.getDate_and_time() +")" +":";
            if(message.getMessage_id().substring(7,8).equals("T")){
                // it is a Text
                String content = "";
                try{
                    content = new String(message.getContent().getBytes(1, (int) message.getContent().length()),"GBK");
                }catch (Exception e){
                    e.printStackTrace();
                }
                String allTogether = "<html>" + SenderNameAndTime+ "<br>" + content + "</html>";
                height = getHeightOfMsgLabel(Constants.CHAT_PANEL_WIDTH, allTogether);
            }
            else{
                // it is an Image
                height = getHeightOfMsgLabel(Constants.CHAT_PANEL_WIDTH, SenderNameAndTime) + Constants.IMAGE_HEIGHT;
            }
            heights[i] = height+30;
        }
        return heights;
    }
}