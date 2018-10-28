package view;

import controller.IContactDetailViewController;
import controller.IControllerFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.MainModel;
import model.User;

import java.io.IOException;

public class ContactDetailView extends AnchorPane implements IContactDetailView {

    @FXML
    private
    Label nameLabel;
    @FXML
    private
    ImageView profileImageView;
    @FXML
    private
    ImageView statusImageView;
    @FXML
    private
    Label statusLabel;
    @FXML
    private
    Button createConvoButton;
    @FXML
    private
    Button closeButton;
    @FXML
    private
    Button removeContactButton;

    User user;


    public ContactDetailView() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ContactDetailView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


    }

    void bindController(IContactDetailViewController controller) {
        createConvoButton.setOnMouseClicked(event -> controller.onCreateConvoClicked(user));

        closeButton.setOnMouseClicked(event -> controller.onCloseButtonClicked());

        removeContactButton.setOnMouseClicked(event -> controller.onRemoveContactButtonClicked(user));
    }

    @Override
    public void setDetailedUser(User user) {
        this.user = user;
        profileImageView.setImage(new Image(user.getProfileImagePath()));
        statusImageView.setImage(new Image(user.getStatusImagePath()));
        nameLabel.setText(user.getFullName());
        statusLabel.setText(user.getStatus().toString());
    }

}
