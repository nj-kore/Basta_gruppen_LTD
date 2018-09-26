package model;

public class User {
    private int id;
    private String password;

    public User(int id) {
        this.id = id;
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
}
