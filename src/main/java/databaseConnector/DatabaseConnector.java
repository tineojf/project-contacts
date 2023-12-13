package databaseConnector;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import dotenv.ConfigEnv;

import java.sql.*;

public class DatabaseConnector {
    private static final DatabaseConnector instance = new DatabaseConnector();
    private Connection connection = null;

    private DatabaseConnector() {
        if (instance != null) {
            throw new IllegalStateException("Ya existe una instancia de DatabaseConnector");
        }
    }

    public static DatabaseConnector getInstance() {
        return instance;
    }

    public Connection getConnection() {
        ConfigEnv configEnv = ConfigEnv.getInstance();

        try {
            String url = "jdbc:mysql://"
                    + configEnv.get("DATABASE_HOST")
                    + ":"
                    + configEnv.get("DATABASE_PORT")
                    + "/"
                    + configEnv.get("DATABASE_NAME");
            String user = configEnv.get("DATABASE_USER");
            String password = configEnv.get("DATABASE_PASSWORD");

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}