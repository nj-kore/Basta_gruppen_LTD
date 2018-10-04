package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.data.User;
import java.io.IOException;

public class ContactListItem extends AnchorPane {


    @FXML
    ImageView contactProfileImageView;

    @FXML
    ImageView contactStatusImageView;

    @FXML
    Label contactNameLabel;

    @FXML
    Label contactStatusLabel;

    private MainView mainView;
    private User user;


    public ContactListItem(User user, MainView mainView) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainView = mainView;
        this.user = user;
        //TODO Add functionality for the three functions below
        //contactListItemStatusImageView.getImage(user.getStatusimage());
        //contactListItemProfileImageView.getImage(user.getProfileImage());
        //contactListItemStatusLabel.setText(user.getStatus());
        contactNameLabel.setText(user.getFullName());
        contactStatusImageView.setImage(new Image(user.getStatusImagePath()));
        contactProfileImageView.setImage(new Image(user.getProfileImagePath()));
        contactStatusLabel.setText(user.getStatus());
    }

    @FXML
    public void contactListItemClicked() {
        mainView.loadDetailView(this.user);
    }

}
