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

public class ContactDetailView extends AnchorPane {

    @FXML
    Label nameLabel;
    @FXML
    ImageView profileImageView;
    @FXML
    ImageView statusImageView;
    @FXML
    Label statusLabel;
    @FXML
    Button createConvoButton;
    @FXML
    Button closeButton;

    User user;


    public ContactDetailView(MainView mainView, MainModel mainModel, IControllerFactory factory) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ContactDetailView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

         IContactDetailViewController detailViewController = factory.getContactDetailViewController(mainModel, mainView);

        createConvoButton.setOnMouseClicked(event -> detailViewController.onCreateConvoClicked(user));

        closeButton.setOnMouseClicked(event -> detailViewController.onCloseButtonClicked());

    }

    public void setDetailedUser(User user) {
        this.user = user;
        profileImageView.setImage(new Image(user.getProfileImagePath()));
        statusImageView.setImage(new Image(user.getStatusImagePath()));
        nameLabel.setText(user.getFullName());
        statusLabel.setText(user.getStatus().toString());
    }

}
