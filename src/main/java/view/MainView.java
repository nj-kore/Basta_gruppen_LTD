package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Conversation;
import model.MainModel;
import model.User;

import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The MainView is the main class of the view package. Linking all the different views together and forwards info
 * given to the class from the model package via the observer class.
 */

public class MainView extends AnchorPane implements Initializable, IMainController, IMainView, Observer {

    private MainModel mainModel;
    private ChatView chatView;
    private LoginView loginView;
    private UserPageView userPage;
    private ArrayList<NewConvoContactListItem> newConvoListItems;
    private User detailedUser;

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

    @FXML
    Button newConvoSaveNameButton;

    @FXML
    TextField newConvoSaveNameTextField;

    @FXML
    Label newConvoSaveNameLabel;


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
        displayLoginPage();

        //chatView.startTiming();

        //Don't really know if this is the way to do it, casting makes it unreplacable, but otherwise this goes into
        //the interface? which seems wrong.

    }

    public MainView(MainModel mainModel){

        this.mainModel = mainModel;
        this.chatView = new ChatView(this, mainModel);
        this.loginView = new LoginView(this, mainModel);
        this.userPage = new UserPageView(this, mainModel);
        this.newConvoListItems = new ArrayList<>();

    }


    /**
     * @param o   is the observable class that called the update method
     * @param arg Decodes the arg to see what kind of task the view should do
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MainModel) {
            switch ((MainModel.UpdateTypes)arg) {
                case ACTIVE_CONVERSATION:
                    chatView.loadMessages();
                    break;
                case CONTACTS:
                    updateContactsList();
                    break;
                case CONVERSATIONS:
                    updateConversationsList();
                    break;
                case INIT:
                    displayMainView();
                    //Cant be run in Init since there are no conversations yet
                    displayChat();
                    chatView.loadMessages();
                    updateContactsList();
                    updateConversationsList();
                    //updateCreateNewConvoLists();
                    break;
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
        Iterator<Conversation> iterator = mainModel.getConversations().values().iterator();
        while (iterator.hasNext()) {
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

        ArrayList<Node> paneList = new ArrayList<>();
        paneList.addAll(newConvoConvoPane.getChildren());
        paneList.addAll(newConvoContactPane.getChildren());

        for (Node node : paneList) {

            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;

            if (newConvoContactListItem.isClicked()) {

                if (newConvoContactPane.getChildren().contains(node)) {

                    newConvoContactPane.getChildren().remove(node);
                    newConvoConvoPane.getChildren().add(newConvoContactListItem);
                } else {

                    newConvoConvoPane.getChildren().remove(node);
                    newConvoContactPane.getChildren().add(newConvoContactListItem);
                }
                newConvoContactListItem.setClicked(false);
                newConvoContactListItem.setStyle("-fx-background-color: aqua");
            }
        }
    }

    @FXML
    private void newConvoCreateConvoButtonClicked() {
        ArrayList<User> users = new ArrayList<>();
        for (Node node : newConvoContactPane.getChildren()) {
            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;
            users.add(newConvoContactListItem.getUser());
        }
        users.add(mainModel.getActiveUser());
        mainModel.createConversation(users, newConvoSaveNameTextField.getText());
        createConvoView.toBack();
        updateConversationsList();
    }

    @FXML
    private void newConvoSaveNameButtonClicked() {
        if(!newConvoSaveNameTextField.getText().isEmpty()) {
            newConvoCreateConvoButton.setDisable(false);
        } else {
            newConvoSaveNameLabel.setText("Conversation needs to have a name");
            newConvoSaveNameLabel.setStyle("-fx-background-color: #FF0000");
        }
    }

    @FXML
    private void newConvoButtonClicked() {
        newConvoCreateConvoButton.setDisable(true);
        newConvoSaveNameLabel.setText("Choose a name for the conversation:");
        newConvoSaveNameLabel.setStyle("");
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
        users.add(mainModel.getActiveUser());
        mainModel.createConversation(users, detailedUser.getFullName());
        contactDetailView.toBack();
    }

    public void loadDetailView(User user) {
        this.contactDetailViewNameLabel.setText(user.getFullName());
        Image profileImage = new Image(user.getProfileImagePath());
        this.contactDetailViewProfilemageView.setImage(profileImage);
        this.contactDetailViewStatusImageView.setImage(new Image(user.getStatusImagePath()));
        this.contactDetailViewStatusLabel.setText(user.getStatus());
        contactDetailView.toFront();
    }


}