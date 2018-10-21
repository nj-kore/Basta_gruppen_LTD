package controller;

import model.MainModel;


public class UserPageController implements IUserPageController {
    private MainModel mainModel;

    public UserPageController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void saveUserInfo(String firstName, String surname, String email, String picURL) {
        mainModel.setUserInfo(firstName, surname, email, picURL);
    }

    public void changePassword(String s){
        mainModel.changePassword(s);
    }


}
