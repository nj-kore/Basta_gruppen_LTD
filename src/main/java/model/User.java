package model;

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
    private String[] premadeStatuses = {"AVAILABLE", "BUSY", "MATEMATISK"};
    private ArrayList<String> statuses = new ArrayList<>();
    private String statusImagePath;
    private String profileImagePath;
    private String[] statusPaths = new String[3];


    public User(int id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        profileImagePath = "pics/userIcon.png";
        statusImagePath = "pics/userIcon.png";
        statusPaths[0] = "pics/statusGreen.png";
        statusPaths[1] = "pics/statusOrange.png";
        statusPaths[2] = "pics/statusRed.png";

    }


    //Getters
    public int getId() {
        return id;
    }

    public String getStatusImagePath(){return statusImagePath;}

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return password;
    }

    public String[] getPremadeStatuses(){
        return premadeStatuses;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    protected ArrayList<Integer> getContacts(){return contacts;}

    public String getProfileImagePath(){return profileImagePath;}

    public String getStatus(){return status;}

    public String getUsername() {
        return username;
    }

    //Setters
    //Todo make proctected when possible
    public void setPassword(String password) {
        this.password = password;
    }

    //Todo make proctected when possible
    public void setEmail(String email) {
        this.email = email;
    }

    //Todo make proctected when possible
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Todo make proctected when possible
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status){this.status=status;

    }

    public void setStatusImagePath(String statusImagePath){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!password.equals(user.password)) return false;
        if (!username.equals(user.username)) return false;
        if (contacts != null ? !contacts.equals(user.contacts) : user.contacts != null) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return status != null ? status.equals(user.status) : user.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + password.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
