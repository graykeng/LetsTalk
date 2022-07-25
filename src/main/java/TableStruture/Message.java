package TableStruture;

public class Message {
    private String message_id;
    private String sender;
    private String receiver;
    private Boolean read_or_unread;
    private int word_count;

    public Message(String message_id, String sender, String receiver, Boolean read_or_unread, int word_count) {
        this.message_id = message_id;
        this.sender = sender;
        this.receiver = receiver;
        this.read_or_unread = read_or_unread;
        this.word_count = word_count;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Boolean getRead_or_unread() {
        return read_or_unread;
    }

    public void setRead_or_unread(Boolean read_or_unread) {
        this.read_or_unread = read_or_unread;
    }

    public int getWord_count() {
        return word_count;
    }

    public void setWord_count(int word_count) {
        this.word_count = word_count;
    }
}
