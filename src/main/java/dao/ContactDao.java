package dao;

import databaseConnector.DatabaseConnector;
import dotenv.ConfigEnv;
import models.ContactModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    // CRUD - GET All
    public ArrayList<ContactModel> getContact() {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        Connection connection = databaseConnector.getConnection();

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();

        String query = "SELECT * FROM " + databaseTableContact;

        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                contacts.add(new ContactModel(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("lastname"),
                        result.getString("phone"),
                        result.getString("email")
                ));
            }
        } catch (Exception e) {
            System.out.println("Select error: " + e.getMessage());
        } finally {
            databaseConnector.closeConnection();
        }
        return contacts;
    }
}
