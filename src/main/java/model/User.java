package model;

import java.util.ArrayList;

public class User {
    private int id;
    private ArrayList<User> contacts = new ArrayList<>();

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected void addContact(User usertoadd){contacts.add(usertoadd);}

    public ArrayList<User> getContacts(){return contacts;}

}
