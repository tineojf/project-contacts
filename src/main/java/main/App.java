package main;

import dao.ContactDao;
import databaseConnector.DatabaseConnector;
import dotenv.ConfigEnv;
import models.ContactModel;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Demo de ConfigEnv
            //System.out.println("Hello World!");
            //ConfigEnv configEnv = ConfigEnv.getInstance();
            //System.out.println(configEnv.get("DATABASE_NAME"));

            // Demo de DatabaseConnector
            DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
            Connection connection = databaseConnector.getConnection();

            // Demo de Statement - POST
            ContactModel contact = new ContactModel(1, "Juan", "Perez", "999888777", "email@gmail.com");
            ContactDao contactDao = new ContactDao();
            //contactDao.postContact(contact);

            // Demo de DatabaseConnector closeConnection
            databaseConnector.closeConnection();

            System.out.println("Programa finalizado");
        } catch (RuntimeException | ClassNotFoundException e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
        }
    }
}