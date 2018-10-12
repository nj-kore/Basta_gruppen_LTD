package controller;

import javafx.scene.input.KeyEvent;

public interface IMainController {


    void searchContactsClicked();

    void onSearchContactsTextFieldKeyPressed(KeyEvent event);

    void searchConversationsClicked();

    void onSearchConversationsEnterKeyPressed(KeyEvent event);
}
