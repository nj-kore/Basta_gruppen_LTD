package controller;

import model.MainModel;
import model.User;
import view.IMainView;

import java.util.ArrayList;


/**
 * The controller class that handles all inputs from the ContactDetailView
 */
public class ContactDetailViewController implements IContactDetailViewController {

    MainModel mainModel;
    IMainView mainView;
    public ContactDetailViewController(MainModel mainModel, IMainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    /**
     * Creates a conversation with the detailed user and the activeUser
     * @param user specifies the detailed user
     */
    @Override
    public void onCreateConvoClicked(User user) {
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(user);
        arrayList.add(mainModel.getActiveUser());
        mainModel.createConversation(arrayList, "");
        mainView.updateConversationsList();
        onCloseButtonClicked();
    }

    /**
     * Closes the detailView by moving the mainView above it in the interface
     */
    @Override
    public void onCloseButtonClicked() {
        mainView.displayMainView();
    }

    /**
     * Removes the detailed user from the activeUsers contact list
     * @param user specifies what user will be removed
     */
    @Override
    public void onRemoveContactButtonClicked(User user) {
        mainModel.removeContact(user.getId());
        onCloseButtonClicked();
    }


}
