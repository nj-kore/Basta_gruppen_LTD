package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.IMainModel;
import model.MainModel;
import model.User;
import sun.applet.Main;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ContactListItem extends AnchorPane {


    @FXML
    ImageView contactProfileImageView;

    @FXML
    ImageView contactStatusImageView;

    @FXML
    Label contactNameLabel;

    @FXML
    Label contactStatusLabel;

    MainView mainView;
    User user;


    public ContactListItem(User user, MainView mainView) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.contactNameLabel.setText(user.getName());
        this.mainView = mainView;
        this.user = user;
        //TODO Add functionality for the three functions below
        //contactListItemStatusImageView.getImage(user.getStatusimage());
        //contactListItemProfileImageView.getImage(user.getProfileImage());
        //contactListItemStatusLabel.setText(user.getStatus());
    }

    @FXML
    public void contactListItemClicked() {
        mainView.loadDetailView(this.user);
    }

}
