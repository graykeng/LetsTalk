package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private Connection con;
    public JDBConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://letstalk354.mysql.database.azure.com/letstalk", "letstalk", "Gray&Kerla&Lester");

    }
    public Connection returnCon(){
        return this.con;
    }


}
