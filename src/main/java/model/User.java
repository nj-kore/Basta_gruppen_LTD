package model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<User> contacts = new ArrayList<>();
    private String name;
    private Image statusImage;


    public User(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }


    //Getters
    public int getId() {
        return id;
    }

    public Image getStatusImage(){
        return statusImage;
    }

    public String getName(){return name;}

    public ArrayList<User> getContacts(){return contacts;}


    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStatusImage(Image statusImage){
        this.statusImage = statusImage;
    }


    //Functionality
    public boolean confirmPassword(String password){
        if (this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    protected void addContact(User userToAdd){contacts.add(userToAdd);}



}
