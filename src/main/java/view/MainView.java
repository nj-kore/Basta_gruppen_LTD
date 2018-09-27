package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.MainModel;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    LoginView loginView = new LoginView(this);

    @FXML
    AnchorPane mainViewAnchorPane;

    @FXML
    FlowPane contactsFlowPane;

    @FXML
    FlowPane conversationsFlowPane;

    @FXML
    HBox mainViewHBox;

    @FXML
    HBox loginHBox;

    @FXML
    StackPane mainViewStackPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mainViewAnchorPane.getChildren().add(chatView);
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(loginView);
        loadContacts();
    }

    public void moveLoginToBack(){
        loginHBox.toBack();
    }

    public void loadContacts(){
        for(User u: MainModel.getInstance().getContacts()){
            contactsFlowPane.getChildren().add(new ContactListItem(u));
        }
    }

}