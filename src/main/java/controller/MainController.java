package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public void searchConversation(){
        mainView.searchConversations();
    }
    @Override
    public void searchConversationsKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchConversation();
            event.consume();
        }
    }

    @Override
    public void searchContactsKeyPressed(KeyEvent event) {

    }

/*    @Override
    public void searchContactsKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            mainModel.searchContacts();
            event.consume();
        }
    }*/

    @Override
    public void searchContacts(String input){
        Iterator<User> iterator = mainModel.searchContacts(input);
        mainView.updateContactList(iterator);
    }

    @Override
    public void searchContactsClicked() {
        Iterator<User> contacts = mainModel.searchContacts(mainView.getContactSearchString());
        mainView.updateContactList(contacts);
    }
}
