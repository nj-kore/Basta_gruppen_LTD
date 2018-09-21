package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable{

    ChatView chatController;

    User currentUser = new User(1);

    @FXML
    AnchorPane MainViewAnchorPane;

    @FXML
    FlowPane ContactsFlowPane;

    @FXML
    FlowPane ConversationsFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatController = new ChatView(this);
        MainViewAnchorPane.getChildren().add(chatController);
    }

}