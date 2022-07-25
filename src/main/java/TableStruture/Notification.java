package TableStruture;

import java.sql.Blob;

public class Notification {
    private int nid;
    private String status;
    private Blob sound;
    private Boolean visibility;

    public Notification(int nid, String status, Blob sound, Boolean visibility) {
        this.nid = nid;
        this.status = status;
        this.sound = sound;
        this.visibility = visibility;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Blob getSound() {
        return sound;
    }

    public void setSound(Blob sound) {
        this.sound = sound;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
