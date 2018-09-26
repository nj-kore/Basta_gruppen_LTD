package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();

    @FXML
    AnchorPane MainViewAnchorPane;

    @FXML
    FlowPane ContactsFlowPane;

    @FXML
    FlowPane ConversationsFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainViewAnchorPane.getChildren().add(chatView);
    }

}