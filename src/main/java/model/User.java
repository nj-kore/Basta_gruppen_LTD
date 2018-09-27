package model;

import java.util.ArrayList;

public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<User> contacts = new ArrayList<>();
    private String name;


    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean confirmPassword(String password){
        if (this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public String getName(){return name;}

    protected void addContact(User userToAdd){contacts.add(userToAdd);}

    public ArrayList<User> getContacts(){return contacts;}

}
