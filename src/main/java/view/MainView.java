package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.data.Conversation;
import model.IMainModel;
import model.MainModel;
import model.data.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController, IMainView{

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
        displayChat();
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(loginView);

        updateContactsList();
        updateConversationsList();
        //loadConversations();
    }

    public void moveLoginToBack(){
        loginHBox.toBack();
    }

    public void updateContactsList() {


        contactsFlowPane.getChildren().clear();

        for (User user : mainModel.getContacts()) {

            ContactListItem contactListItemView = new ContactListItem(user);
            contactsFlowPane.getChildren().add(contactListItemView);
        }

    }

    public void updateConversationsList() {

        conversationsFlowPane.getChildren().clear();
        for (Map.Entry<Integer, Conversation> conversation : MainModel.getInstance().getConversations().entrySet()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(conversation.getValue()));
        }

    }

    @Override
    public void displayContacts() {
        updateContactsList();
    }

    @Override
    public void displayConversations() {
        updateConversationsList();
    }

    @Override
    public void displayChat() {
        mainViewAnchorPane.getChildren().add(chatView);
    }

    @Override
    public void displaySettings() {

    }

    @Override
    public void displayUserPage() {

    }
}