import dao.ContactDao;
import databaseConnector.DatabaseConnector;
import models.ContactModel;

import java.sql.Connection;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Demo de ConfigEnv
            //System.out.println("Hello World!");
            //ConfigEnv confzigEnv = ConfigEnv.getInstance();
            //System.out.println(configEnv.get("DATABASE_NAME"));

            // Demo de DatabaseConnector
            DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
            Connection connection = databaseConnector.getConnection();

            // Demo de ContactModel
            ContactModel contact = new ContactModel(1, "Juan", "Perez", "999888777", "email@gmail.com");

            // Demo de ContactDao
            ContactDao contactDao = new ContactDao();

            // Demo de Statement - POST
            //contactDao.create(contact);

            // Demo de Statement - GET
            ArrayList<ContactModel> listContact = contactDao.findAll();

            System.out.println("\nListado de contactos:");
            for (ContactModel c : listContact) {
                System.out.println(c);
            }

            // Demo de DatabaseConnector closeConnection
            //databaseConnector.closeConnection();

        } catch (RuntimeException | ClassNotFoundException e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
        }
    }
}