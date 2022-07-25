package TableStruture;

public class Own {
    private String user_id;
    private String emoji_number;

    public Own(String user_id, String emoji_number) {
        this.user_id = user_id;
        this.emoji_number = emoji_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmoji_number() {
        return emoji_number;
    }

    public void setEmoji_number(String emoji_number) {
        this.emoji_number = emoji_number;
    }
}
