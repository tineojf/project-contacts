package persistence.dao;

import logic.dotenv.ConfigEnv;
import persistence.DBConnector;
import persistence.models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    private final String databaseTableCategory = ConfigEnv.getInstance().get("DATABASE_TABLE_CATEGORY");
    private static final DBConnector dbconnector = DBConnector.getInstance();
    private static final Connection connection = dbconnector.getConnection();

    // CRUD - POST
    public boolean create(CategoryModel category) {
        String query = "INSERT INTO " + databaseTableCategory + " (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getName());

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
    public ArrayList<CategoryModel> findAll() {
        ArrayList<CategoryModel> categories = new ArrayList<>();

        String query = "SELECT * FROM " + databaseTableCategory;

        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                categories.add(new CategoryModel(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }
        } catch (Exception e) {
            System.err.println("GET error: " + e.getMessage());
        } finally {
            dbconnector.closeConnection();
        }

        return categories;
    }

    // CRUD - GET by ID
    public CategoryModel findById(int id) {
        CategoryModel category = null;

        String query = "SELECT * FROM " + databaseTableCategory + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                category = new CategoryModel(
                        result.getInt("id"),
                        result.getString("name")
                );
            }
        } catch (Exception e) {
            System.err.println("GET error: " + e.getMessage());
        } finally {
            dbconnector.closeConnection();
        }

        return category;
    }

    // CRUD - PUT
    public boolean update(CategoryModel category) {
        String query = "UPDATE " + databaseTableCategory + " SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                System.out.println("PUT - NOT UPDATED");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("PUT error: " + e.getMessage());
            return false;
        } finally {
            dbconnector.closeConnection();
        }
    }

    // CRUD - DELETE
    public boolean delete(int id) {
        CategoryModel category = findById(id);

        if (category == null) {
            return false;
        }

        String query = "DELETE FROM " + databaseTableCategory + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            } else {
                System.out.println("DELETE - NOT DELETED");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("DELETE error: " + e.getMessage());
            return false;
        } finally {
            dbconnector.closeConnection();
        }
    }

}
