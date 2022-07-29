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

    public int CountComminicationEvent() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM communication_event_take");
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }
        return i;
    }

    public int CountEmoji() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM emoji");
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }
        return i;
    }

    public int CountMessage() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM message");
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

    public ArrayList<Emoji> ReadEmoji() throws SQLException {
        ArrayList<Emoji> emojis = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM emoji");
        while (resultSet.next()) {
            Emoji emoji = new Emoji();
            emoji.setEmoji_number(resultSet.getString("emoji_number"));

            emojis.add(emoji);
        }
        return emojis;
    }

    public ArrayList<String> ReadUserOwnEmoji(String uid) throws SQLException{
        ArrayList<String> emojis_number = new ArrayList<>();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM own WHERE user_id = ?;");

        readStatement.setString(1, uid);

        ResultSet resultSet = readStatement.executeQuery();
        while (resultSet.next()){
            String emoji_number = resultSet.getString("emoji_number");
            emojis_number.add(emoji_number);
        }
        return emojis_number;
    }

    public ArrayList<String> ReadMsg_betweenUIDs(String user_id, String friend_id) throws SQLException {
        ArrayList<String> msgList = new ArrayList<>();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM message WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?);");

        readStatement.setString(1, user_id);
        readStatement.setString(2, friend_id);
        readStatement.setString(3, friend_id);
        readStatement.setString(4, user_id);

        ResultSet resultSet = readStatement.executeQuery();
        while (resultSet.next()){
            // Get the sender's name
            String sender_id = resultSet.getString("sender");
            User sender = ReadUser(sender_id);
            String msg_id = resultSet.getString("message_id");

            // Get the date and time that message send
            String event_number = ReadEventNumber_byMsgID(msg_id);
            String date_time = ReadDateAndTime_byEventNum(event_number);

            // Get the message content
            Blob msg_blob = resultSet.getBlob("content");
            String msg = "";
            try{
                msg = sender.getName() + " (send at " + date_time +")" +": \n"
                        + new String(msg_blob.getBytes(1, (int) msg_blob.length()),"GBK");//blob change to String
            }catch (Exception e){
                e.printStackTrace();
            }
            msgList.add(msg);
        }
        return msgList;
    }

    private String ReadDateAndTime_byEventNum(String event_number) throws SQLException {
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM communication_event_take WHERE event_number = ?;");
        readStatement.setString(1, event_number);

        ResultSet resultSet = readStatement.executeQuery();
        if (resultSet.next()){
            String dateAndTime = resultSet.getString("date") + " " + resultSet.getString("time");
            return dateAndTime;
        }
        return null;
    }

    public String ReadEventNumber_byMsgID(String msg_id) throws SQLException {
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM include WHERE message_id = ?;");
        readStatement.setString(1, msg_id);

        ResultSet resultSet = readStatement.executeQuery();
        if (resultSet.next()){
            String event_number = resultSet.getString("event_number");
            return event_number;
        }
        return null;
    }
}
