package view;

import controller.ILoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.MainModel;

import java.io.IOException;

public class LoginView extends AnchorPane implements ILoginController {
    @FXML
    TextField userNameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Label wrongPasswordLabel;

    MainView parentView;

    public LoginView(MainView parentView) {
        this.parentView = parentView;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/LoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //userNameTextField.requestFocus(); //Gives focus to userNameTextField
        wrongPasswordLabel.setVisible(false);

    }


    @FXML
    public void keyPressed(KeyEvent e) {
        if(e.getCode().equals(KeyCode.ENTER)) {
            login();
            e.consume();
            }
        }

    @FXML
    public void login(){
        if (MainModel.getInstance().login(userNameTextField.getText(), passwordField.getText())){
            parentView.moveLoginToBack();
        } else{
            wrongPasswordLabel.setVisible(true);
        }
    }

}
