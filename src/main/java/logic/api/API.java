package logic.api;

import persistence.dao.ContactDao;
import persistence.models.ContactModel;
import persistence.dao.CategoryDao;
import persistence.models.CategoryModel;


import java.util.ArrayList;

public class API {

    public static ArrayList<ContactModel> getContacts() {
        ContactDao contactDao = new ContactDao();
        System.out.println("API - GET ALL");
        return contactDao.findAll();
    }

    public static ArrayList<ContactModel> searchContacts(String name) {
        ContactDao contactDao = new ContactDao();
        System.out.println("API - SEARCH");
        return contactDao.findByName(name);
    }

    public static void postContact(ContactModel contact) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.create(contact)) {
            System.out.println("API - CREATED");
        } else {
            System.out.println("API - NOT CREATED");
        }
    }

    public static void putContact(int id, ContactModel contact) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.update(id, contact)) {
            System.out.println("API - UPDATED");
        } else {
            System.out.println("API - NOT UPDATED");
        }
    }

    public static void deleteContact(int id) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.delete(id)) {
            System.out.println("API - DELETED");
        } else {
            System.out.println("API - NOT DELETED");
        }
    }

    // category
    public static ArrayList<CategoryModel> getCategories() {
        CategoryDao categoryDao = new CategoryDao();
        System.out.println("API - GET ALL");
        return categoryDao.findAll();
    }

    public static void postCategory(CategoryModel category) {
        CategoryDao categoryDao = new CategoryDao();

        if (categoryDao.create(category)) {
            System.out.println("API - CREATED");
        } else {
            System.out.println("API - NOT CREATED");
        }
    }

    public static void putCategory(int id, CategoryModel category) {
        CategoryDao categoryDao = new CategoryDao();

        if (categoryDao.update(id, category)) {
            System.out.println("API - UPDATED");
        } else {
            System.out.println("API - NOT UPDATED");
        }
    }

    public static void deleteCategory(int id) {
        CategoryDao categoryDao = new CategoryDao();

        if (categoryDao.delete(id)) {
            System.out.println("API - DELETED");
        } else {
            System.out.println("API - NOT DELETED");
        }
    }
}
