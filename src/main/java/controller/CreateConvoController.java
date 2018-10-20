package controller;

import model.MainModel;
import model.User;
import view.CreateConvoView;
import view.ICreateConvoView;
import view.IMainView;
import view.MainView;

import java.util.ArrayList;

public class CreateConvoController implements ICreateConvoController{

    private IMainView mainView;
    private MainModel mainModel;
    private ICreateConvoView createConvoView;

    public CreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel) {
        this.mainView = mainView;
        this.createConvoView = createConvoView;
        this.mainModel = mainModel;
    }

    @Override
    public void onCreateConversationButtonClicked() {
        ArrayList<User> users = new ArrayList<>(createConvoView.getSelectedUsers());
        users.add(mainModel.getActiveUser());
        mainModel.createConversation(users, createConvoView.getSaveNameTextFieldText());
        mainView.createConvoViewToBack();
        mainView.updateConversationsList();
    }

    /*@Override
    public void onSaveNameButtonClicked() {
        if(!createConvoView.getSaveNameTextFieldText().isEmpty()) {
            createConvoView.setDisableCreateConvoButton(false);
        } else {
            createConvoView.setDisableCreateConvoButton(true);
            createConvoView.saveNameFailed();
        }
    }*/

    @Override
    public void onCloseButtonClicked() {
        mainView.displayMainView();
    }

}
