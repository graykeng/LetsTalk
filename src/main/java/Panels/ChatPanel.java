package Panels;

import Constants.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {
    private JPanel Title;
    private JPanel msgSendPanel;
    private JScrollPane scrollPanel;

    public ChatPanel() {
        // Setting
        this.setSize(Constants.CHAT_PANEL_WIDTH, Constants.HEIGHT);
        this.setLocation(Constants.SIDE_PANEL_WIDTH+Constants.SELECT_PANEL_WIDTH, 0);
        this.setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);


        // Title panel
        Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setSize(Constants.CHAT_PANEL_WIDTH,Constants.TITLE_HEIGHT);
        JLabel tileLabel = new JLabel("FriendName1");
        tileLabel.setFont(UnifiedFonts.font20B);
        Title.add(tileLabel, BorderLayout.CENTER);

        // scrollPanel
        String strMsg1 = "A: Hi, nice to meet you!\n\n";
        String strMsg2 = "B: Nice to meet you too!\n\n";
        String strMsg3 = "A: The cover page was complete and was included in the original assignment submission without the TA having to request it. The cover page was complete and was included in the original assignment submission without the TA having to request it.The cover page was complete and was included in the original assignment submission without the TA having to request it\n\n";

        String strAll = strMsg1+strMsg2+strMsg3+strMsg3+strMsg3;

        JTextArea textFiled = new JTextArea();
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
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Send: " + textArea.getText());
            }
        });

        JButton addButton = new JButton();
        addButton.setIcon(new ImageIcon("src/main/java/Image/AddButton_small.png"));
        addButton.setSize(20,20);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add File: ");
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
}