package controller;


import model.MainModel;
import view.CreateUserView;
import view.ICreateUserView;
import view.IMainView;
import view.MainView;

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
        mainView.backToChat();
    }



    public void createUser(){
        String u = createUserView.getUserName();
        String fn = createUserView.getFirstName();
        String ln = createUserView.getlastName();
        String pw = createUserView.getPassword();
        Boolean isAdmin = createUserView.getAdminCheckBox();
        mainModel.createUser(u, pw, fn, ln, isAdmin);
        mainView.backToChat();

    }


}
