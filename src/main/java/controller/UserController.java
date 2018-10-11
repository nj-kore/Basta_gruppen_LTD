package controller;

import javafx.scene.control.MenuItem;
import model.MainModel;
import view.LoginView;
import view.MainView;
import view.UserView;

import java.awt.*;

public class UserController implements IUserController {
    private MainModel mainModel;
    private UserView userView;
    private MainView mainView;

    public UserController(UserView userView, MainModel mainModel, MainView mainView) {
        this.userView = userView;
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void onMenuButtonItemClicked(MenuItem m) {
        mainModel.setStatus(m.getText());
    }

    @Override
    public void onOoptionsButtonClicked() {
        mainView.displayUserPage();
    }
}
