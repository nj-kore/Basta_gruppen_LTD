package controller;

import model.MainModel;
import model.User;
import view.IMainView;
import view.MainView;

import java.util.ArrayList;

public class ContactDetailViewController implements IContactDetailViewController {

    MainModel mainModel;
    IMainView mainView;
    public ContactDetailViewController(MainModel mainModel, IMainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }


    @Override
    public void onCreateConvoClicked(User user) {
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(user);
        arrayList.add(mainModel.getActiveUser());
        mainModel.createConversation(arrayList, user.getFirstName());
        mainView.updateConversationsList();
        onCloseButtonClicked();
    }

    @Override
    public void onCloseButtonClicked() {
        mainView.displayMainView();
    }


}
