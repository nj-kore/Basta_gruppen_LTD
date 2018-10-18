package view;

import controller.ILoginController;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.MainModel;

import java.io.IOException;

public class LoginView extends AnchorPane implements ILoginView {

    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Label wrongPasswordLabel;

    /**
     * @param mainModel Loads the fxml document and assigns the LoginController to handle the relevant input
     */
    public LoginView(MainModel mainModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        //The following lines creates a controller that we can temporarily use to assign functions to
        ILoginController c = new LoginController(this, mainModel);

        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.onLoginButtonClicked();
            }
        });
        loginAnchorPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                c.onKeyPressed(event);
            }
        });
        //userNameTextField.requestFocus(); //Gives focus to userNameTextField
        wrongPasswordLabel.setVisible(false);

    }

    @Override
    public void showWrongInputNotification() {
        wrongPasswordLabel.setVisible(true);
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public String getUsername() {
        return userNameTextField.getText();
    }

    @Override
    public void clearTextFields() {
        userNameTextField.clear();
        passwordField.clear();
    }
}
