package TableStruture;

public class Text {
    private String message_id;
    private int word_count;

    public Text(String message_id, int word_count) {
        this.message_id = message_id;
        this.word_count = word_count;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public int getWord_count() {
        return word_count;
    }

    public void setWord_count(int word_count) {
        this.word_count = word_count;
    }
}
