package model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<User> contacts = new ArrayList<>();
    private String name;
    private String status;
    private Image statusImage;
    private Image profileImage;


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

    public Image getStatusImage(){return statusImage;}

    public String getName(){return name;}

    public ArrayList<User> getContacts(){return contacts;}

    public Image getProfileImage(){return profileImage;}

    public String getStatus(){return status;}


    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status){this.status=status;}

    public void setStatusImage(Image statusImage){
        this.statusImage = statusImage;
    }

    public void setProfileImage(Image profileImage){
        this.profileImage=profileImage;
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
