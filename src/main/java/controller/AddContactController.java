package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import model.observerpattern.ModelObservable;
import view.IAddContactView;
import view.IMainView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactController extends ModelObservable implements IAddContactController {

    IMainView mainView;
    MainModel mainModel;
    IAddContactView addContactView;

    public AddContactController(IMainView mainView, MainModel mainModel, IAddContactView addContactView) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        this.addContactView = addContactView;
    }

    @Override
    public void onAddUserButtonClicked() {
        Iterator<User> iterator = addContactView.getClickedUsers();
        while (iterator.hasNext()) {
            mainModel.getActiveUser().addContact(iterator.next().getId());
        }
        addContactView.setConfirmationLabelVisibility(true);
        onSearchButtonClicked("");
        notifyObservers(MainModel.UpdateTypes.CONTACTS);
        System.out.println("har uppdaterat");
    }

    @Override
    public void onDoneButtonClicked() {
        mainView.displayMainView();
    }

    @Override
    public void onSearchButtonClicked(String input) {
        Iterator<User> searchedUsers = mainModel.searchUsers(input);
        addContactView.updateUserPane(checkIteratorOfContacts(searchedUsers));
    }

    private Iterator<User> checkIteratorOfContacts(Iterator<User> searchedUsersIterator) {
        List<User> users = new ArrayList<>();

        while (searchedUsersIterator.hasNext()) {
            User next = searchedUsersIterator.next();
            System.out.println(next.getFirstName());
            if ((!mainModel.userIsContact(next)) && (next.getId() != mainModel.getActiveUser().getId())) {
                users.add(next);
            }
        }

/*
        while (searchedUsersIterator.hasNext()) {

            User next = searchedUsersIterator.next();
            System.out.println(next.getFirstName());

            if (!(next.getId() == mainModel.getActiveUser().getId())) {

                Iterator<User> userContactsIterator = mainModel.getContacts();

                if (!userContactsIterator.hasNext()) {
                    users.add(next);
                } else {
        while (userContactsIterator.hasNext()) {
            if (userContactsIterator.next() != next) {
                users.add(next);
            }
        }
    }
}
        }
                */
        return users.iterator();
    }

    @Override
    public void onSearchTextFieldEnterKeyPressed(KeyEvent event, String input) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onSearchButtonClicked(input);
            event.consume();
        }
    }
}
