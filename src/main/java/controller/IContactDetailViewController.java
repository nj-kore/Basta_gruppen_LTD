package controller;

import model.User;

/**
 * The interface for the ContactDetailViewController
 */
public interface IContactDetailViewController {
    void onCreateConvoClicked(User user);
    void onCloseButtonClicked();
    void onRemoveContactButtonClicked(User user);
}
