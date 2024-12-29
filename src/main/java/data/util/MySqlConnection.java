package data.util;

import util.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String DB_KEY_URL = "db.url";
    private static final String DB_KEY_USERNAME = "db.user";
    private static final String DB_KEY_PASSWORD = "db.password";

    private static final String url;
    private static final String user;
    private static final String pwd;

    static {
        url = Config.getInstance().get(DB_KEY_URL);
        user = Config.getInstance().get(DB_KEY_USERNAME);
        pwd = Config.getInstance().get(DB_KEY_PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pwd);
    }
}
