package model.data;

import javafx.scene.image.Image;

import java.util.ArrayList;


public class User {
    private int id;
    private String password;
    private String username;
    private ArrayList<Integer> contacts = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String statusImagePath;
    private String profileImagePath;


    public User(int id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        profileImagePath = "pics/userIcon.png";
        statusImagePath = "pics/userIcon.png";

    }


    //Getters
    public int getId() {
        return id;
    }

    public String getStatusImagePath(){return statusImagePath;}

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

    public ArrayList<Integer> getContacts(){return contacts;}

    public String getProfileImagePath(){return profileImagePath;}

    public String getStatus(){return status;}

    public String getUsername() {
        return username;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status){this.status=status;}

    public void setStatusImage(String statusImagePath){
        this.statusImagePath = statusImagePath;
    }

    public void setProfileImagePath(String profileImagePath){
        this.profileImagePath=profileImagePath;
    }


    /**
     * Checks if parameter password equals the users password.
     * @param password
     * @return
     * true if password matches
     * false otherwise
     */
    //Functionality
    // TODO: 02/10/2018  Move this
    public boolean confirmPassword(String password){
        if (this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * adds userToAdd to contacts.
     * @param userToAdd
     */
    public void addContact(int userToAdd){contacts.add(userToAdd);}



}
