package JDBC;

import Constants.State;
import TableStruture.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

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

    public int CountInterest() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM interest");
        int i = 0;
        while (resultSet.next()){
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

    public int CountPhoto() throws SQLException{
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM photo_post");
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }
        return i;
    }

    public int CountUserInterest(String uid) throws SQLException{
        PreparedStatement countStatement = con.prepareStatement("SELECT count(*) FROM has WHERE user_id = ?;");

        countStatement.setString(1, uid);

        ResultSet resultSet = countStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("count(*)");
        }
        return -1;
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

    public String ReadNewUser_uid() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        String lastUID = "";
        while (resultSet.next()) {
            lastUID = resultSet.getString("user_id");
        }
        String uid = lastUID.substring(1,lastUID.length());
        int lastID = Integer.parseInt(uid);
        return "U" + String.format("%06d", lastID+1);
    }

    public Object[][] ReadUserID_Name() throws SQLException {
        int count = CountUser();
        Object[][] id_name = new Object[count][2];
        PreparedStatement statement = con.prepareStatement("SELECT user_id, name FROM user;");
        ResultSet resultSet = statement.executeQuery();
        int i = 0;
        while (resultSet.next()) {
            String uid = resultSet.getString("user_id");
            String name = resultSet.getString("name");
            id_name[i][0] = uid;
            id_name[i][1] = name;
            i++;
        }

        return id_name;
    }

    public Object[][] ReadUserID_Name_hasAllFriend() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select user_id, name " +
                                                                "from user u " +
                                                                "WHERE NOT exists " +
                                                                "(select * from user f " +
                                                                "WHERE NOT exists " +
                                                                "(select * " +
                                                                "FROM is_friend_of r " +
                                                                "where u.user_id = r.user_id " +
                                                                "AND f.user_id = r.friend_id));");
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String uid = resultSet.getString("user_id");
            String name = resultSet.getString("name");
            User user = new User();

            user.setUser_id(uid);
            user.setName(name);
            users.add(user);
        }

        int count = users.size();
        Object[][] id_name = new Object[count][2];
        for(int i = 0; i < count; i++){
            id_name[i][0] = users.get(i).getUser_id();
            id_name[i][1] = users.get(i).getName();
        }

        return id_name;
    }

    public Object[][] ReadUserID_Name_overAvg() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select user_id, name FROM user, user_birthday_and_age " +
                                                                "WHERE user.birthday = user_birthday_and_age.birthday " +
                                                                "AND age > (Select AVG(age) from user, user_birthday_and_age  WHERE user.birthday = user_birthday_and_age.birthday) Group By user_id, name;");
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String uid = resultSet.getString("user_id");
            String name = resultSet.getString("name");
            User user = new User();

            user.setUser_id(uid);
            user.setName(name);
            users.add(user);
        }

        int count = users.size();
        Object[][] id_name = new Object[count][2];
        for(int i = 0; i < count; i++){
            id_name[i][0] = users.get(i).getUser_id();
            id_name[i][1] = users.get(i).getName();
        }

        return id_name;
    }

    public Object[][] CountUser_overAvg_OfEachGender() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select gender, count(*), AVG(age) FROM user, user_birthday_and_age " +
                                                                "WHERE user.birthday = user_birthday_and_age.birthday " +
                                                                "Group By gender;");
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String gender = resultSet.getString("gender");
            int numOfUser = resultSet.getInt("count(*)");
            double avgAge = resultSet.getDouble("AVG(age)");
            User user = new User();

            user.setGender(gender);
            user.setAge(numOfUser);
            user.setName("" + avgAge);
            users.add(user);
        }

        int count = users.size();
        Object[][] results = new Object[count][3];
        for(int i = 0; i < count; i++){
            results[i][0] = users.get(i).getGender();
            results[i][1] = users.get(i).getAge();
            results[i][2] = users.get(i).getName();
        }

        return results;
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

    public ArrayList<Message> ReadMsg_betweenUIDs(String user_id, String friend_id) throws SQLException {
        ArrayList<Message> msgList = new ArrayList<>();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM message WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?);");

        readStatement.setString(1, user_id);
        readStatement.setString(2, friend_id);
        readStatement.setString(3, friend_id);
        readStatement.setString(4, user_id);

        ResultSet resultSet = readStatement.executeQuery();
        while (resultSet.next()){
            String message_id = resultSet.getString("message_id");
            String senderUID = resultSet.getString("sender");
            String receiverUID = resultSet.getString("receiver");
            Boolean read_or_unread = resultSet.getBoolean("read_or_unread");
            Blob content = resultSet.getBlob("content");

            // Get the sender's name
            User sender = ReadUser(senderUID);
            String senderName = sender.getName();

            // Get the date and time that message send
            String event_number = ReadEventNumber_byMsgID(message_id);
            String date_and_time = ReadDateAndTime_byEventNum(event_number);

            Message message = new Message(message_id, senderUID,receiverUID,read_or_unread,content,senderName,date_and_time);
            msgList.add(message);
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

    public ArrayList<Interest> ReadAllInterest(User user) throws SQLException{
        ArrayList<Interest> returnList = new ArrayList<>();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM has INNER JOIN interest ON has.interest_id = interest.interest_id;");

        ResultSet resultSet = readStatement.executeQuery();

        while(resultSet.next()) {
            if (Objects.equals(resultSet.getString("user_id"), user.getUser_id())){
                Interest interest = new Interest();
                interest.setInterest_id(resultSet.getString("interest_id"));
                interest.setInterest_name(resultSet.getString("interest_name"));
                interest.setType(resultSet.getString("type"));
                returnList.add(interest);
            }
        }
        return returnList;

    }

    public ArrayList<PhotoPost> PhotoPostByUser(String userid) throws SQLException {
        con = new JDBConnection().returnCon();
        PreparedStatement readStatement = con.prepareStatement("SELECT * FROM photo_post WHERE user_id = ?;");

        readStatement.setString(1, userid);
        ArrayList<PhotoPost> photo_list= new ArrayList<>();

        ResultSet resultSet = readStatement.executeQuery();
        while(resultSet.next()){

            String user_id =resultSet.getString("user_id");
            String photo_id = resultSet.getString("photo_id");
            String text=resultSet.getString("text");
            Blob content= resultSet.getBlob("content");
            String time = resultSet.getString("time");

            DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(time,dateFormat1);

            PhotoPost photo = new PhotoPost(user_id,photo_id,text,date,content);
            photo_list.add(photo);
        }
        return photo_list;

    }
}
