package TableStruture;

public class Administrate {
    private String message_id;
    private String manager_id;

    public Administrate(){}

    public Administrate(String message_id, String manager_id) {
        this.message_id = message_id;
        this.manager_id = manager_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
