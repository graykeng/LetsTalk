package Panels;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JPanel Title;
    private JPanel msgSendPanel;
    private JScrollPane scrollPanel;

    public ChatPanel() {
        // Setting
        this.setSize(250,600);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Title panel
        Title = new JPanel();
        Title.setSize(250,100);
        JLabel tileLabel = new JLabel("FriendName1");
        Title.add(tileLabel);

        // scrollPanel
        String strMsg1 = "A: Hi, nice to meet you!\n";
        String strMsg2 = "B: Nice to meet you too!\n";
        String strMsg3 = "A: The cover page was complete and was included in the original assignment submission without the TA having to request it. The cover page was complete and was included in the original assignment submission without the TA having to request it.The cover page was complete and was included in the original assignment submission without the TA having to request it";

        JTextArea jta = new JTextArea();
        jta.setLineWrap(true);
        jta.setEditable(false);
        jta.setBackground(new Color(238,238,238));
        jta.setText(strMsg1+strMsg2+strMsg3);

        jta.setFont(new Font(null, Font.PLAIN, 15));
        scrollPanel = new JScrollPane(
                jta,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPanel.setSize(250,400);

        // msgPanel
        msgSendPanel = new JPanel();
        msgSendPanel.setSize(250,100);
        msgSendPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        JButton addFileButton = new JButton("Send File");
        msgSendPanel.add(textArea,BorderLayout.CENTER);
        msgSendPanel.add(addFileButton, BorderLayout.EAST);

        this.add(Title, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
        this.add(msgSendPanel, BorderLayout.SOUTH);
    }
}
