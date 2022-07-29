package TableStruture;

import java.sql.Blob;

public class Message {
    private String message_id;
    private String sender;
    private String receiver;
    private Boolean read_or_unread;
    private Blob content;

    public Message(){}

    public Message(String message_id, String sender, String receiver, Boolean read_or_unread, Blob content) {
        this.message_id = message_id;
        this.sender = sender;
        this.receiver = receiver;
        this.read_or_unread = read_or_unread;
        this.content = content;
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

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }
}
