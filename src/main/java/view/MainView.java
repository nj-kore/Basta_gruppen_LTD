package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
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

public class MainView extends AnchorPane implements Initializable, IMainController, IMainView, Observer {

    IMainController mainController;
    private IMainModel mainModel = new MainModel();
    ChatView chatView = new ChatView(this, mainModel);
    LoginView loginView = new LoginView(this, mainModel);
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

        //Don't really know if this is the way to do it, casting makes it unreplacable, but otherwise this goes into
        //the interface? which seems wrong.
        ((MainModel)mainModel).addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof IMainModel) {
            switch((String)arg) {
                case "ACTIVE_CONVERSATION":
                    chatView.loadMessages();
                    break;
                case "CONTACTS":
                    updateContactsList();
                    break;
                case "CONVERSATIONS":
                    updateConversationsList();
                    break;
            }
        }
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
        for (Map.Entry<Integer, Conversation> conversation : mainModel.getConversations().entrySet()) {
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
    @FXML
    @Override
    public void displayUserPage() {
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(userPage);
        loginHBox.toFront();
    }

    @Override
    public void displayMainView() {
        loginHBox.toBack();
    }

}