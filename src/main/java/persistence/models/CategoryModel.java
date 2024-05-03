package persistence.models;

public class CategoryModel {
    private final int id;
    private String name;

    // Constructor
    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // To String
    @Override
    public String toString() {
        return this.name + " - " + this.id;
    }
}
