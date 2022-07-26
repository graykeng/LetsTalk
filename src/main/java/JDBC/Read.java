package JDBC;

import TableStruture.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Read {
    private Connection con;
    public Read(Connection connection){
        this.con = connection;
    }

    public int CountUser() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM user");
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }
        return i;
    }

    public int CountGroup() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM user_group");
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }
        return i;
    }

    public User UserLogin(String UID, String PWD) throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user WHERE user_id = ? AND password = ?;");

        User user = null;
        statement.setString(1, UID);
        statement.setString(2, PWD);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getBlob("headshot"),
                    resultSet.getString("birthday"),
                    resultSet.getString("gender"),
                    resultSet.getString("password")
            );
        }
        else{
            throw new SQLException();
        }

        int age = ReadAge(user.getBirthday());
        user.setAge(age);
        return user;
    }

    public User ReadUser(String UID) throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user WHERE user_id = ?;");

        User user = null;
        statement.setString(1, UID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getBlob("headshot"),
                    resultSet.getString("birthday"),
                    resultSet.getString("gender"),
                    resultSet.getString("password")
            );
        }
        else{
            throw new SQLException();
        }

        int age = ReadAge(user.getBirthday());
        user.setAge(age);
        return user;
    }


    public ArrayList<User> ReadFriendInfo (String uid)throws SQLException{
        ArrayList<User> friends = new ArrayList<>();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM is_friend_of WHERE user_id = ?;");

        statement.setString(1, uid);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            String friend_id = resultSet.getString("friend_id");
            User friend = ReadUser(friend_id);
            friends.add(friend);
        }

        return friends;
    }

    public int ReadAge(String birthday) throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_birthday_and_age WHERE birthday = ?;");

        statement.setString(1, birthday);

        ResultSet resultSet = statement.executeQuery();
        int age = -1;
        if (resultSet.next()){
            age = resultSet.getInt("age");
        }
        return age;
    }

    public UserGroup ReadUserGroup(String gid) throws SQLException{
        UserGroup userGroup = null;
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM user_group WHERE group_id = ?;");
        readStatement.setString(1, gid);

        ResultSet resultSet = readStatement.executeQuery();

        if (resultSet.next()){
            userGroup = new UserGroup();
            userGroup.setGroup_id(resultSet.getString("group_id"));
            userGroup.setName(resultSet.getString("name"));
            userGroup.setGroup_manager(resultSet.getString("group_manager"));
            userGroup.setNumber_of_people(resultSet.getInt("number_of_people"));
        }
        else{
            System.out.println("there are no group with git = " + gid);
            return null;
        }
        return userGroup;
    }

    public ArrayList<String> ReadUserInGroup_byGroupID(String gid) throws SQLException{
        ArrayList<String> users_id = new ArrayList<>();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM user_in_group WHERE group_id = ?;");

        readStatement.setString(1, gid);

        ResultSet resultSet = readStatement.executeQuery();
        while (resultSet.next()){
            String user_id = resultSet.getString("user_id");
            users_id.add(user_id);
        }
        return users_id;
    }

    public boolean CheckIsFriendOf(IsFriendOf isFriendOf) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM is_friend_of WHERE user_id = ? AND friend_id = ?;");

        readStatement.setString(1, isFriendOf.getUser_id());
        readStatement.setString(2, isFriendOf.getFriend_id());

        ResultSet resultSet = readStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        return false;
    }

    public void ReadPhotoAndTime(PhotoAndTime photoAndTime) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO photo_and_time (photo_id, time) VALUES (?, ?);");

        readStatement.setString(1, photoAndTime.getPhoto_id());
        readStatement.setString(2, photoAndTime.getTime());
        readStatement.executeUpdate();
    }

    public void ReadPhotoPost(PhotoPost photoPost) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO photo_post (user_id, photo_id, text) VALUES (?, ?, ?);");

        readStatement.setString(1, photoPost.getUser_id());
        readStatement.setString(2, photoPost.getPhoto_id());
        readStatement.setString(3, photoPost.getText());
        readStatement.executeUpdate();
    }

    public void ReadEDT(Edt edt) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO edt (event_number, date, time) VALUES (?, ?, ?);");

        readStatement.setString(1, edt.getEvent_number());
        readStatement.setString(2, edt.getDate());
        readStatement.setString(3, edt.getTime());
        readStatement.executeUpdate();
    }

    public void ReadContain(Contain contain) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO contain (nid, user_id, event_number) VALUES (?, ?, ?);");

        readStatement.setInt(1, contain.getNid());
        readStatement.setString(2, contain.getUser_id());
        readStatement.setString(3, contain.getEvent_number());
        readStatement.executeUpdate();
    }

    public void ReadCommunicationEventTake(CommunicationEventTake communicationEventTake) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO communication_event_take (user_id, event_number) VALUES (?, ?);");

        readStatement.setString(1, communicationEventTake.getUser_id());
        readStatement.setString(2, communicationEventTake.getEvent_number());
        readStatement.executeUpdate();
    }

    public void ReadNotification(Notification notification) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO notification (nid, status, sound, visibility) VALUES (?, ?, ?, ?);");

        readStatement.setInt(1, notification.getNid());
        readStatement.setString(2, notification.getStatus());
        readStatement.setBlob(3, notification.getSound());
        readStatement.setBoolean(4, notification.getVisibility());
        readStatement.executeUpdate();
    }

    public void ReadInclude(Include include) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO include (message_id, user_id, event_number) VALUES (?, ?, ?);");

        readStatement.setString(1, include.getMessage_id());
        readStatement.setString(2, include.getUser_id());
        readStatement.setString(3, include.getEvent_number());
        readStatement.executeUpdate();
    }

    public void ReadText(Text text) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO text (message_id, word_count) VALUES (?, ?);");

        readStatement.setString(1, text.getMessage_id());
        readStatement.setInt(2, text.getWord_count());
        readStatement.executeUpdate();
    }

    public void ReadImage(Image image) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO image (message_id, format) VALUES (?, ?);");

        readStatement.setString(1, image.getMessage_id());
        readStatement.setString(2, image.getFormat());
        readStatement.executeUpdate();
    }

    public void ReadVoice(Voice voice) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO voice (message_id, length) VALUES (?, ?);");

        readStatement.setString(1, voice.getMessage_id());
        readStatement.setInt(2, voice.getLength());
        readStatement.executeUpdate();
    }

    public void ReadUseMessageManager(UseMessageManager useMessageManager) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO use_message_manager (manager_id, user_id, number_of_message) VALUES (?, ?, ?);");

        readStatement.setString(1, useMessageManager.getManager_id());
        readStatement.setString(2, useMessageManager.getUser_id());
        readStatement.setInt(3, useMessageManager.getNumber_of_message());
        readStatement.executeUpdate();
    }

    public void ReadAdministrate(Administrate administrate) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO administrate (message_id, manager_id) VALUES (?, ?);");

        readStatement.setString(1, administrate.getMessage_id());
        readStatement.setString(2, administrate.getManager_id());
        readStatement.executeUpdate();
    }

    public void ReadMessage(Message message) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO message (message_id, sender, receiver, read_or_unread, word_count) VALUES (?, ?, ?, ?, ?);");

        readStatement.setString(1, message.getMessage_id());
        readStatement.setString(2, message.getSender());
        readStatement.setString(3, message.getReceiver());
        readStatement.setBoolean(4, message.getRead_or_unread());
        readStatement.setInt(5, message.getWord_count());
        readStatement.executeUpdate();
    }

    public FileInputStream imageToBi(String filePath) throws IOException{
        File file = new File(filePath);
        return new FileInputStream(file);
    }

}
