package TableStruture;

public class UseMessageManager {
    private String manager_id;
    private String user_id;
    private int number_of_message;

    public UseMessageManager(String manager_id, String user_id, int number_of_message) {
        this.manager_id = manager_id;
        this.user_id = user_id;
        this.number_of_message = number_of_message;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getNumber_of_message() {
        return number_of_message;
    }

    public void setNumber_of_message(int number_of_message) {
        this.number_of_message = number_of_message;
    }
}
