package TableStruture;

public class Voice {
    private String message_id;
    private int length;

    public Voice(){}

    public Voice(String message_id, int length) {
        this.message_id = message_id;
        this.length = length;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
