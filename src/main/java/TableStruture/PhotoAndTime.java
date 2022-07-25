package TableStruture;

public class PhotoAndTime {
    private String photo_id;
    private String time;

    public PhotoAndTime(){}

    public PhotoAndTime(String photo_id, String time) {
        this.photo_id = photo_id;
        this.time = time;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
