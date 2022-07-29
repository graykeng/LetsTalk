package TableStruture;

public class Interest {
    private String interest_id;
    private String interest_name;
    private String type;

    public Interest(){}

    public Interest(String interest_id, String interest_name, String type) {
        this.interest_id = interest_id;
        this.interest_name = interest_name;
        this.type = type;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterest_id() { return interest_id; }

    public void setInterest_id(String interest_id) { this.interest_id = interest_id; }
}
