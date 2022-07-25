package JDBC;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Read {
    private Connection con;
    public Read(Connection connection){
        this.con = connection;
    }

    public void ReadUserGroup(String group_id, String Name, String Group_manager, int Number_of_people) throws SQLException{
        System.out.println("Read Group" + group_id);
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO user_group (group_id, Name, Group_manager, Number_of_people) VALUES (?, ?, ?, ?);");

        ReadStatement.setString(1, group_id);
        ReadStatement.setString(2, Name);
        ReadStatement.setString(3, Group_manager);
        ReadStatement.setInt(4, Number_of_people);
        ReadStatement.executeUpdate();
    }

    public void ReadUserInGroup(String group_id, String user_id) throws SQLException{
        System.out.println("Read User " + user_id + " in Group " + group_id);
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO user_in_group (group_id, user_id) VALUES (?, ?);");

        ReadStatement.setString(1, group_id);
        ReadStatement.setString(2, user_id);
        ReadStatement.executeUpdate();
    }

    public void ReadUserBirthdayAndAge(String birthday, int age) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO user_birthday_and_age (birthday, age) VALUES (?, ?);");

        ReadStatement.setString(1, birthday);
        ReadStatement.setInt(2, age);
        ReadStatement.executeUpdate();
    }

    public void ReadUser(String user_id, String name, String filePath, String birthday, String gender, String password) throws SQLException, IOException {
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO user (user_id, name, headshot, birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, name);
        // ReadStatement.setBinaryStream(3, imageToBi(filePath));
        ReadStatement.setString(4, birthday);
        ReadStatement.setString(5, gender);
        ReadStatement.setString(6, password);
        ReadStatement.executeUpdate();
    }

    public void ReadOwn(String user_id, String Emoji_number) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO own (user_id, Emoji_number) VALUES (?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, Emoji_number);
        ReadStatement.executeUpdate();
    }

    public void ReadEmoji(String Emoji_number) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO emoji (Emoji_number) VALUES (?);");

        ReadStatement.setString(1, Emoji_number);
        ReadStatement.executeUpdate();
    }

    public void ReadInterest(String Interest_name, String Type) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO interest (Interest_name, Type) VALUES (?, ?);");

        ReadStatement.setString(1, Interest_name);
        ReadStatement.setString(2, Type);
        ReadStatement.executeUpdate();
    }

    public void ReadHas(String user_id, String Interest_name) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO has (user_id, Interest_name) VALUES (?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, Interest_name);
        ReadStatement.executeUpdate();
    }

    public void ReadIsFriendOf(String user_id, String friend_id) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO is_friend_of (user_id, friend_id) VALUES (?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, friend_id);
        ReadStatement.executeUpdate();
    }

    public void ReadPhotoAndTime(String photo_id, String time) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO photo_and_time (photo_id, time) VALUES (?, ?);");

        ReadStatement.setString(1, photo_id);
        ReadStatement.setString(2, time);
        ReadStatement.executeUpdate();
    }

    public void ReadPhotoPost(String user_id, String Photo_id, String Text) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO photo_post (user_id, Photo_id, Text) VALUES (?, ?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, Photo_id);
        ReadStatement.setString(3, Text);
        ReadStatement.executeUpdate();
    }

    public void ReadEDT(String event_number, String Date, String Time) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO edt (event_number, Date, Time) VALUES (?, ?, ?);");

        ReadStatement.setString(1, event_number);
        ReadStatement.setString(2, Date);
        ReadStatement.setString(3, Time);
        ReadStatement.executeUpdate();
    }

    public void ReadContain(int nid, String user_id, String event_number) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO contain (nid, user_id, event_number) VALUES (?, ?, ?);");

        ReadStatement.setInt(1, nid);
        ReadStatement.setString(2, user_id);
        ReadStatement.setString(3, event_number);
        ReadStatement.executeUpdate();
    }

    public void ReadCommunicationEventTake(String user_id, String event_number) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO communication_event_take (user_id, event_number) VALUES (?, ?);");

        ReadStatement.setString(1, user_id);
        ReadStatement.setString(2, event_number);
        ReadStatement.executeUpdate();
    }

    public void ReadNotification(int nid, String Status, Blob Sound, Boolean Visibility) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO notification (nid, Status, Sound, Visibility) VALUES (?, ?, ?, ?);");

        ReadStatement.setInt(1, nid);
        ReadStatement.setString(2, Status);
        ReadStatement.setBlob(3, Sound);
        ReadStatement.setBoolean(4, Visibility);
        ReadStatement.executeUpdate();
    }

    public void ReadInclude(String message_id, String user_id, String Event_number) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO include (message_id, user_id, Event_number) VALUES (?, ?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setString(2, user_id);
        ReadStatement.setString(3, Event_number);
        ReadStatement.executeUpdate();
    }

    public void ReadText(String message_id, int Word_count) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO text (message_id, Word_count) VALUES (?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setInt(2, Word_count);
        ReadStatement.executeUpdate();
    }

    public void ReadImage(String message_id, String Format) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO image (message_id, Format) VALUES (?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setString(2, Format);
        ReadStatement.executeUpdate();
    }

    public void ReadVoice(String message_id, int length) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO voice (message_id, length) VALUES (?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setInt(2, length);
        ReadStatement.executeUpdate();
    }

    public void ReadUseMessageManager(String message_id, String user_id, int number_of_message) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO use_message_manager (message_id, user_id, number_of_message) VALUES (?, ?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setString(2, user_id);
        ReadStatement.setInt(3, number_of_message);
        ReadStatement.executeUpdate();
    }

    public void ReadAdministrate(String message_id, String manager_id) throws SQLException{
        PreparedStatement ReadStatement = con.prepareStatement("Read INTO administrate (message_id, manager_id) VALUES (?, ?);");

        ReadStatement.setString(1, message_id);
        ReadStatement.setString(2, manager_id);
        ReadStatement.executeUpdate();
    }

}
