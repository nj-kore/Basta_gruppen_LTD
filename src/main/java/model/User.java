package model;

import java.util.ArrayList;

public class User {
    private int id;
    private ArrayList<User> contacts = new ArrayList<>();
    private String name;


    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){return name;}

    protected void addContact(User userToAdd){contacts.add(userToAdd);}

    public ArrayList<User> getContacts(){return contacts;}

}
