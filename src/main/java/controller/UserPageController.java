package controller;

import model.MainModel;
import view.MainView;

public class UserPageController implements IUserPageController {
    private MainModel mainModel;
    private MainView mainView;

    public UserPageController(MainModel mainModel, MainView mainview) {
        this.mainModel = mainModel;
    }

    public void saveUserInfo(String fn, String ln, String email) {
        mainModel.setUserInfo(fn, ln, email);
    }

    public void changePassword(String s){
        mainModel.getActiveUser().setPassword(s);
    }

    public void changeProfilePicture(String picURL){
        mainModel.getActiveUser().setProfileImagePath(picURL);
    }
}
