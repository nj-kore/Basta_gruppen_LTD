package view;

import model.User;

import java.util.Iterator;

/**
 * The interface for the AddContactView class
 */
public interface IAddContactView {
    void updateUserList(Iterator<User> users);
    Iterator<User> getClickedUsers();
    void setConfirmationLabelVisibility(boolean bool);
}
