package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import view.LoginView;

public class LoginController implements ILoginController {
    private MainModel mainModel;
    private LoginView loginView;

    public LoginController(LoginView loginView, MainModel mainModel) {
        this.loginView = loginView;
        this.mainModel = mainModel;
    }

    @Override
    public void onLoginButtonClicked() {
        if (!mainModel.login(loginView.getUsername(), loginView.getPassword())) {
            loginView.showWrongInputLabel();
        }else loginView.clearTextFields();      //TODO funkar inte
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            onLoginButtonClicked();
            event.consume();
        }
    }

}
