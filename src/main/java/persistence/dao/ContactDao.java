package persistence.dao;

import persistence.DBConnector;
import logic.dotenv.ConfigEnv;
import persistence.models.ContactModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDao {
    private final String databaseTableContact = ConfigEnv.getInstance().get("DATABASE_TABLE_USERS");
    private static final DBConnector dbconnector = DBConnector.getInstance();
    private static final Connection connection = dbconnector.getConnection();

    // CRUD - POST
    public boolean create(ContactModel contact) {
        String query = "INSERT INTO " + databaseTableContact + " (name, lastname, email, phone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                System.out.println("POST - NOT CREATED");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("POST error: " + e.getMessage());
            return false;
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

    // CRUD - GET by ID
    public ContactModel findByID(int id) {
        String query = "SELECT * FROM " + databaseTableContact + " WHERE id = ? LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return new ContactModel(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("lastname"),
                        result.getString("phone"),
                        result.getString("email")
                );
            } else {
                System.out.println("GET - NOT FOUND");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("GET error: " + e.getMessage());
            return null;
        }
    }

    // CRUD - DELETE
    public boolean delete(int id) {
        ContactModel contact = findByID(id);

        if (contact == null) {
            return false;
        }

        String query = "DELETE FROM " + databaseTableContact + " WHERE id = ? LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("DELETE error: " + e.getMessage());
            return false;
        } finally {
            dbconnector.closeConnection();
        }
    }

}
