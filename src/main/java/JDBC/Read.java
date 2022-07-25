package JDBC;

import TableStruture.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class Read {
    private Connection con;
    public Read(Connection connection){
        this.con = connection;
    }

    public UserGroup ReadUserGroup(Connection connection) throws SQLException{
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM user_group");
        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()){
            System.out.println("no result");
            return null;
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setGroup_id(resultSet.getString("group_id"));
        userGroup.setName(resultSet.getString("name"));
        userGroup.setGroup_manager(resultSet.getString("group_manager"));
        userGroup.setNumber_of_people(resultSet.getInt("number_of_people"));
        readStatement.executeUpdate();
        return userGroup;
    }

    public void ReadUserInGroup(UserInGroup userInGroup) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO user_in_group (group_id, user_id) VALUES (?, ?);");

        readStatement.setString(1, userInGroup.getGroup_id());
        readStatement.setString(2, userInGroup.getUser_id());
        readStatement.executeUpdate();
    }

    public void ReadUserBirthdayAndAge(UserBirthdayAndAge userBirthdayAndAge) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO user_birthday_and_age (birthday, age) VALUES (?, ?);");

        readStatement.setString(1, userBirthdayAndAge.getBirthday());
        readStatement.setInt(2, userBirthdayAndAge.getAge());
        readStatement.executeUpdate();
    }

    public void ReadUser(User user) throws SQLException, IOException {
        PreparedStatement readStatement = con.prepareStatement("Read INTO user (user_id, name, headshot, birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?);");

        readStatement.setString(1, user.getUser_id());
        readStatement.setString(2, user.getName());
        readStatement.setBlob(3, user.getHeadshot());
        readStatement.setString(4, user.getBirthday());
        readStatement.setString(5, user.getGender());
        readStatement.setString(6, user.getPassword());
        readStatement.executeUpdate();
    }

    public void ReadOwn(Own own) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO own (user_id, emoji_number) VALUES (?, ?);");

        readStatement.setString(1, own.getUser_id());
        readStatement.setString(2, own.getEmoji_number());
        readStatement.executeUpdate();
    }

    public void ReadEmoji(Emoji emoji) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO emoji (emoji_number) VALUES (?);");

        readStatement.setString(1, emoji.getEmoji_number());
        readStatement.executeUpdate();
    }

    public void ReadInterest(Interest interest) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO interest (interest_name, type) VALUES (?, ?);");

        readStatement.setString(1, interest.getInterest_name());
        readStatement.setString(2, interest.getType());
        readStatement.executeUpdate();
    }

    public void ReadHas(Has has) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO has (user_id, interest_name) VALUES (?, ?);");

        readStatement.setString(1, has.getUser_id());
        readStatement.setString(2, has.getInterest_name());
        readStatement.executeUpdate();
    }

    public void ReadIsFriendOf(IsFriendOf isFriendOf) throws SQLException{
        PreparedStatement readStatement = con.prepareStatement("Read INTO is_friend_of (user_id, friend_id) VALUES (?, ?);");

        readStatement.setString(1, isFriendOf.getUser_id());
        readStatement.setString(2, isFriendOf.getFriend_id());
        readStatement.executeUpdate();
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
