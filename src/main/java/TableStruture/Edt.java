package TableStruture;

public class Edt {
    private String event_number;
    private String date;
    private String time;

    public Edt(){}

    public Edt(String event_number, String date, String time) {
        this.event_number = event_number;
        this.date = date;
        this.time = time;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
