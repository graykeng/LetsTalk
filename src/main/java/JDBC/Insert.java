package JDBC;

import TableStruture.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Insert {
    private Connection con;
    public Insert(Connection connection){
        this.con = connection;
    }
    public void InsertUserGroup(UserGroup userGroup) throws SQLException{
        System.out.println("Insert Group" + userGroup.getGroup_id());
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user_group (group_id, name, group_manager, number_of_people) VALUES (?, ?, ?, ?);");

        insertStatement.setString(1, userGroup.getGroup_id());
        insertStatement.setString(2, userGroup.getName());
        insertStatement.setString(3, userGroup.getGroup_manager());
        insertStatement.setInt(4, userGroup.getNumber_of_people());
        insertStatement.executeUpdate();
    }

    public void InsertUserInGroup(UserInGroup userInGroup) throws SQLException {
        Read read = new Read(con);
        UserGroup group;
        try{
            // Find if the group is exited
            group = read.ReadUserGroup(userInGroup.getGroup_id());
            if(group != null){
                // If it is
                ArrayList<String> users_uid = read.ReadUserInGroup_byGroupID(group.getGroup_id());

                // Add them as user's friend
                System.out.println("Add "+users_uid.size()+" friends in group!");
                for(int i = 0; i < users_uid.size(); i++){
                    String uid = users_uid.get(i);
                    IsFriendOf isFriendOf = new IsFriendOf(userInGroup.getUser_id(),uid);
                    IsFriendOf isFriendOf2 = new IsFriendOf(uid,userInGroup.getUser_id());

                    if(!read.CheckIsFriendOf(isFriendOf)){
                        InsertIsFriendOf(isFriendOf);
                        InsertIsFriendOf(isFriendOf2);
                    }
                }
            }
            else{
                // If not, insert a new group
                System.out.println("Create a new group");
                group = new UserGroup();
                group.setGroup_id(userInGroup.getGroup_id());
                group.setName("Group #" + (read.CountGroup()+1));
                group.setGroup_manager(userInGroup.getUser_id());
                group.setNumber_of_people(1);

                InsertUserGroup(group);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        // Insert the relation
        System.out.println("Insert User " + userInGroup.getUser_id() + " in Group " + userInGroup.getGroup_id());
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user_in_group (group_id, user_id) VALUES (?, ?);");

        insertStatement.setString(1, userInGroup.getGroup_id());
        insertStatement.setString(2, userInGroup.getUser_id());
        insertStatement.executeUpdate();
    }

    public void InsertUserBirthdayAndAge(UserBirthdayAndAge userBirthdayAndAge) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user_birthday_and_age (birthday, age) VALUES (?, ?);");

        insertStatement.setString(1, userBirthdayAndAge.getBirthday());
        insertStatement.setInt(2, userBirthdayAndAge.getAge());
        insertStatement.executeUpdate();
    }

    public void InsertUser(User user) throws SQLException, IOException {
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO user (user_id, name, headshot, birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?);");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(user.getBirthday(), formatter);
        int age = calculateAge(localDate, LocalDate.now());

        Read read = new Read(con);
        if (read.ReadAge(user.getBirthday()) == -1){
            UserBirthdayAndAge userBirthdayAndAge = new UserBirthdayAndAge(user.getBirthday(), age);
            InsertUserBirthdayAndAge(userBirthdayAndAge);
        }

        insertStatement.setString(1, user.getUser_id());
        insertStatement.setString(2, user.getName());
        insertStatement.setBlob(3, user.getHeadshot());
        insertStatement.setString(4, user.getBirthday());
        insertStatement.setString(5, user.getGender());
        insertStatement.setString(6, user.getPassword());
        insertStatement.executeUpdate();

        // Insert all emoji for user
        InsertAllOwn_byUID(user.getUser_id());

        // Insert is_friend_of for user
        IsFriendOf isFriendOfSelf = new IsFriendOf(user.getUser_id(), user.getUser_id());
        InsertIsFriendOf(isFriendOfSelf);
    }

    public void InsertOwn(Own own) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO own (user_id, emoji_number) VALUES (?, ?);");

        insertStatement.setString(1, own.getUser_id());
        insertStatement.setString(2, own.getEmoji_number());
        insertStatement.executeUpdate();
    }

    public void InsertAllOwn_byUID(String uid) throws SQLException{
        Read read = new Read(con);
        ArrayList<Emoji> emojis = read.ReadEmoji();
        for(int i = 0; i < emojis.size(); i++){
            Own own = new Own(uid,emojis.get(i).getEmoji_number());
            InsertOwn(own);
        }
    }

    public void InsertEmoji(Emoji emoji) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO emoji (emoji_number) VALUES (?);");

        insertStatement.setString(1, emoji.getEmoji_number());
        insertStatement.executeUpdate();
    }

    public void InsertAllEmoji() throws SQLException {
        Read read = new Read(con);
        int numberOfEmoji = read.CountEmoji();
        if(numberOfEmoji == 0){
            String[] strList = new String[]{ "😀", "😁", "😆", "😅", "😂", "😊", "😜", "😟", "😘", "😵", "😢", "😠", "😎"};
            for(int i = 0; i < 13; i++){
                Emoji emoji = new Emoji();
                emoji.setEmoji_number(strList[i]);
                InsertEmoji(emoji);
            }
        }
    }

    public void InsertInterest(Interest interest) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO interest (interest_id, interest_name, type) VALUES (?, ?, ?);");

        insertStatement.setString(1, interest.getInterest_id());
        insertStatement.setString(2, interest.getInterest_name());
        insertStatement.setString(3, interest.getType());
        insertStatement.executeUpdate();
    }

    public void InsertHas(Has has) throws SQLException{

        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO has (user_id, interest_id) VALUES (?, ?);");

        insertStatement.setString(1, has.getUser_id());
        insertStatement.setString(2, has.getInterest_id());
        insertStatement.executeUpdate();

    }

    public void InsertIsFriendOf(IsFriendOf isFriendOf) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO is_friend_of (user_id, friend_id) VALUES (?, ?);");

        insertStatement.setString(1, isFriendOf.getUser_id());
        insertStatement.setString(2, isFriendOf.getFriend_id());
        insertStatement.executeUpdate();
    }

    public void InsertPhotoAndTime(PhotoAndTime photoAndTime) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO photo_and_time (photo_id, time) VALUES (?, ?);");

        insertStatement.setString(1, photoAndTime.getPhoto_id());
        insertStatement.setString(2, photoAndTime.getTime());
        insertStatement.executeUpdate();
    }

    public void InsertEDT(Edt edt) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO edt (event_number, date, time) VALUES (?, ?, ?);");

        insertStatement.setString(1, edt.getEvent_number());
        insertStatement.setString(2, edt.getDate());
        insertStatement.setString(3, edt.getTime());
        insertStatement.executeUpdate();
    }

    public void InsertContain(Contain contain) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO contain (nid, user_id, event_number) VALUES (?, ?, ?);");

        insertStatement.setInt(1, contain.getNid());
        insertStatement.setString(2, contain.getUser_id());
        insertStatement.setString(3, contain.getEvent_number());
        insertStatement.executeUpdate();
    }

    public void InsertCommunicationEventTake(CommunicationEventTake communicationEventTake) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO communication_event_take (user_id, event_number, date, time) VALUES (?, ?, ?, ?);");

        insertStatement.setString(1, communicationEventTake.getUser_id());
        insertStatement.setString(2, communicationEventTake.getEvent_number());
        insertStatement.setString(3, communicationEventTake.getDate());
        insertStatement.setString(4, communicationEventTake.getTime());
        insertStatement.executeUpdate();
    }

    public void InsertNotification(Notification notification) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO notification (nid, status, sound, visibility) VALUES (?, ?, ?, ?);");

        insertStatement.setInt(1, notification.getNid());
        insertStatement.setString(2, notification.getStatus());
        insertStatement.setBlob(3, notification.getSound());
        insertStatement.setBoolean(4, notification.getVisibility());
        insertStatement.executeUpdate();
    }

    public void InsertInclude(Include include) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO include (message_id, user_id, event_number) VALUES (?, ?, ?);");

        insertStatement.setString(1, include.getMessage_id());
        insertStatement.setString(2, include.getUser_id());
        insertStatement.setString(3, include.getEvent_number());
        insertStatement.executeUpdate();
    }

    public void InsertText(Text text) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO text (message_id, word_count) VALUES (?, ?);");

        insertStatement.setString(1, text.getMessage_id());
        insertStatement.setInt(2, text.getWord_count());
        insertStatement.executeUpdate();
    }

    public void InsertImage(Image image) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO image (message_id, format) VALUES (?, ?);");

        insertStatement.setString(1, image.getMessage_id());
        insertStatement.setString(2, image.getFormat());
        insertStatement.executeUpdate();
    }

    public void InsertVoice(Voice voice) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO voice (message_id, length) VALUES (?, ?);");

        insertStatement.setString(1, voice.getMessage_id());
        insertStatement.setInt(2, voice.getLength());
        insertStatement.executeUpdate();
    }

    public void InsertUseMessageManager(UseMessageManager useMessageManager) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO use_message_manager (manager_id, user_id, number_of_message) VALUES (?, ?, ?);");

        insertStatement.setString(1, useMessageManager.getManager_id());
        insertStatement.setString(2, useMessageManager.getUser_id());
        insertStatement.setInt(3, useMessageManager.getNumber_of_message());
        insertStatement.executeUpdate();
    }

    public void InsertAdministrate(Administrate administrate) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO administrate (message_id, manager_id) VALUES (?, ?);");

        insertStatement.setString(1, administrate.getMessage_id());
        insertStatement.setString(2, administrate.getManager_id());
        insertStatement.executeUpdate();
    }

    public void InsertMessage(Message message) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO message (message_id, sender, receiver, read_or_unread, content) VALUES (?, ?, ?, ?, ?);");

        insertStatement.setString(1, message.getMessage_id());
        insertStatement.setString(2, message.getSender());
        insertStatement.setString(3, message.getReceiver());
        insertStatement.setBoolean(4, message.getRead_or_unread());
        insertStatement.setBlob(5, message.getContent());
        insertStatement.executeUpdate();
    }

    public FileInputStream imageToBi(String filePath) throws IOException{
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public void sendTextMsg(String user_id, String friend_id, Blob msgSend_inBlob, int wordCount) throws SQLException {
        // Get event number
        String event_number = "";
        Read read = new Read(con);
        try {
            event_number = "V" + String.format("%06d", read.CountComminicationEvent());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Insert new Communication_event
        CommunicationEventTake communicationEventTake = new CommunicationEventTake(user_id, event_number, LocalDateTime.now());
        InsertCommunicationEventTake(communicationEventTake);

        // Get message id
        String message_id = "";
        try {
            message_id = "M" + String.format("%06d", read.CountMessage())+ "T";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Insert new Message
        Message message = new Message(message_id, user_id, friend_id, false, msgSend_inBlob);
        InsertMessage(message);

        // Insert new Text Message
        Text textMsg = new Text();
        textMsg.setMessage_id(message_id);
        textMsg.setWord_count(wordCount);
        InsertText(textMsg);

        // Insert Include
        Include eventIncludeMsg = new Include(message_id,user_id,event_number);
        InsertInclude(eventIncludeMsg);
    }

    public void sendImageMsg(String user_id, String friend_id, Blob blob, String png) throws SQLException {
        // Get event number
        String event_number = "";
        Read read = new Read(con);
        try {
            event_number = "V" + String.format("%06d", read.CountComminicationEvent());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Insert new Communication_event
        CommunicationEventTake communicationEventTake = new CommunicationEventTake(user_id, event_number, LocalDateTime.now());
        InsertCommunicationEventTake(communicationEventTake);

        // Get message id
        String message_id = "";
        try {
            message_id = "M" + String.format("%06d", read.CountMessage())+ "I";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Insert new Message
        Message message = new Message(message_id, user_id, friend_id, false, blob);
        InsertMessage(message);

        // Insert new Image Message
        Image imageMsg = new Image();
        imageMsg.setMessage_id(message_id);
        imageMsg.setFormat(png);
        InsertImage(imageMsg);

        // Insert Include
        Include eventIncludeMsg = new Include(message_id,user_id,event_number);
        InsertInclude(eventIncludeMsg);
    }

    public void InsertPhotoPost(PhotoPost photoPost) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO photo_post (user_id, photo_id, text, time, content) VALUES (?, ?, ?, ?, ?);");

        insertStatement.setString(1, photoPost.getUser_id());
        insertStatement.setString(2, photoPost.getPhoto_id());
        insertStatement.setString(3, photoPost.getText());
        insertStatement.setString(4, photoPost.getDate_time_str());
        insertStatement.setBlob(5,photoPost.getContent());
        insertStatement.executeUpdate();
    }
}
