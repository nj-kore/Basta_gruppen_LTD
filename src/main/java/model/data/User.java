package model.data;

import javafx.scene.image.Image;

import java.util.ArrayList;


public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<User> contacts = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private Image statusImage;
    private Image profileImage;


    public User(int id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        profileImage = new Image("pics/userIcon.png");
    }


    //Getters
    public int getId() {
        return id;
    }

    public Image getStatusImage(){return statusImage;}

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<User> getContacts(){return contacts;}

    public Image getProfileImage(){return profileImage;}

    public String getStatus(){return status;}

    public String getUsername() {
        return username;
    }

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
    // TODO: 02/10/2018  Move this
    public boolean confirmPassword(String password){
        if (this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public void addContact(User userToAdd){contacts.add(userToAdd);}



}
