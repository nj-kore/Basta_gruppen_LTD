package model.data;

import java.util.ArrayList;

public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<User> contacts = new ArrayList<>();
    private String name;


    public User(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
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

    public void addContact(User userToAdd){contacts.add(userToAdd);}

    public ArrayList<User> getContacts(){return contacts;}

}
