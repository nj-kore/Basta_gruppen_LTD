package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Conversation;
import model.IMainModel;
import model.MainModel;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    LoginView loginView = new LoginView(this);
    IMainController mainController;
    private IMainModel mainModel = MainModel.getInstance();

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
        updateContactsList(mainModel.getContacts());
        updateConversationsList(mainModel.getConversations());
        //loadConversations();
    }

    public void moveLoginToBack(){
        loginHBox.toBack();
    }

    public void updateContactsList(ArrayList<User> contacts) {

        contactsFlowPane.getChildren().clear();

        for (User user : contacts) {

            ContactListItem contactListItemView = new ContactListItem(user);
            contactsFlowPane.getChildren().add(contactListItemView);
        }

    }

    public void updateConversationsList(HashMap<Integer, Conversation> conversations) {

        conversationsFlowPane.getChildren().clear();
        for (Map.Entry<Integer, Conversation> conversation : conversations.entrySet()) {

            ConversationListItem conversationListItem = new ConversationListItem(conversation.getValue());
            conversationsFlowPane.getChildren().add(conversationListItem);
        }

    }

}