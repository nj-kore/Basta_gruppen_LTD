package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import view.ILoginView;

public class LoginController implements ILoginController {
    private MainModel mainModel;
    private ILoginView loginView;

    public LoginController(ILoginView loginView, MainModel mainModel) {
        this.loginView = loginView;
        this.mainModel = mainModel;
    }

    @Override
    public void onLoginButtonClicked() {
        if (!mainModel.login(loginView.getUsername(), loginView.getPassword())) {
            loginView.showWrongInputNotification();
        }else {
            loginView.clearTextFields();
        }
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            onLoginButtonClicked();
            event.consume();
        }
    }

}
