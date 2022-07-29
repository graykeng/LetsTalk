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

    public Interest ReadInterestDup(Interest interest) throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM interest WHERE interest_name = ?;");


        Interest interestw = null;
        return interestw;
    }

    public Message ReadMessage() throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM message WHERE sender = ? AND receiver = ?;");

        Message message = null;
        return message;
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

    public FileInputStream imageToBi(String filePath) throws IOException{
        File file = new File(filePath);
        return new FileInputStream(file);
    }

}
