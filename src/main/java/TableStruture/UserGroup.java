package TableStruture;

public class UserGroup {
    private String group_id;
    private String name;
    private String group_manager;
    private int number_of_people;

    public UserGroup(){}

    public UserGroup(String group_id, String name, String group_manager, int number_of_people) {
        this.group_id = group_id;
        this.name = name;
        this.group_manager = group_manager;
        this.number_of_people = number_of_people;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup_manager() {
        return group_manager;
    }

    public void setGroup_manager(String group_manager) {
        this.group_manager = group_manager;
    }

    public int getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(int number_of_people) {
        this.number_of_people = number_of_people;
    }
}
