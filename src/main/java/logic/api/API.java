package logic.api;

import persistence.dao.ContactDao;
import persistence.models.ContactModel;

import java.util.ArrayList;

public class API {

    public static ArrayList<ContactModel> get() {
        ContactDao contactDao = new ContactDao();
        System.out.println("API - GET ALL");
        return contactDao.findAll();
    }

    public static ArrayList<ContactModel> search(String name) {
        ContactDao contactDao = new ContactDao();
        System.out.println("API - SEARCH");
        return contactDao.findByName(name);
    }

    public static void post(ContactModel contact) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.create(contact)) {
            System.out.println("API - CREATED");
        } else {
            System.out.println("API - NOT CREATED");
        }
    }

    public static void put(int id, ContactModel contact) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.update(id, contact)) {
            System.out.println("API - UPDATED");
        } else {
            System.out.println("API - NOT UPDATED");
        }
    }

    public static void delete(int id) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.delete(id)) {
            System.out.println("API - DELETED");
        } else {
            System.out.println("API - NOT DELETED");
        }
    }
}
