package persistence.dao;

import persistence.DBConnector;
import logic.dotenv.ConfigEnv;
import persistence.models.ContactModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContactDao {
    private final String databaseTableContact = ConfigEnv.getInstance().get("DATABASE_TABLE_USERS");
    private static final DBConnector dbconnector = DBConnector.getInstance();
    private static final Connection connection = dbconnector.getConnection();

    // CRUD - POST
    public void postContact(ContactModel contact) {
        String query = "INSERT INTO " + databaseTableContact + " (name, lastname, email, phone) VALUES ('"
                + contact.getName() + "', '" + contact.getLastName() + "', '"
                + contact.getEmail() + "', '" + contact.getPhone() + "')";

        try {
            connection.createStatement().executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        } finally {
            dbconnector.closeConnection();
        }
    }

    // CRUD - GET All
    public ArrayList<ContactModel> findAll() {
        ArrayList<ContactModel> contacts = new ArrayList<>();

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
            dbconnector.closeConnection();
        }
        return contacts;
    }
}
