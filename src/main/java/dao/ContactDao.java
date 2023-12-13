package dao;

import databaseConnector.DatabaseConnector;
import dotenv.ConfigEnv;
import models.ContactModel;

import java.sql.Connection;

public class ContactDao {
    private final String databaseTableContact = ConfigEnv.getInstance().get("DATABASE_TABLE_USERS");

    // CRUD - POST
    public void postContact(ContactModel contact) {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        Connection connection = databaseConnector.getConnection();

        String query = "INSERT INTO " + databaseTableContact + " (name, lastname, email, phone) VALUES ('"
                + contact.getName() + "', '" + contact.getLastName() + "', '"
                + contact.getEmail() + "', '" + contact.getPhone() + "')";

        try {
            connection.createStatement().executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        } finally {
            databaseConnector.closeConnection();
        }
    }
}
