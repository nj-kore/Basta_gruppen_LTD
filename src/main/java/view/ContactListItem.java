package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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



    public ContactListItem(User user) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        contactNameLabel.setText(user.getFullName());
        contactStatusImageView.setImage(user.getStatusImage());
        contactProfileImageView.setImage(user.getProfileImage());
        contactStatusLabel.setText(user.getStatus());
    }


}
