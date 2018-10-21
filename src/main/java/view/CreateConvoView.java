package view;

import controller.CreateConvoController;
import controller.IControllerFactory;
import controller.ICreateConvoController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class CreateConvoView extends AnchorPane implements ICreateConvoView{

    @FXML
    private AnchorPane createConvoView;
    @FXML
    private FlowPane contactPane;
    @FXML
    private FlowPane convoPane;
    @FXML
    private Button createConvoButton;
    @FXML
    private Button moveUsersButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button saveNameButton;
    @FXML
    private TextField saveNameTextField;
    @FXML
    private Label saveNameLabel;

    private MainModel mainModel;

    CreateConvoView(MainModel mainModel, IMainView mainView, IControllerFactory factory) {
        this.mainModel = mainModel;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreateConvoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        ICreateConvoController createConvoController = factory.getCreateConvoController(mainView, this, mainModel);

        createConvoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onCreateConversationButtonClicked();
            }
        });
        moveUsersButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onMoveUsersButtonClicked();
            }
        });
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onCloseButtonClicked();
            }
        });


    }

    @Override
    public void updateCreateConversationLists() {
        contactPane.getChildren().clear();
        convoPane.getChildren().clear();
        Iterator<User> itr = mainModel.getContacts();
        while (itr.hasNext()) {
            NewConvoContactListItem newConvoContact = new NewConvoContactListItem(itr.next());
            contactPane.getChildren().add(newConvoContact);
        }
    }

    @Override
    public void onMoveUsersButtonClicked() {
        ArrayList<Node> paneList = new ArrayList<>();
        paneList.addAll(this.getConvoPane().getChildren());
        paneList.addAll(this.getContactPane().getChildren());

        for (Node node : paneList) {

            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;

            if (newConvoContactListItem.isClicked()) {

                if (this.getContactPane().getChildren().contains(node)) {

                    this.getContactPane().getChildren().remove(node);
                    this.getConvoPane().getChildren().add(newConvoContactListItem);
                } else {

                    this.getConvoPane().getChildren().remove(node);
                    this.getContactPane().getChildren().add(newConvoContactListItem);
                }
                newConvoContactListItem.setClicked(false);
                newConvoContactListItem.setStyle("-fx-background-color: none");
            }
        }
    }

    @Override
    public ArrayList<User> getSelectedUsers() {

        ArrayList<User> selectedUsers = new ArrayList<>();

        for (Node node : convoPane.getChildren())
        {
            NewConvoContactListItem newConvoContactListItem = (NewConvoContactListItem) node;
            selectedUsers.add(newConvoContactListItem.getUser());
        }
        return selectedUsers;
    }



    private FlowPane getContactPane() {
        return contactPane;
    }

    private FlowPane getConvoPane() {
        return convoPane;
    }

    @Override
    public String getSaveNameTextFieldText() {
        return saveNameTextField.getText();
    }

}
