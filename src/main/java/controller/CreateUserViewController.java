package controller;


import model.MainModel;
import view.ICreateUserView;
import view.IMainView;

public class CreateUserViewController implements ICreateUserViewController {
    private IMainView mainView;
    private MainModel mainModel;
    private ICreateUserView createUserView;

    public CreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
        this.createUserView = createUserView;

    }

    public void cancelCreateUser() {
        mainView.displayChat();
    }



    public void createUser(){
        String userName = createUserView.getUserName();
        String firstName = createUserView.getFirstName();
        String surname = createUserView.getlastName();
        String password = createUserView.getPassword();
        Boolean isAdmin = createUserView.getAdminCheckBox();
        mainModel.createUser(userName, password, firstName, surname, isAdmin);
        mainView.displayChat();

    }


}
