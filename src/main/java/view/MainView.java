package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
import java.util.Map;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    LoginView loginView = new LoginView(this);
    IMainController mainController;
    ArrayList<NewConvoContactListItem> newConvoListItems = new ArrayList<>();
    User detailedUser;
    private IMainModel mainModel = MainModel.getInstance();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainViewAnchorPane.getChildren().add(chatView);
        mainViewHBox.toBack();
        loginHBox.toFront();
        loginHBox.getChildren().clear();
        loginHBox.getChildren().add(loginView);
        updateContactsList();
        updateConversationsList();
        updateCreateNewConvoLists();
    }

    public void moveLoginToBack(){
        loginHBox.toBack();
    }


    //update functions
    public void updateContactsList() {

        contactsFlowPane.getChildren().clear();

        for (User user : mainModel.getContacts()) {
            ContactListItem contactListItemView = new ContactListItem(user,this);
            contactsFlowPane.getChildren().add(contactListItemView);
        }

    }

    public void updateConversationsList() {

        conversationsFlowPane.getChildren().clear();
        for (Map.Entry<Integer, Conversation> conversation : mainModel.getConversations().entrySet()) {

            ConversationListItem conversationListItem = new ConversationListItem(conversation.getValue());
            conversationsFlowPane.getChildren().add(conversationListItem);
        }
    }

    private void updateCreateNewConvoLists() {
        newConvoContactPane.getChildren().clear();
        newConvoConvoPane.getChildren().clear();
        for (User user : mainModel.getContacts()) {
            NewConvoContactListItem newConvoContact = new NewConvoContactListItem(user);
            newConvoContactPane.getChildren().add(newConvoContact);
            newConvoListItems.add(newConvoContact);
        }
    }


    //createNewConvo functionality
    @FXML
    private void newConvoMoveUsersButtonClicked() {
        ArrayList<NewConvoContactListItem> newConvoListItemsCopy = new ArrayList<>(newConvoListItems);

        for (NewConvoContactListItem newConvoContactListItem : newConvoListItemsCopy) {

            if(newConvoContactListItem.isFocused) {

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
        this.contactDetailViewNameLabel.setText(user.getName());
        this.contactDetailViewProfilemageView.setImage(null); //TODO setImage from user
        this.contactDetailViewStatusImageView.setImage(null); //TODO setImage from user
        this.contactDetailViewStatusLabel.setText("active"); //TODO setText from user
        contactDetailView.toFront();
    }


}