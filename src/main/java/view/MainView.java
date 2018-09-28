package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Conversation;
import model.IMainModel;
import model.MainModel;
import model.User;
import java.net.URL;
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
        loadContacts();
        //loadConversations();
    }

    public void moveLoginToBack(){
        loginHBox.toBack();
    }

    public void loadContacts(){
        for(User u: MainModel.getInstance().getContacts()){
            contactsFlowPane.getChildren().add(new ContactListItem(u));
        }
    }
    public void loadConversation(){
        //for(User u: MainModel.getInstance().getConversations()){

        //}
    }

    public void updateContactsList(HashMap<Integer, User> contacts) {

        contactsFlowPane.getChildren().clear();

        for (Map.Entry<Integer, User> user : contacts.entrySet()) {

            ContactListItem contactListItemView = new ContactListItem(user.getValue());
            contactsFlowPane.getChildren().add(contactListItemView);
        }

    }

    public void updateConversationsList(HashMap<Integer, Conversation> conversations) {

        conversationsFlowPane.getChildren().clear();
        ConversationListItemView conversationListItemView = new ConversationListItemView(conversations);
        contactsFlowPane.getChildren().add(conversationListItemView);

    }

}