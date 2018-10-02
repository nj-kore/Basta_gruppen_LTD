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

/**
 * The MainView is the main class of the view package. Linking all the different views together and forwards info
 * given to the class from the model package via the observer class.
 */

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


    /**
     * @param location
     * @param resources
     *
     * Initializes the class and loads the views that makes out the complete mainView.
     * Proceeds to show the loginpage to the user
     * Finally it adds itself as an observer to the model
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayChat();
        updateContactsList();
        updateConversationsList();
        displayLoginPage();

        //Don't really know if this is the way to do it, casting makes it unreplacable, but otherwise this goes into
        //the interface? which seems wrong.
        ((MainModel)mainModel).addObserver(this);

    }


    /**
     * @param o is the observable class that called the update method
     * @param arg
     *
     * Decodes the arg to see what kind of task the view should do
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof IMainModel) {
            if(arg instanceof String) {
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
        currentUserImageView.setImage(mainModel.getActiveUser().getProfileImage());
    }

    /**
     * Clears the contactFlowPane and fills it with new ContactListItems corresponding to different Users
     */
    public void updateContactsList() {
        contactsFlowPane.getChildren().clear();
        Iterator<User> iterator = mainModel.getContacts();
        while (iterator.hasNext()) {
            contactsFlowPane.getChildren().add(new ContactListItem(iterator.next()));
        }
    }



    /**
     * Clears the conversationsFlowPane and fills it with new ConversationListItems corresponding to
     * different Conversations.
     */
    public void updateConversationsList() {

        conversationsFlowPane.getChildren().clear();
        Iterator<Conversation> iterator = mainModel.getConversations();
        while(iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next()));
        }

    }

    @Override
    public void displayLoginPage() {
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(loginView);
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


    public void updateCurrentUserInfo(){
        currentUserImageView.setImage(mainModel.getActiveUser().getProfileImage()); //TODO denna fungerar tydligen inte
    }
}