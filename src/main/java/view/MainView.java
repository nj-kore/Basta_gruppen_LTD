package view;

import controller.IControllerFactory;
import controller.IMainController;
import controller.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Conversation;
import model.MainModel;
import model.User;
import model.observerpattern.ModelObserver;

import java.net.URL;
import java.util.*;

/**
 * The MainView is the main class of the view package. Linking all the different views together and forwards info
 * given to the class from the model package via the observer class.
 *
 * @author Filip Andréasson
 * @author Gustav Häger
 * @author Jonathan Köre
 * @author Gustaf Spjut
 * @author Benjamin Vinnerholt
 * @since 2018-09-09
 */

public class MainView extends AnchorPane implements Initializable, IMainView, ModelObserver {

    private MainModel mainModel;
    private ChatView chatView;
    private LoginView loginView;
    private CreateConvoView createConvoView;
    private UserPageView userPage;
    private User detailedUser;
    private UserToolbar userToolbar;
    private CreateUserView createUserView;

    //contactDetailView
    @FXML
    private
    AnchorPane contactDetailView;

    @FXML
    private
    ImageView contactDetailViewProfilemageView;

    @FXML
    private
    ImageView contactDetailViewStatusImageView;

    @FXML
    private
    Label contactDetailViewStatusLabel;

    @FXML
    private
    Label contactDetailViewNameLabel;


    //mainView
    @FXML
    private
    AnchorPane mainViewAnchorPane;

    @FXML
    private
    FlowPane contactsFlowPane;

    @FXML
    private
    FlowPane conversationsFlowPane;

    @FXML
    private
    HBox mainViewHBox;

    @FXML
    private
    HBox loginHBox;

    @FXML
    private
    HBox createConvoHBox;

    @FXML
    StackPane mainViewStackPane;

    @FXML
    private
    ImageView currentUserImageView;

    @FXML
    private
    ImageView statusImageView;

    @FXML
    private
    MenuButton statusMenu;

    @FXML
    Button newConvoButton;

    @FXML
    private
    AnchorPane currentUserAnchorPane;

    @FXML
    private
    TextField searchContactsTextField;

    @FXML
    private
    TextField searchConversationsTextField;

    @FXML
    private
    ImageView searchContactsImageView;

    @FXML
    private
    ImageView searchConversationsImageView;

    @FXML
    private
    Label noContactsFoundLabel;

    @FXML
    private
    Label noConversationsFoundLabel;


    /**
     * Initializes the class and loads the views that makes out the complete mainView.
     * Proceeds to show the loginpage to the user
     * Finally it adds itself as an observer to the model
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

    public MainView(MainModel mainModel, IControllerFactory factory) {

        this.mainModel = mainModel;
        this.chatView = new ChatView(mainModel, this, factory);
        this.loginView = new LoginView(mainModel, factory);
        this.createConvoView = new CreateConvoView(mainModel, this, factory);
        this.userPage = new UserPageView(this, mainModel, factory);
        this.createUserView = new CreateUserView(this, mainModel, factory);
        this.userToolbar = new UserToolbar(this, mainModel, factory);
    }


    /**
     * This method is called whenever the any ModelObservable object calls the method 'notifyObservers'.
     * The method will use a switch case to call the relevant update method(s) in the application.
     *
     * @param updateType is the type of task the update method will perform
     */
    @Override
    public void update(MainModel.UpdateTypes updateType) {
        switch (updateType) {
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
                updateContactsList();
                updateConversationsList();
                userToolbar.init();
                displayCurrentUser();
                setDefaultConversation();
                chatView.init();
                break;
            case USER_INFO:
                updateUserInfoTextFields();
                //updateCurrentUserInfo();
                userToolbar.updateCurrentUserInfo();
                break;
            default:
                break;
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
    private void updateContactsList() {
        contactsFlowPane.getChildren().clear();
        Iterator<User> iterator = mainModel.getContacts();
        while (iterator.hasNext()) {
            contactsFlowPane.getChildren().add(new ContactListItem(iterator.next(), this));
        }
    }

    public void updateContactList(Iterator<User> iterator) {
        contactsFlowPane.getChildren().clear();
        if (!iterator.hasNext()) {
            contactsFlowPane.getChildren().add(noContactsFoundLabel);
        }

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
        Iterator<Conversation> iterator = mainModel.getUsersConversations();
        while (iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next(), this.mainModel));
        }

    }

    public void updateConversationsList(Iterator<Conversation> iterator) {
        conversationsFlowPane.getChildren().clear();
        if (!iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(noConversationsFoundLabel);
        }
        while (iterator.hasNext()) {
            conversationsFlowPane.getChildren().add(new ConversationListItem(iterator.next(), this.mainModel));
        }

    }

    @Override
    public void setDefaultConversation() {
        updateConversationsList();
        if (!conversationsFlowPane.getChildren().isEmpty()) {
            mainModel.setActiveConversation(((ConversationListItem) conversationsFlowPane.getChildren().get(0)).getConversation().getId());
        }
    }

    @Override
    public void displayLoginPage() {
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(loginView);
    }

    public void displayCreateUserView() {
        mainViewAnchorPane.getChildren().clear();
        mainViewAnchorPane.getChildren().add(createUserView);
    }

    @Override
    public void displayCreateConvoPage() {
        createConvoHBox.toFront();
        createConvoHBox.getChildren().clear();
        createConvoHBox.getChildren().add(createConvoView);
        createConvoView.updateCreateConversationLists();
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
    public void displayCurrentUser() {
        currentUserAnchorPane.getChildren().clear();
        currentUserAnchorPane.getChildren().add(userToolbar);
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


    public void backToChat() {
        mainViewAnchorPane.getChildren().clear();
        mainViewAnchorPane.getChildren().add(chatView);
    }


    public void updateContactsList(List<User> contacts) {
        contactsFlowPane.getChildren().clear();
        for (User user : contacts) {
            ContactListItem contactListItemView = new ContactListItem(user, this);
            contactsFlowPane.getChildren().add(contactListItemView);
        }
    }

    //TODO try to delete?
    public void updateCurrentUserInfo() {
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
        statusImageView.setImage(new Image(mainModel.getActiveUser().getStatusImagePath()));
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

    public void logout() {
        userToolbar.statusMenu.getItems().clear();
        mainModel.setActiveUser(null);
        displayLoginPage();
        loginView.clearTextFields();
        chatView.createUserButtonInVisible();
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

    public String getContactSearchString() {
        return searchContactsTextField.getText();
    }

    public String getConversationSearchString() {
        return searchConversationsTextField.getText();
    }


    public void createConvoViewToBack() {
        createConvoHBox.toBack();
    }
}