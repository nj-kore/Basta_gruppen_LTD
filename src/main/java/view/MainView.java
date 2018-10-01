package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.data.Conversation;
import model.IMainModel;
import model.MainModel;
import model.data.User;

import java.net.URL;
import java.util.*;

public class MainView extends AnchorPane implements Initializable, IMainController, IMainView{

    ChatView chatView = new ChatView();
    LoginView loginView = new LoginView(this);
    IMainController mainController;
    private IMainModel mainModel = MainModel.getInstance();
    UserPageView userPage = new UserPageView(this);

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

    @FXML
    ImageView currentUserImageView;

    @FXML
    ImageView statusImageView;




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

    public void toMainView(){
        loginHBox.toBack();
    }

    public void updateContactsList() {
        contactsFlowPane.getChildren().clear();
        Iterator<User> iterator = mainModel.getContacts();
        while (iterator.hasNext()){
            contactsFlowPane.getChildren().add(new ContactListItem(iterator.next()));
        }
    }

    @FXML
    public void toUserPage(){
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(userPage);
        loginHBox.toFront();
    }


    public void updateConversationsList() {

        conversationsFlowPane.getChildren().clear();
        Iterator<Conversation> iterator = mainModel.getConversations();
        while(iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next()));
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