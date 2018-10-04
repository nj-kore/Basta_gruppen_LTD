package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.data.Conversation;
import model.IMainModel;
import model.MainModel;
import model.data.User;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * The MainView is the main class of the view package. Linking all the different views together and forwards info
 * given to the class from the model package via the observer class.
 */

public class MainView extends AnchorPane implements Initializable, IMainController, IMainView, Observer {

    private IMainModel mainModel = new MainModel();
    ChatView chatView = new ChatView(this, mainModel);
    LoginView loginView = new LoginView(this, mainModel);
    UserPageView userPage = new UserPageView(this);
    ArrayList<NewConvoContactListItem> newConvoListItems = new ArrayList<>();
    User detailedUser;

    //contactDetailView
    @FXML
    AnchorPane contactDetailView;

    @FXML
    ImageView contactDetailViewProfilemageView;

    @FXML
    ImageView contactDetailViewStatusImageView;

    @FXML
    Label contactDetailViewStatusLabel;

    @FXML
    Label contactDetailViewNameLabel;

    //newConversationView
    @FXML
    AnchorPane createConvoView;

    @FXML
    FlowPane newConvoContactPane;

    @FXML
    FlowPane newConvoConvoPane;

    @FXML
    Button newConvoCreateConvoButton;

    @FXML
    Button newConvoMoveUsersButton;

    @FXML
    Button newConvoCloseButton;


    //mainView
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
     * @param resources Initializes the class and loads the views that makes out the complete mainView.
     *                  Proceeds to show the loginpage to the user
     *                  Finally it adds itself as an observer to the model
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ((MainModel) mainModel).addObserver(this);
        displayLoginPage();
        mainModel.login("admin", "123");

        //chatView.startTiming();

        //Don't really know if this is the way to do it, casting makes it unreplacable, but otherwise this goes into
        //the interface? which seems wrong.

    }


    /**
     * @param o   is the observable class that called the update method
     * @param arg Decodes the arg to see what kind of task the view should do
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof IMainModel) {
            if (arg instanceof String) {
                switch ((String) arg) {
                    case "ACTIVE_CONVERSATION":
                        chatView.loadMessages();
                        break;
                    case "CONTACTS":
                        updateContactsList();
                        break;
                    case "CONVERSATIONS":
                        updateConversationsList();
                        break;
                    case "INIT":
                        displayMainView();
                        displayChat();
                        chatView.loadMessages();
                        updateContactsList();
                        //updateCreateNewConvoLists();
                        break;
                }
            }
        }
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

    /**
     * Clears the contactFlowPane and fills it with new ContactListItems corresponding to different Users
     */
    public void updateContactsList() {
        contactsFlowPane.getChildren().clear();
        Iterator<User> iterator = mainModel.getContacts();
        while (iterator.hasNext()) {
            contactsFlowPane.getChildren().add(new ContactListItem(iterator.next(), this));
        }
    }


    /**
     * Clears the conversationsFlowPane and fills it with new ConversationListItems corresponding to
     * different Conversations.
     */
    public void updateConversationsList() {

        conversationsFlowPane.getChildren().clear();
        Iterator<Conversation> iterator = mainModel.getConversations();
        while (iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next()));
        }

    }

    @Override
    public void displayLoginPage() {
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

    public void updateContactsList(ArrayList<User> contacts) {

        contactsFlowPane.getChildren().clear();

        for (User user : contacts) {

            ContactListItem contactListItemView = new ContactListItem(user, this);
            contactsFlowPane.getChildren().add(contactListItemView);
        }
    }

    public void updateCurrentUserInfo() {
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath())); //TODO denna fungerar tydligen inte
    }


    private void updateCreateNewConvoLists() {
        newConvoContactPane.getChildren().clear();
        newConvoConvoPane.getChildren().clear();
        Iterator<User> itr = mainModel.getContacts();
        while (itr.hasNext()) {
            NewConvoContactListItem newConvoContact = new NewConvoContactListItem(itr.next());
            newConvoContactPane.getChildren().add(newConvoContact);
            newConvoListItems.add(newConvoContact);
        }
    }


    //createNewConvo functionality
    @FXML
    private void newConvoMoveUsersButtonClicked() {
        ArrayList<NewConvoContactListItem> newConvoListItemsCopy = new ArrayList<>(newConvoListItems);

        for (NewConvoContactListItem newConvoContactListItem : newConvoListItemsCopy) {

            if (newConvoContactListItem.isFocused) {

                if (newConvoContactListItem.isAdded) {

                    newConvoListItems.remove(newConvoContactListItem);
                    newConvoConvoPane.getChildren().remove(newConvoContactListItem);
                    newConvoContactPane.getChildren().add(newConvoContactListItem);
                    newConvoContactListItem.isAdded = false;
                } else {

                    newConvoContactPane.getChildren().remove(newConvoContactListItem);
                    newConvoListItems.add(newConvoContactListItem);
                    newConvoConvoPane.getChildren().add(newConvoContactListItem);
                    newConvoContactListItem.isAdded = true;
                }
                newConvoContactListItem.isFocused = false;
                newConvoContactListItem.setStyle("-fx-background-color: #f7efef");
            }
        }
    }

    @FXML
    private void newConvoCreateConvoButtonClicked() {
        ArrayList<User> users = new ArrayList<>();
        for (NewConvoContactListItem newConvoContactListItem : newConvoListItems) {
            User user = newConvoContactListItem.getUser();
            users.add(user);
        }
        mainModel.createConversation(users);
    }

    @FXML
    private void newConvoButtonClicked() {
        createConvoView.toFront();
        updateCreateNewConvoLists();
    }

    @FXML
    public void newConvoCloseButtonClicked() {
        createConvoView.toBack();
    }

    //contactDetailView functionality
    @FXML
    public void contactDetailViewCloseButtonClicked() {
        contactDetailView.toBack();
    }

    @FXML
    public void contactDetailViewCreateConvoButtonClicked() {
        ArrayList<User> users = new ArrayList<>();
        users.add(detailedUser);
        mainModel.createConversation(users);
    }

    public void loadDetailView(User user) {
        this.contactDetailViewNameLabel.setText(user.getFullName());
        this.contactDetailViewProfilemageView.setImage(null); //TODO setImage from user
        this.contactDetailViewStatusImageView.setImage(null); //TODO setImage from user
        this.contactDetailViewStatusLabel.setText("active"); //TODO setText from user
        contactDetailView.toFront();
    }


}