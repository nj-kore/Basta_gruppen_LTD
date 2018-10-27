package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.IAddContactView;
import view.IMainView;

import java.util.Iterator;

/**
 * Controller class for the AddContactView
 * @author Filip Andréasson
 */
public class AddContactController implements IAddContactController {

    IMainView mainView;
    MainModel mainModel;
    IAddContactView addContactView;

    public AddContactController(IMainView mainView, MainModel mainModel, IAddContactView addContactView) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        this.addContactView = addContactView;
    }

    /**
     * Adds the clicked user to the active users contact list
     */
    @Override
    public void onAddUserButtonClicked() {
        Iterator<User> iterator = addContactView.getClickedUsers();
        while (iterator.hasNext()) {
            mainModel.addContact(iterator.next().getId());
        }
        addContactView.setConfirmationLabelVisibility(true);
        onSearchButtonClicked("");
    }

    /**
     * Closes the view by moving the main view in front
     */
    @Override
    public void onDoneButtonClicked() {
        mainView.displayMainView();
    }

    /**
     * Updates the user pane with users that are not in the active users contact list
     * @param input specifies what the active user entered in the text field
     */
    @Override
    public void onSearchButtonClicked(String input) {
        Iterator<User> searchedUsers = mainModel.searchUsers(input);
        addContactView.updateUserList(mainModel.checkIteratorOfContacts(searchedUsers));
    }

    /**
     * Notices if the user pressed the enter button. Calls the method onSearchButtonClicked
     * @param event specifies what key was pressed
     * @param input specifies what the active user entered in the text field
     */
    @Override
    public void onSearchTextFieldEnterKeyPressed(KeyEvent event, String input) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onSearchButtonClicked(input);
            event.consume();
        }
    }
}
