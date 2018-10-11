package controller;

import javafx.scene.input.KeyEvent;

public interface IMainController {


    void searchConversation();

    void searchConversationsKeyPressed(KeyEvent event);

    void searchContactsKeyPressed(KeyEvent event);

    void searchContacts(String input);

    void searchContactsClicked();

    void onSearchContactsTextFieldKeyPressed(KeyEvent event);

    void searchConversationsClicked();

    void onSearchConversationsTextFieldKeyPressed(KeyEvent event);
}
