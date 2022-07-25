package TableStruture;

public class UserInGroup {
    private String group_id;
    private String user_id;

    public UserInGroup(){}

    public UserInGroup(String group_id, String user_id) {
        this.group_id = group_id;
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
