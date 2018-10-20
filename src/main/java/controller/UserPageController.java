package controller;

import model.MainModel;
import view.IMainView;
import view.MainView;

public class UserPageController implements IUserPageController {
    private MainModel mainModel;

    public UserPageController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void saveUserInfo(String fn, String ln, String email, String picURL) {
        mainModel.setUserInfo(fn, ln, email, picURL);
    }

    public void changePassword(String s){
        mainModel.changePassword(s);
    }


}
