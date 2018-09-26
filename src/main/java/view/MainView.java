package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    IMainController mainController;
    LoginView loginView = new LoginView();

    @FXML
    AnchorPane MainViewAnchorPane;

    @FXML
    FlowPane ContactsFlowPane;

    @FXML
    FlowPane ConversationsFlowPane;

    @FXML
    HBox mainViewHBox;

    @FXML
    HBox loginHBox;

    @FXML
    StackPane mainViewStackPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        MainViewAnchorPane.getChildren().add(chatView);
        mainViewStackPane.getChildren().clear();
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().add(loginView);
    }

}