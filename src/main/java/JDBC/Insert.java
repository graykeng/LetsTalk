package JDBC;

import java.sql.*;

public class Insert {
    private Connection con;
    public Insert(Connection connection){
        this.con = connection;
    }
    public void InsertUserGroup(String group_id, String Name, String Group_manager, int Number_of_people) throws SQLException{
        System.out.println("Insert Group" + group_id);
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO usergroup (group_id, Name, Group_manager, Number_of_people) VALUES (?, ?, ?, ?);");

        insertStatement.setString(1, group_id);
        insertStatement.setString(2, Name);
        insertStatement.setString(3, Group_manager);
        insertStatement.setInt(4, Number_of_people);
        insertStatement.executeUpdate();
    }

    public void InsertUserInGroup(String group_id, String user_id) throws SQLException{
        System.out.println("Insert User " + user_id + " in Group " + group_id);
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user_in_group (group_id, user_id) VALUES (?, ?);");

        insertStatement.setString(1, group_id);
        insertStatement.setString(2, user_id);
        insertStatement.executeUpdate();
    }

    public void InsertUserBirthdayAndAge(String birthday, int age) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user_birthday_and_age (birthday, age) VALUES (?, ?);");

        insertStatement.setString(1, birthday);
        insertStatement.setInt(2, age);
        insertStatement.executeUpdate();
    }

    public void InsertUser(String user_id, String name, Blob headshot, String birthday, String gender, String password) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user (user_id, name, headshot, birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, name);
        insertStatement.setBlob(3, headshot);
        insertStatement.setString(4, birthday);
        insertStatement.setString(5, gender);
        insertStatement.setString(6, password);
        insertStatement.executeUpdate();
    }

    public void InsertOwn(String user_id, String Emoji_number) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO own (user_id, Emoji_number) VALUES (?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, Emoji_number);
        insertStatement.executeUpdate();
    }

    public void InsertEmoji(String Emoji_number) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO emoji (Emoji_number) VALUES (?);");

        insertStatement.setString(1, Emoji_number);
        insertStatement.executeUpdate();
    }

    public void InsertInterest(String Interest_name, String Type) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO interest (Interest_name, Type) VALUES (?, ?);");

        insertStatement.setString(1, Interest_name);
        insertStatement.setString(2, Type);
        insertStatement.executeUpdate();
    }

    public void InsertHas(String user_id, String Interest_name) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO has (user_id, Interest_name) VALUES (?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, Interest_name);
        insertStatement.executeUpdate();
    }

    public void InsertIsFriendOf(String user_id, String friend_id) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO is_friend_of (user_id, friend_id) VALUES (?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, friend_id);
        insertStatement.executeUpdate();
    }

    public void InsertPhotoAndTime(String photo_id, String time) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO photo_and_time (photo_id, time) VALUES (?, ?);");

        insertStatement.setString(1, photo_id);
        insertStatement.setString(2, time);
        insertStatement.executeUpdate();
    }

    public void InsertPhotoPost(String user_id, String Photo_id, String Text) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO photo_post (user_id, Photo_id, Text) VALUES (?, ?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, Photo_id);
        insertStatement.setString(3, Text);
        insertStatement.executeUpdate();
    }

    public void InsertEDT(String event_number, String Date, String Time) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO edt (event_number, Date, Time) VALUES (?, ?, ?);");

        insertStatement.setString(1, event_number);
        insertStatement.setString(2, Date);
        insertStatement.setString(3, Time);
        insertStatement.executeUpdate();
    }

    public void InsertContain(int nid, String user_id, String event_number) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO contain (nid, user_id, event_number) VALUES (?, ?, ?);");

        insertStatement.setInt(1, nid);
        insertStatement.setString(2, user_id);
        insertStatement.setString(3, event_number);
        insertStatement.executeUpdate();
    }

    public void InsertCommunicationEventTake(String user_id, String event_number) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO communication_event_take (user_id, event_number) VALUES (?, ?);");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, event_number);
        insertStatement.executeUpdate();
    }

    public void InsertNotification(int nid, String Status, Blob Sound, Boolean Visibility) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO notification (nid, Status, Sound, Visibility) VALUES (?, ?, ?, ?);");

        insertStatement.setInt(1, nid);
        insertStatement.setString(2, Status);
        insertStatement.setBlob(3, Sound);
        insertStatement.setBoolean(4, Visibility);
        insertStatement.executeUpdate();
    }

    public void InsertInclude(String message_id, String user_id, String Event_number) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO include (message_id, user_id, Event_number) VALUES (?, ?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setString(2, user_id);
        insertStatement.setString(3, Event_number);
        insertStatement.executeUpdate();
    }

    public void InsertText(String message_id, int Word_count) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO text (message_id, Word_count) VALUES (?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setInt(2, Word_count);
        insertStatement.executeUpdate();
    }

    public void InsertImage(String message_id, String Format) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO image (message_id, Format) VALUES (?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setString(2, Format);
        insertStatement.executeUpdate();
    }

    public void InsertVoice(String message_id, int length) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO voice (message_id, length) VALUES (?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setInt(2, length);
        insertStatement.executeUpdate();
    }

    public void InsertUseMessageManager(String message_id, String user_id, int number_of_message) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO use_message_manager (message_id, user_id, number_of_message) VALUES (?, ?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setString(2, user_id);
        insertStatement.setInt(3, number_of_message);
        insertStatement.executeUpdate();
    }

    public void InsertAdministrate(String message_id, String manager_id) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO administrate (message_id, manager_id) VALUES (?, ?);");

        insertStatement.setString(1, message_id);
        insertStatement.setString(2, manager_id);
        insertStatement.executeUpdate();
    }
}
