package view;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.IMainModel;
import model.MainModel;
import model.User;

import java.awt.*;
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
        contactListItemNameLabel.setText(user.getName());
        //TODO Add functionality for the three functions below
        //contactListItemStatusImageView.getImage(user.getStatusimage());
        //contactListItemProfileImageView.getImage(user.getProfileImage());
        //contactListItemStatusLabel.setText(user.getStatus());
    }


}
