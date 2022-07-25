package TableStruture;

public class PhotoPost {
    private String user_id;
    private String photo_id;
    private String text;

    public PhotoPost(){}

    public PhotoPost(String user_id, String photo_id, String text) {
        this.user_id = user_id;
        this.photo_id = photo_id;
        this.text = text;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
