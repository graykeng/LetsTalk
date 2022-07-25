package TableStruture;

public class Image {
    private String message_id;
    private String format;

    public Image(){}

    public Image(String message_id, String format) {
        this.message_id = message_id;
        this.format = format;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
