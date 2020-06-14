package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final class SingletonHolder {
        public static final ConnectionManager INSTANCE = new ConnectionManager();
    }
    private Connection connection;
    private ConnectionManager(){
        String url="jdbc:mysql://localhost/planificator";
        try {
            connection= DriverManager.getConnection(url,"root","");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ConnectionManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public Connection getConnection(){
        return connection;
    }
}
