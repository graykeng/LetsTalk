package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    private Connection con;
    public Delete(Connection connection){
        this.con = connection;
    }
    public void DeleteIsFriendOf(String user_id, String friend_id) throws SQLException {
        PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM is_friend_of WHERE user_id = ? AND friend_id = ?;");

        deleteStatement.setString(1, user_id);
        deleteStatement.setString(2, friend_id);
        deleteStatement.executeUpdate();
    }

    public void DeleteUser(String user_id) throws SQLException {
        PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM user WHERE user_id = ?;");

        deleteStatement.setString(1, user_id);
        deleteStatement.executeUpdate();
    }

    public void DeletePhoto_Post_photo_and_time(String Photo_id) throws SQLException {
        PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM photo_post_photo_and_time WHERE Photo_id = ?;");

        deleteStatement.setString(1, Photo_id);
        deleteStatement.executeUpdate();
    }

    public void DeleteEmoji(String Emoji_number) throws SQLException {
        PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM emoji WHERE Emoji_number = ?;");

        deleteStatement.setString(1, Emoji_number);
        deleteStatement.executeUpdate();
    }

    public void DeleteEDT(String event_number) throws SQLException {
        PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM edt WHERE event_number = ?;");

        deleteStatement.setString(1, event_number);
        deleteStatement.executeUpdate();
    }
}
