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
        PreparedStatement updateStatement = con.prepareStatement("UPDATE user SET user_id = ?, name = ?, headshot = ?, birthday = ?, gender = ?, password = ?;");

        updateStatement.setString(1, user_id);
        updateStatement.setString(2, name);
        updateStatement.setBlob(3, headshot);
        updateStatement.setString(4, birthday);
        updateStatement.setString(5, gender);
        updateStatement.setString(6, password);
        updateStatement.executeUpdate();
    }
}
