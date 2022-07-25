package TableStruture;

import java.sql.Blob;

public class User {
    private String user_id;
    private String name;
    private Blob headshot;
    private String birthday;
    private String gender;
    private String password;

    public User(){}

    public User(String user_id, String name, Blob headshot, String birthday, String gender, String password) {
        this.user_id = user_id;
        this.name = name;
        this.headshot = headshot;
        this.birthday = birthday;
        this.gender = gender;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getHeadshot() {
        return headshot;
    }

    public void setHeadshot(Blob headshot) {
        this.headshot = headshot;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
