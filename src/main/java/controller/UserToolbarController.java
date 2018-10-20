package controller;

import javafx.scene.control.MenuItem;
import model.MainModel;
import model.StatusType;
import view.IMainView;

public class UserToolbarController implements IUserToolbarController {
    private MainModel mainModel;
    private IMainView mainView;

    public UserToolbarController(MainModel mainModel, IMainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void onMenuButtonItemClicked(String m) {
        mainModel.setStatus(StatusType.valueOf(m.replaceAll(" ", "_")));
    }

    @Override
    public void onOptionsButtonClicked() {
        mainView.displayUserPage();
    }
}
