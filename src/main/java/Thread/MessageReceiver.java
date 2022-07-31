package Thread;

import Constants.State;
import Panels.MainPanel;

public class MessageReceiver implements Runnable{
    private MainPanel beLongTo;

    public MessageReceiver(MainPanel beLongTo) {
        this.beLongTo = beLongTo;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (beLongTo.getState() == State.ChatState){
                beLongTo.UpdateState(State.ChatState);
            }
        }
    }
}
