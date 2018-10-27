package controller;


import javafx.scene.input.KeyEvent;

/**
 * The interface for the AddContactController class
 */
public interface IAddContactController {
    void onAddUserButtonClicked();
    void onDoneButtonClicked();
    void onSearchButtonClicked(String input);
    void onSearchTextFieldEnterKeyPressed(KeyEvent event, String input);
}
