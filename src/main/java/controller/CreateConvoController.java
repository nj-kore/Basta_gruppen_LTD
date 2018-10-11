package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import model.MainModel;
import model.User;
import view.CreateConvoView;
import view.MainView;
import view.NewConvoContactListItem;

import java.util.ArrayList;
import java.util.Iterator;

public class CreateConvoController implements ICreateConvoController{

    private MainView mainView;
    private MainModel mainModel;
    private CreateConvoView createConvoView;

    public CreateConvoController(MainView mainView, CreateConvoView createConvoView, MainModel mainModel) {
        this.mainView = mainView;
        this.createConvoView = createConvoView;
        this.mainModel = mainModel;
    }

    @Override
    public void onMoveUsersButtonClicked() {
        ArrayList<Node> paneList = new ArrayList<>();
        paneList.addAll(createConvoView.getConvoPane().getChildren());
        paneList.addAll(createConvoView.getContactPane().getChildren());

        for (Node node : paneList) {

            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;

            if (newConvoContactListItem.isClicked()) {

                if (createConvoView.getContactPane().getChildren().contains(node)) {

                    createConvoView.getContactPane().getChildren().remove(node);
                    createConvoView.getConvoPane().getChildren().add(newConvoContactListItem);
                } else {

                    createConvoView.getConvoPane().getChildren().remove(node);
                    createConvoView.getContactPane().getChildren().add(newConvoContactListItem);
                }
                newConvoContactListItem.setClicked(false);
                newConvoContactListItem.setStyle("-fx-background-color:");
            }
        }
    }

    @Override
    public void onCreateConversationButtonClicked() {
        ArrayList<User> users = new ArrayList<>();
        for (Node node : createConvoView.getContactPane().getChildren()) {
            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;
            users.add(newConvoContactListItem.getUser());
        }
        users.add(mainModel.getActiveUser());
        mainModel.createConversation(users, createConvoView.getSaveNameTextField().getText());
        createConvoView.toBack();
        mainView.updateConversationsList();
    }

    @Override
    public void onSaveNameButtonClicked() {
        if(!createConvoView.getSaveNameTextField().getText().isEmpty()) {
            createConvoView.getCreateConvoButton().setDisable(false);
        } else {
            createConvoView.getSaveNameLabel().setText("Conversation needs to have a name");
            createConvoView.getSaveNameLabel().setStyle("-fx-background-color: #FF0000");
        }
    }

    @Override
    public void onCloseButtonClicked() {
        mainView.displayMainView();
    }

    @Override
    public void updateConversationLists() {
        createConvoView.getContactPane().getChildren().clear();
        createConvoView.getConvoPane().getChildren().clear();
        Iterator<User> itr = mainModel.getContacts();
        while (itr.hasNext()) {
            NewConvoContactListItem newConvoContact = new NewConvoContactListItem(itr.next());
            createConvoView.getContactPane().getChildren().add(newConvoContact);
        }
    }

    public void updateCreateNewConvoLists() {
        createConvoView.getContactPane().getChildren().clear();
        createConvoView.getConvoPane().getChildren().clear();
        Iterator<User> itr = mainModel.getContacts();
        while (itr.hasNext()) {
            NewConvoContactListItem newConvoContact = new NewConvoContactListItem(itr.next());
            createConvoView.getContactPane().getChildren().add(newConvoContact);
        }
    }
}
