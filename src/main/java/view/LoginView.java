package view;

import controller.ILoginController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginView extends AnchorPane implements ILoginController {
    @FXML
    TextField userNameTextField;
    @FXML
    PasswordField passwordPasswordField;
}
