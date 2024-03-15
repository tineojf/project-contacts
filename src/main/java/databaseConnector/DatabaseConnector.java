package databaseConnector;

import dotenv.ConfigEnv;

import java.sql.*;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    private Connection connection = null;

    private DatabaseConnector() {
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        ConfigEnv configEnv = ConfigEnv.getInstance();

        try {
            String databaseHost = configEnv.get("DATABASE_HOST");
            String databasePort = configEnv.get("DATABASE_PORT");
            String databaseName = configEnv.get("DATABASE_NAME");
            String databaseUser = configEnv.get("DATABASE_USER");
            String databasePassword = configEnv.get("DATABASE_PASSWORD");

            if (databaseHost == null || databasePort == null || databaseName == null || databaseUser == null || databasePassword == null) {
                throw new IllegalStateException("Error en la configuración de la base de datos");
            }

            String databaseURL = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName;

            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        } catch (IllegalStateException e) {
            //e.printStackTrace();
            System.out.println("IllegalStateException: " + e.getMessage());
        } catch (Exception e) {
            //e.printStackTrace();
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