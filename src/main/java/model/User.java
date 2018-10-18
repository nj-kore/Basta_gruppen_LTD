package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {
    private int id;
    private String password;
    private String username;
    private List<Integer> contacts = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private StatusType status;
    private String profileImagePath;
    private String[] statusPaths = new String[3];
    private Boolean isManager;


    public User(int id, String username, String password, String firstName, String lastName, StatusType status, Boolean isManager) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        profileImagePath = "pics/userIcon.png";
        statusPaths[0] = "pics/statusGreen.png";
        statusPaths[1] = "pics/statusOrange.png";
        statusPaths[2] = "pics/statusRed.png";
        this.status = status;
        this.isManager = isManager;

    }


    //Getters
    public int getId() {
        return id;
    }

    public String getStatusImagePath(){
        switch (status){
            case Do_not_disturb: return statusPaths[2];
            case Busy: return statusPaths[1];
            default: return statusPaths[0];
        }
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return password;
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

    protected List<Integer> getContacts(){return contacts;}

    public String getProfileImagePath(){return profileImagePath;}

    public StatusType getStatus(){return status;}

    public String getUsername() {
        return username;
    }

    public Boolean getIsManager(){
        return isManager;
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

    public void setStatus(StatusType status){
        this.status = status;
    }

    public void setProfileImagePath(String profileImagePath){
        this.profileImagePath=profileImagePath;

    }


    /**
     * adds userToAdd to contacts.
     * @param userToAdd
     */
    public void addContact(int userToAdd){contacts.add(userToAdd);}

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        User user = (User) o;

        if (id != user.id) {return false;}
        if (!password.equals(user.password)) {return false;}
        if (!username.equals(user.username)) {return false;}
        if (contacts != null ? !contacts.equals(user.contacts) : user.contacts != null) {return false;}
        if (!firstName.equals(user.firstName)) {return false;}
        if (!lastName.equals(user.lastName)) {return false;}
        if (email != null ? !email.equals(user.email) : user.email != null) {return false;}
        return status != null ? status.equals(user.status) : user.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + password.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + contacts.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + status.hashCode();
        result = 31 * result + profileImagePath.hashCode();
        result = 31 * result + Arrays.hashCode(statusPaths);
        return result;
    }
}
