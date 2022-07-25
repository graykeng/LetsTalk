package TableStruture;

public class Has {
    private String user_id;
    private String interest_name;

    public Has(){}

    public Has(String user_id, String interest_name) {
        this.user_id = user_id;
        this.interest_name = interest_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }
}
