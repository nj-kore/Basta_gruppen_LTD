package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Conversation;
import model.MainModel;
import model.User;
import view.MainView;

import java.util.Iterator;

public class MainController implements IMainController {
    MainView mainView;
    MainModel mainModel;

    public MainController(MainView mainView, MainModel mainModel){
        this.mainView = mainView;
        this.mainModel = mainModel;


    }




    @Override
    public void searchContactsClicked() {
        Iterator<User> contacts = mainModel.searchContacts(mainView.getContactSearchString());
        mainView.updateContactList(contacts);


    }

    @Override
    public void onSearchContactsTextFieldKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            searchContactsClicked();
            event.consume();
        }
    }

    @Override
    public void searchConversationsClicked() {
        Iterator<Conversation> conversations = mainModel.searchConversations(mainView.getConversationSearchString());
        mainView.updateConversationsList(conversations);
    }

    @Override
    public void onSearchConversationsEnterKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchConversationsClicked();
            event.consume();
        }
    }
}
