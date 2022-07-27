package JDBC;

import TableStruture.User;
import TableStruture.UserBirthdayAndAge;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private Connection con;
    public Update(Connection connection){
        this.con = connection;
    }

    public void UpdateUser(User user) throws SQLException{
        // UPDATE Customers SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' WHERE CustomerID = 1;
        PreparedStatement updateStatement = con.prepareStatement("UPDATE user SET name = ?, headshot = ?, birthday = ?, gender = ?, password = ? WHERE user_id = ?;");

        updateStatement.setString(1, user.getName());
        updateStatement.setBlob(2, user.getHeadshot());
        updateStatement.setString(3, user.getBirthday());
        updateStatement.setString(4, user.getGender());
        updateStatement.setString(5, user.getPassword());
        updateStatement.setString(6, user.getUser_id());


        updateStatement.executeUpdate();
    }
}
