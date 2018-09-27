package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    IMainController mainController;
    private IMainModel mainModel = MainModel.getInstance();

    @FXML
    AnchorPane mainViewAnchorPane;

    @FXML
    FlowPane contactsFlowPane;

    @FXML
    FlowPane conversationsFlowPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainViewAnchorPane.getChildren().add(chatView);
    }

    public void getContacts(){
      for (User u :mainModel.getContacts()){
            //ConversationsFlowPane.getChildren().add(new ContactListItem u);
      }
    }

    public void updateContactsList(HashMap<Integer, User> contacts) {

        contactsFlowPane.getChildren().clear();

        for (Map.Entry<Integer, User> user : contacts.entrySet()) {

            ContactListItemView contactListItemView = new ContactListItemView(user.getValue());
            contactsFlowPane.getChildren().add(contactListItemView);
        }

    }

    public void updateConversationsList(HashMap<Integer, Conversation> conversations) {

        conversationsFlowPane.getChildren().clear();
        ConversationListItemView conversationListItemView = new ConversationListItemView(conversations);
        contactsFlowPane.getChildren().add(conversationListItemView);

    }

}