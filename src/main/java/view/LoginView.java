package view;

import controller.ILoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.MainModel;

import java.io.IOException;

public class LoginView extends AnchorPane implements ILoginController {
    @FXML
    TextField userNameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;

    public LoginView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/LoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void login(){
        if (MainModel.getInstance().login(userNameTextField.getText(), passwordField.getText())){
            System.out.println("ree");
        }
    }
}
