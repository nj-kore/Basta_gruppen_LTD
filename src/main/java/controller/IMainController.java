package controller;

import javafx.scene.input.KeyEvent;

public interface IMainController {


    void searchConversation();

    void searchConversationsKeyPressed(KeyEvent event);

    void searchContactsKeyPressed(KeyEvent event);

    void searchContacts(String input);

    void searchContactsClicked();
}
