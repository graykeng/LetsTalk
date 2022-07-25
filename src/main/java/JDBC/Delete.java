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
        PreparedStatement insertStatement = con.prepareStatement("DELETE FROM is_friend_of WHERE user_id = ? AND friend_id = ?;");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, friend_id);
        insertStatement.executeUpdate();
    }
}
