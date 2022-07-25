package TableStruture;

public class CommunicationEventTake {
    private String user_id;
    private String event_number;

    public CommunicationEventTake(String user_id, String event_number) {
        this.user_id = user_id;
        this.event_number = event_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }
}
