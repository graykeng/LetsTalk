package JDBC;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private Connection con;
    public Update(Connection connection){
        this.con = connection;
    }

    public void UpdateUser(String user_id, String name, Blob headshot, String birthday, String gender, String password) throws SQLException{
        PreparedStatement insertStatement = con.prepareStatement("UPDATE user SET user_id = ?, name = ?, headshot = ?, birthday = ?, gender = ?, password = ?;");

        insertStatement.setString(1, user_id);
        insertStatement.setString(2, name);
        insertStatement.setBlob(3, headshot);
        insertStatement.setString(4, birthday);
        insertStatement.setString(5, gender);
        insertStatement.setString(6, password);
        insertStatement.executeUpdate();
    }
}
