package TableStruture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommunicationEventTake {
    private String user_id;
    private String event_number;
    private LocalDateTime dateAndTime;
    private String date;
    private String time;

    public CommunicationEventTake(){}

    public CommunicationEventTake(String user_id, String event_number, LocalDateTime dateAndTime) {
        this.user_id = user_id;
        this.event_number = event_number;
        this.dateAndTime = dateAndTime;

        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        date = dateFormat1.format(dateAndTime);
        time = dateFormat2.format(dateAndTime);

        // LocalDateTime ldt = LocalDateTime.parse("2017-09-28 17:07:05",dateFormat);
        // System.out.println("String Type change to LocalDateTime："+ldt);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
