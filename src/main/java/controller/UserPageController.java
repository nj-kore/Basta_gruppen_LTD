package controller;

import model.MainModel;
import view.MainView;

public class UserPageController implements IUserPageController {
    private MainModel mainModel;
    private MainView mainView;

    public UserPageController(MainModel mainModel, MainView mainview) {
        this.mainModel = mainModel;
    }

    public void saveUserInfo(String fn, String ln, String email, String picURL) {
        mainModel.setUserInfo(fn, ln, email, picURL);
    }

    public void changePassword(String s){
        mainModel.changePassword(s);
    }

    //public void changeProfilePicture(String picURL){
        //mainModel.getActiveUser().setProfileImagePath(picURL);
    //}
}
