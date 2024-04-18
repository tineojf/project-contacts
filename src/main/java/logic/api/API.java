package logic.api;

import persistence.dao.ContactDao;
import persistence.models.ContactModel;

import java.util.ArrayList;

public class API {

    public static ArrayList<ContactModel> getAllContacts() {
        ContactDao contactDao = new ContactDao();
        return contactDao.findAll();
    }

    public static void post(ContactModel contact) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.create(contact)) {
            System.out.println("POST - CREATED");
        } else {
            System.out.println("POST - NOT CREATED");
        }
    }

    public static void put() {
        System.out.println("PUT request");
    }

    public static void delete(int id) {
        ContactDao contactDao = new ContactDao();

        if (contactDao.delete(id)) {
            System.out.println("DELETE - DELETED");
        } else {
            System.out.println("DELETE - NOT DELETED");
        }
    }
}
