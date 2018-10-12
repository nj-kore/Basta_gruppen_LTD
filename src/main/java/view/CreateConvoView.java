package view;

import controller.CreateConvoController;
import controller.ICreateConvoController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;

import java.io.IOException;


public class CreateConvoView extends AnchorPane{

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

    private MainView mainView;
    private MainModel mainModel;

    public CreateConvoView(MainModel mainModel, MainView mainView) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/CreateConvoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainView = mainView;
        this.mainModel = mainModel;

        ICreateConvoController createConvoController = new CreateConvoController(mainView, this, mainModel);

        createConvoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onCreateConversationButtonClicked();
            }
        });
        moveUsersButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onMoveUsersButtonClicked();
            }
        });
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onCloseButtonClicked();
            }
        });
        saveNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createConvoController.onSaveNameButtonClicked();
            }
        });

        this.mainView = mainView;
        this.mainModel = mainModel;

        createConvoButton.setDisable(true);
        saveNameLabel.setText("Choose a name for the conversation:");
        saveNameLabel.setStyle("");
        createConvoView.toFront();
        ((CreateConvoController) createConvoController).updateCreateNewConvoLists();
    }

    public AnchorPane getCreateConvoView() {
        return createConvoView;
    }

    public FlowPane getContactPane() {
        return contactPane;
    }

    public FlowPane getConvoPane() {
        return convoPane;
    }

    public TextField getSaveNameTextField() {
        return saveNameTextField;
    }

    public Label getSaveNameLabel() {
        return saveNameLabel;
    }

    public Button getCreateConvoButton() {
        return createConvoButton;
    }
}
