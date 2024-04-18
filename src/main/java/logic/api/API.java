package logic.api;

import persistence.dao.ContactDao;
import persistence.models.ContactModel;

import java.util.ArrayList;

public class API {

    public static ArrayList<ContactModel> getAllContacts() {
        ContactDao contactDao = new ContactDao();
        return contactDao.findAll();
    }

    public void post() {
        System.out.println("POST request");
    }

    public void put() {
        System.out.println("PUT request");
    }

    public void delete() {
        System.out.println("DELETE request");
    }
}
