package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserPageView extends AnchorPane {

    MainView parent;

    @FXML
    TextField userNameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField oldPasswordTextField;

    @FXML
    TextField newPasswordTextField;

    public UserPageView(MainView parentView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/UserPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parentView;
    }

    @FXML
    public void backToMain(){
        parent.displayMainView();
    }
}
