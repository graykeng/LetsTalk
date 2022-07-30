package TableStruture;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PhotoPost {
    private String user_id;
    private String photo_id;
    private String text;
    private LocalDateTime date_time;
    private String date_time_str;
    private Blob content;

    public PhotoPost(){}

    public PhotoPost(String user_id, String photo_id, String text,LocalDateTime time, Blob content) {
        this.user_id = user_id;
        this.photo_id = photo_id;
        this.text = text;
        this.date_time=time;
        this.content=content;

        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date_time_str = dateFormat1.format(date_time);
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

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public String getDate_time_str() {
        return date_time_str;
    }

    public void setDate_time_str(String date_time_str) {
        this.date_time_str = date_time_str;
    }

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }
}

