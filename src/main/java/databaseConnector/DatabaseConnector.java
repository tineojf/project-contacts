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

    private String[] loadDataDB() {
        ConfigEnv configEnv = ConfigEnv.getInstance();
        String[] keys = new String[3];

        String databaseHost = configEnv.get("DATABASE_HOST");
        String databasePort = configEnv.get("DATABASE_PORT");
        String databaseName = configEnv.get("DATABASE_NAME");
        String databaseUser = configEnv.get("DATABASE_USER");
        String databasePassword = configEnv.get("DATABASE_PASSWORD");

        if (databaseHost == null || databasePort == null || databaseName == null || databaseUser == null || databasePassword == null) {
            throw new IllegalStateException("Error en la configuración de la base de datos");
        } else {
            keys[0] = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName;
            keys[1] = databaseUser;
            keys[2] = databasePassword;
            return keys;
        }
    }

    private void connectToDB() throws SQLException, ClassNotFoundException {
        String[] databaseURL = this.loadDataDB();

        connection = DriverManager.getConnection(databaseURL[0], databaseURL[1], databaseURL[2]);

        System.out.println("Successful connection");
        String databaseName = connection.getMetaData().getDatabaseProductName();
        String databaseVersion = connection.getMetaData().getDatabaseProductVersion();
        System.out.println(databaseName + " " + databaseVersion);
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connectToDB();
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error DB: " + e.getMessage());
            return null;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}