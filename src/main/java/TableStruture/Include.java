package TableStruture;

public class Include {
    private String message_id;
    private String user_id;
    private String event_number;

    public Include(String message_id, String user_id, String event_number) {
        this.message_id = message_id;
        this.user_id = user_id;
        this.event_number = event_number;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
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
