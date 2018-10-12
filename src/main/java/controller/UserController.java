package controller;

import javafx.scene.control.MenuItem;
import model.MainModel;
import view.MainView;
import view.UserToolbar;

public class UserController implements IUserController {
    private MainModel mainModel;
    private UserToolbar userToolbar;
    private MainView mainView;

    public UserController(UserToolbar userToolbar, MainModel mainModel, MainView mainView) {
        this.userToolbar = userToolbar;
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void onMenuButtonItemClicked(MenuItem m) {
        mainModel.setStatus(MainModel.StatusType.valueOf(m.getText().replaceAll(" ", "_")));
    }

    @Override
    public void onOoptionsButtonClicked() {
        mainView.displayUserPage();
    }
}
