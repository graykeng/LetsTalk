package Panels;

import Constants.*;
import JDBC.Insert;
import JDBC.Read;
import TableStruture.User;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatPanel extends JPanel {
    private MainPanel beLongTo;

    private JPanel Title;
    private JPanel msgSendPanel;
    private JScrollPane scrollPanel;
    private JTextArea textFiled = new JTextArea();
    private JTextArea textArea = new JTextArea();
    private JButton sendButton = new JButton("Send");
    private JButton addButton = new JButton();
    private User user;
    private User friend;
    private ArrayList<String> allMsg = new ArrayList<>();

    public ChatPanel(MainPanel mainPanel) {
        beLongTo = mainPanel;
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

        String strAll = mergeToOneString(allMsg);

        textFiled.setLineWrap(true);
        textFiled.setEditable(false);
        textFiled.setBackground(new Color(238,238,238));
        textFiled.setText(strAll);

        textFiled.setFont(UnifiedFonts.font15P);
        scrollPanel = new JScrollPane(
                textFiled,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.CHAT_FIELD_HEIGHT);

        // msgPanel
        msgSendPanel = new JPanel();
        msgSendPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TEXT_FIELD_HEIGHT-38);
        msgSendPanel.setLayout(new BorderLayout());
        textArea.setLineWrap(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Send: " + textArea.getText());
                String msgSend = textArea.getText();
                Blob msgSend_inBlob = null;
                try {
                    // Change msg to Blob
                    msgSend_inBlob = new SerialBlob(msgSend.getBytes("GBK"));

                    // Send blob
                    Insert insert = new Insert(beLongTo.getConnection());
                    insert.sendTextMsg(user.getUser_id(),friend.getUser_id(),msgSend_inBlob);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }

                UpdateMessage();
                textArea.setText("");
            }
        });

        addButton.setIcon(new ImageIcon("src/main/java/Image/AddButton_small.png"));
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

        msgSendPanel.add(textArea,BorderLayout.CENTER);
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

    private String mergeToOneString(ArrayList<String> allMsg) {
        String allStr = "";
        for(int i = 0; i < allMsg.size(); i++){
            String str = allMsg.get(i) + "\n\n";
            allStr = allStr + str;
        }
        return allStr;
    }

    private void showThePopupMenu(Component invoker, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem send_emoji = new JMenuItem("Send Emoji");
        JMenuItem send_file = new JMenuItem("Send File");

        popupMenu.add(send_emoji);
        popupMenu.addSeparator();
        popupMenu.add(send_file);

        send_emoji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendEmoji();
            }
        });
        send_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendFile();
            }
        });

        popupMenu.show(invoker, x, y);
    }

    private void SendEmoji(){
        System.out.println("Send emoji");
        Read read = new Read(beLongTo.getConnection());
        ArrayList<String> emoji_string = null;
        try{
            emoji_string = read.ReadUserOwnEmoji(beLongTo.getUser().getUser_id());
        }catch (SQLException e){
            e.printStackTrace();
        }

        String[] emojis = emoji_string.toArray(new String[emoji_string.size()]);
        // Object[] options = new Object[]{"\uD83D\uDE00", "\uD83D\uDE04", "\uD83D\uDE01","\uD83D\uDE06","\uD83D\uDE05","\uD83D\uDE02","\uD83D\uDE0A","\uD83D\uDE1C","\uD83D\uDE1F","\uD83D\uDE18","\uD83D\uDE35","\uD83D\uDE22","\uD83D\uDE30","\uD83D\uDE28","\uD83D\uDE20","\uD83D\uDE08","\uD83D\uDE37","\uD83D\uDE11","\uD83D\uDE0E"};

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
            textArea.setText(textArea.getText()+emojis[optionSelected]);
        }
    }

    private void SendFile(){
        System.out.println("Send File");
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

    public void UpdateMessage(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        // Get friend name and update title
        this.remove(Title);
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JLabel tileLabel = new JLabel("   " + friend.getName());
        tileLabel.setFont(UnifiedFonts.font20B);
        Title.add(tileLabel, BorderLayout.CENTER);

        Title.setLocation(0,0);
        Title.setBorder(blackline);
        this.add(Title);

        // Get message
        try{
            allMsg = new Read(beLongTo.getConnection()).ReadMsg_betweenUIDs(user.getUser_id(),friend.getUser_id());

        }catch (Exception e){
            e.printStackTrace();
        }

        String strAll = mergeToOneString(allMsg);

        textFiled.setText(strAll);

        this.remove(scrollPanel);
        scrollPanel = new JScrollPane(
                textFiled,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(Constants.CHAT_PANEL_WIDTH,Constants.CHAT_FIELD_HEIGHT);
        scrollPanel.setLocation(0,Constants.TITLE_HEIGHT);
        scrollPanel.setBorder(blackline);
        this.add(scrollPanel);
    }
}
