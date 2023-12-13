package main;

import databaseConnector.DatabaseConnector;
import dotenv.ConfigEnv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Demo de ConfigEnv
            System.out.println("Hello World!");
            ConfigEnv configEnv = ConfigEnv.getInstance();
            System.out.println(configEnv.get("DATABASE_NAME"));
            String databaseTableUsers = configEnv.get("DATABASE_TABLE_USERS");

            // Demo de DatabaseConnector
            DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
            Connection connection = databaseConnector.getConnection();

            // Demo de Statement
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + databaseTableUsers);
            while (rs.next()) {
                int x = rs.getInt("id");
                String s = rs.getString("nombre");
                System.out.println("ID: " + x + " Name: " + s);
            }

            // Demo de DatabaseConnector closeConnection
            databaseConnector.closeConnection();

            System.out.println("Hola");
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
        }
    }
}