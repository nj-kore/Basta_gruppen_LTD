package view;

import controller.IAddContactController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactView extends AnchorPane implements IAddContactView {

    @FXML
    private
    Button searchButton;

    @FXML
    private
    Button addButton;

    @FXML
    private
    Button doneButton;

    @FXML
    private
    FlowPane userPane;

    @FXML
    private
    TextField searchTextField;

    @FXML
    private
    Label confirmationLabel;


    MainModel mainModel;
    MainView mainView;

    public AddContactView(MainView mainView, MainModel mainModel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddContactView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {

            throw new RuntimeException(exception);
        }

    }

    protected void bindController(IAddContactController controller) {
        addButton.setOnMouseClicked(event -> controller.onAddUserButtonClicked());

        doneButton.setOnMouseClicked(event -> controller.onDoneButtonClicked());

        searchButton.setOnMouseClicked(event -> controller.onSearchButtonClicked(searchTextField.getText()));

        searchTextField.setOnKeyPressed(event -> controller.onSearchTextFieldEnterKeyPressed(event, searchTextField.getText()));
    }

    public void updateUserPane(Iterator<User> users) {
        userPane.getChildren().clear();
        while (users.hasNext()) {
            userPane.getChildren().add(new SmallContactListItem(users.next()));
        }
    }

    public Iterator<User> getClickedUsers() {
        Iterator<Node> nodeIterator = userPane.getChildren().iterator();
        List<User> userIterator = new ArrayList<>();
        while (nodeIterator.hasNext()) {
            SmallContactListItem smallContactListItem = (SmallContactListItem) nodeIterator.next();
            if (smallContactListItem.isClicked()) {
                userIterator.add(smallContactListItem.getUser());
            }
        }
        return userIterator.iterator();
    }

    @Override
    public void setConfirmationLabelVisibility(boolean bool) {
        confirmationLabel.setVisible(bool);
    }
}
