package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.IMainModel;
import model.MainModel;
import model.User;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class ContactListItemView extends AnchorPane {

    @FXML
    ImageView contactListItemProfileImageView;

    @FXML
    FlowPane contactFlowPane;

    @FXML
    ImageView contactListItemStatusImageView;

    @FXML
    Label contactListItemNameLabel;

    @FXML
    Label contacListItemStatusLabel;

    private IMainModel mainModel = MainModel.getInstance();


    public ContactListItemView(User user) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
    public void getContacts(){
        for (User u :mainModel.getContacts()){
           contactListItemNameLabel.setText(u.getName());
            contactFlowPane.getChildren().add(contactListItemNameLabel);
        }
    }

}
