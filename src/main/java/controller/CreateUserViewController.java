package controller;


import model.MainModel;
import view.CreateUserView;
import view.MainView;

public class CreateUserViewController implements ICreateUserViewController {
    private MainView mainView;
    private MainModel mainModel;
    private CreateUserView createUserView;

    public CreateUserViewController(MainModel mainModel, MainView mainView, CreateUserView createUserView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
        this.createUserView = createUserView;

    }

    public void cancelCreateUser() {
        mainView.backToChat();
    }



    public void createUser(){
        String u = createUserView.getUserName();
        String fn = createUserView.getFirstName();
        String ln = createUserView.getlastName();
        String pw = createUserView.getPassword();
        Boolean isAdmin = createUserView.getAdminCheckBox();
        mainModel.createUserForController(u, pw, fn, ln, isAdmin);
        mainView.backToChat();

    }

    private Boolean checkInput() {
        if (createUserView.getUserName() != "" && createUserView.getFirstName() != "" && createUserView.getlastName() != ""
                && createUserView.getPassword() != "") {
            return true;
        }
        return false;
    }

}
