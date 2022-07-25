package TableStruture;

public class UserBirthdayAndAge {
    private String birthday;
    private int age;

    public UserBirthdayAndAge(){}

    public UserBirthdayAndAge(String birthday, int age) {
        this.birthday = birthday;
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
