package Thread;

import Constants.State;
import JDBC.Read;
import Panels.ChatPanel;
import Panels.MainPanel;
import TableStruture.Message;
import TableStruture.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class MessageReceiver extends Thread{
    private ArrayList<Message> allMessage;
    private MainPanel beLongTo;
    private Read read;
    private ChatPanel chatPanel;

    public MessageReceiver(MainPanel beLongTo, ChatPanel chatPanel, ArrayList<Message> allMessage) {
        this.allMessage = allMessage;
        this.chatPanel = chatPanel;
        this.beLongTo = beLongTo;
        read = new Read(beLongTo.getConnection());
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread start");


            chatPanel.UpdateMessage();
//                allMessage = read.ReadMsg_betweenUIDs(beLongTo.getUser().getUser_id(),beLongTo.getCurrUser().getUser_id());
        }
    }
}
