package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private static Statement statement;
    private static Connection connection;

    private DatabaseManager(){

    }
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
    public static Statement getStatement() throws SQLException {
        if(statement == null){
            statement = getConnection().createStatement();
        }
        return statement;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
