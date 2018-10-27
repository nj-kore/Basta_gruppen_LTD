package view;

import model.User;

import java.util.Iterator;

public interface IAddContactView {
    void updateUserPane(Iterator<User> users);
    Iterator<User> getClickedUsers();
    void setConfirmationLabelVisibility(boolean bool);
}
