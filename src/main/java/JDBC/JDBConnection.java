package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/letstalk";
    private static final String DEFAULT_DB_USER = "letstalk";

    private Connection con;

    public JDBConnection() throws SQLException {
        String url = getConfig("LETSTALK_DB_URL", DEFAULT_DB_URL);
        String user = getConfig("LETSTALK_DB_USER", DEFAULT_DB_USER);
        String password = getConfig("LETSTALK_DB_PASSWORD", "");

        con = DriverManager.getConnection(url, user, password);
    }

    public Connection returnCon(){
        return this.con;
    }

    private String getConfig(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value;
    }
}
