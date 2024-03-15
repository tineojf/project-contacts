package databaseConnector;

import dotenv.ConfigEnv;

import java.sql.*;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    private Connection connection = null;

    private DatabaseConnector() {
        instance = new DatabaseConnector();
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            return new DatabaseConnector();
        } else {
            return instance;
        }
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