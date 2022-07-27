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
        // UPDATE Customers SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' WHERE CustomerID = 1;
        PreparedStatement updateStatement = con.prepareStatement("UPDATE user SET name = ?, headshot = ?, birthday = ?, gender = ?, password = ? WHERE user_id = ?;");

        updateStatement.setString(1, name);
        updateStatement.setBlob(2, headshot);
        updateStatement.setString(3, birthday);
        updateStatement.setString(4, gender);
        updateStatement.setString(5, password);
        updateStatement.setString(6, user_id);

        updateStatement.executeUpdate();
    }
}
