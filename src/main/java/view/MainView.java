package view;

import controller.IMainController;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

public class MainView extends AnchorPane implements Initializable, IMainView, Observer {

    private MainModel mainModel;
    private ChatView chatView;
    private LoginView loginView;
    private CreateConvoView createConvoView;
    private UserPageView userPage;
    private User detailedUser;
    private UserToolbar userToolbar;

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
    HBox createConvoHBox;

    @FXML
    StackPane mainViewStackPane;

    @FXML
    ImageView currentUserImageView;

    @FXML
    ImageView statusImageView;

    @FXML
    MenuButton statusMenu;

    @FXML
    Button newConvoButton;

    @FXML
    AnchorPane currentUserAnchorPane;

    @FXML
    TextField searchContactsTextField;

    @FXML
    TextField searchConversationsTextField;

    @FXML
    ImageView searchContactsImageView;

    @FXML
    ImageView searchConversationsImageView;

    @FXML
    Label noContactsFoundLabel;

    @FXML
    Label noConversationsFoundLabel;


    /**
     * @param location
     * @param resources Initializes the class and loads the views that makes out the complete mainView.
     *                  Proceeds to show the loginpage to the user
     *                  Finally it adds itself as an observer to the model
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayLoginPage();
        IMainController c = new MainController(this, mainModel);
        searchContactsImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c.searchContactsClicked();
            }
        });

        searchContactsTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                c.onSearchContactsTextFieldKeyPressed(event);
            }
        });

        searchConversationsImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c.searchConversationsClicked();
            }
        });

        searchConversationsTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                c.onSearchConversationsEnterKeyPressed(event);
            }
        });

        //chatView.startTiming();

        //Don't really know if this is the way to do it, casting makes it unreplacable, but otherwise this goes into
        //the interface? which seems wrong.

    }

    public MainView(MainModel mainModel){

        this.mainModel = mainModel;
        this.chatView = new ChatView(mainModel);
        this.loginView = new LoginView(mainModel);
        this.createConvoView = new CreateConvoView(mainModel, this);
        this.userPage = new UserPageView(this, mainModel);
        //TODO look at the line below. I'm ashamed of myself @Nåjs
        this.userToolbar = new UserToolbar(this, mainModel, this);
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
                    chatView.update();
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
                    chatView.update();
                    updateContactsList();
                    updateConversationsList();
                    userToolbar.init();
                    displayCurrentUser();

                    break;
                case USER_INFO:
                    updateUserInfoTextFields();
                    //updateCurrentUserInfo();
                    userToolbar.updateCurrentUserInfo();
            }
        }
        userToolbar.setCurrentUserImageView();
        //currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

    private void updateUserInfoTextFields() {
        userPage.updateUserInfoTextFields();
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

    public void updateContactList(Iterator<User> iterator) {
        contactsFlowPane.getChildren().clear();
        if(!iterator.hasNext()) contactsFlowPane.getChildren().add(noContactsFoundLabel);

        while (iterator.hasNext()) {
            contactsFlowPane.getChildren().add(new ContactListItem(iterator.next(), this));
        }
    }

/*    private void displayNoContactsFound(boolean display) {
        noContactsFoundLabel.setVisible(display);
    }

    private void displayNoConversationsFound(boolean display) {
        noConversationsFoundLabel.setVisible(display);
    }*/


    /**
     * Clears the conversationsFlowPane and fills it with new ConversationListItems corresponding to
     * different Conversations.
     */
    public void updateConversationsList() {

            conversationsFlowPane.getChildren().clear();
            Iterator<Conversation> iterator = mainModel.getConversations().values().iterator();
            while (iterator.hasNext()) {
                conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next(),(MainModel) this.mainModel));
            }

    }

    public void updateConversationsList(Iterator<Conversation> iterator) {
        conversationsFlowPane.getChildren().clear();
        if(!iterator.hasNext()) conversationsFlowPane.getChildren().add(noConversationsFoundLabel);
        while (iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next(),(MainModel) this.mainModel));
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
    public void displayCreateConvoPage() {
        createConvoHBox.toFront();
        createConvoHBox.getChildren().clear();
        createConvoHBox.getChildren().add(createConvoView);
        createConvoView.setMinWidth(mainViewAnchorPane.getWidth());
        //createConvoView.prefHeightProperty().bind(mainViewAnchorPane.heightProperty());
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
    public void displayCurrentUser(){
        currentUserAnchorPane.getChildren().add(userToolbar);
    }
    @Override
    public void displaySettings() {

    }

    @FXML
    @Override
    public void displayUserPage() {
        mainViewAnchorPane.getChildren().clear();
        mainViewAnchorPane.getChildren().add(userPage);
        userPage.prefWidthProperty().bind(mainViewAnchorPane.widthProperty());
        userPage.prefHeightProperty().bind(mainViewAnchorPane.heightProperty());
        updateUserInfoTextFields();

    }

    @Override
    public void displayMainView() {
        mainViewHBox.toFront();
    }


    public void backToChat(){
        mainViewAnchorPane.getChildren().clear();
        mainViewAnchorPane.getChildren().add(chatView);
    }


    public void updateContactsList(ArrayList<User> contacts) {

        contactsFlowPane.getChildren().clear();

        for (User user : contacts) {

            ContactListItem contactListItemView = new ContactListItem(user, this);
            contactsFlowPane.getChildren().add(contactListItemView);
        }
    }

    //TODO try to delete?
    public void updateCurrentUserInfo() {
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
        statusImageView.setImage(new Image((mainModel.getActiveUser().getStatusImagePath())));
        statusMenu.setText(mainModel.getActiveUser().getStatus().toString());
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
        updateConversationsList();
        displayMainView();
    }

    public void loadDetailView(User user) {
        detailedUser = user;
        this.contactDetailViewNameLabel.setText(user.getFullName());
        Image profileImage = new Image(user.getProfileImagePath());
        this.contactDetailViewProfilemageView.setImage(profileImage);
        this.contactDetailViewStatusImageView.setImage(new Image(user.getStatusImagePath()));
        this.contactDetailViewStatusLabel.setText(user.getStatus().toString());
        contactDetailView.toFront();
    }

    @FXML
    public void newConvoButtonClicked() {
        displayCreateConvoPage();
    }

    public String getContactSearchString(){
        return searchContactsTextField.getText();
    }

    public String getConversationSearchString() {
        return searchConversationsTextField.getText();
    }



}