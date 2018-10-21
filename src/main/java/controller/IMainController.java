package controller;

/**
 * @author Benjamin Vinnerholt
 */

import javafx.scene.input.KeyEvent;

public interface IMainController {


    void searchContactsClicked();

    void onSearchContactsTextFieldKeyPressed(KeyEvent event);

    void searchConversationsClicked();

    void onSearchConversationsEnterKeyPressed(KeyEvent event);
}
