package controller;

/**
 * @author Benjamin Vinnerholt
 */

import javafx.scene.input.KeyEvent;
import model.User;

import java.util.List;

public interface IMainController {


    void searchContactsClicked();

    void onSearchContactsTextFieldKeyPressed(KeyEvent event);

    void searchConversationsClicked();

    void onSearchConversationsEnterKeyPressed(KeyEvent event);

    void setActiveConversation (int id);

    void createConversation(List<User> users, String name);

    void logout();
}
